package com.dgr.piorfilme.service.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgr.piorfilme.model.MovieModel;
import com.dgr.piorfilme.model.ProducerModel;
import com.dgr.piorfilme.model.WinnerModel;
import com.dgr.piorfilme.persistence.MovieRepository;
import com.dgr.piorfilme.persistence.ProducerRepository;
import com.dgr.piorfilme.persistence.WinnerRepository;
import com.dgr.piorfilme.service.result.dto.ResultDTO;
import com.dgr.piorfilme.service.result.vo.PremiumIntervalVO;
import com.dgr.piorfilme.service.result.vo.ProducerWinnerVO;
import com.dgr.piorfilme.utils.ConverterToCSV;

@Service
public class ResultService {	
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private WinnerRepository winnerRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	public void importResults() {
		
		List<ResultDTO> results = new ConverterToCSV().chargeFile("movielist.csv", ResultDTO.class);
		
		persistirDados(results);
		
	}
	
	@Transactional
	public void persistirDados(List<ResultDTO> results) {
	    
		results.stream().forEach((r) -> {
						
			MovieModel movie = new MovieModel();
			movie.setTitle(r.getTitle());
			movie.setStudio(r.getStudios());
			movie.setNrYear(r.getYear());
			
			MovieModel m = movieRepository.findByTitleAndStudioAndNrYear(r.getTitle(), r.getStudios(), r.getYear());

			if(m == null) {
			
				movie = movieRepository.save(movie);
				
				if(r.isWinner()) {
					WinnerModel winner = new WinnerModel();
					winner.setMovie(movie);
					winnerRepository.save(winner);
				}
				
				for(String nameProducer : r.listProducers()) {
					ProducerModel producer = new ProducerModel();
					producer.setMovie(movie);
					producer.setName(nameProducer);
					producerRepository.save(producer);
				}
			
			}
			
	    });

	}
	
	public PremiumIntervalVO findWinners() {
		
		return filterWinners(winnerRepository.findWinner());
		
	}


	public PremiumIntervalVO filterWinners(List<WinnerModel> listWinner) {
		
		Map<String, List<MovieModel>> mapWinner = new HashMap<>();
		
		for(WinnerModel winner : listWinner ) {
			
			for(ProducerModel producer : winner.getMovie().getProducers()) {
				
				String chave = producer.getName();
								
				List<MovieModel> movies = new ArrayList<>(); 
				
				if(mapWinner.containsKey(chave))
					movies = mapWinner.get(chave);
				
				movies.add(winner.getMovie());
				
				mapWinner.put(chave, movies);
			}
			
		}
		
		Map<Integer, List<ProducerWinnerVO>> mapIntervalos = new HashMap<>();
		
		for(String key : mapWinner.keySet()) {
			
			List<MovieModel> movies = mapWinner.get(key);
			
			if(movies.size() > 1) {
						
				Iterator<MovieModel> itr = movies.iterator();
				
				MovieModel movieAtual = itr.next();
				
				while (itr.hasNext()) {	
					
					MovieModel movieNext = itr.next();
					
					Integer intervalo =  movieAtual.getNrYear() - movieNext.getNrYear();
					
					ProducerWinnerVO pwVO = new ProducerWinnerVO();
					pwVO.setProducer(key);
					pwVO.setPreviousWin(movieAtual.getNrYear());
					pwVO.setFollowingWin(movieNext.getNrYear());
					pwVO.setInterval(intervalo);
					
					List<ProducerWinnerVO> intervalos = new ArrayList<>();
					
					if(mapIntervalos.containsKey(intervalo))
						intervalos = mapIntervalos.get(intervalo);
					
					intervalos.add(pwVO);
					
					mapIntervalos.put(intervalo, intervalos);
					
					movieAtual = movieNext;
					
				}
				
			}
			
		}
		
		PremiumIntervalVO vo = new PremiumIntervalVO();
		
		if(mapIntervalos.isEmpty())
			return vo;
			
		SortedSet<Integer> keys = new TreeSet<>(mapIntervalos.keySet());
		vo.setMax(mapIntervalos.get(keys.last()));
		vo.setMin(mapIntervalos.get(keys.first()));
		
		return vo;
	}

}
