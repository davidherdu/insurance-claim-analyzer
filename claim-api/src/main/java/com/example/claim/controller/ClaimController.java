package com.example.claim.controller;

import com.example.claim.model.Claim;
import com.example.claim.repository.ClaimRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimRepository repository;
    private final KafkaTemplate<String, String> kafka;

    public ClaimController(ClaimRepository repository, KafkaTemplate<String, String> kafka) {
        this.repository = repository;
        this.kafka = kafka;
    }

    @GetMapping
    public List<Claim> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Claim create(@RequestBody Claim claim) {
        Claim saved = repository.save(claim);
        kafka.send("claims-input", saved.getId().toString());
        return saved;
    }
}

