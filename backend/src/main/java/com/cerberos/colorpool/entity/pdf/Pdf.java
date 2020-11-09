package com.cerberos.colorpool.entity.pdf;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Pdf(String contents, String path){
        this.contents = contents;
        this.path = path;
    }
}
