import { useState } from 'react';
import './ProfileInfo.css';
import validator from 'validator';
import axios from "axios";
import logout from '../../images/logout.png'
import x from '../../images/x.png'
import person from '../../images/person.png'

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

    const handleImage = () => {
        console.log("change photo");
    }

    const handleClose = () => {
        console.log("x pressed");
    }

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
        }
    };

    // Showing success message
    const successMessage = () => {
        return (
            <div className="success1" style={{display: submitted ? '' : 'none'}}>
                <h1>Yay! {firstName} {lastName} successfully registered!!</h1>
            </div>
        );
    };

    // Showing error message if error is true
    const errorMessage = () => {
        return (
            <div className="error1" style={{display: error ? '' : 'none'}}>
                <h1>fix error message</h1>
            </div>
        );
    };
    
    return (
        <div className="overall">
            <div className="form1">
                <h1 className="form-header1">profile</h1>

                <div className="x">
                    {/* <button><img src={x} className="x" onClick={handleClose} /></button> */}
                    <img src={x} />
                </div>

                <div className="messages"> {errorMessage()} {successMessage()} </div>

                <form>
                    <div className="input-container1">
                        <label className="label1">username</label>
                        <input onChange={handleUsername} id="inputs" placeholder="username" className="input1" value={username} type="text" />
                    </div>

                    <div className="input-container1">
                        <label className="label1">password</label>
                        <input onChange={handlePassword} id="inputs" placeholder="password" className="input1" value={password} type="password" />
                    </div>
                    
                    <div className="h-input-container1">
                        <div className="input-container22">
                            <label className="label1">first name</label>
                            <input onChange={handleFirstName} id="inputs" placeholder="first name" className="input1" value={firstName} type="text" />
                        </div>

                        <div className="input-container3">
                            <label className="label1">last name</label>
                            <input onChange={handleLastName} id="inputs" placeholder="last name" className="input1" value={lastName} type="text" />
                        </div>
                    </div>

                    <div className="input-container1">
                        <label className="label1">email</label>
                        <input onChange={handleEmail} id="inputs" placeholder="email" className="input1" value={email} type="email" />
                    </div>

                    <div>
                        {/* <img src={logout} className="logout"/> */}
                        <button onClick={handleSubmit} className="btn1" type="logout">Logout</button>
                    </div>
                </form>
            </div>

            <div className="photo">
                <img src={person} className="person" />
                <h1 className="header">First last</h1>
            </div>
        </div>
    );
}