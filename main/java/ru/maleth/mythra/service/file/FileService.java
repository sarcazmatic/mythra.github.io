package ru.maleth.mythra.service.file;

import org.springframework.web.multipart.MultipartFile;
import ru.maleth.mythra.dto.FileDTO;

import java.io.IOException;

public interface FileService {

    void save(MultipartFile mpFile, String userName, String charName) throws IOException;

    FileDTO findByUserNameAndCharName(String userName, String charName);


}
