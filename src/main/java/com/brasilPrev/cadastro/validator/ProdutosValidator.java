package com.brasilPrev.cadastro.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProdutosValidator implements Validator {

    private final InventoryService inventoryService;

    @Autowired
    public ProdutosValidator(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub

    }

}
