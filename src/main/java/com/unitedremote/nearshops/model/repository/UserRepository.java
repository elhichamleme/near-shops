package com.unitedremote.nearshops.model.repository;

import com.unitedremote.nearshops.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
