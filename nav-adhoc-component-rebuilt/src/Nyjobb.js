import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import { withRouter } from 'react-router-dom';

class Nyjobb extends Component {
    render() {
    	const props = {
    		      dangerouslySetInnerHTML: { __html: '<meta http-equiv="refresh" content="0; url=/nyjobb" />' },
	    };    	
    	//const {item} = this.state;
    	//Here I have to get further with the Form in REACT format. For the moment I keep this redirect to the Form in java format.  
    	
        const title = <h2>{'Importer mottaker fil'}</h2>;
        const divin = <div>{'Importer mottaker fil. Si asta este altceva  '}</div>;
        /*
        return (<div>
            <AppNavbar/>
            <Container className="stfRight">
            	<h1>AdHoc App</h1>
            	<h2>{title}</h2>
            	<hr />	            	
            	<Form onSubmit={this.handleSubmit}>
	                mmmm
	                {divin}
	            </Form>
            </Container>
        </div>
	    );*/ 
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