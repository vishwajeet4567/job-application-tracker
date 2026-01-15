package com.jobtracker.job_application_tracker.service;

import com.jobtracker.job_application_tracker.model.JobApplication;
import com.jobtracker.job_application_tracker.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {
    
    @Autowired
    private JobApplicationRepository repository;
    
    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }
    
    public Optional<JobApplication> getApplicationById(Long id) {
        return repository.findById(id);
    }
    
    public JobApplication createApplication(JobApplication application) {
        return repository.save(application);
    }
    
    public JobApplication updateApplication(Long id, JobApplication updatedApp) {
        return repository.findById(id)
            .map(app -> {
                app.setCompany(updatedApp.getCompany());
                app.setPosition(updatedApp.getPosition());
                app.setStatus(updatedApp.getStatus());
                app.setAppliedDate(updatedApp.getAppliedDate());
                app.setLocation(updatedApp.getLocation());
                app.setNotes(updatedApp.getNotes());
                return repository.save(app);
            })
            .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }
    
    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }
    
    public List<JobApplication> getApplicationsByStatus(String status) {
        return repository.findByStatus(status);
    }
    
    public List<JobApplication> getApplicationsByCompany(String company) {
        return repository.findByCompany(company);
    }
}