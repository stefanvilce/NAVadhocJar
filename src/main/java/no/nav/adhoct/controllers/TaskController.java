package no.nav.adhoct.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.nav.adhoct.services.TaskService;
import no.nav.adhoct.models.*;

@Controller
@RequestMapping(value="/task")
public class TaskController {

	
	@Autowired
	TaskService service;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Task>> getAll() {	
		try {
			List<Task> lista = service.listAll();
	        return new ResponseEntity<List<Task>>(lista, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
	    }
	}	
	
	
	@PostMapping("/update")
	public @ResponseBody ResponseEntity<String> update(@RequestParam(name = "task_uuid") String task_uuid, @RequestParam(name = "max_doc_split") int max_doc_split, @RequestParam(name = "token") String token, HttpServletRequest request) {	
		String jsonString = "{ \"status\": \"success\", \"data\":{\"task_uuid\":" + task_uuid + "}}";
		LOGGER.info("UPDATE MAX_DOC_SPLIT for TASK_UUID=" + task_uuid + "; MAX_DOC_SPLIT=" + max_doc_split);
		HttpSession session=request.getSession();
		String tokenFromSession = (String) session.getAttribute("token");
		try {
			if(tokenFromSession.equals(token)) {
				String rez = service.update(task_uuid, max_doc_split);
				LOGGER.info("UPDATING task max_doc_split ... " + rez);
				JSONObject jsonObject = new JSONObject(jsonString);
	            ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
	            return re;
			} else {
				LOGGER.error("Trying to get the REST API without being loggedin. The SESSIONS are not active.");
		    	return new ResponseEntity<String>("You have to login.", HttpStatus.NOT_FOUND);
			}
	    } catch (JSONException ex) {
	    	LOGGER.error("ERROR UPDATING MAX_DOC_SPLIT ?", jsonString);
	    	return new ResponseEntity<String>("Done", HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@PostMapping("/updatedeadline")
	public @ResponseBody ResponseEntity<String> updateDeadline(@RequestParam(name = "task_uuid") String task_uuid, @RequestParam(name = "deadline") String deadline, @RequestParam(name = "token") String token, HttpServletRequest request) throws ParseException {	
		String jsonString = "{ \"status\": \"success\", \"data\":{\"task_uuid\":" + task_uuid + "}}";
		LOGGER.info("UPDATE DEADLINE for TASK_UUID=" + task_uuid + "; DEADLINE=" + deadline);
		HttpSession session=request.getSession();
		String tokenFromSession = (String) session.getAttribute("token");
		try {
			if(tokenFromSession.equals(token)) {
				String rez = service.updateDeadline(task_uuid, deadline);
				LOGGER.info("UPDATING task DEADLINE ... " + rez);
				JSONObject jsonObject = new JSONObject(jsonString);
	            ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
	            return re;
			} else {
				LOGGER.error("Trying to get the REST API without being loggedin. The SESSIONS are not active.");
		    	return new ResponseEntity<String>("You have to login.", HttpStatus.NOT_FOUND);
			}
	    } catch (JSONException ex) {
	    	LOGGER.error("ERROR UPDATING DEADLINE TASK ?", jsonString);
	    	return new ResponseEntity<String>("Done", HttpStatus.NOT_FOUND);
	    }
	}
	
}
