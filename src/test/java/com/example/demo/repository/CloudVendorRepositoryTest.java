package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.CloudVendor;

@DataJpaTest
public class CloudVendorRepositoryTest {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        cloudVendor = new CloudVendor("T1", "Shoppe",
                "Hanoi", "xxxx");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    // Test case success
    @Test
    void testFindByVendorName_Found() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Shoppe");
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }

    // Test case failure
    @Test
    void testFindByVendorName_NotFound() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Tiktok");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }
}
