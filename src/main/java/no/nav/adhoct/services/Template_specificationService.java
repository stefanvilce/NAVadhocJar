package no.nav.adhoct.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import no.nav.adhoct.models.*;

@Service
public class Template_specificationService {
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Template_specification> listAll() {
        String sql = "SELECT * FROM TEMPLATE_SPECIFICATION";        
        List<Template_specification> templates_list = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Template_specification.class));
		return templates_list;
    }
	
    public void save(Template_specification template_specification) {
        //repo.save(product);
    }
    
     /*
    public Product get(Integer id) {
        return repo.findById(id).get();
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/
	
}
