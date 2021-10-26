import React, {Component} from 'react';
import {Navbar, NavbarBrand} from 'reactstrap';
import {Link} from 'react-router-dom';
//import { Hovedknapp, Fareknapp } from 'nav-frontend-knapper';
import { Container, Row, Column } from "nav-frontend-grid";
import { Panel } from 'nav-frontend-paneler';
import { Hovedknapp } from 'nav-frontend-knapper';
//import { useHistory } from 'react-router-dom';
//import { Redirect } from 'react-router-dom';


export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
        this.go2nyjobb = this.go2nyjobb.bind(this);
        //this.handleClick = this.handleClick.bind(this);
        //this.history = this.history.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }
    go2nyjobb() {
    	//this.props.history = useHistory();
        this.props.history.push('/nyjobb');
        
        //const history = useHistory();
        //history.push("/nyjobb");
    }
    
     

    render() {
    	
        //handleClick = () => history.push('/nyjobb');
    	/*function handleClick() {
    		let history = useHistory();
    	    history.push("/nyjobb");
    	} */   
    	//const history = useHistory();
        return <><Panel><Container className="nav-empower-frontend-container">
			        <Row className="nav-empower-frontend-row">
				        <Column className="nav-empower-frontend-column-buttons col-sm-2">
				            <ul className="nav-empower-button-list">
				            	<li><Link to="/"><Hovedknapp className="nav-empower-button">Home</Hovedknapp></Link></li>
				            	<li><Link to="/tasks"><Hovedknapp className="nav-empower-button">Jobb list</Hovedknapp></Link></li>
				            	<li><Link to="/r_docreceiver"><Hovedknapp className="nav-empower-button">Doc receiver</Hovedknapp></Link></li>
				            	<li><Link to="/r_nyjobb"><Hovedknapp className="nav-empower-button">Ny Jobb</Hovedknapp></Link></li>				            	
				            </ul>
				        </Column>
				    </Row>    
				    	</Container>
				</Panel>
        </>;
    }
}
/*
 * 
 * <Navbar color="dark" dark expand="md">
            		<NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            </Navbar>
 */ 


/*
<Panel>
	            <Container className="nav-empower-frontend-container">
	                <Row className="nav-empower-frontend-row">
	                    <Column className="nav-empower-frontend-column-buttons col-sm-2">
	                        <ul className="nav-empower-button-list">
	                        	<li><Knapp tag={Link} to="/">Home</Knapp></li>
	                        	<li><Knapp tag={Link} to="/">Ny Jobb</Knapp></li>
	                        </ul>
	                    </Column>
	                </Row>    
	              </Container>
	             </Panel>
*/