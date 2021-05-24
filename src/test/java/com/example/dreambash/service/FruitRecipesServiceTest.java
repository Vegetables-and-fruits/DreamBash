package com.example.dreambash.service;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.repository.FruitRecipesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by Iryna Gnatenko
 * Date 2021-05-24
 * Time 11:16 PM
 * Project DreamBash
 */
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
        void getFruitRecipeTest() {
            FruitRecipes recipe1 = new FruitRecipes();
            recipe1.setId("1");
            recipe1.setName("Apple Pie");
            recipe1.setDescription("A very delicious pie");
            recipe1.setServings(3);

            when(mockRepository.findAll())
                    .thenReturn(Arrays.asList(recipe1));

            List<FruitRecipes> actual = fruitRecipesService.getFruitRecipe();

            assertEquals(recipe1.getName(),actual.get(0).getName());
            assertEquals("Apple Pie",actual.get(0).getName());
            assertNotEquals("Not right",actual.get(0).getName());

            verify(mockRepository, times(1)).findAll();
        }


    }

