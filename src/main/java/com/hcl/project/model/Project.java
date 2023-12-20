package com.hcl.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private int id;
    private String name;
    private String Description;
    private boolean Verified;
}
