package com.example.HlebnayaLavka.service;

import com.example.HlebnayaLavka.entity.Menu;
import com.example.HlebnayaLavka.model.dto.AddItemRequest;
import com.example.HlebnayaLavka.model.dto.DeleteItemRequest;
import com.example.HlebnayaLavka.model.dto.MenuModel;
import com.example.HlebnayaLavka.model.dto.UpdateCostItemRequest;
import com.example.HlebnayaLavka.repository.MenuRepository;
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
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<MenuModel> getMenu() {
        List<Menu> menuList = menuRepository.findAll();
        List<MenuModel> menuModels = new ArrayList<>();

        for (Menu menu : menuList) {
            MenuModel menuModel = new MenuModel();

            menuModel.setId(menu.getId());
            menuModel.setName(menu.getName());
            menuModel.setCost(menu.getCost());

            menuModels.add(menuModel);
        }

        return menuModels;

//        return menuList.stream()
//                .map(menu -> {
//                    MenuModel menuModel = new MenuModel();
//                    menuModel.id(menu.getId());
//                    menuModel.name(menu.getName());
//                    menuModel.cost(menu.getCost());
//                    return menuModel;
//                        }).collect(Collectors.toList());
    }

    public MenuModel addItem(AddItemRequest addItemRequest) {
        MenuModel menuModel = new MenuModel();
        Menu menu = new Menu();

        menu.setName(addItemRequest.getName());
        menu.setCost(addItemRequest.getCost());

        menuModel.setId(menu.getId());
        menuModel.setName(menu.getName());
        menuModel.setCost(menu.getCost());

        return menuModel;
    }

    public MenuModel updateCostItem(UpdateCostItemRequest updateCostItemRequest) {
        Menu menu = menuRepository.findById(updateCostItemRequest.getId()).orElseThrow();

        menu.setCost(updateCostItemRequest.getCost());

        menuRepository.save(menu);

        MenuModel updateMenuModel = new MenuModel();

        updateMenuModel.setId(menu.getId());
        updateMenuModel.setName(menu.getName());
        updateMenuModel.setCost(menu.getCost());

        return updateMenuModel;
    }

    public void deleteItem(DeleteItemRequest deleteItemRequest) {
        Menu deleteMenu = menuRepository.findById(deleteItemRequest.getId()).orElseThrow();

        menuRepository.delete(deleteMenu);
    }

    public MenuModel changeItem(MenuModel menuModel) {
        Menu menu = menuRepository.findById(menuModel.getId()).orElseThrow();

        menu.setName(menuModel.getName());
        menu.setCost(menu.getCost());

        menuRepository.save(menu);

        MenuModel changeMenuModel = new MenuModel();

        changeMenuModel.setId(menu.getId());
        changeMenuModel.setName(menu.getName());
        changeMenuModel.setCost(menu.getCost());

        return changeMenuModel;
    }
}
