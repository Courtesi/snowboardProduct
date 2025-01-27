package com.example.nobsv2.transaction;

import lombok.Data;

@Data
public class TransferDTO {
    private String framUser;
    private String taUser;
    private double amount;
}
