package com.example.SpringLearnH2db.Repositories;
import org.springframework.stereotype.Repository;

import com.example.SpringLearnH2db.Entitys.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

}
