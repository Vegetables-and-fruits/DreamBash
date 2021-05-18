package com.example.dreambash.repository;

import com.example.dreambash.model.FruitRecipes;

import java.util.List;

public interface FruitRecipesRepository extends MongoRepository<FruitRecipes, String> {

    List<FruitRecipes> findFruitRecipiesByNeedsOven(boolean needsOven );
}
