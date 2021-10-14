package no.nav.adhoct.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
//@Table(name = "TEMPLATE_SPECIFICATION")
public class Template_specification {    

  private String task_uuid;
  private String file_type;
  private String file_object;

  public Template_specification(){

  }

  public Template_specification(String task_uuid, String file_type, String file_object) {
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

  public String getFile_object() {
      return file_object;
  }

  public void setFile_object(String file_object) {
      this.file_object = file_object;        
  }

}