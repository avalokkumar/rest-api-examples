package com.rest.appstore.dataloader;

import com.rest.appstore.entity.AppStore;
import com.rest.appstore.repository.AppStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Task to load data from .csv file in \resource dir into the mongo db
 *
 */
@Slf4j
public class DataLoaderTask implements CommandLineRunner {


    private final String[] HEADERS = {"counter", "id", "track_name", "size_bytes", "currency", "price",
            "rating_count_tot", "rating_count_ver", "user_rating", "user_rating_ver", "ver", "cont_rating", "prime_genre", "sup_devices.num", "ipadSc_urls.num", "lang.num", "vpp_lic"};

    @Autowired
    private AppStoreRepository appStoreRepository;

    @Override
    public void run(String... args) throws Exception {

        try {
            Path dataPath = Paths.get("src/main/resources/dataset/AppleStore.csv");
            //List<String> data = Files.readAllLines(dataPath);
            //List<String> csvData = data.stream().skip(1).collect(Collectors.toList());

            Reader in = new FileReader(dataPath.toFile());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(in);
            List<AppStore> appStoreList = mapCsvToAppStoreData(records);
            appStoreRepository.saveAll(appStoreList);

        } catch (IOException ex) {
            log.info(ex.getMessage());
        }
    }

    private List<AppStore> mapCsvToAppStoreData(Iterable<CSVRecord> records) {
        List<AppStore> appStoreList = new ArrayList<>();

        records.forEach(csvRecord ->
            appStoreList.add(AppStore.builder()
                    .counter(Long.valueOf(csvRecord.get(HEADERS[0])))
                    .id(csvRecord.get(HEADERS[1]))
                    .trackName(csvRecord.get(HEADERS[2]))
                    .sizeInBytes(Long.valueOf(csvRecord.get(HEADERS[3])))
                    .currency(csvRecord.get(HEADERS[4]))
                    .price(Double.valueOf(csvRecord.get(HEADERS[5])))
                    .ratingTotalCount(Long.valueOf(csvRecord.get(HEADERS[6])))
                    .ratingCountVersion(Long.valueOf(csvRecord.get(HEADERS[7])))
                    .userRating(Double.valueOf(csvRecord.get(HEADERS[8])))
                    .userRatingVersion(Double.valueOf(csvRecord.get(HEADERS[9])))
                    .version(csvRecord.get(HEADERS[10]))
                    .contRating(csvRecord.get(HEADERS[11]))
                    .primeGenre(csvRecord.get(HEADERS[12]))
                    .supDevicesNum(Long.valueOf(csvRecord.get(HEADERS[13])))
                    .iPadScUrlsNum(Long.valueOf(csvRecord.get(HEADERS[14])))
                    .langNum(Long.valueOf(csvRecord.get(HEADERS[15])))
                    .vppLic(Long.valueOf(csvRecord.get(HEADERS[16])))
                    .build())
        );

        return appStoreList;
    }
}
