package com.example.dreambash.controller;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.service.FruitRecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FruitRecipesController {

    private final FruitRecipesService service;

    @GetMapping("/recipes")
    public List<FruitRecipes> getFruitRecipes() {
        return service.getFruitRecipe();
    }

    @PostMapping("/saveRecipe")
    public void saveNewFruitRecipe(@RequestBody FruitRecipes fruitRecipe) {
        service.saveNewFruitRecipe(fruitRecipe);
    }

    @GetMapping("/recipes/needsOven")
    public List<String> getRecipeThatNeedsOven() {
        return service.getRecipeNeedsOven();
    }

}
