package no.nav.adhoct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class NewJobController {

	

	private static final Logger LOGGER = LoggerFactory.getLogger(NewJobController.class);
	
	@GetMapping("/nyjobb")
	public String index() {
		     
		LOGGER.info("Open new form for new job.");
	    
		String textul = "<div style='font-family: Arial; color: #344596;'><H1 style='text-align: center; '>AdHoc App</H1><h2 style='text-align: center;color: #DA7643;'>Registrer ny jobb</h2><hr>";
		textul += "<div style='border: 1px dashed #DCDCDC; text-align: center; margin: auto;'>"
	    		+ "<form action='/nyjobb' method='post' style='text-align: left; border: 1px solid #878787; padding: 5px; width: 300px; margin: auto;'>"
	    		+ "				UUID:<br>"
	    		+ "				<input type='text' name='uuid' value='1' enabled='false' style='width: 100%;'  /><br>"
	    		+ "				File:<br>"
	    		+ "				<input type='file' name='file' style='width: 100%;' /><br><br>"
	    		+ "	    		<input type='submit' name='submit' value='Send' style='width: 100%;'  />"
	    		+ "</form>";
	    
	    textul += " </div></div>";	    
		return textul;
	}
	
}
