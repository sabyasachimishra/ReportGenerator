package com.example.ReportGenerator.service;

import com.example.ReportGenerator.Entity.CustomerDataEntity;
import com.example.ReportGenerator.Repository.CustomerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReportGenerationService {

    @Autowired
    private CustomerDataRepository customerDataRepository;

    public String generateReport() {
        List<CustomerDataEntity> customerDataList = customerDataRepository.findAll();

        StringBuilder reportBuilder = new StringBuilder();

        // Number of unique customers for each contract ID
        Map<Long, Set<Long>> uniqueCustomersByContract = getUniqueCustomersByContract(customerDataList);
        reportBuilder.append("The number of unique customers for each contract Id:\n");
        uniqueCustomersByContract.forEach((contractId, count) ->
                reportBuilder.append(contractId).append(": ").append(count).append("\n"));

        // Number of unique customers for each Geozone
        Map<String, Set<Long>> uniqueCustomersByGeoZone = getUniqueCustomersByGeoZone(customerDataList);
        reportBuilder.append("\nThe number of unique customers for each Geozone:\n");
        uniqueCustomersByGeoZone.forEach((geozone, count) ->
                reportBuilder.append(geozone).append(": ").append(count).append("\n"));

        // Average build duration for each Geozone
        Map<String, Double> averageBuildDurationByGeozone = getAverageBuildDurationByGeozone(customerDataList);
        reportBuilder.append("\nAverage build duration for each Geozone:\n");
        averageBuildDurationByGeozone.forEach((geozone, avgDuration) ->
                reportBuilder.append(geozone).append(": ").append(avgDuration).append("\n"));

        // List of unique customers for each Geozone

        reportBuilder.append("\nList of unique customers for each Geozone:\n");
        uniqueCustomersByGeoZone.forEach((geozone, customerIds) ->
                reportBuilder.append(geozone).append(": ").append(customerIds).append("\n"));

        return reportBuilder.toString();
    }

    private Map<Long, Set<Long>> getUniqueCustomersByContract(List<CustomerDataEntity> customerDataList){
        Map<Long, Set<Long>> uniqueCustomersByContract = customerDataList.stream()
                .collect(Collectors.groupingBy(CustomerDataEntity::getContractId,
                        Collectors.mapping(CustomerDataEntity::getCustomerId, Collectors.toSet())));

        return uniqueCustomersByContract;
    }

    private Map<String, Set<Long>> getUniqueCustomersByGeoZone(List<CustomerDataEntity> customerDataList){
        Map<String, Set<Long>> uniqueCustomersByGeozone = customerDataList.stream()
                .collect(Collectors.groupingBy(CustomerDataEntity::getGeozone,
                        Collectors.mapping(CustomerDataEntity::getCustomerId, Collectors.toSet())));

        return uniqueCustomersByGeozone;
    }

    private Map<String, Double> getAverageBuildDurationByGeozone(List<CustomerDataEntity> customerDataList){
        Map<String, Double> averageBuildDurationByGeozone = customerDataList.stream()
                .collect(Collectors.groupingBy(CustomerDataEntity::getGeozone,
                        Collectors.averagingDouble(data -> {
                            return Double.valueOf(data.getBuildDuration());
                        })));

        return averageBuildDurationByGeozone;

    }
}
