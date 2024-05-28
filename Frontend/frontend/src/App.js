import logo from './logo.svg';
import './App.css';
import ResponsiveAppBar from './Components/Appbar';
import Ingredients from './Components/Ingredients';

function App() {
  return (
    <div className="App">
    <ResponsiveAppBar/>
    <Ingredients/>
    </div>
  );
}

export default App;
