package no.nav.adhoct;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import no.nav.adhoct.models.*;




@Controller
public class IndexController {

	//this class is NOT necessary anymore. It should be deleted 
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping("/index")
	public String greetingTest(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}
	
	
	@GetMapping("/home")
	public String index(Model model) {
		model.addAttribute("title", "AdHoc App");
		model.addAttribute("subtitle", "Tabellen INPUT_FILE");			
		String sql = "SELECT * FROM INPUT_FILE";
		List<Input_file> inputfile = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Input_file.class));	         
		LOGGER.info("Reading the inputs from INPUT_FILE table.");	    
		String textul = "<table style='border: 1px dashed #DCDCDC;'>";
		textul += "<tr style='text-align: center; font-weight: bold; border: 1px solid #878787; padding: 1px;'>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>UUID</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'>FILE TYPE</td>"
	    		+ "				<td style='padding: 3px; font-size: 11px;'> > </td>"
	    		+ "</tr>";
	    for(Input_file row : inputfile) {
           textul += "<tr><td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getTask_uuid() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getFile_type() + "</td>"
	    					+ "<td><button>Get the file</button></td>"
	    			+ "</tr>"; 
        }
	    textul += " </table><hr></div>";
	    textul += "<div style='text-align: center; padding: 30px;'><a href='/nyjobb' class='linkknapp'>Registrer ny jobb/task</a></div>";
	    textul += "<a href='/alldocs'>Go to DOC RECEIVER Table</a>";
	    model.addAttribute("content", textul);		
		return "index";
	}

	
	
	@GetMapping("/alldocs")
	public String alldocs(Model model) {
		model.addAttribute("title", "AdHoc App");
		model.addAttribute("subtitle", "Tabellen DOC_RECEIVER");		
		String sql = "SELECT * FROM DOC_RECEIVER";
		List<Doc_receiver> alldocs = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Doc_receiver.class));
	    LOGGER.info("See all docs. Reading DOC_RECEIVER table.");
				String textul = "<table style='border: 1px dashed #DCDCDC;'>"
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
	    for(Doc_receiver row : alldocs) {
	    	textul += "<tr><td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getDoc_uuid() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getTask_uuid() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getJournal_id() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getStatus() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getName() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getAddress1() + "" + row.getAddress2() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getZip() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getCity() + "</td>"
	    			+ "			<td style='border: 1px solid #B8B8B8; padding: 2px; font-size: 10px;'>" + row.getCountry() + "</td>"
	    			+ "</tr>";           
        }
	    textul += " </table><hr></div>";		
	    textul += "<a href='/'>Go to INPUT_FILE Table</a>";
	    model.addAttribute("content", textul);		
		return "index";
	}	
	
}
