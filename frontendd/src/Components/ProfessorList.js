import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import MModal from './MModal.js'

const ProfessortList = () => {

  const [professors, setProfessors] = useState([]);
  const [loading, setLoading] = useState(false);

  //handleModal
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [id,setId] = useState(0);

  useEffect(() => {
    setLoading(true);

    fetch('http://localhost:9000/professor')
      .then(response => response.json())
      .then(data => {
        setProfessors(data);
        setLoading(false);
      })
  }, []);

  const remove = async (id) => {
    await fetch(`http://localhost:9000/professor/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedProfessor = [...professors].filter(i => i.id !== id);
      setProfessors(updatedProfessor);
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const professorList = professors.map(professor => {
   
    return <tr key={professor.id}>
      <td>{professor.name}</td>
      <td>{professor.sex}</td>
      <td>{professor.email}</td>
      <td>{professor.phone}</td>
      <td>{professor.address}</td>
      <td >{professor.salary}</td>
      
     
      <td>
        <ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/professors/" + professor.id}>Edit</Button>
          <Button  size="sm" color="danger" onClick={()=>{setId(professor.id); handleShow();}} >Delete</Button>
          <MModal show={show}  id={id} handleClose={handleClose} remove = {remove}/>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
       
        <h1  style={{ textAlign: 'center',
                        margin:30 }}>PROFESSORS</h1>
        <Table className="mt-4">
          <thead>
          <tr>
            <th width="20%">Name</th>
            <th>Sex</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th width="20%">Salary</th>

           
            <th width="10%">Actions</th>
          </tr>
          </thead>
          <tbody>
          {professorList}
          </tbody>
        </Table>
        <div className="float-start">
          <Button color="success" tag={Link} to="/professors/new">Add Professor
          </Button>
        </div>
      </Container>
    </div>
  );
};

export default ProfessortList;