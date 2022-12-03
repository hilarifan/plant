import React, { useState } from 'react'
// import './Pot.css'
/*import smiley from '../assets/smiley.png'
import sad from '../assets/sad.png'*/
import './GuestPage.css'
import board from '../../assets/board.png'
import {useEffect} from 'react';
import { useParams,useLocation } from 'react-router-dom';
import Navbar from '../../components/Navbar/Navbar'
import GuestNavbar from '../../components/GuestNavbar/GuestNavbar'

import { getTablePaginationUnstyledUtilityClass } from '@mui/base';

const GuestPage = () => {
    const [img, setImg] = useState(false);
    const [id, setID] = useState(1);
    //const {id} = useParams();

    //const {state} = useLocation();
    //var id = state.id;
    //console.log(id)

    const sadface = require('../../assets/happy-plant.png');

    const [potStates, setPotStates] = useState([]);

    const getPlants = async() => {   
        const response =  await fetch("http://localhost:8080/getPlants/" + id, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json'},
        })
        const data = await response.json();
        console.log("this is data" , data);
        setPotStates(data);
    }
    useEffect(() => { //react hook that runs whenever a component loads
        getPlants();
    }, []);

    return (
        <>
        <GuestNavbar/>
        <div className='board1'>
            <img src={board} alt="board"/>
        </div>

        <div className='yellow-page'>

            {potStates.map((pot,key)=> {
                return <div className='pot-item' key={key}>
                    <img className='happy-sad' src={sadface } alt='sadface' />
                    <p>{pot.plantType}</p>
                    <span>{pot.quantity}</span>
                </div>
            })}

        </div>
        </>
    )
}

export default GuestPage