import React from 'react';
import './App.css';
import Home from "./Home";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import GithubRedirectPage from "./GithubRedirectPage";

function App() {


  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/users/oauth/github" element={<GithubRedirectPage  />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
