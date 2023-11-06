import './App.css';
import ServiceProviderProfile from "./components/ServiceProviderProfile";
import {Routes, Route} from 'react-router-dom';
import Chat from "./components/Chat";
import Rating from "./components/Rating";

function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/service-provider-profile" element={<ServiceProviderProfile />} />
          <Route path="/chat" element={<Chat />} />
          <Route path="/rating" element={<Rating />} />
        </Routes>
    </div>
  );
}

export default App;
