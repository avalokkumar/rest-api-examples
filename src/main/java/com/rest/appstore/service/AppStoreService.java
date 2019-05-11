package com.rest.appstore.service;

import com.rest.appstore.entity.AppStore;
import com.rest.appstore.repository.AppStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class AppStoreService {

    private static final String BLANK = "";
    private AppStoreRepository appStoreRepository;

    @Autowired
    AppStoreService(AppStoreRepository appStoreRepository) {
        this.appStoreRepository = appStoreRepository;
    }

    public List<AppStore> getAllAppStore(String limit) {

        return Optional.ofNullable(limit)
                .map(lim -> appStoreRepository.findAll().stream().limit(Long.valueOf(limit))
                        .collect(Collectors.toList()))
                .orElse(appStoreRepository.findAll());

    }

    public List<AppStore> getAllAppStore(Map<String, String> allRequestParams) {


        String limit = getRequestParam.apply(allRequestParams, "limit");

        return Collections.emptyList();
    }

    private BiFunction<Map<String, String>, String, String> getRequestParam =
            (requestParam, query) -> Optional.ofNullable(requestParam)
                    .filter(requesParam -> requesParam.containsKey(query))
                    .map(requesParam -> requesParam.get(query))
                    .orElse(BLANK);
}
