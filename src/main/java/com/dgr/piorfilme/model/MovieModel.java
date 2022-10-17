package com.dgr.piorfilme.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class MovieModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false)
	private Integer idMovie;
	
	@Column(nullable = false, length = 200)
	private String title;
	
	@Column(nullable = false)
	private Integer nrYear;
	
	@Column(nullable = false, length = 200)
	private String studio;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="movie")
	private List<ProducerModel> producers;

	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNrYear() {
		return nrYear;
	}

	public void setNrYear(Integer nrYear) {
		this.nrYear = nrYear;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public List<ProducerModel> getProducers() {
		return producers;
	}

	public void setProducers(List<ProducerModel> producers) {
		this.producers = producers;
	}

}
