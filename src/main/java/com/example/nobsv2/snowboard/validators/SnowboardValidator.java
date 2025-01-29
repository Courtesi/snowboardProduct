package com.example.nobsv2.snowboard.validators;

import com.example.nobsv2.exceptions.ErrorMessages;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.snowboard.models.Snowboard;
import io.micrometer.common.util.StringUtils;

public class SnowboardValidator {

    private SnowboardValidator() {
    }

    public static void execute (Snowboard snowboard) {
        if (StringUtils.isEmpty(snowboard.getDescription())) {
            throw new ProductNotValidException(ErrorMessages.DESCRIPTION_REQUIRED.getMessage());
        }

        if (snowboard.getPrice() == null) {
            throw new ProductNotValidException(ErrorMessages.PRICE_REQUIRED.getMessage());
        }

        if (snowboard.getPrice() < 0.00) {
            throw new ProductNotValidException(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage());
        }

        if (StringUtils.isEmpty(snowboard.getManufacturer())) {
            throw new ProductNotValidException(ErrorMessages.MANUFACTURER_REQUIRED.getMessage());
        }

        if (snowboard.getCategory() == null) {
            throw new ProductNotValidException(ErrorMessages.CATEGORY_REQUIRED.getMessage());
        }
    }

}
