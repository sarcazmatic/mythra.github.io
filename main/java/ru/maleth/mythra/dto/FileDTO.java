package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileDTO {

    private String name;
    private byte[] content;
    private String contentType;

}
