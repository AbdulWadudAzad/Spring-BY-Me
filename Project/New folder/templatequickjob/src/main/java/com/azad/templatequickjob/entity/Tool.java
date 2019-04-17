package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Enter Tool Name")
    @Size(min = 2,max = 50,message = "Tool name must be between 2 and 50 Characters")
    private String toolName;

    public Tool() {
    }
    public Tool(Long id) {
        this.id=id;
    }

    public Tool(String toolName) {
        this.toolName = toolName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }
}
