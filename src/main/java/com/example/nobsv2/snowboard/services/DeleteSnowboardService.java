package com.example.nobsv2.snowboard.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.snowboard.SnowboardRepository;
import com.example.nobsv2.snowboard.models.Snowboard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteSnowboardService implements Command<UUID, Void> {
    private final SnowboardRepository snowboardRepository;

    public DeleteSnowboardService(SnowboardRepository snowboardRepository) {
        this.snowboardRepository = snowboardRepository;
    }

    @Override
    public ResponseEntity<Void> execute(UUID uuid) {
        Optional<Snowboard> snowBoardOptional = snowboardRepository.findById(uuid);

        if (snowBoardOptional.isEmpty()) {
            throw new ProductNotFoundException();
        }

        snowboardRepository.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}