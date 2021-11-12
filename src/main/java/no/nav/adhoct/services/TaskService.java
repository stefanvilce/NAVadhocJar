package no.nav.adhoct.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import no.nav.adhoct.models.*;


@Service
public class TaskService {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Task> listAll() {
		String sql = "SELECT * FROM TASK";
		List<Task> task_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Task.class));
		return task_list;
    }
	
	
	public String update(int task_id, int max_doc_split) {
		//String sql = "SELECT * FROM TASK";
		jdbcTemplate.update("UPDATE TASK SET MAX_DOC_SPLIT = ? WHERE TASK_ID = ?", max_doc_split, task_id);
		/*List<Task> task_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Task.class));*/
		return "OK";
    }
	
}
