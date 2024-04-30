package com.cederj.uff.tcc.stockmaster.services;

import com.cederj.uff.tcc.stockmaster.models.GenericModel;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;

import java.util.List;

public interface GenericService<TModel extends GenericModel>{
    GenericRepository<TModel> getRepository();

    default TModel save(TModel obj) throws Exception{
        return getRepository().save(obj);
    }

    default List<TModel> findAll() {
        return getRepository().findAll();
    }

    default TModel findById(Long id) {
        if (id == null || id.toString().isEmpty()) {
            throw new RuntimeException("Preencha o campo id");
        }
        if (!getRepository().existsById(id)) {
            throw new RuntimeException("Não existe registros com esse id");
        }

       return getRepository().findById(id).orElseThrow(() -> new RuntimeException("O registro não foi encontrado no sistema"));
    }

    default void deleteById(Long id)  {
        if(!getRepository().existsById(id)) {
            throw new RuntimeException("Não existe registro com este id");
        }
        getRepository().deleteById(id);
    }
}
