package com.example.HlebnayaLavka.controller.api;

import com.example.HlebnayaLavka.controllers.api.PurchaseApiDelegate;
import com.example.HlebnayaLavka.model.dto.AddPurchaseRequest;
import com.example.HlebnayaLavka.model.dto.GetFilterPurchaseRequest;
import com.example.HlebnayaLavka.model.dto.PurchaseModel;
import com.example.HlebnayaLavka.service.PurchaseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PurchaseApiControllerImpl implements PurchaseApiDelegate {

    PurchaseService purchaseService;

    @Override
    public ResponseEntity<PurchaseModel> addPurchase (AddPurchaseRequest addPurchaseRequest) {
        return ResponseEntity.ok(purchaseService.addPurchase(addPurchaseRequest));
    }

    @Override
    public ResponseEntity<List<PurchaseModel>> getFilterPurchase (GetFilterPurchaseRequest getFilterPurchaseRequest) {
        return ResponseEntity.ok(purchaseService.getFilterPurchase(getFilterPurchaseRequest));
    }
}
