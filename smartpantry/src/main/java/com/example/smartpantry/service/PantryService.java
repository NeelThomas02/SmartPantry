package com.example.smartpantry.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.example.smartpantry.model.PantryItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PantryService {

    public List<PantryItem> getPantryItems(String userId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("pantryItems").whereEqualTo("userId", userId).get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        List<PantryItem> pantryItems = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            Map<String, Object> data = doc.getData();
            PantryItem item = new PantryItem(
                    data.get("name").toString(),
                    Integer.parseInt(data.get("quantity").toString()),
                    data.get("expiryDate").toString()
            );
            pantryItems.add(item);
        }
        return pantryItems;
    }
}
