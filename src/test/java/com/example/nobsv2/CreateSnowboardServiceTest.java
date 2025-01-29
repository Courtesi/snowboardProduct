package com.example.nobsv2;

import com.example.nobsv2.snowboard.CategoryRepository;
import com.example.nobsv2.snowboard.SnowboardRepository;
import com.example.nobsv2.snowboard.models.Category;
import com.example.nobsv2.snowboard.models.Snowboard;
import com.example.nobsv2.snowboard.models.SnowboardDTO;
import com.example.nobsv2.snowboard.services.CreateSnowboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreateSnowboardServiceTest {

    @Mock
    private SnowboardRepository snowboardRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    @Value("${noProfanityApiKey}")
    private String apiKey;

    @InjectMocks
    private CreateSnowboardService createSnowboardService;


    @BeforeEach
    public void setup() {
        // Initializes mocks and injects them
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_snowboard_when_create_snowboard_service_then_return_created_product() {
        // Given
        Snowboard snowboard = new Snowboard();
        snowboard.setDescription("Product description testcasesnowboard");
        snowboard.setPrice(500.00);
        snowboard.setManufacturer("Zorbox");
        Category category = new Category();
        category.setId(1);;
        snowboard.setCategory(category);

        category.setType("All-Mountain");

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(snowboardRepository.save(snowboard)).thenReturn(snowboard);

        // When
        ResponseEntity<SnowboardDTO> response = createSnowboardService.execute(snowboard);

        // Then
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(new SnowboardDTO(snowboard)), response);

        verify(snowboardRepository, times(1)).save(snowboard);
    }
}
