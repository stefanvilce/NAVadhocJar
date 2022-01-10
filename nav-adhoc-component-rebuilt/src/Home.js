import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Container } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import { Knapp } from 'nav-frontend-knapper';
import { Input } from 'nav-frontend-skjema';

class Home extends Component {
	
	 constructor(props) {
	        super(props);
	        this.handleChange = this.handleChange.bind(this);
	        this.handleSubmit = this.handleSubmit.bind(this);
	        this.handleLogout = this.handleLogout.bind(this);
	        this.checkAllFields = this.checkAllFields.bind(this);
	        this.state = {
	        		isOpen: false,
	        		loggedin: "",
	        		bruker: "",
	        		passord: "",
	        		disableKnapp: true,
	        		showGreenMsg: false,
	        		showRedMsg: false,
	        		refreshLogout: false
	        };        
	 }
	
	 
	 componentDidMount() {
	        localStorage.getItem("loggedin") == "YES" ? this.setState({ loggedin: "YES"}) : this.setState({ loggedin: "NO"});
	 }
	 
	 
	 
	 
	 handleChange = (ev) => {    	
	    	this.setState({[ev.target.name]: ev.target.value}, this.checkAllFields); 
	 }
	 
	 
	 checkAllFields(){
	    	//We have to check all the fields to see if these contain informations. These should not be empty	    	
		 	if(this.state.bruker.length > 2 && this.state.passord.length > 3)
	    		this.setState({disableKnapp: false})
	    	else 
	    		this.setState({disableKnapp: true});
	 }
	 
	 
	 handleSubmit(){
		 	var bruker = this.state.bruker;
		 	var passord = this.state.passord;		 
		 	this.setState({ showGreenMsg: false, showRedMsg: false });
		 	var requestOptions = {
				  method: 'POST',
				  redirect: 'follow'
				};
			fetch("/api/login?username=" + bruker + "&password=" + passord, requestOptions)
			  .then(response => response.json())
			  .then(result => {				  	
				  	var status = result.status;
				  	console.log("And the status is: " + status);
				  	if(status == "success"){
				  		localStorage.setItem("loggedin", "YES");
				  		localStorage.setItem("bruker", result.data.username);
				  		localStorage.setItem("token", result.data.token);
				  		this.setState({ showGreenMsg: true, loggedin: "YES", bruker: result.data.username });
				  	} else {
				  		this.setState({ showRedMsg: true, loggedin: "", bruker: "" });
				  	}
				  })
			  .catch(error => console.log('error', error));
	 }

	 
	 handleLogout() {    	
		 	localStorage.removeItem("bruker");
		 	localStorage.removeItem("loggedin");
	    	this.setState({bruker: "", loggedin: "", refreshLogout: true});
	 }
	 
	 
     render() {    	 
    	 
    	 var theLoginForm = (
    			<React.Fragment>
	     			<div className="loginform">
	     				<h3>Logg inn</h3>
	     				<div className="loginformInn">
		     				<span>Bruker:</span> 
		     				<Input name="bruker" id="bruker" type="text" value={this.state.bruker} onChange={this.handleChange}  />
		     				<br />		
		     				<span>Passord:</span>
		     				<Input name="passord" id="passord" type="password" value={this.state.passord} onChange={this.handleChange}  />
		     				<br />
		     				{this.state.disableKnapp ?
		     						<Knapp disabled id="sendLogin">Send</Knapp>
		     						:
		     						<Knapp id="sendLogin" onClick={() => { this.handleSubmit() }}>Send</Knapp>
		     				}		     				
	     				</div>
	     			</div>
     			</React.Fragment>
     	);
    	 
    	 
    	 var theLoggedinMsg = (
     			<React.Fragment>
 	     			<div className="loginform">
 	     				<h2>Du er logget inn nå</h2>
 	     				<Knapp id="sendLogout" onClick={() => { this.handleLogout() }}>Logg ut</Knapp>
 	     			</div>
      			</React.Fragment>
      	);
    	 
    	 var refreshAfterLoginLogout = '<meta http-equiv="refresh" content="0; url=/" />';
    	 
    	 var loginSuccess = (
    			 <React.Fragment>
    			 	<p dangerouslySetInnerHTML={{__html: refreshAfterLoginLogout}}></p>  
    			 	<p className="available_message">
	    			 	Du er logget inn nå.
	    			 </p>
    			 </React.Fragment>
    			 );
    	 
    	 var loginFail = (
    			 <p className="notavailable_message">
    			 	FEIL!
    			 	<br />
    			 	Brukeren eller passordet er feil.
    			 </p>
    			 );
    	 
    	 
    	 var logoutRedirect = (
    			<p dangerouslySetInnerHTML={{__html: refreshAfterLoginLogout}}></p> 
    	 );
    	 
        return (
            <div>
                <AppNavbar/>
                <Container className="stfRight">
                	<h1>AdHoc App</h1>
                	<h2> </h2>
                	<hr />
                	<p>Velkommen til AdHoc brevproduksjon</p>
                	{ this.state.showGreenMsg ? loginSuccess : null }
                	{ this.state.showRedMsg ? loginFail : null }
                	{ this.state.loggedin == "YES" ? theLoggedinMsg : theLoginForm }
                	
                	{ this.state.refreshLogout ? logoutRedirect : null }
                </Container>
            </div>
        );
     }
}
export default withRouter(Home);