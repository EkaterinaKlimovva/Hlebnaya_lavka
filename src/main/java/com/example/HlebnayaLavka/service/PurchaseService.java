package com.example.HlebnayaLavka.service;

import com.example.HlebnayaLavka.constants.ServiceConstants;
import com.example.HlebnayaLavka.controllers.api.PurchaseApiDelegate;
import com.example.HlebnayaLavka.entity.Customer;
import com.example.HlebnayaLavka.entity.Menu;
import com.example.HlebnayaLavka.entity.Purchase;
import com.example.HlebnayaLavka.model.dto.AddPurchaseRequest;
import com.example.HlebnayaLavka.model.dto.GetFilterPurchaseRequest;
import com.example.HlebnayaLavka.model.dto.PurchaseModel;
import com.example.HlebnayaLavka.repository.CustomerRepository;
import com.example.HlebnayaLavka.repository.MenuRepository;
import com.example.HlebnayaLavka.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PurchaseService implements PurchaseApiDelegate {

    private final PurchaseRepository purchaseRepository;

    private final CustomerRepository customerRepository;

    private final MenuRepository menuRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, CustomerRepository customerRepository, MenuRepository menuRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
    }

    public PurchaseModel addPurchase (AddPurchaseRequest addPurchaseRequest) {
        Purchase purchase = new Purchase();

        Customer customer = customerRepository.findById(addPurchaseRequest.getCustomerId()).orElseThrow();
        Menu item = menuRepository.findById(addPurchaseRequest.getItemId()).orElseThrow();

        purchase.setCustomer(customer);
        purchase.setMenu(item);
        purchase.setCount(addPurchaseRequest.getCount());
        purchase.setDatePurchase(LocalDate.now());

        double sumCost; /* = customer.getSumCost() >= ServiceConstants.VIP_SUM_COST
                ? item.getCost() * addPurchaseRequest.getCount() * ServiceConstants.SALE_COEFFICIENT
                : item.getCost() * addPurchaseRequest.getCount(); */

        if (customer.getSumCost() >= ServiceConstants.VIP_SUM_COST)
            sumCost = item.getCost() * addPurchaseRequest.getCount() * ServiceConstants.SALE_COEFFICIENT;
        else
            sumCost = item.getCost() * addPurchaseRequest.getCount();

        purchase.setSumCost(sumCost);
        customer.setSumCost(customer.getSumCost() + purchase.getSumCost());

        Purchase purchaseItem = purchaseRepository.save(purchase);

        PurchaseModel newPurchase = new PurchaseModel();

        newPurchase.setId(purchaseItem.getId());
        newPurchase.setCustomerId(purchaseItem.getCustomer().getId());
        newPurchase.setItemId(purchaseItem.getMenu().getId());
        newPurchase.setCount(purchaseItem.getCount());
        newPurchase.setSumCost(purchaseItem.getSumCost());
        newPurchase.setDatePurchase(purchaseItem.getDatePurchase());

        return newPurchase;
    }


    public List<PurchaseModel> getFilterPurchase (GetFilterPurchaseRequest getFilterPurchaseRequest) {
        List<Purchase> purchases = purchaseRepository.findAllByFilter(getFilterPurchaseRequest.getStartDate(), getFilterPurchaseRequest.getEndDate());

        List<PurchaseModel> purchaseModels = new ArrayList<>();

        for (Purchase purchase : purchases) {
            PurchaseModel purchaseModel = new PurchaseModel();

            purchaseModel.setId(purchase.getId());
            purchaseModel.setCustomerId(purchase.getCustomer().getId());
            purchaseModel.setItemId(purchase.getMenu().getId());
            purchaseModel.setCount(purchase.getCount());
            purchaseModel.setSumCost(purchase.getSumCost());
            purchaseModel.setDatePurchase(purchase.getDatePurchase());

            purchaseModels.add(purchaseModel);
        }

        return purchaseModels;
    }
}
