package com.stefanosgersch.traineeship.service.student;

import com.stefanosgersch.traineeship.domain.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    void applyForTraineeship(Long studentId);

}
