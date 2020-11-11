package com.cerberos.colorpool.model.pdf;

import com.cerberos.colorpool.entity.pdf.Pdf;
import lombok.*;

public class PdfModel {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{
        private String contents;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res{
        private Integer id;
        private String contents;
        private String path;


        @Builder
        public Res(int id, String contents, String path){
            this.id = id;
            this.contents = contents;
            this.path = path;
        }

        public Pdf toEntity(){
            Pdf pdf = Pdf.builder()
                    .id(this.id)
                    .contents(this.contents)
                    .path(this.path)
                    .build();
            return pdf;
        }
    }


}
