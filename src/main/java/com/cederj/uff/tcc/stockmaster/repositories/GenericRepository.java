package com.cederj.uff.tcc.stockmaster.repositories;

import com.cederj.uff.tcc.stockmaster.models.GenericModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<TModel extends GenericModel> extends JpaRepository<TModel, Long> {
}
