import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import { withRouter } from 'react-router-dom';

class Nyjobb extends Component {
	
	
	constructor(props) {
        super(props);
        this.addTheTitle = this.addTheTitle.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
        		title: "Ny jobb, lagre fil",
        		job: 0,
        		value: "Please, put your UUID"
        };
    }
	
	

    addTheTitle(){
    		return "ADHOC :: Ny jobb";
    }
    
    
    handleChange(event) {
    	this.setState({value: event.target.value});
    }

    handleSubmit(event) {
      alert('An essay was submitted: ' + this.state.value);
      event.preventDefault();
    }
    
    
	
    render() {
    	const props = {
    		      dangerouslySetInnerHTML: { __html: '<meta http-equiv="refresh" content="0; url=/nyjobb" />' },
	    };    	
    	const {title} = this.state;
    	//Here I have to get further with the Form in REACT format. For the moment I keep this redirect to the Form in java format.  
    	/*
        //const title = <h2>{'Importer mottaker fil'}</h2>;
        const divin = <div>{'Importer mottaker fil. Si asta este altceva . . .   '}</div>;
       
        return (<React.Fragment>
		            <AppNavbar/>
		            <Container className="stfRight">
			            <h1>AdHoc App</h1>
			        	<h2>{title}</h2>
			        	<hr />
			        	<div className="wrappingform">
				          <Form onSubmit={this.handleSubmit}>
				            <fieldset>
				              <label>
				                <p>UUID:</p>
				                <Input name="UUID" type="text" value={this.state.value} onChange={this.handleChange} />
				              </label>
				            </fieldset>
				            <fieldset>
				              <label>
				                <p>Valgt fil:</p>
				                <Input name="fil" type="file" />
				              </label>
				            </fieldset>
				            <fieldset><input type="submit" value="Submit" /></fieldset>				            
				            {divin}
				          </Form>
			          </div>
		            </Container>
		        </React.Fragment>
	    ); 
        */
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
        
        return (
                <div  {...props}></div>
            );
    }
}
export default withRouter(Nyjobb);