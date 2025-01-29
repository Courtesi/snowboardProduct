package com.example.nobsv2.snowboard.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.exceptions.CategoryNotFoundException;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.snowboard.CategoryRepository;
import com.example.nobsv2.snowboard.SnowboardRepository;
import com.example.nobsv2.snowboard.models.*;
import com.example.nobsv2.snowboard.validators.ProfanityChecker;
import com.example.nobsv2.snowboard.validators.SnowboardValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UpdateSnowboardService implements Command<UpdateSnowboardCommand, SnowboardDTO> {

    private final SnowboardRepository snowboardRepository;
    private final CategoryRepository categoryRepository;

    @Value("${noProfanityApiKey}")
    private String apiKey;

    public UpdateSnowboardService(SnowboardRepository snowboardRepository, CategoryRepository categoryRepository) {
        this.snowboardRepository = snowboardRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<SnowboardDTO> execute(UpdateSnowboardCommand updateSnowboardCommand) {
        //validator
        SnowboardValidator.execute(updateSnowboardCommand.getSnowboard());

        Optional<Snowboard> snowboard = snowboardRepository.findById(updateSnowboardCommand.getId());
        if (snowboard.isEmpty()) {
            throw new ProductNotFoundException();
        }

        Snowboard newSnowboard = updateSnowboardCommand.getSnowboard();

        //CHECK FOR PROFANITY
        ProfanityChecker.execute(newSnowboard, apiKey);

        Optional<Category> category = categoryRepository.findById(newSnowboard.getCategory().getId());

        if (category.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        //Set ID, Category, Timestamp_Created, and Region for newSnowboard
        newSnowboard.setId(updateSnowboardCommand.getId());
        newSnowboard.setCategory(category.get());
        newSnowboard.setTimestamp_created(snowboard.get().getTimestamp_created());
        newSnowboard.setRegion(snowboard.get().getRegion());

        //Set Timestamp_Updated
        String formattedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        newSnowboard.setTimestamp_updated(formattedNow);

        Snowboard savedSnowboard = snowboardRepository.save(newSnowboard);
        return ResponseEntity.ok(new SnowboardDTO(savedSnowboard));
    }
}