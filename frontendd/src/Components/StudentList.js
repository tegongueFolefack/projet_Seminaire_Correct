import React, { useEffect, useState } from 'react';
import {  Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import MModal from './MModal.js';
import '../style/StudentList.css';




const StudentList = () => {

  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);

  //handleModal
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const [id,setId] = useState(0);
  


  

  useEffect(() => {
    setLoading(true);

    fetch('http://localhost:9000/api/students', { mode:'cors'})
      .then(response => response.json())
      .then(data => {
        setStudents(data);
        setLoading(false);
      })
      
  }, []);
 
    
 
  const remove = async (id) => {

    // const express = require('express');
    // const cors = require('cors');
    // const app = express();
    // app.use(cors());
    await fetch(`http://localhost:9000/api/student/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedStudent = [...students].filter(i => i.id !== id);
      console.log("succes")
      setStudents(updatedStudent);
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const StudentList = students.map(student => {
   
    return <tr key={student.id}>
      <td style={{whiteSpace: 'nowrap'}}>{student.name}</td>
      <td>{student.email}</td>
      <td>{student.phone}</td>
      <td>{student.address}</td>
      <td>{student.sex}</td>
     
     
      <td>
        < ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/students/" + student.id}>Edit</Button>
          <Button size="sm" color="secondary" tag={Link} to={"/getSeminars/" + student.id}>GetSeminar</Button>
          <Button  size="sm" color="danger"   onClick={()=>{setId(student.id); setShow(true)}} >Delete</Button>
          <MModal show={show}  id={id} handleClose={handleClose} remove = {remove}/>


        </ ButtonGroup>
      </td>
    </tr>
  });
//onClick={() =>remove(student.id)}
  return (
    <div>
      <AppNavbar></AppNavbar>
      <Container fluid >
      <div className="float-end">
          <Button color="success" tag={Link} to="/students/new">Add Student</Button>
          
        </div>
        
        <h3  style={{ textAlign: 'center',
                        margin:30 }}>STUDENTLISTS</h3>
        <Table className="mt-4">
          <thead>
          <tr>
            <th width="20%">Name</th>
            <th width="20%">Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Sex</th>
           
           
            <th width="10%">Actions</th>
          </tr>
          </thead>
          <tbody>
          {StudentList}
          </tbody>
        </Table>
        
      </Container>
    </div>
  );
};

export default StudentList;