package com.example.HlebnayaLavka.controller.api;

import com.example.HlebnayaLavka.controllers.api.CustomerApiDelegate;
import com.example.HlebnayaLavka.model.dto.AddCustomerRequest;
import com.example.HlebnayaLavka.model.dto.ChangeNameCustomerRequest;
import com.example.HlebnayaLavka.model.dto.CustomerModel;
import com.example.HlebnayaLavka.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerApiControllerImpl implements CustomerApiDelegate {

    CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerModel>> getAllCustomer () {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @Override
    public ResponseEntity<CustomerModel> changeNameCustomer (ChangeNameCustomerRequest changeNameCustomerRequest) {
        return ResponseEntity.ok(customerService.changeNameCustomer(changeNameCustomerRequest));
    }

    @Override
    public ResponseEntity<CustomerModel> addCustomer (AddCustomerRequest addCustomerRequest) {
        return ResponseEntity.ok(customerService.addCustomer(addCustomerRequest));
    }
}
