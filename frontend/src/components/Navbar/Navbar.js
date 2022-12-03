import React from 'react'
import { Link, useLocation, useNavigate } from "react-router-dom"
import './Navbar.css'

export default function Navbar() {
    const {state} = useLocation();
    var id = state.id;
    const navigate = useNavigate();

    return <nav className="nav">
        <Link to="/home" className="site-title">p l a n t</Link>
        <ul>
            {/* <li>
                <Link to="/yourfriends" className="nav-button">Your Friends</Link>
            </li> */}
            <li>
                <Link to="/profile" onClick={(e) => {e.preventDefault(); navigate("/profile", {state: {id: id}})}} className="nav-button">My account</Link>
            </li>
        </ul>
    </nav>
}