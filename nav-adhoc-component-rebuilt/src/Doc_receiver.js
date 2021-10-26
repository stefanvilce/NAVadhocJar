import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container, Table } from 'reactstrap';
import { Knapp } from 'nav-frontend-knapper';
import { withRouter } from 'react-router-dom';
import "nav-frontend-tabell-style";

class Doc_receiver extends Component {
	
	constructor(props) {
        super(props);
        this.state = {docs: []};
    }

    componentDidMount() {
        fetch('/doc_receiver/all')
            .then(response => response.json())
            .then(data => this.setState({docs: data}));
    }
	
    render() {
    	const {docs} = this.state;
    	/*
    	if (isLoading) {
            return <p>Loading...</p>;  style={{whiteSpace: 'nowrap'}}
        }
        */
    	
    	const docsList = docs.map(doc => {
            return <tr key={doc.doc_uuid}>
            	<td>{doc.doc_uuid}</td>
            	<td>{doc.task_uuid}</td>
                <td>{doc.doc_name}</td>
                <td>{doc.address1}</td>
                <td>{doc.address2}</td>
                <td>{doc.zip}</td>
                <td>{doc.city}</td>
                <td>{doc.country}</td>
            </tr>
        });
    	
        return (
            <div>
                <AppNavbar/>                
                <Container className="stfRight">
                	<h1>AdHoc App</h1>
                	<h2>Doc_receiver tabellen</h2>
                	<hr />
                	<table className="tabell tabell--stripet">
	                    <thead>
	                    <tr>
	                        <th>Doc UUID</th>
	                        <th>Task UUID</th>
	                        <th>Doc Name</th>
	                        <th>Address</th>
	                        <th> * </th>
	                        <th>ZIP</th>
	                        <th>City</th>
	                        <th>Country</th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    {docsList}
	                    </tbody>
	                </table>
                </Container>
            </div>
        );
    }
}
export default withRouter(Doc_receiver);