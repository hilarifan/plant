import { useState } from 'react';
import './ProfilePage.css'
 
export default function ProfilePage() {

    return (
        <div className="form">
            <h1 className="form-header">profile</h1>

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