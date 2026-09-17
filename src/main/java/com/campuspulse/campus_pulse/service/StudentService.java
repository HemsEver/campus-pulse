package com.campuspulse.campus_pulse.service;

import com.campuspulse.campus_pulse.exception.ResourceNotFoundException;
import com.campuspulse.campus_pulse.model.Student;
import com.campuspulse.campus_pulse.repository.StudentRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public List<Student> findAll(String search) {
        return search == null || search.isBlank()
                ? repository.findAll()
                : repository.findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(search, search);
    }

    public Student findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
    }

    public long count() { return repository.count(); }

    public List<Student> findByInterest(String interest) {
        return repository.findByInterestsContainingIgnoreCase(interest);
    }

    public List<Student> findByDepartment(String department) {
        return repository.findByDepartmentContainingIgnoreCase(department);
    }

    public static List<StudentMatchResult> buildMatchResults(List<Student> students, List<String> desiredSkills, String excludeStudentId) {
        if (students == null || students.isEmpty() || desiredSkills == null || desiredSkills.isEmpty()) {
            return List.of();
        }

        Set<String> requiredSkills = new HashSet<>();
        for (String skill : desiredSkills) {
            if (skill != null && !skill.isBlank()) {
                requiredSkills.add(skill.trim().toLowerCase());
            }
        }

        List<StudentMatchResult> matches = new ArrayList<>();
        for (Student student : students) {
            if (student == null || student.getId() != null && excludeStudentId != null && student.getId().equals(excludeStudentId)) {
                continue;
            }
            List<String> sharedSkills = new ArrayList<>();
            if (student.getInterests() != null) {
                for (String skill : student.getInterests()) {
                    if (skill != null && requiredSkills.contains(skill.trim().toLowerCase())) {
                        sharedSkills.add(skill.trim());
                    }
                }
            }
            double matchScore = 0;
            if (student.getCgpa() != null) {
                matchScore += (student.getCgpa() / 10.0) * 50.0;
            }
            matchScore += sharedSkills.size() * 25.0;
            if (student.getYear() != null) {
                matchScore += Math.max(0, 10 - Math.abs(3 - student.getYear())) * 2.0;
            }
            matches.add(new StudentMatchResult(student, sharedSkills.size(), Math.round(matchScore * 10.0) / 10.0, sharedSkills));
        }

        matches.sort(Comparator.comparingDouble(StudentMatchResult::matchScore).reversed());
        return matches;
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public Student update(String id, Student student) {
        Student current = findById(id);
        student.setId(current.getId());
        return repository.save(student);
    }

    public void delete(String id) {
        repository.delete(findById(id));
    }

    public record StudentMatchResult(Student student, int skillMatches, double matchScore, List<String> sharedSkills) {
    }
}
