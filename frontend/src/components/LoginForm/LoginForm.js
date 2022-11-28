import { useState } from 'react';
import './LoginForm.css';
import { Link } from 'react-router-dom';
import validator from 'validator';
import axios from "axios";
 
export default function Form() {

    // States for registration
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

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

    // Handling the form submission
    // use api call to setError, and setSubmitted
    const handleSubmit = async(e) => {
        e.preventDefault();

        if (username === '' || password === '') {
            setError(true);
        }
        else {
            setSubmitted(true);
            setError(false);

            const response = await fetch("http://localhost:3000/login", {
                method: 'GET',
                headers: { 'Content-Type': 'application/json'}
            })
            console.log(await response.json())
        }
    };

    // Showing success message
    const successMessage = () => {
        return (
            <div className="success" style={{display: submitted ? '' : 'none'}}>
                <h1>Yay! successful login!</h1>
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
            <h1 className="form-header">login</h1>

            {/* <div className="messages"> {errorMessage()} {successMessage()} </div> */}
            <div className="messages"> {errorMessage()}</div>
            
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
        </div>
    );
}