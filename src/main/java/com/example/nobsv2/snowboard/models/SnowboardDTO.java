package com.example.nobsv2.snowboard.models;

import lombok.Data;

import java.util.UUID;

@Data
public class SnowboardDTO {
    private UUID id;
    private String description;
    private Double price;
    private String manufacturer;
    private Category category;
//    private String timestamp_created;
//    private String timestamp_updated;
    private String region;

    public SnowboardDTO(Snowboard snowboard) {
        this.id = snowboard.getId();
        this.description = snowboard.getDescription();
        this.price = snowboard.getPrice();
        this.manufacturer = snowboard.getManufacturer();
        this.category = snowboard.getCategory();
//        this.timestamp_created = snowboard.getTimestamp_created();
//        this.timestamp_updated = snowboard.getTimestamp_updated();
        this.region = snowboard.getRegion();
    }
}
