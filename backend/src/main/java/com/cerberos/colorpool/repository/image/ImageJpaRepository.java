package com.cerberos.colorpool.repository.image;

import com.cerberos.colorpool.entity.image.Image;
import com.cerberos.colorpool.entity.pdf.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageJpaRepository extends JpaRepository<Image,Integer> {
    Optional<Image> findFirstByOrderByIdDesc();
}
