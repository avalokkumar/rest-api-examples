package com.rest.appstore.rest;

import com.rest.appstore.model.FilterCriteria;
import com.rest.appstore.service.AppStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/", headers = "Accept=application/json", produces = "application/json")
public class AppStoreController {


    @Autowired
    private AppStoreService appStoreService;

    @GetMapping("appstores")
    public ResponseEntity<Object> getAllAppStores(@RequestParam(value = "limit", required = false) String limit) {

        return ResponseEntity.ok().body(appStoreService.getAllAppStore(limit));
    }

    @GetMapping("appstores/filtered}")
    public ResponseEntity<Object> getAllAppStoresByFilter(@RequestParam Map<String,String> allRequestParams) {

        return ResponseEntity.ok().body(appStoreService.getAllAppStore(allRequestParams));
    }

}
