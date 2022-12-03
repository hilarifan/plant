import React, { useState } from 'react'
// import './Pot.css'
/*import smiley from '../assets/smiley.png'
import sad from '../assets/sad.png'*/
import './HomePage.css'
import board from '../../assets/board.png'
import {useEffect} from 'react';
import { useParams,useLocation } from 'react-router-dom';
import axios from "axios";
import Water from '../../components/Water/Water'
import Navbar from '../../components/Navbar/Navbar'


import AddNewPlant from '../../components/AddNewPlant/AddNewPlant';


const HomePage = () => {
    const [img, setImg] = useState(false);
    //const {id} = useParams();

    const {state} = useLocation();
    var id = state.id;
    console.log(id)

    //const [userID, setUserID] = useState('');

    const [potStates, setPotStates] = useState([]);

    /*{const [potStates, setPotStates] = useState([
        {
            Name: "philodendron",
            Quantity: 5
        },
        {
            Name: "aloe vera",
            Quantity: 3
        },
        {
            Name: "fern",
            Quantity: 2
        },
        {
            Name: "green onion",
            Quantity: 4
        },
    ])}*/

    // array of objects
    // const getPlants = () => {
    //     fetch(`http://localhost:8080/getplants/${id}`)
    //     .then(res.json())
    //     .then(res => {
    //         console.log(res)
    //         setPotStates(res)
    //     })
    // }

    var plantList = [];
    const getPlants = async() => {   
        const response =  await fetch("http://localhost:8080/getPlants/" + id, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json'},
        })
        const data = await response.json();
        console.log("this is data" , data);
        // plantList = data;
        // console.log("list" , plantList);
        setPotStates(data);

        //  plantList = JSON.parse(data);
        
    }

    // const getPlants = async() => {     
    //     const response =  fetch("http://localhost:8080/getPlants/${id}", {
    //         method: 'GET',
    //         headers: { 'Content-Type': 'application/json'},
    //     })
    //     const data = await response.json();
    //     setPotStates(data);
    // }


    // '[
    //     {"Name":"Philodendron","Quantity":"4"},
    //     {"Name":"AloeVera","Quantity":"0",},
    //     {"Name":"Fern","Quantity":"0"},
    //     {"Name":"GreenOnion","Quantity":"0"}
    // ]'
    useEffect(() => { //react hook that runs whenever a component loads
        getPlants();
    }, );


    const [potChange, setPotChange] = useState(false)
    const sadface = require('../../assets/happy-plant.png');
    const happyface = require('../../assets/sad-plant.png');
    const water = require('../../assets/water.png');
    const minus = require('../../assets/minus.png');
    const minusclick = require('../../assets/minus-hover.png');
    const plus = require('../../assets/plus.png');
    const plusclick = require('../../assets/plus-hover.png');
    const redminus = require('../../assets/redminus.png');
    const watered = require('../../assets/waterPress.png');


    /*{useEffect(() => {
        fetch("/api/pots").then(res => res.json()).then(res=> {
            console.log(res)
        })
    },[])}*/
    const images = {sadface,happyface};

    const [canWater, setWater] = useState(true);

    const getResponse = async() => {
        const response = await fetch("", {
            method: 'GET',
            headers: { 'Content-Type': 'application/json'},
        })

        const data = response.json();
        console.log(data);

        const obj = JSON.parse(data);

        if(obj.canWater == false) {
            setWater(true);
        }
    }

    const imgChangeHandler = (e) => {
        console.log(e.target.src)
        // if(!img) {
            // setImg(true);
            // e.target.parentElement.closest(".happy-sad").src = sadface
            e.target.parentElement.previousElementSibling.src = sadface
            e.target.src = water
        // }else{
            // setImg(false)
            // e.target.src = happyface

        // }
        if(canWater) {
            setImg(true);
        }
    };

    const [minusB, setMinusB] = useState(false);
    const minusChangeHandler = () => {
        if(!minusB) {
            setMinusB(true);
            
        }else{
            setMinusB(false);
        }
    };

    const [plusB, setPlusB] = useState(false);
    const plusChangeHandler = () => {
        if(!plusB) {
            setPlusB(true);
            
        }else{
            setPlusB(false);
        }
    };

   


    const notifyHome= () => {
        //setAddSubmitted(addData);
        //console.log(addSubmitted);
        console.log("notified");
        setPlusB(false);
        getPlants();

    }

    const minusHandler = async(pot) => {
        //e.preventDefault();
        // setUserID(id);
        const minusPlant = {
            //plantType : potStates[minusI].Name,
            plantType: pot.plantType,
            userId : id
          }
        const response = await fetch("http://localhost:8080/minusPlant", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(minusPlant),
        })
        getPlants();
        console.log(await response.json());
    }

    return (
        <>
        <Navbar/>
        <div className='board'>
            <img src={board} alt="board"/>
        </div>
        <div className='minus'>
                <img src={!minusB ? minus : minusclick } alt='minus' onClick={minusChangeHandler}/>
        </div>
        <div className='plus'>
                <img src={!plusB ? plus : plusclick } alt='plus' onClick={plusChangeHandler}/>
        </div>
        <div className='yellow-page'>
            {potStates.map((pot,key)=> {
               return <div className='pot-item' key={key}>
                    <div className='redminus-img'
                        onClick={(e) => minusHandler(pot)}
                     >
                        <img src={!minusB ? '' : redminus}/>
                    </div>
                    <img className='happy-sad' src={happyface } alt='sadface' />
                    <div className='water-img'>
                        <img src={pot.water ? watered : water}  onClick={imgChangeHandler}/>
                    </div>
                    <p>{pot.plantType}</p>
                    <span>{pot.quantity}</span>
                </div>
            })}

            {/* add modal, hide it by display: none in css */}
            {plusB && <AddNewPlant notifyHome={notifyHome}/>}
        </div>
        </>
    )
}

export default HomePage