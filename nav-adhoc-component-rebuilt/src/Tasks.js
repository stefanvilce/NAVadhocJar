import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Button, Container } from 'reactstrap';
import { Knapp } from 'nav-frontend-knapper';
import { withRouter } from 'react-router-dom';
import "nav-frontend-tabell-style";

class Tasks extends Component {
	
	constructor(props) {
        super(props);
        this.addTheTitle = this.addTheTitle.bind(this);
        this.getTaskId = this.getTaskId.bind(this);
        this.getConsole = this.getConsole.bind(this);
        this.reloadTasks = this.reloadTasks.bind(this);
        this.changeColor = this.changeColor.bind(this);
        this.handleKeyPress = this.handleKeyPress.bind(this);
        this.escFunction = this.escFunction.bind(this);
        this.state = {
        		tasks: [],
        		docs: [],
        		showDocs: false,
        		h2: 'Jobboversikt',
        		job: 0,
        		selectedRow: 0,
        		setEditMode: false,
        		editTaskId: null
        };
    }

    componentDidMount() {
        fetch('/task/all')
            .then(response => response.json())
            .then(data => this.setState({tasks: data}));
        document.title = this.addTheTitle();
        document.addEventListener("keydown", this.escFunction, false);
    }
    
    
    addTheTitle(){
    		return "ADHOC :: NAV app";
    }
    
    getTaskId(taskId){
    	this.setState({job: taskId});
    }
    
    getConsole(){
    	if(this.state.job < 1){
    		alert('Du må velge en linje først.');
    	} else {
	    	var doc_receiver_taskui_link = '/doc_receiver/taskid/' + this.state.job;
	    	this.setState({ showDocs: true, h2: "Brevmottakerliste" });
	    	fetch(doc_receiver_taskui_link).then(response => response.json()).then(data => this.setState({docs: data}));
    	}    	
    }
    
