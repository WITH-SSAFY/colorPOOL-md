package com.cerberos.colorpool.model.pdf;

import com.cerberos.colorpool.entity.pdf.Pdf;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PdfModel {
    private String contents;

    public Pdf toEntity(String contents, String path){
        Pdf pdf = Pdf.builder()
                .contents(contents)
                .path(path)
                .build();
        return pdf;
    }
}
