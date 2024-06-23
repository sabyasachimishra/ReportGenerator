package com.example.ReportGenerator.Helper;

import com.example.ReportGenerator.Entity.CustomerDataEntity;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDataObjectCreator {


    public List<CustomerDataEntity> parseData(String input) {
        List<CustomerDataEntity> customerDataList = new ArrayList<>();

        String[] inputLines = input.split("\n");

        for (String line : inputLines) {
            CustomerDataEntity customerDataObject = createCustomerObjectFromSingleLineString(line);
            customerDataList.add(customerDataObject);
        }

        return customerDataList;
    }

    public CustomerDataEntity createCustomerObjectFromSingleLineString(String inputString) {
        CustomerDataEntity customerDataObject = new CustomerDataEntity();
        String[] customerDetails = inputString.split(",");

        if (customerDetails.length != 6) {
            throw new RuntimeException("Invalid String");
        } else {
            try {
                customerDataObject.setCustomerId(Long.parseLong(customerDetails[0].trim()));
                customerDataObject.setContractId(Long.parseLong(customerDetails[1].trim()));
                customerDataObject.setGeozone(customerDetails[2].trim());
                customerDataObject.setTeamCode(customerDetails[3].trim());
                customerDataObject.setProjectCode(customerDetails[4].trim());
                customerDataObject.setBuildDuration(Long.parseLong(customerDetails[5].trim().replaceAll("s$", "")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
        return customerDataObject;
    }

}
