package com.dgr.piorfilme.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ConverterToCSV {
	
	public <T> List<T> chargeFile(String endArquivo, Class<? extends T> type) {
		
		try {		
				
		    final CsvToBean<T> beans = 
		    	    new CsvToBeanBuilder<T>(new FileReader(endArquivo))
		    	        .withType(type)
		    	        .withIgnoreQuotations(true)
		    	        .withThrowExceptions(false)
		    	        .withSeparator(';')
		    	        .build();

		    final List<T> results = beans.parse();

    	    beans.getCapturedExceptions().stream().forEach((exception) -> {
    	    	throw new CsvImportException("Não foi possível importar todas as linhas");
    	    });
    	    
    	    return results;
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
		
	}

}
