package com.hcl.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.project.model.Project;
import com.hcl.project.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable(value = "id") int projectId) {
        // check if id is not valid
        if (projectId <= 0) {
            return ResponseEntity.badRequest().body("Invalid project id");
        }
        Project project = projectService.getProjectById(projectId);

        if (project == null) {
            return ResponseEntity.ok().body("Project with id " + projectId + " not found");
        }
        return ResponseEntity.ok().body(project);
    }
}