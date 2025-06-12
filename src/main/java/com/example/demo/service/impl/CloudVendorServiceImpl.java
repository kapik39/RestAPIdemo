package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.CloudVendorNotFoundException;
import com.example.demo.model.CloudVendor;
import com.example.demo.repository.CloudVendorRepository;
import com.example.demo.service.CloudVendorService;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Success";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Update Success";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Delete Success";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        if (cloudVendorRepository.findById(cloudVendorId).isEmpty()) {
            throw new CloudVendorNotFoundException("Request Cloud Vendor does not exits!");
        }
        return cloudVendorRepository.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }

    @Override
    public List<CloudVendor> getByVendorName(String vendorName) {
        return cloudVendorRepository.findByVendorName(vendorName);
    }
}
