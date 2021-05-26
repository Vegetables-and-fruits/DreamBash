package com.example.dreambash.repository;

import com.example.dreambash.model.FruitRecipes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FruitRecipesRepository extends MongoRepository<FruitRecipes, String> {

    List<FruitRecipes> findFruitRecipesByNeedsOven(boolean needsOven);
    List<FruitRecipes> findFruitRecipesByName(String name);
    //Optional<FruitRecipes> findById(String id);
}
