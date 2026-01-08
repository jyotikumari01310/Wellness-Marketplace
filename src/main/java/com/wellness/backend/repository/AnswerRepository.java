

package com.wellness.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellness.backend.entity.Answer;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestion_Id(Long questionId);

}
