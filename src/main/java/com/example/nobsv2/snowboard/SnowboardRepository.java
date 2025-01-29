package com.example.nobsv2.snowboard;

import com.example.nobsv2.snowboard.models.Snowboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SnowboardRepository extends JpaRepository<Snowboard, UUID> {

    @Query("SELECT sb FROM Snowboard sb JOIN Category c ON sb.category.id = c.id WHERE sb.description LIKE %:keyword% OR c.type LIKE %:keyword% ")
    List<Snowboard> findByDescriptionOrCategoryContaining(@Param("keyword") String keyword);

    @Query("SELECT sb FROM Snowboard sb WHERE sb.description LIKE %:keyword%")
    List<Snowboard> findByDescriptionContaining(@Param("keyword") String keyword);

    @Query("SELECT sb FROM Snowboard sb JOIN Category c ON sb.category.id = c.id WHERE c.type LIKE %:keyword%")
    List<Snowboard> findByCategoryContaining(@Param("keyword") String keyword);
}
