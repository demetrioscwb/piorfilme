package com.dgr.piorfilme.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dgr.piorfilme.model.WinnerModel;

public interface WinnerRepository extends JpaRepository<WinnerModel, Integer>{
	
	@Query(value="SELECT w FROM WinnerModel w JOIN w.movie m ORDER BY m.nrYear DESC")
    List<WinnerModel> findWinner();


}
