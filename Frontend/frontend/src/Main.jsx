import React from 'react'
import videoBack from './background.mp4'

const Main = () => {
return (
    <div className='main'>
        <video src={videoBack} autoPlay loop muted />
    </div>
)
}

export default Main