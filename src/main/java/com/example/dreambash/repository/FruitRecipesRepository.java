package com.example.dreambash.repository;

import com.example.dreambash.model.FruitRecipes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitRecipesRepository extends MongoRepository<FruitRecipes, String> {

    List<FruitRecipes> findFruitRecipesByNeedsOven(boolean needsOven);
    //Optional<FruitRecipes> findById(String id);
}
