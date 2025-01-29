package com.example.nobsv2.snowboard.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.exceptions.CategoryNotFoundException;
import com.example.nobsv2.snowboard.CategoryRepository;
import com.example.nobsv2.snowboard.SnowboardRepository;
import com.example.nobsv2.snowboard.models.*;
import com.example.nobsv2.snowboard.validators.ProfanityChecker;
import com.example.nobsv2.snowboard.validators.SnowboardValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CreateSnowboardService implements Command<Snowboard, SnowboardDTO> {

    private final SnowboardRepository snowboardRepository;
    private final CategoryRepository categoryRepository;

    @Value("${noProfanityApiKey}")
    private String apiKey;

    public CreateSnowboardService(SnowboardRepository snowboardRepository, CategoryRepository categoryRepository) {
        this.snowboardRepository = snowboardRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<SnowboardDTO> execute(Snowboard snowboard) {
        //validator for description, price, manufacturer, and category (check if all needed attributes are present)
        SnowboardValidator.execute(snowboard);

        Optional<Category> category = categoryRepository.findById(snowboard.getCategory().getId());
        if (category.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        snowboard.setCategory(category.get());

        //CHECK FOR PROFANITY
        ProfanityChecker.execute(snowboard, apiKey);

        //Set Timestamp_created/updated and Region for newly created snowboard
        String formattedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        snowboard.setTimestamp_created(formattedNow);
        snowboard.setTimestamp_updated(formattedNow);
        snowboard.setRegion(Regions.US.getMessage());

        Snowboard savedSnowboard = snowboardRepository.save(snowboard);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SnowboardDTO(savedSnowboard));
    }
}