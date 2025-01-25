package ru.maleth.mythra.controller.file;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.maleth.mythra.dto.FileDTO;
import ru.maleth.mythra.service.file.FileService;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Data
@Slf4j
@RequestMapping("/file/{userName}/{charName}")
public class FileController {

    private final FileService fileService;
    private static final String PAGE = "directToPage";
    private final Gson gson = new Gson();

    @PostMapping(value = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@PathVariable String userName,
                             @PathVariable String charName,
                             @RequestPart(value = "file") MultipartFile mpFile,
                             HttpServletRequest request) throws IOException {
        log.info("Получен запрос на загрузку файла для персонажа '{}' с названием '{}'. Эндпоинт {}. Метод {}",
                charName, mpFile.getOriginalFilename(), request.getRequestURL(), request.getMethod());
        fileService.save(mpFile, userName, charName);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<byte[]> getFile(@PathVariable String userName,
                                          @PathVariable String charName) {
        log.info("Получен запрос на получение файла персонажа '{}'", charName);
        FileDTO fileDTO = fileService.findByUserNameAndCharName(userName, charName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDTO.getContentType()))
                .body(fileDTO.getContent());
    }

}