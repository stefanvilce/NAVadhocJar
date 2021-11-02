import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import Tasks from './Tasks';
import Nyjobb from './Nyjobb';
import Doc_receiver from './Doc_receiver';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
//import ClientList from './ClientList';
//import ClientEdit from "./ClientEdit";

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/nyjobb' exact={true} component={Nyjobb}/>
            <Route path='/tasks' component={Tasks}/>
            <Route path='/r_docreceiver' component={Doc_receiver}/>
          </Switch>
        </Router>        
    )
  }
}

export default App;



/*
 *  <Route path='/clients' exact={true} component={ClientList}/>
            <Route path='/clients/:id' component={ClientEdit}/>
 * 
 
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
*/