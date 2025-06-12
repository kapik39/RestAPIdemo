package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.example.demo.model.CloudVendor;
import com.example.demo.service.CloudVendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.databind.ObjectWriter;

@WebMvcTest(CloudVendorController.class)
public class CloudVendorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CloudVendorService cloudVendorService;
    private CloudVendor cloudVendor1;
    private CloudVendor cloudVendor2;
    List<CloudVendor> cloudVendorsList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cloudVendor1 = new CloudVendor("T1", "Shoppe", "Hanoi", "xxxxx");
        cloudVendor2 = new CloudVendor("T2", "Tiktok", "HCM", "yyyyy");
        cloudVendorsList.add(cloudVendor1);
        cloudVendorsList.add(cloudVendor2);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testCreateCloudVendorDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendor1);

        when(cloudVendorService.createCloudVendor(cloudVendor1)).thenReturn("Success");
        this.mockMvc.perform(post("/cloudvendor/").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteCloudVendorDetails() throws Exception {
        when(cloudVendorService.deleteCloudVendor("T1")).thenReturn("Delete Success");
        this.mockMvc.perform(delete("/cloudvendor/T1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception {
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorsList);
        this.mockMvc.perform(get("/cloudvendor/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("T1")).thenReturn(cloudVendor1);
        this.mockMvc.perform(get("/cloudvendor/T1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateCloudVendorDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendor1);

        when(cloudVendorService.updateCloudVendor(cloudVendor1)).thenReturn("Update Success");
        this.mockMvc.perform(put("/cloudvendor/").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }
}
