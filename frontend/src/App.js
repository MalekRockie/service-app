import './App.css';
import Navbar from "./components/Navbar";
import ServiceProviderProfile from "./components/ServiceProviderProfile";
import { BrowserRouter as Router,Routes, Route} from 'react-router-dom';
import Chat from "./components/Chat";
import Rating from "./components/Rating";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/service-provider-profile" element={<ServiceProviderProfile />} />
          <Route path="/chat" element={<Chat />} />
          <Route path="/rating" element={<Rating />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
