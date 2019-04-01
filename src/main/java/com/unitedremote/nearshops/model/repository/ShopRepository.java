package com.unitedremote.nearshops.model.repository;

import com.unitedremote.nearshops.model.entity.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepository extends MongoRepository<Shop, String>, ShopRepositoryCustom {
}
