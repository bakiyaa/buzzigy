import React from 'react';
import './App.css';
import Header from './components/Header/Header';
import Home from './components/Home/Home';

function App() {
  return (
    <div className="App">
      <Header />
      <Home />
      {/* Footer could go here */}
    </div>
  );
}

export default App;
