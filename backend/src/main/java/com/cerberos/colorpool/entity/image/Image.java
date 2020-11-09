package com.cerberos.colorpool.entity.image;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="IMAGE")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID",nullable = false)
    private Integer id;
    @Column(name = "PATH", nullable = false)
    private String path;

    @Builder
    public Image(String path){
        this.path = path;
    }
}
