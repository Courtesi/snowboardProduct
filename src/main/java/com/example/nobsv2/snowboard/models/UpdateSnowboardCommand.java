package com.example.nobsv2.snowboard.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateSnowboardCommand {
    private UUID id;
    private Snowboard snowboard;

    public UpdateSnowboardCommand(UUID id, Snowboard snowboard) {
        this.id = id;
        this.snowboard = snowboard;
    }
}
