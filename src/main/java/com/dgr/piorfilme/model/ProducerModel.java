package com.dgr.piorfilme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="producer")
public class ProducerModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false)
	private Integer idProducer;
	
	@Column(nullable = false, length = 200)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="producer_movie")
	private MovieModel movie;

	public Integer getIdProducer() {
		return idProducer;
	}

	public void setIdProducer(Integer idProducer) {
		this.idProducer = idProducer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MovieModel getMovie() {
		return movie;
	}

	public void setMovie(MovieModel movie) {
		this.movie = movie;
	}
	
}
