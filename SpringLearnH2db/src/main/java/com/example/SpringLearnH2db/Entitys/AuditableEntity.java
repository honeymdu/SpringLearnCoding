package com.example.SpringLearnH2db.Entitys;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
@Getter
@Setter
@EntityListeners(value = { AuditingEntityListener.class })
public class AuditableEntity {


    @CreatedDate
    private LocalDateTime createddate;

    @LastModifiedDate
    private LocalDateTime updateddate;

    @CreatedBy
    private String createdBy;
    
    @LastModifiedBy
    private String updatedBy;

}
