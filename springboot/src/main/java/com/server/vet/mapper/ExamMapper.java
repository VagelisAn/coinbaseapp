package com.server.vet.mapper;

import com.server.vet.dto.ExamDTO;
import com.server.vet.dto.OwnerDTO;
import com.server.vet.entity.Exam;
import com.server.vet.entity.Owner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    @Named("mapToExamDTO")
    @Mapping(source = "pet.id", target = "petId")
    ExamDTO mapToExamDTO(Exam exam);
    @IterableMapping(qualifiedByName="mapToExamDTO")
    List<ExamDTO> mapToExamDTOs(List<Exam> list);

    @Named("mapToExam")
    Exam mapToExam(ExamDTO examDTO);
    @IterableMapping(qualifiedByName="mapToExam")
    List<Exam> mapToExams(List<ExamDTO> list);


}
