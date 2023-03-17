import React from 'react';
import {  Navbar, NavbarBrand,Container,Button } from 'reactstrap';
//import { faHome } from "@fortawesome/free-solid-svg-icons";
//import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from 'react-router-dom';
// import logo from '../asset/logo.webp';
import '../style/AppNavbar.css';
import Dropdown from 'react-bootstrap/Dropdown';



const AppNavbar = () => {

  //const [isOpen, setIsOpen] = useState(false);

  return (



    <Navbar  dark d-flex="true" color='dark' >
      <a className="navbar-brand text-uppercase fw-bold" href="/index.html">
        <span className="bg-transparent bg-gradient p-1 rounded-3 text-primary">MANAGEMENT</span> Seminar
      </a>
     
      <NavbarBrand tag={Link} to="">
        <Container fluid>
       

          <div className='d-flex float-end'>
            <div >
        
              <Dropdown>
                  <Dropdown.Toggle >Students</Dropdown.Toggle>
                  <Dropdown.Menu>
                    <Link className='ms-10' to="/students">studentList</Link><br/>
                      <Link to="/students/new">addStudent</Link>
                  </Dropdown.Menu>
              </Dropdown>
            </div>

            <div >
        
              <Dropdown>
                  <Dropdown.Toggle >Professors</Dropdown.Toggle>
                  <Dropdown.Menu>
                    <Link className='ms-10' to="/professors">ProfessorList</Link><br/>
                    <Link to="/professors/new">addProfessor</Link>
                  </Dropdown.Menu>
              </Dropdown>
            </div>

            <div >
        
              <Dropdown>
                  <Dropdown.Toggle >Seminars</Dropdown.Toggle>
                  <Dropdown.Menu>
                    <Link className='ms-10' to="/seminars">SeminarList</Link><br/>
                    <Link to="/seminars/new">addSeminar</Link>
                  </Dropdown.Menu>
              </Dropdown>
            </div>
            <div className='button'><Button  className='btn bg-outline-primary btn-sm'><Link to="/enrollments" className='text-white text-decoration-none'>enrollment</Link></Button></div>
            <div className='button'><Button  className='btn bg-primary btn-sm'><Link to="/" className='text-white text-decoration-none'>Home</Link></Button></div>           
          </div>
           
        </Container>
      </NavbarBrand>
    </Navbar>
    
      
      

  );
};

export default AppNavbar;
