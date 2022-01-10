import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Container, Form } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import { Input, Label } from 'nav-frontend-skjema';

class Nyjobb extends Component {
	
	
	
	constructor(props) {
        super(props);
        this.addTheTitle = this.addTheTitle.bind(this);
        this.showSelectCSVfc = this.showSelectCSVfc.bind(this);
        this.showSelectWORDfc = this.showSelectWORDfc.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onFileCSVChangeHandler = this.onFileCSVChangeHandler.bind(this);
        this.onFileWORDChangeHandler = this.onFileWORDChangeHandler.bind(this);
        this.enableDisableKnappLagreJobb = this.enableDisableKnappLagreJobb.bind(this);
        this.checkAllFields = this.checkAllFields.bind(this);
        this.state = {
        		title: "Ny jobb, lagre fil",
        		job: 0,
        		unikuuid: false,
        		uuid: "",
        		requester: "",
        		document_title: "",
        		archive_unit: "",
        		archive_theme: "",        		
        		selectedFileCSV: null,
        		selectedFileWORD: null,
        		showSelectCSV: false,
        		showSelectWORD: false,
        		disabledKnappLagreJobb: true,
        		showUUIDMessageGreen: false,
        		showUUIDMessageRed: false        		
        };
    }
		

    addTheTitle(){
    		return "ADHOC :: Ny jobb";
    }    
    
    
    showSelectCSVfc(){
       	this.setState({showSelectCSV: true});
    }
    
    showSelectWORDfc(){
    	this.setState({showSelectWORD: true});
    }
    
    enableDisableKnappLagreJobb = (e) => {
    	var v = e.target.value;
    	if(v.length == 0){
    		alert("You have to fill out this field!");
    		this.setState({disabledKnappLagreJobb: true});
    		this.setState({unikuuid: false});
    	} else {
    		console.log("UUID value: " + v);    		
    		fetch('/api/checkuuid?uuid=' + v, {
                method: 'GET'
            }).then(response => response.json())
            	.then(result => {
            		console.info(result);
	                if(result.status == "success"){
	                	if(result.data.founduuid == "0") {
		                    console.log(result.data);	
		                    this.setState({showUUIDMessageGreen: true});
		                    this.setState({showUUIDMessageRed: false});
		                    this.setState({unikuuid: true});
		                    this.checkAllFields();
		                } else {
		                	this.setState({showUUIDMessageRed: true});
		                	this.setState({showUUIDMessageGreen: false});
		                	this.setState({unikuuid: false});
		                	this.checkAllFields();
		                	console.log("Problemer: Denne UUID eksisterer allerede inn i databasen.");
		                }
	                } else {
	                	console.error("Communication problems!");
	                }
            }).catch(error => console.log('error', error));  
    		    		
    		this.setState({unikuuid: true});
    		this.checkAllFields();  		
    	}
    }
    
    checkAllFields(){
    	//We have to check all the fields to see if these contain informations. These should not be empty
    	if(this.state.unikuuid && this.state.requester.length > 0 && this.state.document_title.length > 0 && this.state.archive_unit.length > 0 && this.state.archive_theme.length > 0)
    		this.setState({disabledKnappLagreJobb: false})
    	else 
    		this.setState({disabledKnappLagreJobb: true});
    }
    
    
    onFileChangeHandler = (e) => {
        this.setState({
            selectedFile: e.target.files[0]
        });
    };
    
    
    onFileCSVChangeHandler = (e) => {
        this.setState({
            selectedFileCSV: e.target.files[0]
        });
    };
    
    onFileWORDChangeHandler = (e) => {
        this.setState({
            selectedFileWORD: e.target.files[0]
        });
    };
    
          
    
