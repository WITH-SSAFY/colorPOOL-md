package com.cerberos.colorpool.entity.pdf;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="PDF")
public class Pdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PDF_ID", nullable = false)
    private Integer id;

    @Lob
    @Column(name="CONTENTS")
    private String contents;

    @Column(name="PATH")
    private String path;
}
