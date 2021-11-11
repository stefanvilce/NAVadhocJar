package no.nav.adhoct.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import no.nav.adhoct.models.*;


@Service
public class Doc_receiverService {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Doc_receiver> listAll() {
		String sql = "SELECT * FROM DOC_RECEIVER";
		List<Doc_receiver> docreceiver_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Doc_receiver.class));
		return docreceiver_list;
    }
	
	
	public List<Doc_receiver> listByTaskId(String task_id){
		String sql = "SELECT * FROM DOC_RECEIVER WHERE TASK_UUID='" + task_id + "'";
		List<Doc_receiver> docreceiver_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Doc_receiver.class));
		return docreceiver_list;
	}
	
}
