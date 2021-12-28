package no.nav.adhoct.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import no.nav.adhoct.models.*;


@Service
public class Doc_receiverService {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Doc_receiverService.class);
	
	public List<Doc_receiver> listAll() {
		String sql = "SELECT * FROM DOC_RECEIVER";
		List<Doc_receiver> docreceiver_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Doc_receiver.class));
		return docreceiver_list;
    }
	
	
	public List<Doc_receiver> listByTaskId(String uuid){
		String sql = "SELECT * FROM DOC_RECEIVER WHERE TASK_UUID='" + uuid + "'";
		List<Doc_receiver> docreceiver_list = jdbcTemplate.query(sql,
	                BeanPropertyRowMapper.newInstance(Doc_receiver.class));
		return docreceiver_list;
	}
	
	
	public int nrOfDocsByUUID(String uuid, String filter, String search_journal_id, String search_personal_id) {
		String sqlFilter = (filter.equals("all") ? " " : " AND STATUS LIKE '" + filter + "'");
		sqlFilter = sqlFilter + (search_journal_id.equals("all") ? " " : " AND JOURNAL_ID LIKE '%" + search_journal_id + "%'");
		sqlFilter = sqlFilter + (search_personal_id.equals("all") ? " " : " AND PERSON_ID LIKE '%" + search_personal_id + "%'");
		String sql = "SELECT COUNT(*) FROM DOC_RECEIVER WHERE TASK_UUID='" + uuid + "' " + sqlFilter;		
		List<Integer> rows = jdbcTemplate.queryForList(sql, Integer.class);
		if (rows.size() == 0) { 
			return 0; 
		} else  {			
			return rows.get(0); 
		}
    }
	
	
	public List<Doc_receiver> listPerPageByTaskId(String uuid, int page, int perPage, String filter, String search_journal_id, String search_personal_id) {
		int skipFirstRows = (page - 1) * perPage;
		String sqlFilter = (filter.equals("all") ? " " : " AND STATUS LIKE '" + filter + "'");
		sqlFilter = sqlFilter + (search_journal_id.equals("all") ? " " : " AND JOURNAL_ID LIKE '%" + search_journal_id + "%'");
		sqlFilter = sqlFilter + (search_personal_id.equals("all") ? " " : " AND PERSON_ID LIKE '%" + search_personal_id + "%'");
		String sql = "SELECT * FROM DOC_RECEIVER WHERE TASK_UUID='" + uuid + "' " + sqlFilter + " OFFSET " + skipFirstRows + " ROWS FETCH NEXT " + perPage + " ROWS ONLY";
		LOGGER.info("Get the docs for page nr. " + page + ": " + sql);
		List<Doc_receiver> docreceiver_list = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Doc_receiver.class));
		return docreceiver_list;
    }
	
	
	
	public List<String> listStatusesByTaskId(String uuid) {
		String sql = "SELECT DISTINCT STATUS FROM DOC_RECEIVER WHERE TASK_UUID='" + uuid + "'";
		List<String> status_list=jdbcTemplate.queryForList(sql,String.class);
		return status_list;
    }
}
