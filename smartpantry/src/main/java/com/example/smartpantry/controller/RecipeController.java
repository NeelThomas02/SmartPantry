package com.example.smartpantry.controller;

import com.example.smartpantry.service.PantryService;
import com.example.smartpantry.service.RecipeSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private PantryService pantryService;

    @Autowired
    private RecipeSuggestionService recipeService;

    @GetMapping("/suggestions")
    public ResponseEntity<?> getRecipeSuggestions(@RequestParam String userId) {
        try {
            // Fetch pantry items for the user
            List<String> ingredients = pantryService.getPantryItems(userId).stream()
                    .map(item -> item.getName())
                    .collect(Collectors.toList());

            // Fetch recipes from Spoonacular
            String recipes = recipeService.getRecipeSuggestions(ingredients);
            return ResponseEntity.ok(recipes);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
