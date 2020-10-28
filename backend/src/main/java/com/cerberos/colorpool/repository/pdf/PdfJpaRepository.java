package com.cerberos.colorpool.repository.pdf;

import com.cerberos.colorpool.entity.pdf.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfJpaRepository extends JpaRepository<Pdf, Integer> {
}
