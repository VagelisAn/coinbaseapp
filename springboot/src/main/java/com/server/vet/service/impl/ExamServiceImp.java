package com.server.vet.service.impl;

import com.server.vet.dto.ExamDTO;
import com.server.vet.entity.Exam;
import com.server.vet.entity.Pet;
import com.server.vet.mapper.ExamMapper;
import com.server.vet.repository.ExamRepository;
import com.server.vet.repository.PetRepository;
import com.server.vet.service.ExamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamServiceImp implements ExamService {

    @Value("${upload.path}")
    private String uploadPath;

    private final ExamRepository examsRepository;

    private final PetRepository petRepository;

    private final ExamMapper examMapper;



    public ExamDTO save(MultipartFile file, Long petId) throws IOException {
        Exam fileEntity = new Exam();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setDate(new Date());
        fileEntity.setExtension(file.getContentType().split("/")[1]);
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new EntityNotFoundException(petId.toString()));

        fileEntity.setPet(pet);

        return examMapper.mapToExamDTO(examsRepository.save(fileEntity));
    }

    public Optional<Exam> getFile(Long id) {
        return examsRepository.findById(id);
    }

    public List<Exam> getAllPetFiles(Long id) {
        return examsRepository.findByPetId(id);
    }

    @Override
    public ExamDTO save(MultipartFile file, String typeExams, Long petId) throws IOException {

        Pet pet = petRepository.findById(petId).orElseThrow(() -> new EntityNotFoundException(petId.toString()));
//System.getProperty("home.dir")+"/resources/file.jpg"
        String directoryPath = uploadPath+"/"+pet.getName()+"/"+StringUtils.cleanPath(file.getOriginalFilename())+"/"+file.getOriginalFilename();

        Exam fileEntity = new Exam();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setTypeExams(typeExams);
        fileEntity.setDate(new Date());
        fileEntity.setStoreUrl(directoryPath+"/"+StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setExtension(file.getContentType().split("/")[1]);

//        Path root = Paths.get(directoryPath);
//        if (!Files.exists(root)) {
//            try {
//                Files.createDirectories(root);
//            } catch (IOException e) {
//                throw new RuntimeException("Could not create upload folder!");
//            }
//        }
//        Files.copy(file.getInputStream(), root.resolve(StringUtils.cleanPath(file.getOriginalFilename())));
        
        fileEntity.setPet(pet);

        return examMapper.mapToExamDTO(examsRepository.save(fileEntity));
    }

//    public byte[] downloadImage(String fileName) throws IOException{
//        Optional<ProductImage> imageObject = imageRepo.findByName(fileName);
//        String fullPath = imageObject.get().getImagePath();
//        return Files.readAllBytes(new File(fullPath).toPath());
//    }

    @Override
    public Optional<ExamDTO> getExams(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ExamDTO> getAllExams() {
        return null;
    }

    @Override
    public List<ExamDTO> getExam(Long id) {
        return null;
    }

    @Override
    public List<ExamDTO> getAllExams(Long id) {
        return null;
    }

    @Override
    public void deleteExams(Long id) {
        Exam exam = examsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        examsRepository.delete(exam);
    }

}
