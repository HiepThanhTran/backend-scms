package com.fh.scms.repository;

import com.fh.scms.pojo.Customer;
import com.fh.scms.pojo.User;

import java.util.List;
import java.util.Map;

public interface CustomerRepository {

    Customer findById(Long id);

    Customer findByUser(User user);

    void save(Customer customer);

    void update(Customer customer);

    void delete(Long id);

    Long count();

    List<Customer> findAllWithFilter(Map<String, String> params);
}
