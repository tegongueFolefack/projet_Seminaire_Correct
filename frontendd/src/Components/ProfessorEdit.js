import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const ProfessorEdit = () => {
  const professors= {
    address: '', 
    email: '',
    name: '',
    phone: '',
    salary: '',
    sex: ''
    
  };
  const [professor, setProfessors] = useState(professors);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id !== 'new') {
      //recuperer avec id
      fetch(`http://localhost:9000/professor/${id}`)
        .then(response => response.json())
        .then(data => setProfessors(data));
        
    }
  }, [id, setProfessors]);
 
  const handleChange = (event) => {
    const { name, value } = event.target

    setProfessors({ ...professor, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log(professor)
    await fetch('http://localhost:9000/professor/prof' + (professor.id ? '/' + professor.id : ''), {
      method: (professor.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(professor)
    });
    setProfessors(professors);
    navigate('/professors');
  }

  const title = <h2>{professor.id ? 'Edit Professor' : 'Add Professor'}</h2>;

  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit} >

          <FormGroup>
              <Label for="name">Name</Label>
              <Input type="text" name="name" id="name" value={professor.name || ''}
                    onChange={handleChange} required/>
            </FormGroup>

            <FormGroup>
            <Label for="email">Email</Label>
            <Input type="text" name="email" id="email" value={professor.email || ''}
                   onChange={handleChange} required/>
          </FormGroup>
        
         

          <FormGroup >
              <Label for="sex">Sex</Label>
              <select name='sex' className="form-control" onChange={handleChange}  >
                     <option value="liste">Liste de choix...</option>
                      <option  value='H'  
                      autoComplete="address-level1">Homme</option>
                      <option  value='F' 
                      autoComplete="address-level1">Femme</option>     
              </select>
            </FormGroup>
         
          
          <div className="row">
            <FormGroup className="col-md-5 mb-3">
              <Label for="address">Address</Label>
              <Input type="text" name="address" id="address" value={professor.address || ''}
                    onChange={handleChange} required/>
            </FormGroup>

            <FormGroup className="col-md-4 mb-3">
              <Label for="phone">Phone</Label>
              <Input type="number" name="phone" id="phone" value={professor.phone || ''}
                     onChange={handleChange} required/>
            </FormGroup>
            
            <FormGroup className="col-md-3 mb-3">
              <Label for="salary">salary</Label>
              <Input type="number" name="salary" id="salary" value={professor.salary|| ''}
                     onChange={handleChange} autoComplete="address-level1"/>
            </FormGroup>
            
          </div>
          <FormGroup>
            <Button color="primary" type="submit ">Save</Button>
            <Button color="secondary" tag={Link} to="/professors">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default ProfessorEdit;