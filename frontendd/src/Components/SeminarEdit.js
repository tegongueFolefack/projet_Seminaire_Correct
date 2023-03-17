import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const StudentEdit = () => {
  const seminars= {
    fees: '',
    name: ''
    
    
  };
  const [seminar, setSeminars] = useState(seminars);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id !== 'new') {
      //recuperer avec id
      fetch(`http://localhost:9000/seminar/${id}`)
        .then(response => response.json())
        .then(data => setSeminars(data));


        
    }
  }, [id, setSeminars]);
 
  const handleChange = (event) => {
    const { name, value } = event.target

    setSeminars({ ...seminar, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
   
    await fetch('http://localhost:9000/seminar/sem' + (seminar.id ? '/' + seminar.id : ''), {
      method: (seminar.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(seminar)
    });
    setSeminars(seminars);
    navigate('/seminars');
  }

  const title = <h2>{seminar.id ? 'Edit Seminar' : 'Add Seminar'}</h2>;

  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit} >
        
          <FormGroup>
            <Label for="fees">Fees</Label>
            <Input type="number" name="fees" id="fess" value={seminar.fees || ''}
                   onChange={handleChange} autoComplete="address-level1" required/>
          </FormGroup>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={seminar.name || ''}
                   onChange={handleChange} autoComplete="address-level1" required/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit ">Save</Button>{''}
            <Button color="secondary" tag={Link} to="/seminars">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default StudentEdit;