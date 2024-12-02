package com.example.smartpantry.controller;

import com.example.smartpantry.model.PantryItem;
import com.example.smartpantry.service.PantryService;
import com.example.smartpantry.service.NutritionalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionalAnalysisController {

    @Autowired
    private PantryService pantryService;

    @Autowired
    private NutritionalAnalysisService nutritionalService;

    @GetMapping("/analyze")
    public ResponseEntity<?> getNutritionalAnalysis(@RequestParam(required = false) String userId) {
        // Validate that userId is provided
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(400).body("User ID is required for nutritional analysis.");
        }

        try {
            // Fetch pantry items for the user
            List<PantryItem> pantryItems = pantryService.getPantryItems(userId);

            if (pantryItems == null || pantryItems.isEmpty()) {
                return ResponseEntity.status(404).body("No pantry items found for the user.");
            }

            // Fetch nutritional data for each pantry item
            List<Map<String, Object>> nutritionalInfo = pantryItems.stream()
                    .map(item -> Map.of(
                            "item", item,
                            "nutritionalData", nutritionalService.getNutritionalData(String.valueOf(item))
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(nutritionalInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Failed to fetch nutritional analysis",
                    "details", e.getMessage()
            ));
        }
    }
}
