package com.example.dreambash.service;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.repository.FruitRecipesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test //Sana
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

    @Test //Sana
    void deleteFruitRecipe() {
        doNothing().when(mockRepository).deleteById(anyString());

        fruitRecipesService.deleteFruitRecipe("id3");

        verify(mockRepository, times(1)).deleteById("id3");
    }
}