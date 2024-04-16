package com.example.HlebnayaLavka.controller.api;

import com.example.HlebnayaLavka.controllers.api.MenuApiDelegate;
import com.example.HlebnayaLavka.model.dto.AddItemRequest;
import com.example.HlebnayaLavka.model.dto.DeleteItemRequest;
import com.example.HlebnayaLavka.model.dto.MenuModel;
import com.example.HlebnayaLavka.model.dto.UpdateCostItemRequest;
import com.example.HlebnayaLavka.service.MenuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuApiControllerImpl implements MenuApiDelegate {

    MenuService menuService;

    @Override
    public ResponseEntity<List<MenuModel>> getMenu () {
        return ResponseEntity.ok(menuService.getMenu());
    }

    @Override
    public ResponseEntity<MenuModel> addItem (AddItemRequest addItemRequest) {
        return ResponseEntity.ok(menuService.addItem(addItemRequest));
    }

    @Override
    public ResponseEntity<MenuModel> updateCostItem (UpdateCostItemRequest updateCostItemRequest) {
        return ResponseEntity.ok(menuService.updateCostItem(updateCostItemRequest));
    }

    @Override
    public ResponseEntity<Void> deleteItem (DeleteItemRequest deleteItemRequest) {
        menuService.deleteItem(deleteItemRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<MenuModel> changeItem (MenuModel menuModel) {
        return ResponseEntity.ok(menuService.changeItem(menuModel));
    }
}
