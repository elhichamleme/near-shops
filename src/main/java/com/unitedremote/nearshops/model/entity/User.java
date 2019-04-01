package com.unitedremote.nearshops.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@NoArgsConstructor @Getter @Setter
public class User {

    private String email;
    private List<Shop> preferredShops;
}
