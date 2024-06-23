package com.example.ReportGenerator.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customerdetails")
public class CustomerDataEntity {

    @Id
    private Long customerId;
    private Long contractId;
    private String geozone;
    private String teamCode;
    private String projectCode;
    private Long buildDuration;

}
