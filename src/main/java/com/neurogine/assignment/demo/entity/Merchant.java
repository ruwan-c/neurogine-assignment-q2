package com.neurogine.assignment.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString

public class Merchant {
    @Id
    @Column(name = "merchantId", nullable = false)
    private String merchantId;

    @Column
    private String name;

}
