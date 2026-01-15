package com.jobtracker.job_application_tracker.repository;

import com.jobtracker.job_application_tracker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(String status);
    List<JobApplication> findByCompany(String company);
}