import React from 'react';
import './LoadingPage.css'

export default function LoadingPage() {
    setTimeout(function() {
        window.location.replace('login');
    }, 5000);
    
    return (
        <div className='green-page'>
            <h1 className='loading'>p l a n t</h1>
        </div>
    )
}