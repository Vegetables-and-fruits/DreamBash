package com.example.dreambash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FruitRecipes {
    String id;
    String name;
    String description;
    int servings;
    boolean hasRecipes;
    boolean needsOven;

}
/**
 * db.recipes.insertOne({
 * "name":"",
 * "description":"",
 * "servings":0,
 * "hasRecipes":true/false,
 * "needsOven":true/fals
 * })
 */