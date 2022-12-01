import { useState, useEffect } from 'react';
import './ProfileInfo.css';
import validator from 'validator';
import axios from "axios";
import logout from '../../images/logout.png'
import x from '../../images/x.png'
import person from '../../images/person.png'
import { useNavigate, useLocation } from 'react-router-dom';

export default function Form() {
    
    // States for registration info
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');

    useEffect(() => {
        getData();
    },[])

    // State for checking the buttons
    const [close, setClose] = useState(false);
    const navigate = useNavigate();

    const {state} = useLocation();
    var id = state.id;

    const handleLogout = () => {
        navigate("/login");
    }

    const handleClose = () => {
        navigate("/home");
    }

    // get all user id info
    const getData = async(e) => {
        e.preventDefault();
        
        const response = await fetch("http://localhost:8080/getData/${id}", {
            method: 'GET',
            headers: { 'Content-Type': 'application/json'},
        })

        const data = await response.json();
        console.log(data);

        const obj = JSON.parse(data);

        setUsername(obj.username);
        setPassword(obj.password);
        setFirstName(obj.firstName);
        setLastName(obj.lastName);
        setEmail(obj.email);
    }
    
    return (
        <div className="overall">
            <div className="form1">
                <h1 className="form-header1">profile</h1>

                <div className="x">
                    <img src={x} onClick={handleClose} />
                </div>

                <div>
                    <div className="input-container1">
                        <label className="label1">username</label>
                        <label id="inputs" className="input1" value={username} type="text" />
                        {/* <input onChange={handleUsername} id="inputs" placeholder="username" className="input1" value={username} type="text" /> */}
                    </div>

                    <div className="input-container1">
                        <label className="label1">password</label>
                        <label id="inputs" className="input1" value={password} type="text" />
                        {/* <input onChange={handlePassword} id="inputs" placeholder="password" className="input1" value={password} type="password" /> */}
                    </div>
                    
                    <div className="h-input-container1">
                        <div className="input-container22">
                            <label className="label1">first name</label>
                            <label id="inputs" className="input1" value={firstName} type="text" />
                            {/* <input onChange={handleFirstName} id="inputs" placeholder="first name" className="input1" value={firstName} type="text" /> */}
                        </div>

                        <div className="input-container3">
                            <label className="label1">last name</label>
                            <label id="inputs" className="input1" value={lastName} type="text" />
                            {/* <input onChange={handleLastName} id="inputs" placeholder="last name" className="input1" value={lastName} type="text" /> */}
                        </div>
                    </div>

                    <div className="input-container1">
                        <label className="label1">email</label>
                        <label id="inputs" className="input1" value={email} type="text" />
                        {/* <input onChange={handleEmail} id="inputs" placeholder="email" className="input1" value={email} type="email" /> */}
                    </div>

                    <div>
                        <img src={logout} className="logout" onClick={handleLogout} />
                    </div>
                </div>
            </div>

            <div className="photo">
                <img src={person} className="person" />
                <h1 className="header">First last</h1>
            </div>
        </div>
    );
}