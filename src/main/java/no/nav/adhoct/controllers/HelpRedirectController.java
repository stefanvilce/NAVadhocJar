package no.nav.adhoct.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpRedirectController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelpRedirectController.class);
	
	@GetMapping("/tasks")
	public String tasksRedirect() {		     
		LOGGER.info("See the tasks list on React interface.");	    
		return "<meta http-equiv=\"refresh\" content=\"0; URL=/\" />";
	}
	
}
