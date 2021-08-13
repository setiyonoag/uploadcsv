package com.pro5.uploadcsv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pro5.uploadcsv.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
}