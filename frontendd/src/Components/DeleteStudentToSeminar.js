import React, { useEffect, useState } from 'react';
import {  Button, ButtonGroup, Container, Table } from 'reactstrap';
import { useParams } from "react-router-dom";
import AppNavbar from './AppNavbar';
import MModal from './MModal';




const StudentList = () => {

  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);
  //const[enrolId, setEnrollId] = useState(0)

  //handleModal
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
 
  const [idd,setIdd] = useState(0);


  let enrolId=0
  

  const {id} = useParams();
  const seminarId=id;
  //const studentId=id
  useEffect(() => {
    setLoading(true);
    if (id !== null) {
      fetch(`http://localhost:9000/seminar/studentList/${id}`)
        .then(response => response.json())
        .then(data => {
            setStudents(data);
          setLoading(false);
         
        } );
        
    }
  }, [id]);
 
  const remove = async (id) => {
    await fetch(`http://localhost:9000/enrollment/findstud/${id}`, {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(response => response.json())
    .then(data => {
      //console.log(data[0].seminar.id)
      //console.log(data.filter(enroll=> enroll.seminar.id == seminarId)[0].id)
      enrolId = data.filter(enroll=> enroll.seminar.id === seminarId )[0].id
      console.log(enrolId)
    });
    
    await fetch(`http://localhost:9000/seminar/deleteStudent/${enrolId}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedStudent = [...students].filter(i => i.id !== id);
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
        <ButtonGroup>
        <Button  size="sm" color="danger" onClick={() =>{setIdd(student.id);setShow(true);}} >Delete</Button>
            <MModal show={show}  id={idd} handleClose={handleClose} remove = {remove}/>
          

        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <h3>STUDENTLIST</h3>
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