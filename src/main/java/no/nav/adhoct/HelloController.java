package no.nav.adhoct;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import no.nav.adhoct.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



@RestController
public class HelloController {


	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping("/indexvechi")
	public String index() {
		
		String sql = "SELECT * FROM INPUT_FILE";
		List<Input_file> inputfile = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Input_file.class));
	         
		LOGGER.info("Opening first page");
	    
		String textul = "<div style='font-family: Arial; color: #344596;'><H1 style='text-align: center; '>AdHoc App</H1><h2 style='text-align: center;color: #DA7643;'>Tabellen INPUT_FILE</h2>";
		textul += "<table style='border: 1px dashed #DCDCDC;'>"
	    		+ "<tr style='text-align: center; font-weight: bold; border: 1px solid #878787; padding: 1px;'>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>UUID</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>FILE TYPE</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'> > </td>"
	    		+ "</tr>";
	    for(Input_file model : inputfile) {
           textul += "<tr><td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getTask_uuid() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getFile_type() + "</td>"
	    					+ "<td><button>Get the file</button></td>"
	    			+ "</tr>"; 
        }
	    textul += " </table><hr></div>";
	    textul += "<div style='text-align: center; padding: 30px;'><a href='/nyjobb' style='padding: 5px 40px 5px 40px; background-color: 4444CD; color: white; font-weight: 600; border-radius:1px; text-decoration: none; font-family: Tahoma;'>Registrer ny jobb</a></div>";
	    textul += "<a href='/alldocs'>Go to DOC RECEIVER Table</a>";
		return textul;
	}
	
	
	@GetMapping("/alldocsvechi")
	public String doc_receiverListAll() {
		
		String sql = "SELECT * FROM DOC_RECEIVER";
		List<Doc_receiver> alldocs = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Doc_receiver.class));
	   
		LOGGER.info("See all docs");
		
	    String textul = "<div style='font-family: Arial; color: #344596;'><H1 style='text-align: center; '>AdHoc App</H1><h2 style='text-align: center;color: #DA7643;'>Tabellen DOC_RECEIVER</h2>";
	    textul += "<table style='border: 1px dashed #DCDCDC;'>"
	    		+ "<tr style='text-align: center; font-weight: bold; border: 1px solid #878787; padding: 1px;'>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>DOC_UUID</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>TASK_UUID</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>JOURNAL_ID</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>STATUS</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>DOC_NAME</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>ADRESS</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>ZIP</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>CITY</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>COUNTRY</td>"
	    		+ "</tr>";
	    for(Doc_receiver model : alldocs) {
	    	textul += "<tr><td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getDoc_uuid() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getTask_uuid() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getJournal_id() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getStatus() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getDoc_name() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getAddress1() + "" + model.getAddress2() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getZip() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getCity() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + model.getCountry() + "</td>"
	    			+ "</tr>";           
        }
	    textul += " </table><hr></div>";	    
		return textul;
	}

}