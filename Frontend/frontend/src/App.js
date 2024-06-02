import './App.css';
import ResponsiveAppBar from './Components/Appbar';
import Ingredients from './Components/Ingredients';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import * as React from 'react';
import Recipes from './Components/Recipes';
import Cooking from './Cooking.json';
import Lottie from 'lottie-react';
import Main from './Main';



function Fridge() {
  const [isFridgeOpen, setIsFridgeOpen] = React.useState(false);

  const handleFridgeClick = () => {
    setIsFridgeOpen(!isFridgeOpen);
  };

  return (
    <div className={`fridge ${isFridgeOpen ? 'open' : 'closed'}`} onClick={handleFridgeClick}>
      
      <div className="fridge-door"></div>
      <div className="fridge-content">
        <Lottie animationData={Cooking}/>
        
        <h1>Welcome to Your Fridge</h1>
        <p>Click to open and explore your ingredients and recipes!</p>
        <div className={`shelf ${isFridgeOpen ? 'visible' : ''}`}>
          <Link to="/ingredients" className="shelf-item">Ingredients</Link>
          <Link to="/recipes" className="shelf-item">Recipes</Link>
          
        </div>
        
      </div>
      
    </div>
    
  );
}

function App() {
  return (
    
    <Router>
      
      <ResponsiveAppBar />
      
      <Routes>
      
        <Route path="/ingredients" element={<Ingredients />} />
        <Route path="/recipes" element={<Recipes />} />
        <Route path="/" element={<Fridge />} />
        
      </Routes>
    </Router>
  );
}

export default App;
