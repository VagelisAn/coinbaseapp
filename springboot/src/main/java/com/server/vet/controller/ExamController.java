package com.server.vet.controller;


import com.server.vet.dto.ExamDTO;
import com.server.vet.entity.Exam;
import com.server.vet.service.impl.ExamServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamServiceImp examinationServiceImp;

//    @PostMapping("/{name}/{id}")
//    public ResponseEntity<ExamDTO> upload(@RequestParam("file") MultipartFile file, @PathVariable("name") String name, @PathVariable("id") Long idPet) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(examinationServiceImp.save(file,name,idPet));
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//
//        }
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ByteArrayResource> getExam(@PathVariable String id) {
//        Optional<ExamDTO> fileEntityOptional = examinationServiceImp.getExams(id);
//
//        if (!fileEntityOptional.isPresent()) {
//            return ResponseEntity.notFound()
//                    .build();
//        }
//
//        ExamDTO fileEntity = fileEntityOptional.get();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
//                .contentType(MediaType.valueOf(fileEntity.getContentType()))
//                .body(new ByteArrayResource(fileEntity.getData()));
//    }
//
//    @GetMapping("/pet/{id}")
//    public List<ExamDTO> getExamsPet(@PathVariable Long id) {
//        return examinationServiceImp.getExam(id);
//    }
//
//    @GetMapping("{id}")
//    public Exam getFile(@PathVariable Long id) {
//      return examinationServiceImp.getFile(id);
//    }
//


    @PostMapping("/{typeExams}/{id}")
    public ExamDTO upload(@RequestParam("file") MultipartFile file, @PathVariable("id") Long idPet, @PathVariable("typeExams") String typeExams) throws IOException {
            return examinationServiceImp.save(file,typeExams,idPet);   }

    @GetMapping("/pet/{id}")
    public List<ExamDTO> list(@PathVariable("id") Long idPet) {
        return examinationServiceImp.getAllPetFiles(idPet)
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private ExamDTO mapToFileResponse(Exam fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileEntity.getId().toString())
                .toUriString();
        ExamDTO fileResponse = new ExamDTO();
        fileResponse.setId(fileEntity.getId());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setTypeExams(fileEntity.getTypeExams());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
//        byte[] image = productImageService.downloadImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id) {
        Optional<Exam> fileEntityOptional = examinationServiceImp.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }
        Exam fileEntity = fileEntityOptional.get();
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileEntity.getId().toString())
                .toUriString();


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .eTag(downloadURL)
                .body(fileEntity.getData());
    }

    @DeleteMapping("/{id}")
    public void deleteExamsPet(@PathVariable Long id) {
        examinationServiceImp.deleteExams(id);
    }

}
