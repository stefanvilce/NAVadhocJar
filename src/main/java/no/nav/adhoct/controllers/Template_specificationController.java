package no.nav.adhoct.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.nav.adhoct.services.Template_specificationService;
import no.nav.adhoct.models.*;

@Controller
@RequestMapping(value="/template_specification")
public class Template_specificationController {

	@Autowired
	Template_specificationService service;
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Template_specification>> getAll() {	
		
		try {
			List<Template_specification> lista = service.listAll();
	        return new ResponseEntity<List<Template_specification>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Template_specification>>(HttpStatus.NOT_FOUND);
	    } 
		
	}
	
}