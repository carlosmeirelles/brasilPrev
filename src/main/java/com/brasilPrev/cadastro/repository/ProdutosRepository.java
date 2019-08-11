package com.brasilPrev.cadastro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import antlr.collections.List;

public interface ProdutosRepository extends CrudRepository {

    @Query("select p "
            + "from PRODUTOS p "
            + "where UPPER(p.PRODUTO) like UPPER(?1) or ")
    List search(String term);

}
