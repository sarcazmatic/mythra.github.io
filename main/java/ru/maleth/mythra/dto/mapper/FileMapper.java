package ru.maleth.mythra.dto.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.maleth.mythra.dto.FileDTO;
import ru.maleth.mythra.model.File;

import java.io.IOException;

@Component
public class FileMapper {

    public File fromMPFile(MultipartFile mpFile) throws IOException {
        return File.builder()
                .name(mpFile.getOriginalFilename())
                .content(mpFile.getBytes())
                .contentType(mpFile.getContentType())
                .build();
    }

    public FileDTO fromFile(File file) {
        FileDTO fileDTO = FileDTO.builder()
                .content(file.getContent())
                .name(file.getName())
                .contentType(file.getContentType())
                .build();

        return fileDTO;
    }

}
