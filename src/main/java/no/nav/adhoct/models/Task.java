package no.nav.adhoct.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
//@Table(name = "TASK")
public class Task {    

  //@Column(name="TASK_UUID")
  private String task_uuid;
  private String status;
  private String requester;
  private String date_received;
  private String date_updated;
  private String deadline;
  private Integer max_doc_split;
  private Integer docs_in_job;
  private Integer docs_distributed;
  private String expire_date;
  private String doc_format;
  private String csserver_id;
  private byte[] preview_doc;
  private String archive_theme;
  private Integer archive_unit;
  private String document_title;
  

  public Task(){

  }

  public Task(String task_uuid, String status, String requester, String date_received, String date_updated, String deadline, Integer max_doc_split,
  			 Integer docs_in_job, Integer docs_distributed, String expire_date, String doc_format, String csserver_id, byte[] preview_doc, 
  			 String archive_theme, Integer archive_unit, String document_title) 
  {
      this.task_uuid = task_uuid;
      this.status = status;
      this.requester = requester;
      this.date_received = date_received;
      this.date_updated = date_updated;
      this.deadline = deadline;
      this.max_doc_split = max_doc_split;
      this.docs_in_job = docs_in_job;
      this.docs_distributed = docs_distributed;
      this.expire_date = expire_date;
      this.doc_format = doc_format;
      this.csserver_id = csserver_id;
      this.preview_doc = preview_doc;
      this.archive_theme = archive_theme;
      this.archive_unit = archive_unit;
      this.document_title = document_title;
  }

  public String getTask_uuid() {
		return task_uuid;
	}

	public void setTask_uuid(String task_uuid) {
		this.task_uuid = task_uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getDate_received() {
		return date_received;
	}

	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	
	public String getDate_updated() {
		return date_updated;
	}

	public void setDate_received(String date_received) {
		this.date_received = date_received;
	}
	
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Integer getMax_doc_split() {
		return max_doc_split;
	}

	public void setMax_doc_split(Integer max_doc_split) {
		this.max_doc_split = max_doc_split;
	}

	public Integer getDocs_in_job() {
		return docs_in_job;
	}

	public void setDocs_in_job(Integer docs_in_job) {
		this.docs_in_job = docs_in_job;
	}

	public Integer getDocs_distributed() {
		return docs_distributed;
	}

	public void setDocs_distributed(Integer docs_distributed) {
		this.docs_distributed = docs_distributed;
	}

	public String getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}

	public String getDoc_format() {
		return doc_format;
	}

	public void setDoc_format(String doc_format) {
		this.doc_format = doc_format;
	}

	public String getCsserver_id() {
		return csserver_id;
	}

	public void setCsserver_id(String csserver_id) {
		this.csserver_id = csserver_id;
	}

	public byte[] getPreview_doc() {
		return preview_doc;
	}

	public void setPreview_doc(byte[] preview_doc) {
		this.preview_doc = preview_doc;
	}

	public String getArchive_theme() {
		return archive_theme;
	}

	public void setArchive_theme(String archive_theme) {
		this.archive_theme = archive_theme;
	}

	public Integer getArchive_unit() {
		return archive_unit;
	}

	public void setArchive_unit(Integer archive_unit) {
		this.archive_unit = archive_unit;
	}

	public String getDocument_title() {
		return document_title;
	}

	public void setDocument_title(String document_title) {
		this.document_title = document_title;
	}

}