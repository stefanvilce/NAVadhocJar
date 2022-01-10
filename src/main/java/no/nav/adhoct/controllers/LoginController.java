package no.nav.adhoct.controllers;


import org.springframework.http.ResponseEntity;

//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.SecureRandom;
import java.util.Base64;


/*******************************************************************************
 * 
 *  
 * @author StefanVilceloiu
 *			This class is only to login to app. 
 *			It is generating a token which are stored in SESSION
 *			
 *
 *
 ********************************************************************************/



@RestController
public class LoginController {
	
	
	@Value("${login.username}")
    private String login_username;
	
	@Value("${login.password}")
    private String login_password;
	
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	
	@RequestMapping(value = {"/api/login"}, method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, 
								    		HttpServletRequest request,
								            HttpServletResponse response) 
	{
		/*
		HttpSession session=request.getSession();  
        String uname = (String) session.getAttribute("token");  
        LOGGER.info(" **** SESIUNEA (token): " + uname);
        */
        LOGGER.info("LOGIN... username: " + username);
        String jsonString = "";
        try {
            if(login_username.equals(username) && login_password.equals(password)) {
            	
            	//generate a new session
                HttpSession newSession = request.getSession(true);
                //Cookie message = new Cookie("message", "Welcome");
                //response.addCookie(message);
                String token = generateToken();
                newSession.setAttribute("token", token);
                newSession.setAttribute("username", username);
                
                //https://medium.com/@kasunpdh/session-management-in-java-using-servlet-filters-and-cookies-7c536b40448f                
                
                jsonString = "{\n"
                        + "    \"status\": \"success\",\n"
                        + "    \"data\": {\n"
                        + "        \"username\": \"" + username +"\", \n"
                        + "        \"loggedin\": \"yes\", \n"
                        + "        \"token\": \"" + token + "\" \n"
                        + "    }\n"
                        + "}";
                LOGGER.debug("The token is: " + token);
            	JSONObject jsonObject = new JSONObject(jsonString);
            	ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
                return re;
            } else {
            	LOGGER.info("LOGIN... username: " + username + ". Wrong password or username. Login fail.");
            	jsonString = "{\n"
                        + "    \"status\": \"NOT_FOUND\",\n"
                        + "    \"data\": {\n"
                        + "        \"username\": \"" + username +"\", \n"
                        + "        \"loggedin\": \"no\", \n"
                        + "    }\n"
                        + "}";
            	JSONObject jsonObject = new JSONObject(jsonString);
            	ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
                return re;
            }            
        } catch (JSONException ex) {
            return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }	
	
	
	@RequestMapping(value = {"/api/logout"}, method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) 	{
		HttpSession session=request.getSession();  
        String username = (String) session.getAttribute("username"); 
		String jsonString = "{\n"
                + "    \"status\": \"success\",\n"
                + "    \"data\": {\n"
                + "        \"username\": \"" + username +"\", \n"
                + "        \"loggedin\": \"no\" \n"
                + "    }\n"
                + "}";
		session.removeAttribute("username");
		session.removeAttribute("token");
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
        	ResponseEntity<String> re = new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
            return re;
		} catch (JSONException ex) {
            return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
	}
	
	
	
	private static String generateToken() {
		byte[] randomBytes = new byte[32];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}
}
