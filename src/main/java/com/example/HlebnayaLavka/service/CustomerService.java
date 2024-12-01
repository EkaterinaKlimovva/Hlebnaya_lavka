package com.example.HlebnayaLavka.service;

import com.example.HlebnayaLavka.entity.Customer;
import com.example.HlebnayaLavka.model.dto.AddCustomerRequest;
import com.example.HlebnayaLavka.model.dto.ChangeNameCustomerRequest;
import com.example.HlebnayaLavka.model.dto.CustomerModel;
import com.example.HlebnayaLavka.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {

    @Autowired
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

    public CustomerModel changeNameCustomer(ChangeNameCustomerRequest changeNameCustomerRequest) {
        Customer customer = customerRepository.findById(changeNameCustomerRequest.getId()).orElseThrow();

        customer.setName(changeNameCustomerRequest.getName());

        customerRepository.save(customer);

        CustomerModel newCustomerModel = new CustomerModel();
        newCustomerModel.setId(customer.getId());
        newCustomerModel.setName(customer.getName());
        newCustomerModel.setSumCost(customer.getSumCost());

        return newCustomerModel;
    }

    public CustomerModel addCustomer (AddCustomerRequest addCustomerRequest) {
        CustomerModel customerModel = new CustomerModel();
        Customer customer = new Customer();

        customer.setName(addCustomerRequest.getName());

        customerRepository.save(customer);

        customerModel.setId(customer.getId());
        customerModel.setName(customer.getName());
        customerModel.setSumCost(customer.getSumCost());

        return customerModel;
    }
}
