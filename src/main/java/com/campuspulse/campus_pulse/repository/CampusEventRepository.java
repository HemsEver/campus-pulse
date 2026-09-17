package com.campuspulse.campus_pulse.repository;

import com.campuspulse.campus_pulse.model.CampusEvent;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampusEventRepository extends MongoRepository<CampusEvent, String> {
    List<CampusEvent> findByEventDateAfterOrderByEventDateAsc(LocalDateTime date);
    List<CampusEvent> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);
    List<CampusEvent> findByCategoryContainingIgnoreCaseAndEventDateAfterOrderByEventDateAsc(String category, LocalDateTime date);
    boolean existsByDemoKey(String demoKey);
}
