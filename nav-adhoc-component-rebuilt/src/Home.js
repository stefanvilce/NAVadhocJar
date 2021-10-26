import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Container } from 'reactstrap';
import { Knapp } from 'nav-frontend-knapper';
import { withRouter } from 'react-router-dom';

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container className="stfRight">
                	<h1>AdHoc App</h1>
                	<h2>Welcome!</h2>
                	<hr />
                	<p>Welcome on the AdHoc App!</p>
                </Container>
            </div>
        );
    }
}
export default withRouter(Home);