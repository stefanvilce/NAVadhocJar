package no.nav.adhoct.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import no.nav.adhoct.models.*;


@Service
@Component
@Repository("Input_file")
public class Input_fileService {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public List<Input_file> listAll() {
		String sql = "SELECT * FROM INPUT_FILE";
		List<Input_file> inputfile_list = null;
		inputfile_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Input_file.class));
		return inputfile_list;
    }
	
	
	public List<Input_file> listByUUID(String uuid) {
		try {
			String sql = "SELECT * FROM INPUT_FILE WHERE TASK_UUID='" + uuid + "'";
			//Input_file input = (Input_file) jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Input_file.class));
			List<Input_file> inputfile_list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Input_file.class));
			
			/*
			Map<String, String> params = new HashMap<>();
		    params.put("uuid", uuid);
			@SuppressWarnings("deprecation")
			List<Input_file> inputfile_list = jdbcTemplate.query(sql, new Object[] { uuid }, BeanPropertyRowMapper.newInstance(Input_file.class)); //("SELECT * FROM INPUT_FILE WHERE TASK_UUID=:uuid", params,
					//BeanPropertyRowMapper.newInstance(Input_file.class));*/
			return inputfile_list;
		} catch(Exception e) {
			System.out.println("Gool");
			System.out.println(e.getMessage());
			return null;
		} 
		
	}
	
	
	public int checkExistByUUID(String uuid) {
		String sql = "SELECT COUNT(*) FROM INPUT_FILE WHERE TASK_UUID='" + uuid + "'";
		/*Map<String, Object> rowCount = jdbcTemplate.queryForMap(sql);
		for(@SuppressWarnings("rawtypes") Map.Entry m:rowCount.entrySet()){  
			   System.out.println(m.getKey()+" "+m.getValue());  
		} */
		//List<Integer> intgr = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Integer.class));
		
		 // SQL Operation #3 - SQL READ Operation
		/*
        String sqlSelectQuery = "SELECT name, email, address, telephone FROM contact";
        List listContacts = jdbcTemplateObj.query(sqlSelectQuery, new RowMapper() {
            public Contact mapRow(ResultSet result, int rowNum) throws SQLException {
                Contact contactObj = new Contact();
                contactObj.setName(result.getString("name"));
                contactObj.setEmail(result.getString("email"));
                contactObj.setAddress(result.getString("address"));
                contactObj.setPhone(result.getString("telephone"));
                return contactObj;
            }
        });
        
        int list = jdbcTemplate.query(sql, new Integer);
		*/
		
		//List<Integer> i = jdbcTemplate.queryForList(sql, Integer.class);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		if (rows.size() == 0) { 
			return 0; } 
		else  {			
			return 1; //rows.get(0);
		}
    }
	
	
	
	
}
