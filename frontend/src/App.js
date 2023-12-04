import './App.css';
import {Routes, Route} from 'react-router-dom';
import Chat from "./components/Chat";
import HomePage from "./components/HomePage";
import Signup from "./components/Signup";
import Login from "./components/Login";
import ServiceBrowser from './components/ServiceBrowser';
import ServiceProviderProfile from './components/ServiceProviderProfile';
import Reviews from './components/Reviews';
import ServiceProviderSignUp from './components/ServiceProviderSignUp';
import ServiceProviderLogIn from './components/ServiceProviderLogIn';
import ServiceProviderOrders from './components/Orders';

function App() {
  return (

    <div className="App">
      <Routes>
          <Route path="/chat" element={<Chat />} />
          <Route path="/" element={<HomePage />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
          <Route path='/ServiceBrowser' element={<ServiceBrowser/>}/>
          <Route path='service-provider-profile/:id' element={<ServiceProviderProfile/>}/>
          <Route path='/service-provider/:id/reviews' element={<Reviews/>}/>
          <Route path='/ProviderSignUp' element={<ServiceProviderSignUp/>}/>
          <Route path='/ProviderLogIn' element={<ServiceProviderLogIn/>}/>
          <Route path='/Orders' element={<ServiceProviderOrders/>}/>
        </Routes>
    </div>

  );
}

export default App;
