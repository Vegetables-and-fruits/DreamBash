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

    public FruitRecipes saveNewFruitRecipe(FruitRecipes fruitRecipes) {
        return recipesRepository.save(fruitRecipes);
    }

    public List<String> getRecipeNeedsOven() {
        List<FruitRecipes> receipNeedsOven = recipesRepository.findFruitRecipesByNeedsOven(true);

        return receipNeedsOven.stream()
                .map(fruitRecipes -> fruitRecipes.getName())
                .collect(Collectors.toList());
    }
}
