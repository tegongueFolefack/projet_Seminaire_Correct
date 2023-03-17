import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import AppNavbar from './AppNavbar';


const AddProfessorToSeminar = () => {

  const [professor, setProfessor] = useState([]);
  //const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);

    fetch('http://localhost:9000/professor')
      .then(response => response.json())
      .then(data => {
        setProfessor(data);
        setLoading(false);
      })
  }, []);

  const addProfessor = async (prof) => {
    const parameterId = { seminarId: id, professorId: prof }
    await fetch(`http://localhost:9000/seminar/addProfessor`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(parameterId)
    }).then(() => {
      let updatedStudent = [...professor].filter(i => i.id !== id);
      setProfessor(updatedStudent);
      navigate('/seminars')
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const professorList = professor.map(professor => {

    return <tr key={professor.id}>
      <td>{professor.name}</td>
      <td>{professor.sex}</td>
      <td>{professor.email}</td>
      <td>{professor.phone}</td>
      <td>{professor.address}</td>
      <td >{professor.salary}</td>


      <td>
        <ButtonGroup>
          <Button size="sm" color="danger" onClick={() => addProfessor(professor.id)}>AddProfessor</Button>


        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar />
      <Container fluid>

        <h3>PROFESSORS</h3>
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
      </Container>
    </div>
  );
};

export default AddProfessorToSeminar;