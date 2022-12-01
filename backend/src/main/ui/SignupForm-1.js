import { useState } from 'react';
import './SignupForm.css';
import validator from 'validator';
import axios from "axios";
 
export default function Form() {

    // States for registration
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');

    // States for checking the errors
    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState(false);

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
        } 
        else if (!validator.isEmail(email))  {
            setError(true);
        }
        else {
            setSubmitted(true);
            setError(false);
        }
        const user  = {
            username: username,
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password
        }

        const response = await fetch("http://localhost:3000/signup", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(user),
        })
        console.log(await response.json())
    };

    // Showing success message
    const successMessage = () => {
        return (
            <div className="success" style={{display: submitted ? '' : 'none'}}>
                <h1>Yay! {firstName} {lastName} successfully registered!!</h1>
            </div>
        );
    };

    // Showing error message if error is true
    const errorMessage = () => {
        return (
            <div className="error" style={{display: error ? '' : 'none'}}>
                <h1>fix error message</h1>
            </div>
        );
    };

    return (
        <div className="form">
            <h1 className="form-header">sign up</h1>

            <div className="messages"> {errorMessage()} {successMessage()} </div>
            {/* <div className="messages"> {errorMessage()}</div> */}
            
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