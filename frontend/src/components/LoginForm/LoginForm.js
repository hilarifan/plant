import React, { useState } from 'react';
import './LoginForm.css';
import { Link, useNavigate } from 'react-router-dom';
import validator from 'validator';
import axios from "axios";
 
export default function Form() {

    // States for registration
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    // States for checking the errors
    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = useNavigate();

    // Handling the name change
    const handleUsername = (e) => {
        setUsername(e.target.value);
        setSubmitted(false);
    };
    const handlePassword = (e) => {
        setPassword(e.target.value);
        setSubmitted(false);
    };

    // Handling the form submission
    // use api call to setError, and setSubmitted
    const handleSubmit = async(e) => {
        e.preventDefault();

        if (username === '' || password === '') {
            setError(true);
            setErrorMessage('Please fill in all the fields');
        }
        else {
            const user = {
                username: username,
                password: password
            }
            //console.log(JSON.stringify(user));
            const response = await fetch("http://localhost:8080/login", {
                method: 'POST',
                headers: { 'Content-Type': 'application/json'},
                body: JSON.stringify(user)
            });
            
            //object gets the json object response from backend
            const obj = await response.json();
            
            // console.log(obj)
            // console.log(obj.error);

            //check bj.JsonParameter 
            if (obj.error == "true") {
                setError(true);
                setErrorMessage(obj.errorMsg);
                //console.log(obj.errorMsg);
            }
            else {
                setError(false);
                setSubmitted(true);
                navigate("/home", {state: {id: obj.id}}); // change this to home later
                console.log(obj.error);
                // if u want to use id do this:
                // const {state} = useLocation();
                // var id = state.id;
           }
        }
    };

    // Showing error message if error is true
    const handleErrorMessage = () => {
        return (
            <div className="error" style={{display: error ? '' : 'none'}}>
                <h1>{errorMessage}</h1>
            </div>
        );
    };

    return (
        <div className="form">
            <h1 className="form-header">login</h1>

            <div className="messages"> {handleErrorMessage()}</div>
            
            <form>
                {/* Labels and inputs for form data */}
                <div className="input-container">
                    <label className="label">username</label>
                    <input onChange={handleUsername} className="input" value={username} type="text" />
                </div>

                <div className="input-container">
                    <label className="label">password</label>
                    <input onChange={handlePassword} className="input" value={password} type="password" />
                </div>
                <div className="link-container">
                    <Link to={'/signup'} className="signup-link">don't have an account?</Link>  
                </div>                
                <button onClick={handleSubmit} className="btn" type="submit">Submit</button>
            </form>

            <Link to={'/guest'} className="guest-link">view as guest</Link>
        </div>
    );
}
