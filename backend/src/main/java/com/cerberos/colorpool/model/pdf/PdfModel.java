package com.cerberos.colorpool.model.pdf;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PdfModel {
    private String contents;
    private String path;
}
