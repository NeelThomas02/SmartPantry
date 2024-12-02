package com.smartpantryapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecipeSuggestionService {

    private static final String SPOONACULAR_API_KEY = "your_spoonacular_api_key";
    private static final String SPOONACULAR_API_URL = "https://api.spoonacular.com/recipes/complexSearch";

    public String getRecipeSuggestions(List<String> ingredients) {
        RestTemplate restTemplate = new RestTemplate();
        String ingredientsQuery = String.join(",", ingredients);

        String url = String.format("%s?apiKey=%s&includeIngredients=%s&number=5",
                SPOONACULAR_API_URL, SPOONACULAR_API_KEY, ingredientsQuery);

        return restTemplate.getForObject(url, String.class);
    }
}
