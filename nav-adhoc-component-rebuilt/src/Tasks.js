import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Container } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import "nav-frontend-tabell-style";
import DatePicker from 'react-datepicker';
//import Datetime from 'react-datetime';
//import DateTimePicker from 'react-datetime-picker';
//import "react-datetime/css/react-datetime.css";
import "react-datepicker/dist/react-datepicker.css";



class Tasks extends Component {	

	
	constructor(props) {
        super(props);
        this.addTheTitle = this.addTheTitle.bind(this);
        this.getTaskId = this.getTaskId.bind(this);
        this.getConsole = this.getConsole.bind(this);
        this.reloadTasks = this.reloadTasks.bind(this);
        this.changeColor = this.changeColor.bind(this);
        this.handleKeyPress = this.handleKeyPress.bind(this);
        this.handleDeadlineChange = this.handleDeadlineChange.bind(this);
        this.handleDeadlineSubmit = this.handleDeadlineSubmit.bind(this);
        this.getDocsForPage  = this.getDocsForPage.bind(this);
        this.pageBtnPage = this.pageBtnPage.bind(this);
        this.pageChangeSelect = this.pageChangeSelect.bind(this);
        this.escFunction = this.escFunction.bind(this);
        this.state = {
        		tasks: [],
        		docs: [],
        		showDocs: false,
        		h2: 'Jobboversikt',
        		job: 0,
        		selectedRow: 0,
        		setEditMode: false,
        		setEditModeDeadline: false, 
        		editTaskId: null,
        		deadlineDate: new Date(),
        		docs_rowsPerPage: 5,
        		docs_selectedPage: 1,
        		docs_maxPage: 1
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
	    	this.setState({ showDocs: true, h2: "Brevmottakerliste", docs_rowsPerPage: 5, docs_selectedPage: 1, docs_maxPage: 1 });
	    	var doc_receiverNrDocs_link = '/doc_receiver/nrdocspertaskid/' + this.state.job;
	    	fetch(doc_receiverNrDocs_link).then(responseNr => responseNr.json()).then(
	    			d => this.setState({docs_maxPage: Math.ceil(d.data.founddocsperuuid / this.state.docs_rowsPerPage)}, this.getDocsForPage)	    			
	    	);
    	}    	
    }
    
    
    getDocsForPage(){
    	//this function does the same as getConsole()
    	if(this.state.job < 1){
    		alert('Du må velge en linje først.');
    	} else {
    		var doc_receiver_uuidperpage_link = '/doc_receiver/uuid/perpage/' + this.state.job + '/' + this.state.docs_selectedPage + '/' + this.state.docs_rowsPerPage;
    		fetch(doc_receiver_uuidperpage_link).then(response => response.json()).then(data => this.setState({docs: data}));
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
	    	this.setState({ setEditMode: false, setEditModeDeadline: false, editTaskId: null });
	    }
    }
    
    
     pageGoTilPage = (event) =>{
    	 if(event.key === 'Enter'){
    		 const inputValue = document.getElementById("page").value;
    		 if(inputValue < 1 || inputValue > this.state.docs_maxPage){
    			 alert("Verdien skal bli mellom 1 og " + this.state.docs_maxPage);
    		 } else {
    			 this.setState({ docs_selectedPage: inputValue }, this.getDocsForPage);
    		 }    		     		 
    	 }
     }
     
     
     pageBtnPage(p){
    	 if(this.state.docs_selectedPage == 1 && p == -1){
    		 alert("Denne er først side!");
    	 } else if(this.state.docs_maxPage == this.state.docs_selectedPage && p == 1){
    		 alert("Denne er siste side!");
    	 } else {
    		 var page = this.state.docs_selectedPage;
    		 page = page + p;
    		 this.setState({docs_selectedPage: page}, this.getDocsForPage);
    	 }
     }
     
