package com.hcl.project.repository;

import java.util.List;

import com.hcl.project.model.Project;

public interface ProjectRepository {
    List<Project> findAll();

    Project findById(int id);
}
