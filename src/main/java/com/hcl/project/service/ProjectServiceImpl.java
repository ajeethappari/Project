package com.hcl.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.project.exception.ResourceNotFoundException;
import com.hcl.project.model.Project;
import com.hcl.project.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(int id) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new ResourceNotFoundException("Project with id " + id + " not found");
        }
        return project;
    }
}
