import React from 'react'
import { Link } from "react-router-dom"
import './GuestNavbar.css'

export default function GuestNavbar() {
    return <nav className="nav">
        <Link to="/guest" className="site-title">p l a n t</Link>
        <ul>
            {/* <li>
                <Link to="/yourfriends" className="nav-button">Your Friends</Link>
            </li> */}
            <li>
                <Link to="/login" className="nav-button">Log in</Link>
            </li>
        </ul>
    </nav>
}