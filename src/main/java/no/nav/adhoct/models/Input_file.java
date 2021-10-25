package no.nav.adhoct.models;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;



@EntityScan
//@Table(name = "INPUT_FILE")
public class Input_file {    

    //@Column(name="TASK_UUID")
	@Autowired
	private String task_uuid;
	
	@Autowired
	private String file_type;
	
	@Autowired
	private byte[] file_object;
	
	@Autowired
	public Input_file(){
	
	}

	  @Autowired
	  public Input_file(String task_uuid, String file_type, byte[] file_object) {
	      this.task_uuid = task_uuid;
	      this.file_type = file_type;
	      this.file_object = file_object;
	  }
	
	  public String getTask_uuid() {
	      return task_uuid;
	  }
	
	  @Autowired
	  public void setTask_uuid(String task_uuid) {
	      this.task_uuid = task_uuid;
	  }
	
	  public String getFile_type() {
	      return file_type;
	  }
	
	  @Autowired
	  public void setFile_type(String file_type) {
	      this.file_type = file_type;
	  }
	
	  public byte[] getFile_object() {
	      return file_object;
	  }
	
	  @Autowired
	  public void setFile_object(byte[] file_object) {
	      this.file_object = file_object;        
	  }

}