package com.dgr.piorfilme.service.result.vo;

import java.util.List;

public class PremiumIntervalVO {

	private List<ProducerWinnerVO> min;
	private List<ProducerWinnerVO> max;
	
	public List<ProducerWinnerVO> getMin() {
		return min;
	}
	public void setMin(List<ProducerWinnerVO> min) {
		this.min = min;
	}
	public List<ProducerWinnerVO> getMax() {
		return max;
	}
	public void setMax(List<ProducerWinnerVO> max) {
		this.max = max;
	}
	
	

}
