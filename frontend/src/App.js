import './App.css';
import {Routes, Route} from 'react-router-dom';
import Chat from "./components/Chat";
import HomePage from "./components/HomePage";
import Signup from "./components/Signup";
import Login from "./components/Login";
import ServiceBrowser from './components/ServiceBrowser';


function App() {
  return (

    <div className="App">
      <Routes>
          <Route path="/chat" element={<Chat />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
          <Route path='/ServiceBrowser' element={<ServiceBrowser/>}/>
        </Routes>
    </div>

  );
}

export default App;
