package com.example.nobsv2.snowboard.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "snowboard")
public class Snowboard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @NotNull(message = "Needs a description")
    private String description;

//    @NotNull(message = "Needs a price")
//    @PositiveOrZero(message = "Cannot be less than 0")
    private Double price;

    @NotNull(message = "Needs a manufacturer")
    private String manufacturer;

    //NO cascade = CascadeType.ALL BECAUSE WE DO NOT WANT TO DELETE THE CATEGORY IF THE SNOWBOARD IS DELETED
    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
//    @NotNull(message = "Needs a category")
    private Category category;

    private String timestamp_created;

    private String timestamp_updated;

    private String region;
}
