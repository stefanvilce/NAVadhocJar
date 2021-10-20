package no.nav.adhoct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
public class NewJobController {


	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewJobController.class);	

	
	@GetMapping("/nyjobb")
	public String nyjobb(Model model) {		     
		model.addAttribute("title", "AdHoc App");
		model.addAttribute("subtitle", "Ny Jobb, lagre fil");		
		
		LOGGER.info("Open new form for new job.");
	    
		String textul = "<div style='border: 1px dashed #DCDCDC; text-align: center; margin: auto;'>"
	    		+ "<form action='/nyjobb' method='post' style='text-align: left; border: 1px solid #878787; padding: 25px; width: 400px; margin: auto;' enctype=\"multipart/form-data\">"
	    		+ "				UUID:<br>"
	    		+ "				<input type='text' name='uuid' value='1' enabled='false' style='width: 100%;' class='skjemaelement__input'  /><br>  <br>"
	    		+ "				File:<br>"
	    		+ "				<input type='file' name='file' style='width: 100%;' class='skjemaelement__input' /><br> <br>"
	    		+ "	    		<input type='submit' name='submit' value='Send' class='knapp'  /> <input type='reset' name='submit' value='Cancel' class='knapp knapp--fare'  />"
	    		+ "</form>";
	    
	    textul += " </div> ";
	    model.addAttribute("content", textul);
		return "index";
	}
	
	
	
	
	@RequestMapping(value = "/nyjobb", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	//@PostMapping("/nyjobb")
	public String nyjobbPost(@RequestParam("uuid") String uuid, @RequestParam(value = "file", required = false) MultipartFile file, Model model) throws IOException {		     
		model.addAttribute("title", "AdHoc App");
		model.addAttribute("subtitle", "Ny Jobb, lagre fil");
		LOGGER.info("The new job is saved.");
		String textul = " . . . saving . . . ";
	    String filename = "";
	    String extension = "";
	    byte[] fileContent = null;
	    if(file != null) {
	    	filename = file.getOriginalFilename();
	    	String[] getExtension = filename.split("\\.");
	    	extension = getExtension[getExtension.length - 1];
	    	fileContent = file.getBytes();
	    }
	    textul += " <hr> <meta http-equiv='refresh' content='1; url=/'>";
	    jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);	    
	    model.addAttribute("content", textul);
		return "index";
	}
	
	
}
