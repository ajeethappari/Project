package com.hcl.project.controller;

import com.hcl.project.model.Project;
import com.hcl.project.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjects() {
        // Arrange
        List<Project> projects = Arrays.asList(new Project(1, "Project 1", "Beginning of everything", true),
                new Project(2, "Project 2", "End of everything", false));
        when(projectService.getAllProjects()).thenReturn(projects);

        // Act
        ResponseEntity<?> response = projectController.getAllProjects();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projects, response.getBody());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void testGetProjectById_ValidId() {
        // Arrange
        int projectId = 1;
        Project project = new Project(projectId, "Project 1", "Beginning of everything", true);
        when(projectService.getProjectById(projectId)).thenReturn(project);

        // Act
        ResponseEntity<?> response = projectController.getProjectById(projectId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
        verify(projectService, times(1)).getProjectById(projectId);
    }

    @Test
    void testGetProjectById_InvalidId() {
        // Arrange
        int projectId = -1;

        // Act
        ResponseEntity<?> response = projectController.getProjectById(projectId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid project id", response.getBody());
        verify(projectService, never()).getProjectById(projectId);
    }

    @Test
    void testGetProjectById_ProjectNotFound() {
        // Arrange
        int projectId = 1;
        when(projectService.getProjectById(projectId)).thenReturn(null);

        // Act
        ResponseEntity<?> response = projectController.getProjectById(projectId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Project with id " + projectId + " not found", response.getBody());
        verify(projectService, times(1)).getProjectById(projectId);
    }
}