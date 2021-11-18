package no.nav.adhoct.models;


import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan
//@Table(name = "Doc_receiver")
public class Doc_receiver {    

    private Integer doc_uuid;
    private String task_uuid;
    private String journal_id;
    private String status;
    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String zip;
    private String city;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String country;
    private String distribuerjournalpost_id;
    private String person_id;
    private String json_file;
    private String created_date;
    private String updated_date;
    
    public Doc_receiver(){

    }

    public Doc_receiver(Integer doc_uuid, String task_uuid, String journal_id, String status, String name, 
    		String address1, String address2, String address3, String zip, String city, 
    		String field1, String field2, String field3, String field4, String field5, String field6, String field7, String field8, String field9,
    		String country, String distribuerjournalpost_id, String person_id, String json_file, String created_date, String updated_date
    		) {
    	this.doc_uuid = doc_uuid;
    	this.task_uuid = task_uuid;
        this.journal_id = journal_id;
        this.status = status;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.zip = zip;
        this.city = city;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
        this.field7 = field7;
        this.field8 = field8;
        this.field9 = field9;
        this.country = country;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

	public Integer getDoc_uuid() {
		return doc_uuid;
	}

	public void setDoc_uuid(Integer doc_uuid) {
		this.doc_uuid = doc_uuid;
	}

	public String getTask_uuid() {
		return task_uuid;
	}

	public void setTask_uuid(String task_uuid) {
		this.task_uuid = task_uuid;
	}

	public String getJournal_id() {
		return journal_id;
	}

	public void setJournal_id(String journal_id) {
		this.journal_id = journal_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}

	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}

	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}

	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistribuerjournalpost_id() {
		return distribuerjournalpost_id;
	}

	public void setDistribuerjournalpost_id(String distribuerjournalpost_id) {
		this.distribuerjournalpost_id = distribuerjournalpost_id;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getJson_file() {
		return json_file;
	}

	public void setJson_file(String json_file) {
		this.json_file = json_file;
	}
	
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	
	public String getCreated_date() {
		return created_date;
	}
	
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	
	public String getUpdated_date() {
		return updated_date;
	}

    
    

}