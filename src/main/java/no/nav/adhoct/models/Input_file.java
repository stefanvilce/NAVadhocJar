package no.nav.adhoct.models;

//import javax.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
//@Table(name = "INPUT_FILE")
public class Input_file {    

  //@Column(name="TASK_UUID")
  private String task_uuid;
  private String file_type;
  private byte[] file_object;

  public Input_file(){

  }

  public Input_file(String task_uuid, String file_type, byte[] file_object) {
      this.task_uuid = task_uuid;
      this.file_type = file_type;
      this.file_object = file_object;
  }

  public String getTask_uuid() {
      return task_uuid;
  }

  public void setTask_uuid(String task_uuid) {
      this.task_uuid = task_uuid;
  }

  public String getFile_type() {
      return file_type;
  }

  public void setFile_type(String file_type) {
      this.file_type = file_type;
  }

  public byte[] getFile_object() {
      return file_object;
  }

  public void setFile_object(byte[] file_object) {
      this.file_object = file_object;        
  }

}