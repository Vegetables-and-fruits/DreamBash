
package com.example.dreambash.repository;

import com.example.dreambash.model.FruitRecipes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class FruitRecipesRepositoryTest {
    @Autowired
    FruitRecipesRepository fruitRecipesRepository;

    @Test
    void findFruitRecipesByNeedsOven() {
        FruitRecipes recipe1 = new FruitRecipes();
        recipe1.setId("1");
        recipe1.setName("Apple pie");
        recipe1.setDescription("description");
        recipe1.setServings(1);
        recipe1.setHasRecipes(true);
        recipe1.setNeedsOven(true);
        fruitRecipesRepository.save(recipe1);
        List<FruitRecipes> fruitRecipes = fruitRecipesRepository.findFruitRecipesByNeedsOven(true);
        assertEquals(fruitRecipes.get(0), recipe1);
    }

    @Test
    void findFruitRecipesByName() {
        FruitRecipes recipe2 = new FruitRecipes();
        recipe2.setId("1");
        recipe2.setName("Apple pie");
        recipe2.setDescription("description");
        recipe2.setServings(1);
        recipe2.setHasRecipes(true);
        recipe2.setNeedsOven(true);
        fruitRecipesRepository.save(recipe2);
        List<FruitRecipes> fruitRecipes = fruitRecipesRepository.findFruitRecipesByName("Apple pie");
        assertEquals(fruitRecipes.get(0), recipe2);
    }
}
