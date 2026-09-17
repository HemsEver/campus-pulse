package com.campuspulse.campus_pulse.repository;

import com.campuspulse.campus_pulse.model.Announcement;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnouncementRepository extends MongoRepository<Announcement, String> {
    List<Announcement> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);
    List<Announcement> findByPriorityIgnoreCaseOrderByPublishedAtDesc(String priority);
    List<Announcement> findByOrderByPublishedAtDesc();
    List<Announcement> findByStatusIgnoreCaseOrderByPublishedAtDesc(String status);
    boolean existsByDemoKey(String demoKey);
}
