import { useState } from 'react';
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

            const response = await fetch("http://localhost:8080/login", {
                method: 'POST',
                headers: { 'Content-Type': 'application/json'},
                body: JSON.stringify(user)
            });
            const r_json = response.json();
            
            //const r_json = '{"error":true, "errorMessage":"choose a different username"}'; //remove this
            //const r_json = '{"error":false, "id":3}'; 

            console.log(r_json);
            const obj = JSON.parse(r_json);

            if (obj.error == true) {
                setError(true);
                setErrorMessage(obj.errorMessage);
            }
            else {
                setError(false);
                setSubmitted(true);
                navigate("/signup", {state: {id: obj.id}}); // change this to home later
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
        </div>
    );
}
