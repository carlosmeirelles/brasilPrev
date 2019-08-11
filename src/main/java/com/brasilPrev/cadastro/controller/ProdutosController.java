package com.brasilPrev.cadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brasilPrev.cadastro.model.Produtos;
import com.brasilPrev.cadastro.repository.ProdutosRepository;
import com.brasilPrev.cadastro.validator.ProdutosValidator;

@RestController
@RequestMapping("/produtos")

public class ProdutosController {


        private final ProdutosRepository repository;
        private final ProdutosValidator validator;
        
        @Autowired
        public ProdutosController(ProdutosRepository repository, ProdutosValidator validator) {
            this.repository = repository;
            this.validator = validator;
        }
        @InitBinder
        protected void initBinder(WebDataBinder binder) {
            binder.addValidators(validator);
        }
        @RequestMapping(method = RequestMethod.GET)
        public Iterable findAll() {
            return repository.findAll();
        }
        @RequestMapping(method = RequestMethod.POST)
        public Produtos create(@RequestBody @Valid Produtos detail) {
            return (Produtos) repository.save(detail);
        }

}
