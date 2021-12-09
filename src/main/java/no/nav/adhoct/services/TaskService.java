package no.nav.adhoct.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
	
	
	public String update(String task_id, int max_doc_split) {
		jdbcTemplate.update("UPDATE TASK SET MAX_DOC_SPLIT=?, STATUS=?, DATE_UPDATED=SYSDATE WHERE TASK_UUID=?", max_doc_split, "updated", task_id);
		return "OK";
    }
	
	
	public String updateDeadline(String task_id, String deadline) {
		//Timestamp.valueOf("2018-11-12 01:02:03.123456789")
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date from = (Date) df.parse(deadline);
		Timestamp ts = Timestamp.from(Instant.parse(deadline));
		//Timestamp t = Timestamp.valueOf(deadline);
		jdbcTemplate.update("UPDATE TASK SET DEADLINE=?, STATUS=?, DATE_UPDATED=SYSDATE WHERE TASK_UUID=?", ts, "updated", task_id);
		return "OK";
    }
	
}
