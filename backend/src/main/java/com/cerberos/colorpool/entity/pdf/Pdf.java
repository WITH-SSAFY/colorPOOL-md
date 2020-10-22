package com.cerberos.colorpool.entity.pdf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pdf {
    @Id
    @GeneratedValue
    private Integer id;
}
