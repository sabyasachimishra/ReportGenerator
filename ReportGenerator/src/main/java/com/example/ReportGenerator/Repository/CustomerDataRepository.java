package com.example.ReportGenerator.Repository;

import com.example.ReportGenerator.Entity.CustomerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataRepository extends JpaRepository<CustomerDataEntity,Long> {
}
