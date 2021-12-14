import React, {Component} from 'react';
import {Link} from 'react-router-dom';
//import { Hovedknapp, Fareknapp } from 'nav-frontend-knapper';
import { Container, Row, Column } from "nav-frontend-grid";
import { Panel } from 'nav-frontend-paneler';
import { Hovedknapp, Fareknapp } from 'nav-frontend-knapper';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
        this.go2nyjobb = this.go2nyjobb.bind(this);
        this.knappBrevmottaker = this.knappBrevmottaker.bind(this);
        this.state = {
        		link: "",
        		knappBrevmottakerliste: "Brevmottakerliste"
        };
    }
    
    componentDidMount() {
        this.setState({link: window.location.pathname.split('/')[1]});
    }
    

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }
    
    knappBrevmottaker(ind){
    	if(ind === 1)
    		this.setState({knappBrevmottakerliste: "Oppdater status info"})
    	else
    		this.setState({knappBrevmottakerliste: "Brevmottakerliste"})
    }
    
    
    go2nyjobb() {
    	//Mai tin functia asta aici doar ca sa mai verific din cand in cand care e starea// de testare
    	//this.props.history = useHistory();
    	
    	const applicationPath = window.location.pathname.split('/')[1];
    	console.log("Este calea: " + applicationPath);
        //this.props.history.push('/nyjobb');
        
        //const history = useHistory();
        //history.push("/nyjobb");
    }
    
     

    render() {
    	
    	var getConsole = this.props.getConsole;
    	var reloadTasks = this.props.reloadTasks;
    	var showSelectFirstFormfc = this.props.showSelectFirstFormfc;
    	var showSelectCSVfc = this.props.showSelectCSVfc;
    	var showSelectWORDfc = this.props.showSelectWORDfc;
    	var handleSubmit = this.props.handleSubmit;
    	var disableKnapp = this.props.disableKnapp;
    	    	    	
    	
        return <><Panel><Container className="nav-empower-frontend-container">
					        <Row className="nav-empower-frontend-row">
						        <Column className="nav-empower-frontend-column-buttons col-sm-2bis">
						        {this.state.link == "nyjobb" 
						        	?
							        	<ul className="nav-empower-button-list">
							            	<li><Hovedknapp className="nav-empower-button" onClick={() => { showSelectCSVfc(); }}>Last inn mottakersliste</Hovedknapp></li>
							            	<li><Hovedknapp className="nav-empower-button" onClick={() => { showSelectWORDfc(); }}>Last inn brevmal</Hovedknapp></li>
							            	<li>
							            	{ disableKnapp ? 
							            		 <Hovedknapp disabled className="nav-empower-button">Lagre ny jobb</Hovedknapp>
							            		: 
							            		 <Hovedknapp className="nav-empower-button" onClick={() => { handleSubmit(); }}>Lagre ny jobb</Hovedknapp>
							            	}
							            	</li>
							            	
							            	<li><Link to="/"><Fareknapp className="nav-empower-button knapp--fare">Avbryt</Fareknapp></Link></li>					            	
							            </ul>							           
						            :
							            <ul className="nav-empower-button-list">
							            	<li><Link to="/"><Hovedknapp className="nav-empower-button">Startside</Hovedknapp></Link></li>
							            	<li><Link to="/nyjobb"><Hovedknapp className="nav-empower-button">Ny jobb</Hovedknapp></Link></li>						            	
							            	{this.state.link == "tasks"
							                    ? 
							                    <li><Hovedknapp className="nav-empower-button" onClick={() => { reloadTasks(); this.knappBrevmottaker(-1); }}>Jobboversikt</Hovedknapp></li>
							                	: 
							                	<li><Link to="/tasks"><Hovedknapp className="nav-empower-button">Jobboversikt</Hovedknapp></Link></li>
							                }
							            	{this.state.link == "tasks"
							                    ? 
							                    <li><Hovedknapp className="nav-empower-button" onClick={() => { getConsole(); this.knappBrevmottaker(1); }}>{this.state.knappBrevmottakerliste}</Hovedknapp></li>							                    
							                	: null
							                }							            	
							            </ul>
						        }   
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