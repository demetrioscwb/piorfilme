package com.dgr.piorfilme.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgr.piorfilme.model.MovieModel;

public interface MovieRepository extends JpaRepository<MovieModel, Integer>{

	MovieModel findByTitleAndStudioAndNrYear(String title, String studios, Integer year);
	

}
