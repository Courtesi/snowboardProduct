package com.example.nobsv2.snowboard.services;

import com.example.nobsv2.Query;
import com.example.nobsv2.snowboard.SnowboardRepository;
import com.example.nobsv2.snowboard.models.SearchSnowboardCommand;
import com.example.nobsv2.snowboard.models.SnowboardDTO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SearchSnowboardService implements Query<SearchSnowboardCommand, List<SnowboardDTO>> {

    private final SnowboardRepository snowboardRepository;

    public SearchSnowboardService(SnowboardRepository snowboardRepository) {
        this.snowboardRepository = snowboardRepository;
    }

    public int comparePrice(SnowboardDTO s1, SnowboardDTO s2) {
        return Double.compare(s1.getPrice(), s2.getPrice());
    }

    @Override
    @CachePut(value = "searchSnowboardCache", key = "#query.getQuery() + '_' + #query.getColumn() + '_' + #query.getOrder()")
    public ResponseEntity<List<SnowboardDTO>> execute(SearchSnowboardCommand query) {
        List<SnowboardDTO> snowboardDTOs;

        if (query.getColumn().equals("Description")) {
            snowboardDTOs = new java.util.ArrayList<>(snowboardRepository.findByDescriptionContaining(query.getQuery())
                    .stream()
                    .limit(10)
                    .map(SnowboardDTO::new)
                    .toList());
        } else if (query.getColumn().equals("Category")) {
            snowboardDTOs = new java.util.ArrayList<>(snowboardRepository.findByCategoryContaining(query.getQuery())
                    .stream()
                    .limit(10)
                    .map(SnowboardDTO::new)
                    .toList());
        } else {
            snowboardDTOs = new java.util.ArrayList<>(snowboardRepository.findByDescriptionOrCategoryContaining(query.getQuery())
                    .stream()
                    .limit(10)
                    .map(SnowboardDTO::new)
                    .toList());
        }

        //Sorting Order
        if (query.getOrder().equals("Price")) {
            snowboardDTOs.sort(Comparator.comparing(SnowboardDTO::getPrice));
            return ResponseEntity.ok(snowboardDTOs);
        } else if (query.getOrder().equals("Alphabetical")) {
            snowboardDTOs.sort(Comparator.comparing(SnowboardDTO::getDescription, String.CASE_INSENSITIVE_ORDER));
            return ResponseEntity.ok(snowboardDTOs);
        } else {
            return ResponseEntity.ok(snowboardDTOs);
        }
    }




}
