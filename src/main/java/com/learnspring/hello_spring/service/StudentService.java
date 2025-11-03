package com.learnspring.hello_spring.service;

import com.learnspring.hello_spring.entity.Fee;
import com.learnspring.hello_spring.entity.Student;
import com.learnspring.hello_spring.repository.FeeRepository;
import com.learnspring.hello_spring.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class StudentService {
    StudentRepository studentRepository;
    FeeRepository feeRepository;

    @Transactional
    public Student createStudentAndFee(String firstName, String lastName, String email, BigDecimal feeAmount) {
        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        student = studentRepository.save(student);

        Fee fee = Fee.builder()
                .amountPaid(feeAmount)
                .student(student)
                .isPaid(true)
                .build();

        feeRepository.save(fee);

        // Commit happens here due to @Transactional
        // If any exception occurs, both student and fee creations will be rolled back
        return student;
    }
}
