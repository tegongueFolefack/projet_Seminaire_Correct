import React from 'react';
import '../style/App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import '../style/Home.css'

const Home = () => {
  return (
    <body className='body'>
         <div >
          <AppNavbar/>
          <Container  className=  'liens text-center mt-500 '>
            <h1 className='titre pl-50 pr-50 text-white'>Property management...</h1>

            
            <Button color="link" className=' btn  bg-primary btn-lg text-decoration-none '><Link to="/students" className='text-white text-decoration-none'>Students</Link></Button>
            <Button color="link" className=' btn  bg-primary btn-lg text-decoration-none'><Link to="/professors" className='text-white text-decoration-none'>Professors</Link></Button>
            <Button color="link" className=' btn  bg-primary btn-lg text-decoration-none'><Link to="/seminars" className='text-white text-decoration-none'>seminars</Link></Button>
            <Button color="link" className=' btn  bg-primary btn-lg text-decoration-none'><Link to="/enrollments" className='text-white text-decoration-none'>Enrollment</Link></Button>
          </Container>
          <footer>
          </footer>
      </div>
     
    </body>
    
     
  );
}

export default Home;