package com.example.ReportGenerator.service;

import com.example.ReportGenerator.Entity.CustomerDataEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface CustomerDataService {

    CustomerDataEntity createCustomer(CustomerDataEntity customerDataObject);
    Optional<CustomerDataEntity> getCustomerById(Long customerId);
    boolean deleteCustomerById(Long customerId);
    void bulkImport(MultipartFile file) throws Exception;
}
