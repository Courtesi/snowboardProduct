package com.example.nobsv2.snowboard;

import com.example.nobsv2.snowboard.models.SearchSnowboardCommand;
import com.example.nobsv2.snowboard.models.Snowboard;
import com.example.nobsv2.snowboard.models.SnowboardDTO;
import com.example.nobsv2.snowboard.models.UpdateSnowboardCommand;
import com.example.nobsv2.snowboard.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SnowboardController {

    private final CreateSnowboardService createSnowboardService;

    private final GetSnowboardsService getSnowboardsService;

    private final GetSnowboardByIdService getSnowboardByIdService;

    private final SearchSnowboardService searchSnowboardService;

    private final UpdateSnowboardService updateSnowboardService;

    private final DeleteSnowboardService deleteSnowboardService;

    public SnowboardController(CreateSnowboardService createSnowboardService,
                               GetSnowboardsService getSnowboardsService,
                               GetSnowboardByIdService getSnowboardByIdService,
                               SearchSnowboardService searchSnowboardService,
                               UpdateSnowboardService updateSnowboardService,
                               DeleteSnowboardService deleteSnowboardService) {
        this.createSnowboardService = createSnowboardService;
        this.getSnowboardsService = getSnowboardsService;
        this.getSnowboardByIdService = getSnowboardByIdService;
        this.searchSnowboardService = searchSnowboardService;
        this.updateSnowboardService = updateSnowboardService;
        this.deleteSnowboardService = deleteSnowboardService;
    }

    @PostMapping("/create_snowboard")
    public ResponseEntity<SnowboardDTO> createSnowboard(@RequestBody Snowboard snowboard) {
        return createSnowboardService.execute(snowboard);
    }

    @GetMapping("/snowboards")
    public ResponseEntity<List<SnowboardDTO>> getSnowboards() {
        return getSnowboardsService.execute(null);
    }

    @GetMapping("/snowboard/{id}")
    public ResponseEntity<SnowboardDTO> getSnowboard(@PathVariable UUID id) {
        return getSnowboardByIdService.execute(id);
    }

    @GetMapping("/snowboard/search")
    public ResponseEntity<List<SnowboardDTO>> searchSnowboard(@RequestParam String query, @RequestParam Optional<String> column, @RequestParam Optional<String> order) {
        String finalColumn = column.orElse("");
        String finalOrder = order.orElse("");

        return searchSnowboardService.execute(new SearchSnowboardCommand(query, finalColumn, finalOrder));
    }

    @PutMapping("/snowboard/{id}")
    public ResponseEntity<SnowboardDTO> updateSnowboard(@PathVariable UUID id, @RequestBody Snowboard snowboard) {
        return updateSnowboardService.execute(new UpdateSnowboardCommand(id, snowboard));
    }

    @DeleteMapping("/snowboard/{id}")
    public ResponseEntity<Void> deleteSnowboard(@PathVariable UUID id) {
        return deleteSnowboardService.execute(id);
    }
}
