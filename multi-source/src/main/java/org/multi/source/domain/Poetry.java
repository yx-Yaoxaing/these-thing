package org.multi.source.domain;

import lombok.Data;

@Data
public class Poetry {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String intro;
    private String translation;
    private String masterComment;
    private String annotation;

    private Integer type;


}
