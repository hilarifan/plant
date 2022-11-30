import React, { useState } from 'react'
import AddNewPlant from '../components/AddNewPlant/AddNewPlant'

const Home = () => {
  const [isModelVisible, setIsModalVisible] = useState(false)
  return (
    <div>
      <button
        onClick={() => setIsModalVisible(!isModelVisible)}
        style={{
          padding: '15px ',
          background: 'red',
          border: 'none',
          color: 'white',
          borderRadius: '7px',
          cursor: 'pointer',
        }}
      >
        Click Me
      </button>
      {isModelVisible && <AddNewPlant setIsModalVisible={setIsModalVisible} />}
    </div>
  )
}

export default Home
