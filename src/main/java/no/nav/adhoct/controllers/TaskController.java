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

import no.nav.adhoct.services.TaskService;
import no.nav.adhoct.models.*;

@Controller
@RequestMapping(value="/task")
public class TaskController {

	
	@Autowired
	TaskService service;
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Task>> getAll() {	
		
		try {
			List<Task> lista = service.listAll();
	        return new ResponseEntity<List<Task>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
	    } 
		
	}
	
}
