package com.unitedremote.nearshops.model.repository;

import com.unitedremote.nearshops.model.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.geoNear;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

public class ShopRepositoryImpl implements ShopRepositoryCustom {


    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShopRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Custom Repository method that get shops ordered by the nearest to a provided latitude and longitude
    @Override
    public List<Shop> shopsOrderedByDistance(double latitude, double longitude) {


        NearQuery nearQuery = NearQuery.near(new Point(latitude,longitude));
        Query query= new Query();
        query.with(new Sort(Sort.Direction.ASC, "distance"));
        nearQuery.query(query);
        nearQuery.spherical(true);
        Aggregation aggregation = newAggregation(geoNear(nearQuery,"distance"));

        AggregationResults<Shop> aggregationResults = mongoTemplate.aggregate(aggregation, Shop.class, Shop.class);

        return aggregationResults.getMappedResults();




    }
}
