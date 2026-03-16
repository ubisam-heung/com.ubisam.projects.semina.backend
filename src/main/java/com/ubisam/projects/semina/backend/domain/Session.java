package com.ubisam.projects.semina.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "examples_sessions")
@Data
public class Session {
    
    @Id
    private String principal;
    private String state;
    private Long timestamp;

}
