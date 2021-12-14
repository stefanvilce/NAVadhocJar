package no.nav.adhoct.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import no.nav.adhoct.services.Doc_receiverService;
import no.nav.adhoct.models.*;

@Controller
@RequestMapping(value="/doc_receiver")
public class Doc_receiverController {

	
	@Autowired
	Doc_receiverService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Doc_receiverController.class);	
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Doc_receiver>> getAll() {	
		LOGGER.info("GET all Documents from DOC_RECEIVER table.");
		try {
			List<Doc_receiver> lista = service.listAll();
	        return new ResponseEntity<List<Doc_receiver>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Doc_receiver>>(HttpStatus.NOT_FOUND);
	    } 
		
	}
	
	
	@GetMapping("/taskid/{task_id}")
	public @ResponseBody ResponseEntity<List<Doc_receiver>> listByTaskId(@PathVariable String task_id) {	
		LOGGER.info("Getting Documents from DOC_RECEIVER table for TASK_UUID=" + task_id);
		try {
			List<Doc_receiver> lista = service.listByTaskId(task_id);
	        return new ResponseEntity<List<Doc_receiver>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Doc_receiver>>(HttpStatus.NOT_FOUND);
	    }		
	}
		
	
	
	@GetMapping("/nrdocspertaskid/{task_id}")
	public @ResponseBody ResponseEntity<?> nrOfDocsByUUID(@PathVariable String task_id) {	
		LOGGER.info("Getting No. of Documents from DOC_RECEIVER table for TASK_UUID=" + task_id);
		try {
			Integer nrDocsPerUUID = service.nrOfDocsByUUID(task_id);			
			
			String jsonResponse = "{ \"status\": \"success\", \n "
					+ "\"data\": { \n"
					+ "\"founddocsperuuid\": \"" + nrDocsPerUUID + "\", \n "
							+ "\"uuid\": \"" + task_id + "\" "
							+ "\n} "
							+ "\n}";
			JSONObject jsonObject = new JSONObject(jsonResponse);
            ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
	        return re;
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Doc_receiver>>(HttpStatus.NOT_FOUND);
	    }	
	}
	
	
	
	@GetMapping("/uuid/perpage/{uuid}/{page}/{per_page}")
	public @ResponseBody ResponseEntity<?> listPerPageByTaskId(@PathVariable String uuid, @PathVariable Integer page, @PathVariable Integer per_page) {	
		LOGGER.info("Getting the Documents from DOC_RECEIVER table for TASK_UUID=" + uuid + " / per page");		
		try {
			List<Doc_receiver> lista = service.listPerPageByTaskId(uuid, page, per_page);
	        return new ResponseEntity<List<Doc_receiver>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Doc_receiver>>(HttpStatus.NOT_FOUND);
	    }
		
	}
	
	
}
