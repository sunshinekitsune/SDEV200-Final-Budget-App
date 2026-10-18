package com.ivytech.sdev200.budget_app.service;

import com.ivytech.sdev200.budget_app.model.User;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.stereotype.Service;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

@Service
public class BudgetStorageService {

    private static final Logger log = System.getLogger(BudgetStorageService.class.getName());
    private static final Path DATA_DIR = Paths.get("data");
    private final JsonMapper jsonMapper;

    public BudgetStorageService() {
        this.jsonMapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
        } catch (IOException e) {
            log.log(Level.ERROR, "Failed creating data directory", e);
        }
    }

    public synchronized void saveUser(User user) {
        Path filePath = DATA_DIR.resolve(sanitize(user.getUsername()) + ".json");
        try {
            jsonMapper.writeValue(filePath.toFile(), user);
            log.log(Level.INFO, "Persisted budget data for user: {0}", user.getUsername());
        } catch (Exception e) {
            log.log(Level.ERROR, "Failed to save user JSON", e);
        }
    }

    public synchronized Optional<User> loadUser(String username) {
        Path filePath = DATA_DIR.resolve(sanitize(username) + ".json");
        if (!Files.exists(filePath)) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonMapper.readValue(filePath.toFile(), User.class));
        } catch (Exception e) {
            log.log(Level.ERROR, "Failed reading user JSON", e);
            return Optional.empty();
        }
    }

    public boolean userExists(String username) {
        return Files.exists(DATA_DIR.resolve(sanitize(username) + ".json"));
    }

    private String sanitize(String name) {
        return name.trim().toLowerCase().replaceAll("[^a-z0-9_-]", "_");
    }
}
