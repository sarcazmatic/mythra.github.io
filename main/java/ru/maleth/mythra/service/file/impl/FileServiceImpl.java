package ru.maleth.mythra.service.file.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.maleth.mythra.dto.FileDTO;
import ru.maleth.mythra.dto.mapper.FileMapper;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.File;
import ru.maleth.mythra.repo.FileRepo;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.file.FileService;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final CharacterService characterService;
    private final FileMapper fileMapper;
    private final FileRepo fileRepo;

    @Override
    public void save(MultipartFile mpFile, String userName, String charName) throws IOException {
        File file = fileMapper.fromMPFile(mpFile);
        Character character = characterService.findByUserNameAndCharName(userName, charName);
        file.setCharacter(character);
        fileRepo.save(file);
    }

    @Override
    public FileDTO findByUserNameAndCharName(String userName, String charName) {
        log.info("Ищем файл по имени юзера '{}' и персонажа '{}'", userName, charName);
        File file = fileRepo.findByCharacterCharNameAnd(userName, charName);
        return fileMapper.fromFile(file);
    }

}
