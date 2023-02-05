package com.neurogine.assignment.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class ApplicationLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String operation;
    private String endPoint;
    private String method;
    @Column(columnDefinition = "VARCHAR(2500)")
    private String params;
    private String requestTime;

}