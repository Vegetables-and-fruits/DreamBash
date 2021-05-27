package com.example.dreambash.service;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.repository.FruitRecipesRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FruitRecipesServiceTest {

    FruitRecipesService fruitRecipesService;

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

        FruitRecipes fruktsallad = new FruitRecipes("id2", "Fruktsallad",
                "description", 12, false, false);

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
        FruitRecipes recipe1 = new FruitRecipes();
        recipe1.setId("1");
        recipe1.setName("Apple Pie");
        recipe1.setDescription("A very delicious pie");
        recipe1.setServings(3);

        when(mockRepository.findAll())
                .thenReturn(Arrays.asList(recipe1));

        List<FruitRecipes> actual = fruitRecipesService.getFruitRecipe();

        assertEquals(recipe1.getName(), actual.get(0).getName());
        assertEquals("Apple Pie", actual.get(0).getName());
        assertNotEquals("Not right", actual.get(0).getName());

        verify(mockRepository, times(1)).findAll();
    }

    @Test 
    void saveNewFruitRecipeTest() {
        FruitRecipes recipe1 = new FruitRecipes();
        recipe1.setId("1");
        recipe1.setName("Apple Pie");
        recipe1.setDescription("A very delicious pie");
        recipe1.setServings(3);
        when(mockRepository.save(recipe1))
                .thenReturn(recipe1);

        FruitRecipes actual = fruitRecipesService.saveNewFruitRecipe(recipe1);

        assertEquals(actual, recipe1);
        assertEquals(recipe1.getName(), actual.getName());

        verify(mockRepository, times(1)).save(any());

    }

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
