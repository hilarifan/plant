import LoadingPage from './pages/LoadingPage/LoadingPage'
import LoginPage from './pages/LoginPage/LoginPage'
import SignupPage from './pages/SignupPage/SignupPage'
import HomePage from './pages/HomePage/HomePage'
import ProfilePage from './pages/ProfilePage/ProfilePage'
import GuestPage from './pages/GuestPage/GuestPage'

// ADD UR PAGE
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path='/' exact element={<LoadingPage/>} />
          <Route path='/login' element={<LoginPage/>} />
          <Route path='/signup' element={<SignupPage/>} />
          <Route path='/home' element={<HomePage/>} />
          <Route path='/home/:id' element={<HomePage/>} />
          <Route path='/profile' element={<ProfilePage/>} />
          <Route path='/guest' element={<GuestPage/>} />

          
        </Routes>
      </div>
    </Router>
  );
}

export default App;