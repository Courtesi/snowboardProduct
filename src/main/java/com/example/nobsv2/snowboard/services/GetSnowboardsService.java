package com.example.nobsv2.snowboard.services;

import com.example.nobsv2.Query;
import com.example.nobsv2.snowboard.SnowboardRepository;
import com.example.nobsv2.snowboard.models.Snowboard;
import com.example.nobsv2.snowboard.models.SnowboardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSnowboardsService implements Query<Void, List<SnowboardDTO>> {

    private final SnowboardRepository snowboardRepository;

    public GetSnowboardsService(SnowboardRepository snowboardRepository) {
        this.snowboardRepository = snowboardRepository;
    }


    @Override
    public ResponseEntity<List<SnowboardDTO>> execute(Void input) {
        List<Snowboard> snowboards = snowboardRepository.findAll();

        List<SnowboardDTO> snowboardDTOs = snowboards.stream().map(SnowboardDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(snowboardDTOs);
    }
}