    handleChange = (ev) => {    	
    	this.setState({[ev.target.name]: ev.target.value}, this.checkAllFields);  	
    	if([ev.target.name] == "uuid"){ // the green and red message under the field should disappear
    		this.setState({showUUIDMessageGreen: false});
            this.setState({showUUIDMessageRed: false});
    	}    	
    }
    
    
    handleSubmit = () => {
    	//event.preventDefault();
    	const formData = new FormData();
        formData.append('uuid', this.state.uuid);
        formData.append('requester', this.state.requester);
        formData.append('document_title', this.state.document_title);
        formData.append('archive_unit', this.state.archive_unit);
        formData.append('archive_theme', this.state.archive_theme);        
        formData.append('file', this.state.selectedFileCSV);
        formData.append('file2', this.state.selectedFileWORD);
        
        if(this.state.selectedFileCSV != null){
        	fetch('/api/savenyjobb', {
	                method: 'post',
	                body: formData
	            }).then(res => res.json())
	              .then(result => {
	            	  if(result.status == "success"){
	                      alert("Datoene ble sendt og lagret.");
	                      this.props.history.push("/");
	            	  } else {
	            		  console.log(result);
	            		  alert("Data er ikke lagret!");
	            	  }
	         }).catch(error => console.log('error', error));
        } else {
        	alert("Felten CSV fil er obligatorisk!");
        	this.setState({showSelectCSV: true});
        }
    }    
    
	
    render() {
    	const props = {
    		      dangerouslySetInnerHTML: { __html: '<meta http-equiv="refresh" content="0; url=/nyjobb" />' },
	    };    	
    	const {title} = this.state;
    	//Here I have to get further with the Form in REACT format. For the moment I keep this redirect to the Form in java format.  
    	
        return (<React.Fragment>
		            <AppNavbar showSelectCSVfc = {this.showSelectCSVfc} showSelectWORDfc = {this.showSelectWORDfc} handleSubmit = {this.handleSubmit} disableKnapp = {this.state.disabledKnappLagreJobb} />
		            <Container className="stfRight">
			            <h1>AdHoc App</h1>
			        	<h2>{title}</h2>
			        	<hr />
			        	<div className="wrappingform">
				        	<div>
						        <table>
						        	<tr>
							        	<td>
						        			<Label htmlFor="uuid">Ordre nr.:</Label>
							        		<Input name="uuid" id="uuid" type="text" value={this.state.uuid} onChange={this.handleChange} onBlur={this.enableDisableKnappLagreJobb} />
							        		{this.state.showUUIDMessageGreen ? <span className="available_message">Dette Ord. nr. er tilgjengelig.</span> : null }
							        		{this.state.showUUIDMessageRed ? <span className="notavailable_message">Dette Ord. nr. er ikke tilgjengelig.</span> : null }
							        		
							        	</td>
						        		<td>
						        			<Label htmlFor="requester">Bestiller:</Label>
							        		<Input name="requester" id="requester" type="text" value={this.state.requester} onChange={this.handleChange} />
						        		</td>
							        	<td>
							        		<Label htmlFor="document_title">Brevtittel:</Label>
							        		<Input name="document_title" id="document_title" type="text"  value={this.state.document_title} onChange={this.handleChange}  />
						        		</td>
						        		<td>
							        		<Label htmlFor="archive_unit">Journalførende enhet:</Label>
							        		<Input name="archive_unit" id="archive_unit" type="text"  value={this.state.archive_unit} onChange={this.handleChange}  />
						        		</td>
							        	<td>
							        		<Label htmlFor="archive_theme">Tema:</Label>
							        		<Input name="archive_theme" id="archive_theme" type="text"  value={this.state.archive_theme} onChange={this.handleChange}  />
						        		</td>
						        	</tr>
						        </table>
					        </div>
					        <div className="uploadfileinput" style={ this.state.showSelectCSV ? {display: "block"} : {display: "none"} }>
					        	    <fieldset>
						                <Label htmlFor="filecsv">CSV fil:</Label>
						                <Input name="filecsv" id="filecsv" type="file" accept=".csv, .xml, .xls, .xlsx"  onChange={this.onFileCSVChangeHandler} />				               
						            </fieldset>
					        </div>
					        <div className="uploadfileinput" style={ this.state.showSelectWORD ? {display: "block"} : {display: "none"} }>
					        	    <fieldset>
						                <Label htmlFor="fileword">WORD / PDF fil:</Label>
						                <Input name="fileword" id="fileword" type="file" accept=".pdf, .doc, .docx, .rtf"  onChange={this.onFileWORDChangeHandler} />				               
						            </fieldset>
					        </div>
			          </div>
		            </Container>
		        </React.Fragment>
	    );
        
        //https://reactjs.org/docs/forms.html
        //https://www.w3schools.com/react/react_forms.asp
        //https://www.digitalocean.com/community/tutorials/how-to-build-forms-in-react
        
        /*
        return (
            <div>
                <AppNavbar/>
                <Container>                	
                    <p dangerouslySetInnerHTML={{__html: thisIsMyCopy}}"></p>                    
                </Container>
            </div>
        );*/
        /*
        return (
                <div  {...props}></div>
            );*/
    }
}
export default withRouter(Nyjobb);