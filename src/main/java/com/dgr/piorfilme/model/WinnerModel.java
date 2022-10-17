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
@Table(name="winner")
public class WinnerModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false)
	private Integer idWinner;	

	@ManyToOne
	@JoinColumn(name="idMovie", nullable=false)
	private MovieModel movie;

	public Integer getIdWinner() {
		return idWinner;
	}

	public void setIdWinner(Integer idWinner) {
		this.idWinner = idWinner;
	}

	public MovieModel getMovie() {
		return movie;
	}

	public void setMovie(MovieModel movie) {
		this.movie = movie;
	}

}
