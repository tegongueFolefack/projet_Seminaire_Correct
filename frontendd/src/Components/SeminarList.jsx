import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import MModal from './MModal.js'


const SeminarList = () => {

  const [seminars, setSeminars] = useState([]);
  const [loading, setLoading] = useState(false);

   //handleModal
   const [show, setShow] = useState(false);

   const handleClose = () => setShow(false);
   const handleShow = () => setShow(true);
   const [id,setId] = useState(0);
 

  useEffect(() => {
    setLoading(true);

    fetch('http://localhost:9000/seminar')
      .then(response => response.json())
      .then(data => {
        setSeminars(data);
        setLoading(false);
      })
  }, []);

  const remove = async (id) => {
    await fetch(`http://localhost:9000/seminar/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedSeminar = [...seminars].filter(i => i.id !== id);
      setSeminars(updatedSeminar);
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const SeminarList = seminars.map(seminar=> {
   
    return (
      <tr key={seminar.id}>
        <td style={{whiteSpace: 'nowrap'}}>{seminar.fees}</td>
        <td>{seminar.name}</td>
      
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/seminars/" + seminar.id}>Edit</Button>
            <Button  size="sm" color="danger" onClick={() =>{setId(seminar.id);handleShow();}} >Delete</Button>
            <MModal show={show}  id={id} handleClose={handleClose} remove = {remove}/>

            <Button size="sm" color="secondary" tag={Link} to={"/deleteStudent/" + seminar.id}>deleteStudent</Button>
            <Button size="sm" color="primary" tag={Link} to={"/addStudent/" + seminar.id }>AddStudent</Button>
            <Button size="sm" color="primary" tag={Link} to={"/studentList/" + seminar.id }>StudentList</Button>

            <Button size="sm" color="primary" tag={Link} to={"/addProfessor/" + seminar.id }>AddProfessor</Button>
            
    
          </ButtonGroup>
        </td>
    </tr>
    
   
  )});

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
       
        <h1 style={{ textAlign: 'center',
                        margin:30 }}>SEMINARS</h1>
        <Table className="mt-4">
          <thead>
          <tr>
            <th width="20%">fees</th>
            <th width="20%">Name</th>
            <th width="10%">Actions</th>
          </tr>
          </thead>
          <tbody>
          {SeminarList} 
          </tbody>
        </Table>
        <div className="float-start">
          <Button color="success" tag={Link} to="/seminars/new">Add Seminar</Button>
        </div>
      </Container>
    </div>
  );
};

export default SeminarList;