package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Long> {
    Image save(Image image);

    Optional<Image> findById(Long id);

    void deleteById(Long id);
}
