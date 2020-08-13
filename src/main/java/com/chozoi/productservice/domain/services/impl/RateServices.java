package com.chozoi.productservice.domain.services.impl;

import com.chozoi.productservice.app.dtos.RateDTO;
import com.chozoi.productservice.domain.entities.product.Product;
import com.chozoi.productservice.domain.entities.rate.Rate;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.services.BaseServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RateServices extends BaseServices {

    public ResponseEntity<?> addRate(RateDTO rateDTO, String accessToken) {
        long userId = hasUserToken(accessToken);
        Product product = productRepository.getOne(rateDTO.getProductId());

        Rate rate = Rate.builder()
                .content(rateDTO.getContent())
                .ratePoints(rateDTO.getRatePoints())
                .userId(userId)
                .product(product)
                .build();

        ratesRepository.save(rate);
        return ResponseEntity.ok("Success!");
    }

    public ResponseEntity<?> updateRate(long id, RateDTO newRate, String accessToken) {
        Long userId = hasUserToken(accessToken);

        Rate rate = ratesRepository.findByIdAndUserId(id, userId);
        if (Objects.isNull(rate)) {
            throw new NotFoundException("Rate Not Found!");
        }

        rate.setContent(newRate.getContent());
        rate.setRatePoints(newRate.getRatePoints());

        ratesRepository.saveAndFlush(rate);
        return ResponseEntity.ok("Updated!");
    }

    public ResponseEntity<?> delete(long id, String accessToken) {
        long userId = hasUserToken(accessToken);
        Rate rate = ratesRepository.findByIdAndUserId(id, userId);
        if (Objects.isNull(rate)) {
            throw new NotFoundException("Not Found Rate");
        }
        ratesRepository.delete(rate);
        return ResponseEntity.ok("Deleted!");
    }

    public ResponseEntity<?> findAllByUserId(String accessToken) {
        long userId = hasUserToken(accessToken);
        List<Rate> rates = ratesRepository.findByUserId(userId);

        return ResponseEntity.ok(convertToRateDTOs(rates));
    }
}
