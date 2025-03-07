package com.server.vet.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {

    public void save(MultipartFile file, String name, String codeChip);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
    ;
}