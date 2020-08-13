package com.chozoi.productservice.app.controllers;

import com.chozoi.productservice.app.dtos.RateDTO;
import com.chozoi.productservice.domain.services.impl.RateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rate")
public class RateController {
    @Autowired
    private RateServices rateService;

    @PostMapping("/add")
    public ResponseEntity<?> addRate(
            @RequestBody RateDTO rateDTO, @RequestParam("accessToken") String accessToken) {
        return rateService.addRate(rateDTO, accessToken);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRate(@PathVariable("id") Long id,
                                        @RequestBody RateDTO rateDTO, @RequestParam("accessToken") String accessToken) {
        return rateService.updateRate(id, rateDTO, accessToken);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRate(
            @PathVariable("id") Long id, @RequestParam("accessToken") String accessToken) {
        return rateService.delete(id, accessToken);
    }

    @GetMapping("/get-rate-user")
    public ResponseEntity<?> getRateById(@RequestParam("accessToken") String accessToken) {
        return rateService.findAllByUserId(accessToken);
    }
}
