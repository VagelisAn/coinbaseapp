package com.server.vet.repository;



import com.server.vet.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{
//    @Transactional
    List<Exam> findByPetId(Long id);
    Optional<Exam> findById(Long id);
}
