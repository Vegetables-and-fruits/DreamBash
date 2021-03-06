package com.example.dreambash.controller;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.service.FruitRecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public FruitRecipes saveNewFruitRecipe(@RequestBody FruitRecipes fruitRecipe) {
        return service.saveNewFruitRecipe(fruitRecipe);
    }

    @GetMapping("/recipes/needsOven")
    public List<String> getRecipeThatNeedsOven() {
        return service.getRecipeNeedsOven();
    }

    @GetMapping("/recipes/byName")
    public List<String> getRecipeByTheName() {
        return service.getRecipeByName();
    }

    @DeleteMapping("/recipes/delete")
    public String deleteFruitRecipe(@RequestParam String id) {
        service.deleteFruitRecipe(id);
        return "Deleted FruitRecipe " + id;
    }

    @PatchMapping(path = "/update")
    public @ResponseBody
    String updateFruitRecipe(@RequestParam String id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) Integer servings,
                             @RequestParam(required = false) Boolean hasRecipes,
                             @RequestParam(required = false) Boolean needsOven) {
        FruitRecipes fruitRecipes = service.updateFruitRecipe(id, name, description, servings, hasRecipes, needsOven);
        if (fruitRecipes == null)
            return "Could not find recipe with id " + id;

        return "Recipe with id " + id + " updated";
    }
}
