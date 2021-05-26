package com.example.dreambash.service;

import com.example.dreambash.model.FruitRecipes;
import com.example.dreambash.repository.FruitRecipesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
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
    public void deleteFruitRecipe(String fruitRecipesId){
        recipesRepository.deleteById(fruitRecipesId);
    }

    public Optional<FruitRecipes> findById(String id) {

        return recipesRepository.findById(id);
    }

    public FruitRecipes updateFruitRecipe(String id,
                             String name,
                             String description,
                             Integer servings,
                             Boolean hasRecipes,
                             Boolean needsOven)
    {
        Optional<FruitRecipes> optionalFruitRecipes = recipesRepository.findById(id);
        if (!optionalFruitRecipes.isPresent())
            return null;

        FruitRecipes updateItem = optionalFruitRecipes.get();

        if (name!=null) updateItem.setName(name);
        if (description!=null) updateItem.setDescription(description);
        if (servings!=null) updateItem.setServings(servings);
        if (hasRecipes!=null) updateItem.setHasRecipes(hasRecipes);
        if (needsOven!=null) updateItem.setNeedsOven(needsOven);

        return recipesRepository.save(updateItem);
    }
}
