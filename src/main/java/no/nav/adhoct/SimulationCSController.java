package no.nav.adhoct;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
//import io.swagger.annotations.*;


/*******************************************************************************
 * 
 *  
 * @author StefanVilceloiu
 *			This class is only to simulate the others of the services 
 *			which are coming from Extream, OTDS. Communication Server (CS)
 *
 *
 ********************************************************************************/



//@Api(tags = "Operations on EWS Exstream Engine, Communication Server")
@RestController
public class SimulationCSController {
	
	
	@Value("${login.username}")
    private String login_username;
	
	@Value("${login.password}")
    private String login_password;

	private static final Logger LOGGER = LoggerFactory.getLogger(SimulationCSController.class);
	
	@RequestMapping(value = {"/api/communications"}, method = RequestMethod.POST)
    public ResponseEntity<String> getCommunications(@RequestBody Object object) {
        LOGGER.info("receving communications request...");
        LOGGER.info(object.toString());
        String jsonString = "{\n"
                + "    \"status\": \"success\",\n"
                + "    \"data\": {\n"
                + "        \"id\": \"1D185104-F479-5B46-8F3F-9A6817DB479C\",\n"
                + "        \"attributeMap\": {},\n"
                + "        \"result\": [\n"
                + "            {\n"
                + "                \"content\": {\n"
                + "                    \"data\": \"eyJGVU5LU0pPTiI6ICJTRU5UUkFMUFJJTlQifQ==\", \n"
                + "                    \"contentType\": \"application/octet-stream\",\n"
                + "                    \"streamingFilePath\": null,\n"
                + "                    \"empty\": false\n"
                + "                }\n"
                + "            }\n"
                + "        ]\n"
                + "    }\n"
                + "}";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
            return re;
        } catch (JSONException ex) {
            return new ResponseEntity<String>("Done", HttpStatus.OK);
        }
    }
	
	
	
	@RequestMapping(value = {"/api/login"}, method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        LOGGER.info("LOGIN... username: " + username);
        String jsonString = "{\n"
                + "    \"status\": \"success\",\n"
                + "    \"data\": {\n"
                + "        \"username\": \"" + username +"\" \n"
                + "    }\n"
                + "}";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if(login_username.equals(username) && login_password.equals(password)) {
            	ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
                return re;
            } else {
            	return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
            }
            
        } catch (JSONException ex) {
            return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
