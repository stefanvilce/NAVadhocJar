import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import { Knapp } from 'nav-frontend-knapper';
import { withRouter } from 'react-router-dom';

class Tasks extends Component {
	
	constructor(props) {
        super(props);
        this.state = {tasks: []};
    }

    componentDidMount() {
        fetch('/task/all')
            .then(response => response.json())
            .then(data => this.setState({tasks: data}));
    }
	
    render() {
    	const {tasks} = this.state;
    	/*
    	if (isLoading) {
            return <p>Loading...</p>;  style={{whiteSpace: 'nowrap'}}
        }
        */
    	
    	const tasksList = tasks.map(task => {
            return <tr key={task.task_id}>
            	<td>{task.task_id}</td>
            	<td>{task.status}</td>
                <td>{task.requester}</td>
                <td>{task.date_received}</td>
                <td>{task.max_doc_split}</td>
                <td>{task.docs_in_job}</td>
                <td>{task.docs_distributed}</td>
            </tr>
        });
	
    
        return (
            <div>
                <AppNavbar/>
                <Container className="stfRight">
	            	<h1>AdHoc App</h1>
	            	<h2>Task tabellen</h2>
	            	<hr />	            	
	            	<table className="tabell tabell--stripet">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Status</th>
                        <th>Requester</th>
                        <th>Date received</th>
                        <th>Max doc split</th>
                        <th>Docs in Job</th>
                        <th>Docs distributed</th>
                    </tr>
                    </thead>
                    <tbody>
                    {tasksList}
                    </tbody>
                </table>
	            	
	            </Container>
            </div>
        );
    }
}
export default withRouter(Tasks);