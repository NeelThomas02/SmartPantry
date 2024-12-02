package com.example.smartpantry.controller;

import com.smartpantryapp.service.PantryService;
import com.smartpantryapp.service.NutritionalAnalysisService;
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
    public ResponseEntity<?> getNutritionalAnalysis(@RequestParam String userId) {
        try {
            // Fetch pantry items for the user
            List<String> pantryItems = pantryService.getPantryItems(userId).stream()
                    .map(item -> item.getName())
                    .collect(Collectors.toList());

            // Fetch nutritional data for each pantry item
            List<Map<String, Object>> nutritionalInfo = pantryItems.stream()
                    .map(item -> Map.of(
                            "item", item,
                            "nutritionalData", nutritionalService.getNutritionalData(item)
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(nutritionalInfo);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
