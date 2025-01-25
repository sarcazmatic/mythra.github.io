package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IncomingFileDTO {

    private String content;
    private Long size;
    private String name;
    private String contentType;
    private String originalFilename;

}
