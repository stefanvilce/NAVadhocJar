import React, { Component } from 'react';
import './App.css';
import { withRouter } from 'react-router-dom';

class Nyjobb extends Component {
    render() {
    	const props = {
    		      dangerouslySetInnerHTML: { __html: '<meta http-equiv="refresh" content="0; url=/nyjobb" />' },
	    };
	    return (
	        <div {...props}></div>
	    ); /*
        return (
            <div>
                <AppNavbar/>
                <Container>                	
                    <p dangerouslySetInnerHTML={{__html: thisIsMyCopy}}"></p>                    
                </Container>
            </div>
        );*/
    }
}
export default withRouter(Nyjobb);