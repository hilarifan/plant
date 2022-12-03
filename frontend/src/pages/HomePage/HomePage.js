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

    const {state} = useLocation();
    var id = state.id;
    //console.log(id)

    const [potStates, setPotStates] = useState([]);

    const getPlants = async() => {   
        const response =  await fetch("http://localhost:8080/getPlants/" + id, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json'},
        })
        const data = await response.json();
        //console.log("this is data" , data);
        setPotStates(data);        
    }

    useEffect(() => { //react hook that runs whenever a component loads
        getPlants();
    });


    const [potChange, setPotChange] = useState(false)
    const happyface = require('../../assets/happy-plant.png');
    const sadface = require('../../assets/sad-plant.png');
    const water = require('../../assets/waterPress.png');
    const minus = require('../../assets/minus.png');
    const minusclick = require('../../assets/minus-hover.png');
    const plus = require('../../assets/plus.png');
    const plusclick = require('../../assets/plus-hover.png');
    const redminus = require('../../assets/redminus.png');
    const watered = require('../../assets/water.png');

    const images = {sadface,happyface};

    const [canWater, setWater] = useState(true);

    const imgChangeHandler = async(e, pot) => {
        console.log("clicked on the button~ needs watering: "+pot.needsWatering)
        if(pot.needsWatering){
            console.log(e.target.src)
                e.target.parentElement.previousElementSibling.src = happyface
                e.target.src = watered
            console.log("you watered the plant")
            const waterPlant = {
                plantType: pot.plantType,
                userId : id,
                needsWatering : false
              }
            const response = await fetch("http://localhost:8080/waterPlant", {
                method: 'POST',
                headers: { 'Content-Type': 'application/json'},
                body: JSON.stringify(waterPlant),
            })  
            console.log("after watered. needs watering: "+ pot.needsWatering)
            console.log(e.target.src)


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
        console.log("notified");
        setPlusB(false);
        getPlants();

    }

    const minusHandler = async(pot) => {
        const minusPlant = {
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
                    <img className='happy-sad' src={pot.needsWatering ? sadface : happyface } alt='happyface' />
                    <div className='water-img'>
                        <img src={pot.needsWatering ? water : watered}  onClick={e => imgChangeHandler(e, pot)}/>
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