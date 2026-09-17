package com.campuspulse.campus_pulse.repository;

import com.campuspulse.campus_pulse.model.CampusMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampusMessageRepository extends MongoRepository<CampusMessage, String> {
    List<CampusMessage> findByReceiverIgnoreCaseOrderByTimestampDesc(String receiver);
    long countByStatusIgnoreCase(String status);
    List<CampusMessage> findBySenderContainingIgnoreCaseOrReceiverContainingIgnoreCaseOrSubjectContainingIgnoreCase(String sender, String receiver, String subject);
    boolean existsByDemoKey(String demoKey);
}
