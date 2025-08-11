import logo from './logo.svg';
import './App.css';
import {Routes, Route} from "react-router-dom"
import SignUp from './components/SignUp';
import Login from './components/Login';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path = "/" element = {<Login />}/>
        <Route path = "/SignUp" element = {<SignUp/>}/>
      </Routes>
    </div>
  );
}

export default App;
