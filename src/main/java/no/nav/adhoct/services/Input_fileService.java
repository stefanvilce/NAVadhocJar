package no.nav.adhoct.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import no.nav.adhoct.models.*;


@Service
public class Input_fileService {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Input_file> listAll() {
		String sql = "SELECT * FROM INPUT_FILE";
		List<Input_file> inputfile_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Input_file.class));
		return inputfile_list;
    }
	
}
