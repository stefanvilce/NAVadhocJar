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
        this.addTheTitle = this.addTheTitle.bind(this);
        this.getTaskId = this.getTaskId.bind(this);
        this.getConsole = this.getConsole.bind(this);
        this.state = {
        		tasks: [],
        		button: 'Gicule',
        		job: '200'
        };
    }

    componentDidMount() {
        fetch('/task/all')
            .then(response => response.json())
            .then(data => this.setState({tasks: data}));
        document.title = this.addTheTitle();
        console.log("Textele care vin si pleaca.....");
    }
    
    
    addTheTitle(){
    		return "Texte de salvat";
    }
    
    getTaskId(taskId){
    	alert("Goguleee, " + this.state.button);
    	this.setState({job: taskId}).then(this.getConsole());    	
    }
    
    getConsole(){
    	//functia asta este doar de test
    	console.log("Si acum, esteeee: " + this.state.job);
    }
	
    
    render() {
    	const {tasks} = this.state;
    	/*
    	if (isLoading) {
            return <p>Loading...</p>;  style={{whiteSpace: 'nowrap'}}
        }
        */
    	
    	//console.log("Test, bueey");l
    	
    	const tasksList = tasks.map(task => {
            return <tr key={task.task_id} onClick={() => { this.getTaskId(task.task_id) }}>
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
	            	<h2>Jobb oversikt</h2>
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