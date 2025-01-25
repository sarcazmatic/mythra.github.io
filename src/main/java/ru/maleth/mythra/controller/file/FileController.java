package ru.maleth.mythra.controller.file;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import ru.maleth.mythra.dto.CharacterFullDTO;
import ru.maleth.mythra.dto.FileDTO;
import ru.maleth.mythra.dto.IncomingFileDTO;
import ru.maleth.mythra.dto.NewCharacterDTO;
import ru.maleth.mythra.dto.NewCharacterFullDTO;
import ru.maleth.mythra.service.character.CharacterCreationService;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.file.FileService;
import ru.maleth.mythra.service.levelup.LevelUpService;
import ru.maleth.mythra.service.sheet.CharsheetService;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Controller
@Data
@Slf4j
@RequestMapping("/file/{name}/{charName}")
public class FileController {

    private final FileService fileService;
    private static final String PAGE = "directToPage";

    @PostMapping(value = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@PathVariable(name = "name") String userName,
                           @PathVariable String charName,
                           @RequestParam(name = "file") MultipartFile mpFile,
                           HttpServletRequest request) throws IOException {
        log.info("Получен запрос на загрузку файла для персонажа '{}'. Эндпоинт {}. Метод {}",
                charName, request.getRequestURL(), request.getMethod());
        fileService.save(mpFile, userName, charName);
        System.out.println(mpFile.getOriginalFilename());
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<byte[]> getFile(@PathVariable(name = "name") String userName,
                                          @PathVariable String charName) {
        log.info("Получен запрос на получение файла персонажа '{}'", charName);
        FileDTO fileDTO = fileService.findByUserNameAndCharName(userName, charName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDTO.getContentType()))
                .body(fileDTO.getContent());
    }

}