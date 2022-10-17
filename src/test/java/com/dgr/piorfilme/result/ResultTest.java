package com.dgr.piorfilme.result;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.util.Assert;

import com.dgr.piorfilme.persistence.WinnerRepository;
import com.dgr.piorfilme.service.result.ResultService;
import com.dgr.piorfilme.service.result.vo.PremiumIntervalVO;
import com.dgr.piorfilme.service.result.vo.ProducerWinnerVO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResultTest {
	
	@Autowired
	private WinnerRepository winnerRepository;
	
	@Autowired
	private ResultService resultService;
	
    @Test
    public void criarNovaAvaliacaoTest() {
    	
    	PremiumIntervalVO vo = resultService.filterWinners(winnerRepository.findWinner());
    	
    	ProducerWinnerVO min = vo.getMin().get(0);
    	ProducerWinnerVO max = vo.getMax().get(0);    	
    	
    	System.out.println(min.getInterval() + min.getProducer());
    	Assert.isTrue(min.getInterval() == 1, "Intervalo mínimo");
    	Assert.isTrue(min.getProducer().equals("Joel Silver"), "Erro ao definir producer com menor intervalo");
    	
    	System.out.println(max.getInterval()+max.getProducer());
    	Assert.isTrue(max.getInterval() == 13, "Intervalo maximo");
    	Assert.isTrue(max.getProducer().equals("Matthew Vaughn"), "Erro ao definir producer com maior intervalo");    	

    }

}
