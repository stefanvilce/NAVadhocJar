package no.nav.adhoct.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import no.nav.adhoct.services.Input_fileService;
import no.nav.adhoct.models.*;

@Controller
@RequestMapping(value="/input_file")
public class Input_fileController {

	
	@Autowired
	Input_fileService service;
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Input_file>> getAll() {	
		
		try {
			List<Input_file> lista = service.listAll();
	        return new ResponseEntity<List<Input_file>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Input_file>>(HttpStatus.NOT_FOUND);
	    } 
		
	}
	
}
