package no.nav.adhoct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
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
	    		+ "				Valgt fil:<br>"
	    		+ "				<input type='file' name='file' style='width: 100%;' class='skjemaelement__input' /><br> <br>"
	    		+ "	    		<input type='submit' name='submit' value='Last opp fil' class='knapp'  /> "
	    		+ "				<input type='reset' name='cancel' value='Avbryt' class='knapp knapp--fare' onclick=\"javascript:window.location='/';\"  />"
	    		+ "</form>";
	    
	    textul += " </div> ";
	    model.addAttribute("content", textul);
		return "index";
	}
	
	
	
	
	@RequestMapping(value = "/nyjobb", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	//@PostMapping("/nyjobb")
	//This is the old part/function of savin' the data sent through form. OLD style within the thymleaf  
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
	    //SimpleDateFormat timp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // I should replace this. I don't use this anymore.
	    //String timp = dateFormatNow.toString();
	    
	    if(records > 0) {
	    	textul = "There is a file for this Task already.<br>Try again with another Task ID! <hr> <meta http-equiv='refresh' content='3; url=/nyjobb'>";	    	
	    } else {
	    	LOGGER.info("The new job is saved.");
	    	int checkTask_idInTASKtbl = checkTask(uuid);
	    	if(checkTask_idInTASKtbl > 0) {
	    		jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    		jdbcTemplate.update("UPDATE TASK SET DATE_RECEIVED=SYSDATE, MAX_DOC_SPLIT=1 WHERE TASK_UUID=?", uuid);
	    	} else {
	    		jdbcTemplate.update("INSERT INTO TASK(TASK_UUID, DATE_RECEIVED, MAX_DOC_SPLIT) VALUES (?, SYSDATE, 1)", uuid);
	    		jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    		LOGGER.info("New TASK_UUID created. The new TASK_ID is " + uuid);
	    	}
	    }
	    
	    model.addAttribute("content", textul);
		return "index";
	}
	
	
	//This is the first API which is loading the file into database. It is no needed anymore.
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)	
    public ResponseEntity<?> uploadFile(@RequestParam("uuid") String uuid, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String filename = "";
	    String extension = "";
        byte[] fileContent = null;
	    if(file != null) {
	    	filename = file.getOriginalFilename();
	    	String[] getExtension = filename.split("\\.");
	    	extension = getExtension[getExtension.length - 1];
	    	fileContent = file.getBytes();
	    }
	    
	    Integer uuidInt = Integer.parseInt(uuid);
	    int records = 0;
	    if(uuidInt > 0) {
	    	records = checkInputFile(uuid);
	    } else {
	    	//create new UUID in TASK table if there is no TASK there 
	    	Integer newUUID = findMaxPlusOneUUIDinTask();
	    	uuid = newUUID.toString();
	    }
	    if(records > 0) {
	    	LOGGER.info("There is a file for this Task already. Try again with another Task ID!");
	    } else {
	    	LOGGER.info("The new job is saved.");
	    	int checkTask_idInTASKtbl = checkTask(uuid);
	    	if(checkTask_idInTASKtbl > 0) {
	    		jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    		jdbcTemplate.update("UPDATE TASK SET DATE_RECEIVED=SYSDATE, MAX_DOC_SPLIT=1 WHERE TASK_UUID=?", uuid);
	    	} else {
	    		jdbcTemplate.update("INSERT INTO TASK(TASK_UUID, DATE_RECEIVED, MAX_DOC_SPLIT) VALUES (?, SYSDATE, 1)", uuid);
	    		jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    		LOGGER.info("New TASK_UUID created. The new TASK_ID is " + uuid);
	    	}
	    	LOGGER.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
	    }
        return ResponseEntity.ok().build();
    }
	
	
	
	@PostMapping(value = "/savenyjobb", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)	
    public ResponseEntity<?> saveNyJobb(	@RequestParam("uuid") String uuid,
											@RequestParam("requester") String requester,  
											@RequestParam("document_title") String document_title, 
											@RequestParam("archive_unit") String archive_unit, 
											@RequestParam("archive_theme") String archive_theme, 
    										@RequestParam(value = "file", required = false) MultipartFile file,
    										@RequestParam(value = "file2", required = false) MultipartFile file2    										
    									) throws IOException {
		// if UUID == 0 then save a new UUID
		// else overwrite the old UUID / modify OLD UUID
				
        String filename = "";
	    String extension = "";
        byte[] fileContent = null;
	    if(file != null) {
	    	filename = file.getOriginalFilename();
	    	String[] getExtension = filename.split("\\.");
	    	extension = getExtension[getExtension.length - 1];
	    	fileContent = file.getBytes();
	    }
	    
	    String filename2 = "";
	    String extension2 = "";
        byte[] fileContent2 = null;
	    if(file2 != null) {
	    	filename2 = file2.getOriginalFilename();
	    	String[] getExtension2 = filename2.split("\\.");
	    	extension2 = getExtension2[getExtension2.length - 1];
	    	fileContent2 = file2.getBytes();
	    }
	    
	    Integer uuidInt = Integer.parseInt(uuid);
	    Integer archive_unitInt = Integer.parseInt(archive_unit);
	    int records = 0;
	    int recordsTemplate_specification = 0;
	    if(uuidInt > 0) {
	    	records = checkInputFile(uuid);
	    	recordsTemplate_specification = checkTemplateSpecification(uuid);
	    } else {
	    	//create new UUID in TASK table if there is no TASK there 
	    	Integer newUUID = findMaxPlusOneUUIDinTask();
	    	uuid = newUUID.toString();
	    }
	    if(records > 0 && recordsTemplate_specification > 0) {
	    	LOGGER.info("There is a file for this Task already. Try again with another Task ID!");
	    } else {
	    	LOGGER.info("The new job is saved.");
	    	int checkTask_idInTASKtbl = checkTask(uuid);
	    	if(checkTask_idInTASKtbl > 0) {
	    		jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    		jdbcTemplate.update("INSERT INTO TEMPLATE_SPECIFICATION(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension2, fileContent2);
	    		jdbcTemplate.update("UPDATE TASK SET DATE_UPDATED=SYSDATE, REQUESTER=?, ARCHIVE_THEME=?, ARCHIVE_UNIT=?, DOCUMENT_TITLE=?  WHERE TASK_UUID=?", requester, archive_theme, archive_unitInt, document_title, uuid);
	    	} else {
	    		jdbcTemplate.update("INSERT INTO TASK(TASK_UUID, DATE_RECEIVED, DATE_UPDATED, REQUESTER, MAX_DOC_SPLIT, ARCHIVE_THEME, ARCHIVE_UNIT, DOCUMENT_TITLE) VALUES (?, SYSDATE, SYSDATE, ?, 1, ?, ?, ?)", uuid, requester, archive_theme, archive_unitInt, document_title);
	    		LOGGER.info("New TASK_UUID created. The new TASK_ID is " + uuid);	    		
	    		if(file != null) {
	    			LOGGER.info("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES ('%s','%s',?)", uuid, extension);
	    			jdbcTemplate.update("INSERT INTO INPUT_FILE(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension, fileContent);
	    			LOGGER.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
	    		}
	    		if(file2 != null) {
	    			LOGGER.info("INSERT INTO TEMPLATE_SPECIFICATION(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES ('%s','%s',?)", uuid, extension2);
	    			jdbcTemplate.update("INSERT INTO TEMPLATE_SPECIFICATION(TASK_UUID, FILE_TYPE, FILE_OBJECT) VALUES (?,?,?)", uuid, extension2, fileContent2);
	    			LOGGER.info(String.format("File name '%s' uploaded successfully.", file2.getOriginalFilename()));
	    		}	    		
	    	}
	    	
	    }
	    
	    String jsonResponseOK = "{ \"status\": \"success\", \"data\": {\"uuid\": \"" + uuid + "\"} }";
	    try {
            JSONObject jsonObject = new JSONObject(jsonResponseOK);
            ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
            return re;
        } catch (JSONException ex) {
            return new ResponseEntity<String>("Done", HttpStatus.OK);
        }
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
	
	
	public Integer checkTemplateSpecification(String uuid) {
		String sql = "SELECT COUNT(*) FROM TEMPLATE_SPECIFICATION WHERE TASK_UUID='" + uuid + "'";
		List<Integer> i = jdbcTemplate.queryForList(sql, Integer.class);
		if (i.size() == 0) { 
			return 0; 
			} 
		else  {		
			LOGGER.info("We have found another  " + i.get(0) + " record(s) with the same TASK_UUID in the TEMPLATE_SPECIFICATION table.");
			return i.get(0);
		}
	}
	
	
	public Integer checkTask(String uuid) {
		String sql = "SELECT COUNT(*) FROM TASK WHERE TASK_UUID='" + uuid + "'";
		List<Integer> i = jdbcTemplate.queryForList(sql, Integer.class);
		if (i.size() == 0) { 
			return 0; 
			} 
		else  {		
			LOGGER.info("We have found another  " + i.get(0) + " record(s) with the same TASK_UUID in the TASK table.");
			return i.get(0);
		}
	}
	
	
	public Integer findMaxPlusOneUUIDinTask() {
		String sql = "SELECT MAX(TO_NUMBER(task_uuid)) FROM TASK";
		List<Integer> i = jdbcTemplate.queryForList(sql, Integer.class);
		if (i.size() == 0) { 
			return 0; 
		} else  {		
			LOGGER.info("New UUID in TASK is  " + i.get(0));
			return 1 + i.get(0);
		}
	}
	
}
