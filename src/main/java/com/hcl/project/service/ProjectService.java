package com.hcl.project.service;

import java.util.List;

import com.hcl.project.model.Project;

public interface ProjectService {
    List<Project> getAllProjects();

    Project getProjectById(int projectId);

}