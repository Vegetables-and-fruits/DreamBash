
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
        FruitRecipes fruit=new FruitRecipes();
        fruit.setId("1");
        fruit.setName("Apple pie");
        fruit.setDescription("description");
        fruit.setServings(1);
        fruit.setHasRecipes(true);
        fruit.setNeedsOven(true);
        fruitRecipesRepository.save(fruit);
        List<FruitRecipes>fruits=fruitRecipesRepository.findFruitRecipesByNeedsOven(true);
        assertEquals(fruits.get(0),fruit);
    }

@Test
    void findFruitRecipesByName(){
    FruitRecipes fruit=new FruitRecipes();
    fruit.setId("1");
    fruit.setName("Apple pie");
    fruit.setDescription("description");
    fruit.setServings(1);
    fruit.setHasRecipes(true);
    fruit.setNeedsOven(true);
    fruitRecipesRepository.save(fruit);
    List<FruitRecipes>fruits=fruitRecipesRepository.findFruitRecipesByName("Apple pie");
    assertEquals(fruits.get(0),fruit);
}
}
