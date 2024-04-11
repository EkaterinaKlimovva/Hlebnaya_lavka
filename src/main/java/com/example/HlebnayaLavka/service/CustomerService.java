package com.example.HlebnayaLavka.service;

import com.example.HlebnayaLavka.entity.Customer;
import com.example.HlebnayaLavka.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.example.HlebnayaLavka.model.dto.CustomerModel;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {

    CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerModel> customerModels = new ArrayList<>();

        for (Customer customer: customers) {
            CustomerModel customerModel = new CustomerModel();

            customerModel.setId(customer.getId());
            customerModel.setName(customer.getName());
            customerModel.setSumCost(customer.getSumCost());

            customerModels.add(customerModel);
        }
        return customerModels;
    }
}
