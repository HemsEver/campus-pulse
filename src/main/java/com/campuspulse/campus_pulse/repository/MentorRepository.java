package com.campuspulse.campus_pulse.repository;

import com.campuspulse.campus_pulse.model.Mentor;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MentorRepository extends MongoRepository<Mentor, String> {
    List<Mentor> findByExpertiseContainingIgnoreCaseOrDepartmentContainingIgnoreCase(String expertise, String department);
    List<Mentor> findByAvailabilityIgnoreCase(String availability);
    List<Mentor> findByExpertiseContainingIgnoreCaseOrMentoringAreasContainingIgnoreCase(String expertise, String mentoringArea);
    boolean existsByDemoKey(String demoKey);
}
