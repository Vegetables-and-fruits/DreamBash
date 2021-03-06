package com.example.dreambash.service;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.repository.FruitRecipesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
class FruitRecipesServiceTest {

    FruitRecipesService fruitRecipesService;

    @Autowired
    FruitRecipesRepository fruitRecipesRepository;

    @Mock
    FruitRecipesRepository mockRepository;

    @BeforeEach
    void setUp() {
        fruitRecipesService = new FruitRecipesService(mockRepository);
    }


    @Test
    void getRecipeNeedsOven() {
        FruitRecipes budapest = new FruitRecipes("id1", "Budapestrulle",
                "description", 8, false, true);

        when(mockRepository.findFruitRecipesByNeedsOven(true))
                .thenReturn(Collections.singletonList(budapest));

        List<String> ovenNeeded = fruitRecipesService.getRecipeNeedsOven();

        assertEquals(1, ovenNeeded.size());
        assertEquals(budapest.getName(), ovenNeeded.get(0));

        verify(mockRepository, times(1)).findFruitRecipesByNeedsOven(true);

    }

    @Test
    void deleteFruitRecipe() {
        doNothing().when(mockRepository).deleteById(anyString());

        fruitRecipesService.deleteFruitRecipe("id3");

        verify(mockRepository, times(1)).deleteById("id3");
    }

    @Test
    void getFruitRecipeTest() {
        FruitRecipes recipe = new FruitRecipes("1", "Apple Pie","A very delicious pie", 3, true, true );

        when(mockRepository.findAll())
                .thenReturn(Arrays.asList(recipe));

        List<FruitRecipes> actual = fruitRecipesService.getFruitRecipe();

        assertEquals(recipe.getName(), actual.get(0).getName());
        assertEquals("Apple Pie", actual.get(0).getName());
        assertNotEquals("Not right", actual.get(0).getName());

        verify(mockRepository, times(1)).findAll();
    }

    @Test
    void saveNewFruitRecipeTest() {
        FruitRecipes recipe = new FruitRecipes("1", "Apple Pie","A very delicious pie", 3, true, true );

        when(mockRepository.save(recipe))
                .thenReturn(recipe);

        FruitRecipes actual = fruitRecipesService.saveNewFruitRecipe(recipe);

        assertEquals(actual, recipe);
        assertEquals(recipe.getName(), actual.getName());

        verify(mockRepository, times(1)).save(any());

    }

    @Test
    void ifUpdatingWithWrongId_returnsNull() {
        FruitRecipesService fruitRecipesServiceNoMockRepository = new FruitRecipesService(fruitRecipesRepository);
        fruitRecipesRepository.save(new FruitRecipes("1", "Recipe 1", "Description 1",
                6, true, true));

        FruitRecipes fruitRecipes = fruitRecipesServiceNoMockRepository.updateFruitRecipe("FelId",
                "Bra namn", null, null, null, null);

        assertNull(fruitRecipes);
    }

    @Test
    void ifUpdatingWithWrongCorrectId_returnsUpdatedFruitRecipes() {
        FruitRecipesService fruitRecipesServiceNoMockRepository = new FruitRecipesService(fruitRecipesRepository);
        fruitRecipesRepository.save(new FruitRecipes("1", "Recipe 1", "Description 1", 6, true, true));
        FruitRecipes fruitRecipes = fruitRecipesServiceNoMockRepository.updateFruitRecipe("1", "Bra namn", null, 4, null, false);
        assertNotNull(fruitRecipes);
        assertEquals(fruitRecipes.getId(), "1");
        assertEquals(fruitRecipes.getName(), "Bra namn");
        assertEquals(fruitRecipes.getDescription(), "Description 1");
        assertEquals(fruitRecipes.getServings(), 4);
        assertTrue(fruitRecipes.isHasRecipes());
        assertFalse(fruitRecipes.isNeedsOven());
    }

}
