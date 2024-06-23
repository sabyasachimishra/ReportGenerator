package com.example.ReportGenerator.service;

import com.example.ReportGenerator.Entity.CustomerDataEntity;
import com.example.ReportGenerator.Helper.CustomerDataObjectCreator;
import com.example.ReportGenerator.Repository.CustomerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    @Autowired
    private CustomerDataRepository customerDataRepository;

    @Autowired
    private CustomerDataObjectCreator customerDataObjectCreator;


    @Override
    public CustomerDataEntity createCustomer(CustomerDataEntity customerDataObject) {
        return customerDataRepository.save(customerDataObject);
    }

    @Override
    public Optional<CustomerDataEntity> getCustomerById(Long customerId) {
        return customerDataRepository.findById(customerId);
    }

    @Override
    public boolean deleteCustomerById(Long customerId) {
        if (customerDataRepository.existsById(customerId)) {
            customerDataRepository.deleteById(customerId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void bulkImport(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("The file you have passed is empty. Please check and reuplod");
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String fileContent = br.lines().collect(Collectors.joining("\n"));
            List<CustomerDataEntity> customerList = customerDataObjectCreator.parseData(fileContent);
            customerDataRepository.saveAll(customerList);

        } catch (Exception e) {
            throw new Exception("Error in file Processing", e);
        }

    }
}
