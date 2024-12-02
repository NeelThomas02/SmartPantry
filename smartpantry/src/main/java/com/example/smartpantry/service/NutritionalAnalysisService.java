package com.example.smartpantry.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NutritionalAnalysisService {

    private static final String OPENFOODFACTS_API_URL = "https://world.openfoodfacts.org/api/v0/product/";

    public Map<String, Object> getNutritionalData(String productName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = OPENFOODFACTS_API_URL + productName + ".json";

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("product")) {
                Map<String, Object> product = (Map<String, Object>) response.get("product");
                Map<String, Object> nutrients = (Map<String, Object>) product.get("nutriments");

                return Map.of(
                        "calories", nutrients.getOrDefault("energy-kcal_100g", "N/A"),
                        "protein", nutrients.getOrDefault("proteins_100g", "N/A"),
                        "fat", nutrients.getOrDefault("fat_100g", "N/A"),
                        "sugar", nutrients.getOrDefault("sugars_100g", "N/A")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Map.of("error", "Nutritional data not found");
    }
}
