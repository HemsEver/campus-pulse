package com.campuspulse.campus_pulse.repository;

import com.campuspulse.campus_pulse.model.Student;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(String name, String department);
    List<Student> findByDepartmentContainingIgnoreCase(String department);
    List<Student> findByInterestsContainingIgnoreCase(String interest);
    boolean existsByDemoKey(String demoKey);
}
