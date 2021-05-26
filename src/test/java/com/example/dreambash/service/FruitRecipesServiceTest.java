package com.example.dreambash.service;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.repository.FruitRecipesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class FruitRecipesServiceTest {

    @Autowired
    FruitRecipesRepository fruitRecipesRepository;

    @Test
    public void ifUpdatingWithWrongId_returnsNull() {
        FruitRecipesService fruitRecipesServiceNoMockRepository = new FruitRecipesService(fruitRecipesRepository);
        fruitRecipesRepository.save(new FruitRecipes("1", "Recipe 1", "Description 1", 6, true, true));
        FruitRecipes fruitRecipes = fruitRecipesServiceNoMockRepository.updateFruitRecipe("FelId", "Bra namn", null,null,null,null);
        assertNull(fruitRecipes);
    }

    @Test
    public void ifUpdatingWithWrongCorrectId_returnsUpdatedFruitRecipes() {
        FruitRecipesService fruitRecipesServiceNoMockRepository = new FruitRecipesService(fruitRecipesRepository);
        fruitRecipesRepository.save(new FruitRecipes("1", "Recipe 1", "Description 1", 6, true, true));
        FruitRecipes fruitRecipes = fruitRecipesServiceNoMockRepository.updateFruitRecipe("1", "Bra namn", null,4,null,false);
        assertNotNull(fruitRecipes);
        assertTrue(fruitRecipes.getId().equals("1"));
        assertTrue(fruitRecipes.getName().equals("Bra namn"));
        assertTrue(fruitRecipes.getDescription().equals("Description 1"));
        assertTrue(fruitRecipes.getServings() == 4);
        assertTrue(fruitRecipes.isHasRecipes());
        assertFalse(fruitRecipes.isNeedsOven());
    }

}