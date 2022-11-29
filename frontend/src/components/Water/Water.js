import React from 'react';
import './Water.css'
import {useState} from 'react';

export default function Water({handleClick, notClicked}) {
    const water = require('../../assets/water.png');
    const watered = require('../../assets/waterPress.png');
    const images = {water,watered};
    const [img, setImg] = useState(false);


    const imgChangeHandler = () => {
        if(!img) {
            setImg(true);
        }else{
            setImg(false)
        }
    };

    return (
        <div className='green-page'>
            <img src={!img ? water : watered } onClick={imgChangeHandler}/>
        </div>
    )
}