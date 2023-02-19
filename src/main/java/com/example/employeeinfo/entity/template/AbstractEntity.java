package com.example.employeeinfo.entity.template;

import com.example.employeeinfo.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private Timestamp updated_at;

    @JoinColumn(updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee UpdatedBy;
}
