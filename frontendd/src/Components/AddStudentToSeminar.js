import React, { useEffect, useState } from 'react';
import {  Button, ButtonGroup, Container, Table } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import AppNavbar from './AppNavbar';


const AddStudentToSeminar = () => {

  const [student, setStudent] = useState([]);
  //const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);

    fetch('http://localhost:9000/api/students')
      .then(response => response.json())
      .then(data => {
        setStudent(data);
        setLoading(false);
      })
  }, []);
 
  const addStudent = async (studid) => {
    const parameterId = {student_Id: studid,seminar_Id:id}
    try {
      await fetch(`http://localhost:9000/seminar/addstud`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body:JSON.stringify(parameterId)
    }).then(() => {
      navigate('/seminars')
    });

    } catch (error) {
      console.error(error);
    }
    
    // .then(() => {
    //   let updatedStudent = [...student].filter(i => i.id !== id);
    //   setStudent(updatedStudent);
    //   navigate('/seminars')
    // });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const StudentList = student.map(student => {
   
    return <tr key={student.id}>
      <td style={{whiteSpace: 'nowrap'}}>{student.name}</td>
      <td>{student.email}</td>
      <td>{student.phone}</td>
      <td>{student.address}</td>
      <td>{student.sex}</td>
     
     
      <td>
        <ButtonGroup>
          <Button size="sm" color="danger"   onClick={()=>addStudent(student.id)}>AddStudent</Button>
          

        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        
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

export default AddStudentToSeminar;