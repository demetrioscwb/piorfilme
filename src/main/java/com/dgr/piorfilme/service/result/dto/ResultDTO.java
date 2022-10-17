package com.dgr.piorfilme.service.result.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDTO {

	private Integer year;
	private String title;
	private String studios;
	private String producers;
	private String winner;
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStudios() {
		return studios;
	}
	public void setStudios(String studios) {
		this.studios = studios;
	}
	public String getProducers() {
		return producers;
	}
	public void setProducers(String producers) {
		this.producers = producers;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
		
	@Override
	public String toString() {
		return "ResultDTO [year=" + year + ", title=" + title + ", studios=" + studios + ", producers=" + producers
				+ ", winner=" + winner + "]";
	}
	public boolean isWinner() {
		if(winner== null || winner.equals(""))
			return false;
		if(winner.equals("yes"))
			return true;
		return false;
	}

	public List<String> listProducers() {
		
		List<String> lista = new ArrayList<>();
		
		String [] porVirgula = producers.trim().split(",");
				
		for(String strVirgula : porVirgula) {
			
			String [] porAnd = strVirgula.trim().split(" and ");
			
			for(String strAnd : porAnd)
				lista.add(strAnd.trim());
				
		}		
		
		return lista;
	}
	
	
}
