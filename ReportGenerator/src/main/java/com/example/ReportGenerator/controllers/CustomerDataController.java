package com.example.ReportGenerator.controllers;
import com.example.ReportGenerator.Entity.CustomerDataEntity;
import com.example.ReportGenerator.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/v1/customerData")
public class CustomerDataController {

    @Autowired
    private CustomerDataService customerDataService;

    // POST endpoint to create a single customer record
    @PostMapping
    public ResponseEntity<CustomerDataEntity> createCustomer(@RequestBody CustomerDataEntity customerDataObject) {
        CustomerDataEntity savedCustomer = customerDataService.createCustomer(customerDataObject);
        return ResponseEntity.ok(savedCustomer);
    }

    // GET endpoint to fetch a customer record by customer ID
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDataEntity> getCustomerById(@PathVariable Long customerId) {
        Optional<CustomerDataEntity> customerDataObject = customerDataService.getCustomerById(customerId);
        return customerDataObject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE endpoint to delete a customer record by customer ID
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long customerId) {
        boolean isDeleted = customerDataService.deleteCustomerById(customerId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // POST endpoint to upload a file and bulk import customer data
    @PostMapping("/bulk")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            customerDataService.bulkImport(file);
            return ResponseEntity.ok("File uploaded and data imported successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }
}


