import './App.css';
import ServiceProviderProfile from "./components/ServiceProviderProfile";
import {Routes, Route} from 'react-router-dom';
import ServiceBrowser from "./components/ServiceBrowser";

function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/service-provider-profile/:id" element={<ServiceProviderProfile />} />
          <Route path="/service-providers" element={<ServiceBrowser />} />
        </Routes>
    </div>
  );
}

export default App;