    reloadTasks(){
    	this.setState({ showDocs: false, h2: "Jobboversikt" });
    	fetch('/task/all')
	        .then(response => response.json())
	        .then(data => this.setState({tasks: data}));
    }
    
          
    changeColor(i){
       this.setState({selectedRow: i });
    }
     
     
    escFunction(event){
	    if(event.keyCode === 27) {
	    	this.setState({ setEditMode: false, editTaskId: null });
	    }
    }
     
     
     handleKeyPress = (event) => {    	 
    	  if(event.key === 'Enter'){
    		const elId = this.state.editTaskId;  
    		const inputValue = document.getElementById("maxdocsInput" + elId).value;  
    	    //console.log('Enter press here! ' + inputValue);    	    
    	    var requestOptions = {
    		  method: 'POST',
    		  redirect: 'follow'
    		};
    		fetch("/task/update?task_id="+elId+"&max_doc_split="+inputValue, requestOptions)
    		  .then(response => response.text())
    		  .then(result => console.log(result))
    		  .catch(error => console.log('error', error));
    	    this.setState({ setEditMode: false, editTaskId: null });    	    
    	    setTimeout(() => {this.reloadTasks()}, 500);
    	  }
     }
	
    
     render() {
    	const {tasks} = this.state;
    	var {docs} = this.state;
    	var {showDocs} = this.state;
    	var {h2} = this.state;
    	
    	/*
    	if (isLoading) {
            return <p>Loading...</p>;  style={{whiteSpace: 'nowrap'}}
        }
        */
    	
    	var tasksList = tasks.map(task => {
            return <tr key={task.task_id} className={this.state.selectedRow === task.task_id ? "rowSelected" : "linje" } onClick={() => { this.getTaskId(task.task_id); this.changeColor(task.task_id); }}>
            	<td>{task.task_id}</td>
            	<td>{task.status}</td>
                <td>{(task.date_received !== null) ? task.date_received.substring(0, 10) : " - "}</td>
                <td>{(task.deadline !== null) ? task.deadline.substring(0, 10) : "-" }</td>
                <td id={'maxdocs' + task.task_id} onClick={() => { this.setState({ setEditMode: true, editTaskId: task.task_id }); }} onBlur={() => { this.setState({ setEditMode: false, editTaskId: null }); }}>{
                	(this.state.setEditMode && this.state.editTaskId === task.task_id) ?
                			( <input id={'maxdocsInput' + task.task_id} className="inputMaxDocsSplit" size="4" defaultValue={task.max_doc_split} onClick={() => console.log('Teste')} onKeyPress={this.handleKeyPress}  /> ) : 
                					(
                					task.max_doc_split
                					)
                	}
                </td>
                <td>{task.docs_in_job}</td>
                <td>{task.docs_distributed}</td>
                <td>{(task.expire_date !== null) ? task.expire_date.substring(0, 10) : " - "}</td>
                <td>{task.doc_format}</td>
                <td>{task.csserver_id}</td>
                <td>{task.preview_doc}</td>
            </tr>            
        });
    	
    	var theWholeTaskTable = (    		
				    		<table className="tabell tabell--stripet">
				            <thead>
				            <tr>
				                <th>Jobb<br />nummer</th>
				                <th>Status</th>
				                <th>Date received</th>
				                <th>Utsendelsesfrist</th>
				                <th>Max doc split</th>
				                <th>Antall<br />dokumenter<br />i jobb</th>
				                <th>Dokumenter<br />distribuert</th>
				                <th>Expire<br />date</th>
				                <th>Doc<br />format</th>
				                <th>CS Server id</th>
				                <th>Preview<br />doc</th>
				            </tr>
				            </thead>
				            <tbody>
				            {tasksList}
				            </tbody>
				        </table>    		
    	);
    	
    	/****/
    	
    	var docsList = docs.map(doc => {
            return <tr key={doc.doc_uuid}>
            	<td>{doc.doc_uuid}</td>
            	<td>{doc.task_uuid}</td>
                <td>{doc.journal_id}</td>
                <td>{doc.status}</td>
                <td>{doc.address1}</td>
                <td>{doc.address2}</td>
                <td>{doc.address3}</td>
                <td>{doc.zip}</td>
                <td>{doc.city}</td>                
                <td>{doc.field1}</td>
                <td>{doc.field2}</td>
                <td>{doc.field3}</td>
                <td>{doc.field4}</td>
                <td>{doc.field5}</td>
                <td>{doc.field6}</td>
                <td>{doc.field7}</td>
                <td>{doc.field8}</td>
                <td>{doc.field9}</td>                
                <td>{doc.country}</td>
                <td>{doc.DISTRIBUERJOURNALPOST_ID}</td>
                <td>{doc.person_id}</td>
                <td>File</td>
            </tr>
        });
    	
    	var theWholeDocTable = (<table className="tabell tabell--stripet">
				        <thead>
				        <tr>
				            <th>Dokument<br />id</th>
				            <th>Jobb<br />nummer</th>
				            <th>Journalid</th>
				            <th>Status</th>
				            <th>Navn</th>
				            <th>Adresse 1</th>
				            <th>Adresse 2</th>
				            <th>Postnummer</th>
				            <th>Poststed</th>				            
				            <th>Flettefelt 1</th>
				            <th>Flettefelt 2</th>
				            <th>Flettefelt 3</th>
				            <th>Flettefelt 4</th>
				            <th>Flettefelt 5</th>
				            <th>Flettefelt 6</th>
				            <th>Flettefelt 7</th>
				            <th>Flettefelt 8</th>
				            <th>Flettefelt 9</th>				            
				            <th>Land</th>
				            <th>Distribusjonsid</th>
				            <th>Fødselsnummer</th>
				            <th>Dokument</th>
				        </tr>
				        </thead>
				        <tbody>
				        {docsList}
				        </tbody>
				    </table>
    	);
    	
    	const oppdatterStatus = (
    			<div className="central4button">
    				<Knapp mini onClick={() => { this.getConsole(); }}>Oppdater status info</Knapp>
    			</div>
    	);
	
    
        return (
            <div>
                <AppNavbar getConsole = {this.getConsole} reloadTasks = {this.reloadTasks} />
                <Container className="stfRight">
	            	<h1>AdHoc App</h1>
	            	<h2>{h2}</h2>
	            	<hr />
	            	{showDocs ? theWholeDocTable : theWholeTaskTable}
	            	{showDocs ? oppdatterStatus : null}
	            </Container>
            </div>
        );
    }
}
export default withRouter(Tasks);