     pageChangeSelect = (event) => {
    	 this.setState({docs_rowsPerPage: event.target.value, docs_selectedPage: 1}, this.getDocsForPage);
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
    		fetch("/task/update?task_uuid="+elId+"&max_doc_split="+inputValue, requestOptions)
    		  .then(response => response.text())
    		  .then(result => console.log(result))
    		  .catch(error => console.log('error', error));
    	    this.setState({ setEditMode: false, editTaskId: null });    	    
    	    setTimeout(() => {this.reloadTasks()}, 500);
    	  }
     }
     
     
     handleDeadlineSubmit() {
    	 const elId = this.state.editTaskId;
    	 const deadline = this.state.deadlineDate;
    	 console.log("Saved. DATE " + deadline.toISOString());
    	 var requestOptions = {
       		  method: 'POST',
       		  redirect: 'follow'
       		};
       		fetch("/task/updatedeadline?task_uuid="+elId+"&deadline=" + deadline.toISOString(), requestOptions)
       		  .then(response => response.text())
       		  .then(result => { 
       			  console.log(result);
       			  this.setState({ setEditModeDeadline: false, editTaskId: null }, this.reloadTasks);
       			  })       		  
       		  .catch(error => console.log('error', error));
       		setTimeout(() => {console.log("The new deadline was saved!")}, 500);
     }
     
     
     handleDeadlineChange = (date) => { 
     		this.setState({deadlineDate: date});
     }
     
     /*
     calculateMaxPage = (data, rowsPerPage) => {
    	  ///var d = Object.assign({}, data);
    	  console.log(typeof data);
    	  console.log(data);
    	  console.log("calculateMaxPage: data.size -> " + Object.keys(data).length);
    	  const num = Math.ceil(Object.keys(data).length / rowsPerPage);
    	  console.log("calculateMaxPage: num -> " + num);
    	  console.log("calculateMaxPage: data -> " + data);
    	  return num; // I don't know if it is necessary to have this return once we've got state.docs_maxPage
	 };

	 sliceData = (data, page, rowsPerPage) => {
		 var docsMax = this.calculateMaxPage(data, rowsPerPage);
		 var d = Object.assign({}, data);
		 console.log(d);
		 //var docsMax = this.calculateMaxPage(d, rowsPerPage);
		 console.log("RowsPerPage: " + rowsPerPage);
		 console.log("docsMaxPage: " + docsMax);
		 this.setState({docs_rowsPerPage: rowsPerPage, docs_maxPage: docsMax});   	  
		 return Object.values(d.slice((page - 1) * rowsPerPage, page * rowsPerPage));
	 };*/
	
    
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
            return <tr key={task.task_uuid} className={this.state.selectedRow === task.task_uuid ? "rowSelected" : "linje" } onClick={() => { this.getTaskId(task.task_uuid); this.changeColor(task.task_uuid); }}>
            	<td className="alignRight">{task.task_uuid}</td>
            	<td className="alignRight">{task.status}</td>
                <td>{(task.date_received !== null) ? task.date_received.substring(0, 19) : " - "}</td>
                <td>{(task.date_updated !== null) ? task.date_updated.substring(0, 19) : " - "}</td>
                <td id={'deadline' + task.task_uuid} onClick={() => { this.setState({ setEditModeDeadline: true, setEditMode: false, editTaskId: task.task_uuid }); }} onBlur={() => { /*this.setState({ setEditModeDeadline: false, editTaskId: null }); */}} className="editable">
                	{
                	(this.state.setEditModeDeadline && this.state.editTaskId === task.task_uuid) ?
                			(<div><DatePicker id={'deadlineInput' + task.task_uuid}  defaultValue={task.deadline} value={this.state.deadlineDate} 
                				selected={this.state.deadlineDate}
                				onChange={this.handleDeadlineChange}
	                			showTimeSelect
                				dateFormat="y-MM-dd H:mm:ss"
                	         /><button onClick={this.handleDeadlineSubmit}>Lagre</button></div>
                	         ) 
                	         : 
        					 (
        						(task.deadline !== null) ? task.deadline.substring(0, 19) : "-"
        					 )
                	}
                </td>
                <td id={'maxdocs' + task.task_uuid} onClick={() => { this.setState({ setEditMode: true, setEditModeDeadline: false, editTaskId: task.task_uuid }); }} onBlur={() => { this.setState({ setEditMode: false, editTaskId: null }); }} className="editable alignRight">
                	{
                	(this.state.setEditMode && this.state.editTaskId === task.task_uuid) ?
                			( <input id={'maxdocsInput' + task.task_uuid} className="inputMaxDocsSplit" size="4" defaultValue={task.max_doc_split} onKeyPress={this.handleKeyPress}  /> ) : 
                					(
                					task.max_doc_split
                					)
                	}
                </td>
                <td className="alignRight">{task.docs_in_job}</td>
                <td className="alignRight">{task.docs_distributed}</td>
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
				                <th>Jobb<br />mottatt</th>
				                <th>Jobb<br />oppdatert</th>
				                <th>Utsendelsesfrist</th>
				                <th>Antall dokumenter<br />pr kjøring</th>
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
                <td>{doc.distribuerjournalpost_id}</td>
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
    	
    	var paginate = (
    			<div className="center">
    				<button onClick={() => { this.pageBtnPage(-1) }}> &nbsp; &lt; &nbsp; </button> &nbsp;  
    				<button onClick={() => { this.pageBtnPage(1) }}> &nbsp; &gt; &nbsp; </button> 
    				
    				<span> Siden {this.state.docs_selectedPage} av {this.state.docs_maxPage} | </span>
    				
    				<span>Gå til side: 
    					<input id="page" className="inputMaxDocsSplit" size="4" pattern="[0-9]*" 
    						value={this.state.docs_selectedPage}
    						onChange={e => this.setState({docs_selectedPage: e.target.value})} 
    						onBlur={e => (e.target.value == null || e.target.value < 1 || !Number.isInteger(e.target.value) || e.target.value > this.state.docs_maxPage) ?  this.setState({docs_selectedPage: 1}) : null }
    						onKeyPress={this.pageGoTilPage} />
    				</span>
    				<span> | </span>
    				<select id="selectPerPage" onChange={this.pageChangeSelect} value={this.state.docs_rowsPerPage}>
	                    <option value="5">Vis 5</option>
	                    <option value="10">Vis 10</option>
	                    <option value="100">Vis 100</option>
	                    <option value="1000">Vis 1000</option>
	                 </select>
    				
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
	            	{showDocs ? paginate : null}
	            </Container>
            </div>
        );
    }
}
export default withRouter(Tasks);