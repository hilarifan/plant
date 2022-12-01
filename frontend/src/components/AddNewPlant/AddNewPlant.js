import React, { useState } from 'react'
import './AddNewPlant.css'
import CancelIcon from '@mui/icons-material/Cancel'
// import axios from 'axios'


const AddNewPlant = ({ setIsModalVisible }) => {
  const [closeModal, setCloseModal] = useState(false)

  const {state} = useLocation();
  var id = state.id;

  // data state
  const [plantValue, setPlantValue] = useState('Green onion')
  const [dayValue, setDayValue] = useState('monday')
  const [timeValue, setTimeValue] = useState('')
  const [meridiemValue, setMeridiemValue] = useState('')
  const [userID, setUserID] = useState('');

  const handleSubmit = async(e) => {
    e.preventDefault();

    setUserID(id);
    const addPlant = {
      plantType : plantValue,
      userID : userID
    }
    
    const response = await fetch("http://localhost:8080/addPlant", {
      method: 'POST',
      headers: { 'Content-Type': 'application/json'},
      body: JSON.stringify(addPlant),
    })
    console.log(await response.json());
  }

  return (
    <div
      className={closeModal ? 'modal-container-invisible' : 'modal-container'}
    >
      <div className='modal' style={{ position: 'relative' }}>
        <CancelIcon
          onClick={() => {
            setCloseModal(!closeModal)
            setIsModalVisible(false)
            setDayValue('monday')
          }}
          style={{
            position: 'absolute',
            top: 10,
            cursor: 'pointer',
            right: 10,
          }}
        />
        <h2>Adding New Plant</h2>
        <form className='modal-submit-form'>
          <label htmlFor=''>what type of plant is it?</label>
          <div className='first-select-div'>
            <select style={{fontFamily: "Gluten Medium"}}
              onChange={(e) => {
                setPlantValue(e.target.value)
              }}
            >
              <option value='greeno'>Green onion</option>
              <option value='philo'>Philodendron</option>
              <option value='aloe'>Aloe vera</option>
              <option value='fern'>Fern</option>
            </select>
          </div>
          <label htmlFor=''>when was the last time you waterd it?</label>
          <div className='second-select-div'>
            <select style={{fontFamily: "Gluten Medium"}}
              onChange={(e) => {
                setDayValue(e.target.value)
              }}
            >
              <option value='monday'>monday</option>
              <option value='tuesday'>tuesday</option>
              <option value='wednesday'>wednesday</option>
              <option value='thursday'>thursday</option>
              <option value='friday'>friday</option>
              <option value='saturday'>saturday</option>
              <option value='sunday'>sunday</option>
            </select>
            <div>
              <input style={{fontFamily: "Gluten Medium"}}
                type='text'
                placeholder='00:00'
                onChange={(e) => {
                  setTimeValue(e.target.value)
                }}
              />
              <input style={{fontFamily: "Gluten Medium"}}
                type='text'
                placeholder='AM/PM'
                onChange={(e) => {
                  setMeridiemValue(e.target.value)
                }}
              />
            </div>
          </div>
        </form>
        <button style={{fontFamily: "Gluten Medium"}}
          className='submit-btn'
          disabled={
            // !plantValue || !dayValue || !timeValue || !meridiemValue
            !plantValue
              ? true
              : false
          }
          onClick={() => {
            setCloseModal(!closeModal)
            setIsModalVisible(false)
            handleSubmit()
          }}
        >
          Submit
        </button>
      </div>
    </div>
  )
}

export default AddNewPlant;
