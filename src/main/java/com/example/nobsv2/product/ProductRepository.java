package com.example.nobsv2.product;


import com.example.nobsv2.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //spring data jpa
    List<Product> findByNameContaining(String name);

    //JPQL Java Persistence Query Language (will change query to match database you're using
    //i.e mySQL, postgreSQL, etc.
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String keyword);

    //is read as mySQL query now, is not translated from JPQL to mySQL
    //no longer database agnostic, will not work with postgreSQL database if you switch dbs
    @Query(value = "SELECT * FROM Product WHERE name LIKE %:keyword% OR description LIKE %:keyword%", nativeQuery = true)
    List<Product> findByNameOrDescriptionContainingNativeQuery(@Param("keyword") String keyword);
}
