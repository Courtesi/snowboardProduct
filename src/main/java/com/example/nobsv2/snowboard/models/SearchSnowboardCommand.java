package com.example.nobsv2.snowboard.models;

import lombok.Getter;

@Getter
public class SearchSnowboardCommand {
    private final String query;
    private final String column;
    private final String order;

    public SearchSnowboardCommand(String query, String column, String order) {
        this.query = query;
        this.column = column;
        this.order = order;
    }
}
