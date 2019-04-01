package com.unitedremote.nearshops.model.repository;

import com.unitedremote.nearshops.model.entity.Shop;

import java.util.List;

public interface ShopRepositoryCustom {

    List<Shop> shopsOrderedByDistance(double latitude, double longitude);
}
