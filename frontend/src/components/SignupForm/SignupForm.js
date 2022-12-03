import { useState } from 'react';
import './SignupForm.css';
import validator from 'validator';
import { Link, useNavigate } from 'react-router-dom';

import axios from "axios";
 
export default function Form() {

    // States for registration
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');

    const navigate = useNavigate();

    // States for checking the errors
    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    // Handling the name change
    const handleUsername = (e) => {
        setUsername(e.target.value);
        setSubmitted(false);
    };
    const handlePassword = (e) => {
        setPassword(e.target.value);
        setSubmitted(false);
    };
    const handleFirstName = (e) => {
        setFirstName(e.target.value);
        setSubmitted(false);
    };
    const handleLastName = (e) => {
        setLastName(e.target.value);
        setSubmitted(false);
    };
    const handleEmail = (e) => {
        setEmail(e.target.value);
        setSubmitted(false);
    };

    // Handling the form submission
    // use api call to setError, and setSubmitted
    const handleSubmit = async(e) => {
        e.preventDefault();
        

        if (username === '' || firstName === '' || lastName === '' || email === '' || password === '') {
            setError(true);
            setErrorMessage('Please fill in all the fields');
        } 
        else if (!validator.isEmail(email))  {
            setError(true);
            setErrorMessage('Please enter a valid email');
        }
        else {

            const user  = {
                username: username,
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password
            }
    
            const response = await fetch("http://localhost:8080/signup", {
                method: 'POST',
                headers: { 'Content-Type': 'application/json'},
                body: JSON.stringify(user),
            })
            
            const obj = await response.json();
            console.log(obj.id);

            if (obj.id == -1) {
                setError(true);
                setErrorMessage("username already exists");
            }
            else {
                setSubmitted(true);
                setError(false);
                navigate("/login", {state: {id: obj.id}}); // change this to home later
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
            <h1 className="form-header">sign up</h1>

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

                <div className="input-container">
                    <label className="label">email</label>
                    <input onChange={handleEmail} className="input" value={email} type="email" />
                </div>
                
                <div className="h-input-container">
                    <div className="input-container2">
                        <label className="label">first name</label>
                        <input onChange={handleFirstName} className="input" value={firstName} type="text" />
                    </div>

                    <div className="input-container2">
                        <label className="label">last name</label>
                        <input onChange={handleLastName} className="input" value={lastName} type="text" />
                    </div>
                </div>

                <button onClick={handleSubmit} className="btn" type="submit">Submit</button>
            </form>
        </div>
    );
}