import React from 'react';
import './Water.css'
import {useState, useLocation} from 'react';
import { useNavigate } from 'react-router-dom';

export default function Water({handleClick, notClicked}) {
    const water = require('../../assets/water.png');
    const watered = require('../../assets/waterPress.png');
    const images = {water,watered};
    const [img, setImg] = useState(false);
    const [canWater, setWater] = useState(false);
    const [home, setHome] = useState(false);

    // const {state} = useLocation();
    // var id = state.id;

    const navigate = useNavigate();

    const getResponse = async() => {
        const response = await fetch("", {
            method: 'GET',
            headers: { 'Content-Type': 'application/json'},
        })

        const data = response.json();
        console.log(data);

        const obj = JSON.parse(data);

        if(obj.error == false) {
            setWater(true);
        }
    }

    const imgChangeHandler = (e) => {
        if(canWater) {
            setImg(true);
            setWater(false);
            navigate("/home");
        }
    };

    return (
        <div className='green-page'>
            <img src={!img ? water : watered } onClick={imgChangeHandler}/>
        </div>
    )
}