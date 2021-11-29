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
        this.showSelectFirstFormfc = this.showSelectFirstFormfc.bind(this);
        this.showSelectCSVfc = this.showSelectCSVfc.bind(this);
        this.showSelectWORDfc = this.showSelectWORDfc.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onFileCSVChangeHandler = this.onFileCSVChangeHandler.bind(this);
        this.onFileWORDChangeHandler = this.onFileWORDChangeHandler.bind(this);
        this.state = {
        		title: "Ny jobb, lagre fil",
        		job: 0,
        		uuid: "0",
        		requester: "",
        		document_title: "",
        		archive_unit: "0",
        		archive_theme: "",        		
        		selectedFileCSV: null,
        		selectedFileWORD: null,
        		showFirstForm: true,
        		showSelectCSV: false,
        		showSelectWORD: false
        };
    }
		

    addTheTitle(){
    		return "ADHOC :: Ny jobb";
    }    
    
    
    showSelectFirstFormfc(){
    	this.setState({showSelectCSV: false, showSelectWORD: false, showFirstForm: true});
    }
    
    showSelectCSVfc(){
       	this.setState({showSelectCSV: true, showSelectWORD: false, showFirstForm: false});
    }
    
    showSelectWORDfc(){
    	this.setState({showSelectCSV: false, showSelectWORD: true, showFirstForm: false});
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
    	this.setState({[ev.target.name]: ev.target.value});
    }    
    
    handleSubmit = () => {
    	//event.preventDefault();
        console.log("Par.1 " + this.state.uuid);
        console.log("Par.2 " + this.state.requester);
        console.log("Archive unit: " + this.state.archive_unit);
        
        const formData = new FormData();
        formData.append('uuid', this.state.uuid);
        formData.append('requester', this.state.requester);
        formData.append('document_title', this.state.document_title);
        formData.append('archive_unit', this.state.archive_unit);
        formData.append('archive_theme', this.state.archive_theme);        
        formData.append('file', this.state.selectedFileCSV);
        formData.append('file2', this.state.selectedFileWORD);
        fetch('/savenyjobb', {
            method: 'post',
            body: formData
        }).then(res => {
            if(res.ok) {
                console.log(res.data);
                alert("The data were sent and saved!")
            } else {
            	alert("Error! \nThe data cannot be sent!");            	
            }
        });  
        
        
        
    }
    
	
    render() {
    	const props = {
    		      dangerouslySetInnerHTML: { __html: '<meta http-equiv="refresh" content="0; url=/nyjobb" />' },
	    };    	
    	const {title} = this.state;
    	//Here I have to get further with the Form in REACT format. For the moment I keep this redirect to the Form in java format.  
    	
        return (<React.Fragment>
		            <AppNavbar showSelectCSVfc = {this.showSelectCSVfc} showSelectWORDfc = {this.showSelectWORDfc} showSelectFirstFormfc = {this.showSelectFirstFormfc} handleSubmit = {this.handleSubmit} />
		            <Container className="stfRight">
			            <h1>AdHoc App</h1>
			        	<h2>{title}</h2>
			        	<hr />
			        	<div className="wrappingform">
				        	<div style={ this.state.showFirstForm ? {display: "block"} : {display: "none"} }>
						        <table>
						        	<tr>
						        		<td>
						        			<input name="uuid" id="uuid" type="hidden" value={this.state.uuid} />
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
					        <div style={ this.state.showSelectCSV ? {display: "block"} : {display: "none"} }>
					        	<Form>
								    <fieldset>
						                <Label htmlFor="filecsv">CSV fil:</Label>
						                <Input name="filecsv" id="filecsv" type="file" accept=".csv, .xml, .xls, .xlsx"  onChange={this.onFileCSVChangeHandler} />				               
						            </fieldset>
					            </Form>
					        </div>
					        <div style={ this.state.showSelectWORD ? {display: "block"} : {display: "none"} }>
					        	<Form>
								    <fieldset>
						                <Label htmlFor="fileword">WORD / PDF fil:</Label>
						                <Input name="fileword" id="fileword" type="file" accept=".pdf, .doc, .docx, .rtf"  onChange={this.onFileWORDChangeHandler} />				               
						            </fieldset>
					            </Form>
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