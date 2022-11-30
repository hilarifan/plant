import React, { useState } from 'react'
import './AddNewPlant.css'
import CancelIcon from '@mui/icons-material/Cancel'
import axios from 'axios'

const AddNewPlant = ({ setIsModalVisible }) => {
  const [closeModal, setCloseModal] = useState(false)

  // data state
  const [planetValue, setPlanetValue] = useState('1')
  const [dayValue, setDayValue] = useState('monday')
  const [timeValue, setTimeValue] = useState('')
  const [meridiemValue, setMeridiemValue] = useState('')

  const handleSubmit = async () => {
    console.log(planetValue, dayValue, timeValue, meridiemValue)
    await axios.post('http://localhost:8080/addPlant', {
      planetValue,
      dayValue,
      timeValue,
      meridiemValue,
    })

    setPlanetValue('')
    setDayValue('monday')
    setTimeValue('')
    setMeridiemValue('')
  }
  console.log("loggin stuff here");
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
                setPlanetValue(e.target.value)
              }}
            >
              <option value='1'>1</option>
              <option value='2'>2</option>
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
            !planetValue || !dayValue || !timeValue || !meridiemValue
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
