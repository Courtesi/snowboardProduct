package com.example.nobsv2.snowboard.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    public String toString() {
        return "(" + id.toString() + ", " + type + ")";
    }
}
