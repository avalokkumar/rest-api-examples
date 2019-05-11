package com.rest.appstore.repository;

import com.rest.appstore.entity.AppStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppStoreRepository extends MongoRepository<AppStore, String> {


}
