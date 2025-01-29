package com.example.nobsv2.snowboard.services;

import com.example.nobsv2.Query;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.snowboard.SnowboardRepository;
import com.example.nobsv2.snowboard.models.Snowboard;
import com.example.nobsv2.snowboard.models.SnowboardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetSnowboardByIdService implements Query<UUID, SnowboardDTO> {

    private final SnowboardRepository snowboardRepository;

    public GetSnowboardByIdService(SnowboardRepository snowboardRepository) {
        this.snowboardRepository = snowboardRepository;
    }


    @Override
    public ResponseEntity<SnowboardDTO> execute(UUID id) {
        Optional<Snowboard> snowboard = snowboardRepository.findById(id);

        if (snowboard.isPresent()) {
            return ResponseEntity.ok(new SnowboardDTO(snowboard.get()));
        }

        throw new ProductNotFoundException();
    }
}
