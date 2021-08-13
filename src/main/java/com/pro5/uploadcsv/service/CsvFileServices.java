package com.pro5.uploadcsv.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro5.uploadcsv.model.Customer;
import com.pro5.uploadcsv.repository.CustomerRepository;
import com.pro5.uploadcsv.utils.ApacheCommonsCsvUtil;


@Service
public class CsvFileServices {
	
	@Autowired
	CustomerRepository customerRepository;

	// Store Csv File's data to database
	public void store(InputStream file) {
		try {
			// Using ApacheCommons Csv Utils to parse CSV file
			List<Customer> lstCustomers = ApacheCommonsCsvUtil.parseCsvFile(file);
			
			// Using OpenCSV Utils to parse CSV file
			// List<Customer> lstCustomers = OpenCsvUtil.parseCsvFile(file);
			
			// Save customers to database
			customerRepository.saveAll(lstCustomers);
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	// Load Data to CSV File
    public void loadFile(Writer writer) throws IOException {
    	try {
        	List<Customer> customers = (List<Customer>) customerRepository.findAll();
        	
        	// Using ApacheCommons Csv Utils to write Customer List objects to a Writer
             ApacheCommonsCsvUtil.customersToCsv(writer, customers);
        	
        	// Using Open CSV Utils to write Customer List objects to a Writer
        	// OpenCsvUtil.customersToCsv(writer, customers);    		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
    }
}