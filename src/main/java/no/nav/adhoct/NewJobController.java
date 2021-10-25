package no.nav.adhoct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
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
	    
	    int records = checkInputFile(uuid);
	    
	    if(records > 0) {
	    	textul = "There is a file for this Task already.<br>Try again with another Task ID! <hr> <meta http-equiv='refresh' content='3; url=/nyjobb'>";	    	
	    } else {
	    	LOGGER.info("The new job is saved.");
	    	int checkTask_idInTASKtbl = checkTask(uuid);
	    	if(checkTask_idInTASKtbl > 0) {
	    		jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    	} else {
	    		jdbcTemplate.update("INSERT INTO TASK(TASK_ID) VALUES (?)", uuid);
	    		jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    		LOGGER.info("New TASK_ID created. The new TASK_ID is " + uuid);
	    	}
	    }
	    
	    model.addAttribute("content", textul);
		return "index";
	}
	
	
	public Integer checkInputFile(String uuid) {
		String sql = "SELECT COUNT(*) FROM INPUT_FILE WHERE TASK_UUID='" + uuid + "'";
		List<Integer> i = jdbcTemplate.queryForList(sql, Integer.class);
		if (i.size() == 0) { 
			return 0; 
			} 
		else  {		
			LOGGER.info("We have found another  " + i.get(0) + " record(s) with the same TASK_UUID in the INPUT_FILE table.");
			return i.get(0);
		}
	}
	
	
	public Integer checkTask(String uuid) {
		String sql = "SELECT COUNT(*) FROM TASK WHERE TASK_ID='" + uuid + "'";
		List<Integer> i = jdbcTemplate.queryForList(sql, Integer.class);
		if (i.size() == 0) { 
			return 0; 
			} 
		else  {		
			LOGGER.info("We have found another  " + i.get(0) + " record(s) with the same TASK_ID in the TASK table.");
			return i.get(0);
		}
	}
	
	
}
