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

import no.nav.adhoct.services.Doc_receiverService;
import no.nav.adhoct.models.*;

@Controller
@RequestMapping(value="/doc_receiver")
public class Doc_receiverController {

	
	@Autowired
	Doc_receiverService service;
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Doc_receiver>> getAll() {	
		
		try {
			List<Doc_receiver> lista = service.listAll();
	        return new ResponseEntity<List<Doc_receiver>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Doc_receiver>>(HttpStatus.NOT_FOUND);
	    } 
		
	}
	
}
