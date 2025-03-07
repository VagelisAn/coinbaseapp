package com.server.vet.service;


import com.server.vet.dto.ExamDTO;
import com.server.vet.entity.Exam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ExamService {

    public ExamDTO save(MultipartFile file, String name, Long idPet) throws IOException;

    public Optional<ExamDTO> getExams(Long id);

    public List<ExamDTO> getAllExams();

    List<ExamDTO> getExam(Long id);

    public List<ExamDTO> getAllExams(Long id);

    public void deleteExams(Long id);

    public Optional<Exam> getFile(Long id);

}