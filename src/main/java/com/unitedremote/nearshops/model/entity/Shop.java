package com.unitedremote.nearshops.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "shops")
@NoArgsConstructor @Getter @Setter
public class Shop {

    @Id
    public String Id;
    private String picture;
    private String name;
    private String email;
    private String city;
    private Location location;
    private double distance;


}
