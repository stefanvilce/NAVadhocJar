import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Container, Form } from 'reactstrap';
import { Link, withRouter } from 'react-router-dom';
import { Input, Label } from 'nav-frontend-skjema';



class Nyjobb extends Component {
	
	constructor(props) {
        super(props);
        this.addTheTitle = this.addTheTitle.bind(this);
        this.showSelectCSVfc = this.showSelectCSVfc.bind(this);
        this.showSelectWORDfc = this.showSelectWORDfc.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onFileChangeHandler = this.onFileChangeHandler.bind(this);
        this.state = {
        		title: "Ny jobb, lagre fil",
        		job: 0,
        		uuid: "",
        		selectedFile: null,
        		showFirstForm: true,
        		showSelectCSV: false,
        		showSelectWORD: false
        };
    }
	
	

    addTheTitle(){
    		return "ADHOC :: Ny jobb Vechea structura";
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
    
    
    handleChange = (ev) => {
    	this.setState({uuid: ev.target.value});
    }


    handleSubmit = (event) => {
      event.preventDefault();
      console.log("Par.1 " + this.state.uuid);
      console.log("Par.2 " + this.state.selectedFile);
      
      const formData = new FormData();
      formData.append('uuid', this.state.uuid);
      formData.append('file', this.state.selectedFile); //this.fileInput.files[0],
      fetch('/upload', {
          method: 'post',
          body: formData
      }).then(res => {
          if(res.ok) {
              console.log(res.data);
              alert("File uploaded successfully.")
          }
      });      
    }
    
    
	
    render() {
    	const props = {
    		      dangerouslySetInnerHTML: { __html: '<meta http-equiv="refresh" content="0; url=/nyjobb" />' },
	    };    	
    	const {title} = this.state;
    	//Here I have to get further with the Form in REACT format. For the moment I keep this redirect to the Form in java format.  
    	
        //const title = <h2>{'Importer mottaker fil'}</h2>;
        const divin = <div>{' '}</div>;
        // style={ this.state.showFirstForm ? {backgroundColor: "lightblue"} : null 
       
        return (<React.Fragment>
		            <AppNavbar/>
		            <Container className="stfRight">
			            <h1>AdHoc App</h1>
			        	<h2>{title}</h2>
			        	<hr />			        	
			        	<div className="wrappingform">
				          <Form onSubmit={this.handleSubmit}>
				           
				            <fieldset>
				              <Label htmlFor="UUID">UUID:</Label>
				                <Input name="UUID" id="UUID" type="text" value={this.state.uuid} onChange={this.handleChange} />
				            </fieldset>
				            <fieldset>
				                <Label htmlFor="fil">Valgt fil:</Label>
				                <Input name="file" id="file" type="file"  onChange={this.onFileChangeHandler} />				               
				            </fieldset>
				            <fieldset>
				            		<input type='submit' name='submit' value='Last opp fil' className='knapp' />
				            		<input type='reset' name='cancel' value='Avbryt' className='knapp knapp--fare' id="cancelbutton" onClick="javascript:window.location='/';"  />				            		
				            </fieldset>
				            {divin}
				            
				          </Form>
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