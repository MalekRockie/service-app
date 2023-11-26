import './App.css';
import ServiceProviderProfile from "./components/ServiceProviderProfile";
import {Routes, Route} from 'react-router-dom';
import ServiceBrowser from "./components/ServiceBrowser";
import Reviews from "./components/Reviews";
import Chat from "./components/Chat";
import Orders from "./components/Orders";

function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/service-provider-profile/:id" element={<ServiceProviderProfile />} />
          <Route path="/service-provider/:id/reviews" element={<Reviews />} />
          <Route path="/service-provider/:id/orders" element={<Orders />} />
          <Route path="/service-providers" element={<ServiceBrowser />} />
          <Route path="/chat" element={<Chat />} />
        </Routes>
    </div>
  );
}

export default App;
