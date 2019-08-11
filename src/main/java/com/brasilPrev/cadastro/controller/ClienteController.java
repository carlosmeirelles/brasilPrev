package com.brasilPrev.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brasilPrev.cadastro.model.Clientes;
import com.brasilPrev.cadastro.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Clientes create(@RequestBody Clientes cliente) {
        return (Clientes) repository.save(cliente);
    }

}