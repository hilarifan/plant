import { useState, useEffect } from 'react';
import '../SignupForm/SignupForm.css';
import './ProfileInfo.css';
import validator from 'validator';
import x from '../../images/x.png';
import { useNavigate, useLocation } from 'react-router-dom';
 
export default function Form() {

    // States for registration
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [close, setClose] = useState(false);

    useEffect(() => {
        getData();
    }, []);

    const navigate = useNavigate();

    const {state} = useLocation();
    var id = state.id;

    const handleLogout = () => {
        navigate("/login");
    }

    const handleClose = () => {
        navigate("/home", {state: {id: id}});
    }

    const getData = async() => {
        //e.preventDefault();
        const response = await fetch("http://localhost:8080/profile/" + id, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json'},
        })

        const obj = await response.json();

        console.log(obj);

        setUsername(obj.username);
        setPassword(obj.password);
        setFirstName(obj.firstName);
        setLastName(obj.lastName);
        setEmail(obj.email);
    }

    return (
        <div className="form1">
            <div className="profile-header">
                <h1 className="form-header1">profile</h1>
                <img className="close-image" src={x} onClick={handleClose} />
            </div>
            
            <div>
                {/* Labels and inputs for form data */}
                <div className="input-container1">
                    <h1 className="label1">username</h1>
                    <h1 className="input1">{username}</h1>
                </div>

                <div className="input-container1">
                    <h1 className="label1">password</h1>
                    <h1 className="input1"> {password}</h1>
                </div>

                <div className="input-container1">
                    <h1 className="label1">email</h1>
                    <h1 className="input1">{email}</h1>
                </div>
                
                <div className="h-input-container1">
                    <div className="input-container3">
                        <h1 className="label2">first name</h1>
                        <h1 className="input1">{firstName}</h1>
                    </div>

                    <div className="input-container3">
                        <h1 className="label2">last name</h1>
                        <h1 className="input1">{lastName}</h1>
                    </div>
                </div>

                <button onClick={handleLogout} className="btn1" type="submit">Logout</button>
            </div>
        </div>
    );
}
