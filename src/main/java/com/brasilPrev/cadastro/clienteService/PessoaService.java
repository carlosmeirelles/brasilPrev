package com.brasilPrev.cadastro.clienteService;

import org.apache.catalina.core.ApplicationContext;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.brasilPrev.cadastro.model.Clientes;
import com.brasilPrev.cadastro.model.Pedidos;
import com.brasilPrev.cadastro.repository.ProdutosRepository;

public class PessoaService {

    @Autowired
    Clientes clientes;

    @Autowired
    Pedidos pedidos;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ApplicationContext ctx = (ApplicationContext) SpringApplication.run(Main.class);
        Clientes cliente = new Clientes();
        cliente.setNomeCliente("Carlos");
        cliente.setBairro("Mooca");
        cliente.setCidade("São Paulo");
        ProdutosRepository repository = ((BeanFactory) ctx).getBean(ProdutosRepository.class);
        repository.save(cliente);
    }

}
