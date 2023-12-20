package com.hcl.project.repository;

import org.springframework.stereotype.Repository;
import com.hcl.project.model.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryProjectRepository implements ProjectRepository {
    private final Map<Integer, Project> projects = new HashMap<>();

    {
        Project project1 = new Project(1, "Project 1", "Beginning of everything", true);
        Project project2 = new Project(2, "Project 2", "End of everything", true);

        projects.put(project1.getId(), project1);
        projects.put(project2.getId(), project2);
    }

    @Override
    public List<Project> findAll() {
        return List.copyOf(projects.values());
    }

    @Override
    public Project findById(int id) {
        return projects.get(id);
    }
}