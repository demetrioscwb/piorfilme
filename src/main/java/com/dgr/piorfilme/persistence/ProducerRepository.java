package com.dgr.piorfilme.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgr.piorfilme.model.ProducerModel;

public interface ProducerRepository extends JpaRepository<ProducerModel, Integer>{
	

}
