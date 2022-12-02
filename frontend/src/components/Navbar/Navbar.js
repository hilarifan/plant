import React from 'react'
import { Link } from "react-router-dom"
import './Navbar.css'

export default function Navbar() {
    return <nav className="nav">
        <Link to="/home" className="site-title">p l a n t</Link>
        <ul>
            {/* <li>
                <Link to="/yourfriends" className="nav-button">Your Friends</Link>
            </li> */}
            <li>
                <Link to="/account" className="nav-button">My account</Link>
            </li>
        </ul>
    </nav>
}