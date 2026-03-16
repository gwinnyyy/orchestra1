package com.hps.orchestraspa.controller;

import com.hps.orchestraspa.model.Massage;
import com.hps.orchestraspa.service.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/massages")
public class MassageController {

    @Autowired
    private MassageService massageService;

    @GetMapping("/all")
    public ResponseEntity<Massage[]> getAll() throws Exception {
        return ResponseEntity.ok(massageService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Massage> add(@RequestBody Massage massage) throws Exception {
        return ResponseEntity.ok(massageService.create(massage));
    }
}
