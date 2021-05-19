package com.example.dreambash.service;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.repository.FruitRecipesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FruitRecipesService {
    private final FruitRecipesRepository recipesRepository;

    public List<FruitRecipes> getFruitRecipe() {
        return recipesRepository.findAll();
    }

    public void saveNewFruitRecipe(FruitRecipes fruitRecipes) {
        recipesRepository.save(fruitRecipes);
    }

    public List<String> getRecipeNeedsOven() {
        List<FruitRecipes> receipNeedsOven = recipesRepository.findFruitRecipiesByNeedsOven(true);

        return receipNeedsOven.stream()
                .map(fruitRecipes -> fruitRecipes.getName())
                .collect(Collectors.toList());
    }
}
