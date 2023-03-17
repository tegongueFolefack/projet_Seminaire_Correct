import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import '../style/StudentEdit.css'
// import { useForm } from "react-hook-form";
// import { yupResolver } from '@hookform/resolvers/yup';
// import * as Yup from "yup";

const StudentEdit = () => {

  

  

  const students= {
    name: '',
    email: '',
    phone: '',
    sex: '',
    address: '' 
  };
  const [student, setStudent] = useState(students);
  const navigate = useNavigate();
  const { id } = useParams();

 

  useEffect(() => {
    if (id !== 'new') {
      //recuperer avec id
      fetch(`http://localhost:9000/api/Student/${id}`)
        .then(response => response.json())
        .then(data => setStudent(data));
        
    }
  }, [id, setStudent]);
 
  const handleChange = (event) => {
    const { name, value } = event.target

    setStudent({ ...student, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log(student)
    await fetch('http://localhost:9000/api/stud' + (student.id ? '/' + student.id : ''), {
      method: (student.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(student)
    });
    setStudent(students);
    navigate('/students');
  }

  const title = <h2  style={{ textAlign: 'center',
  margin:30 }}>{student.id ? 'Edit Student' : 'Add Student'}</h2>;

  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit} >

          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={student.name || ''}
                   onChange={handleChange} placeholder='entrez votre nom' required/>
          </FormGroup>

          <FormGroup>
            <Label for="email">Email</Label>
            <Input type="email" name="email" id="email" value={student.email || ''}
                   onChange={handleChange} placeholder='entrez votre email'required/>
          </FormGroup>
         
        
          
          <div className="row">

            <FormGroup className="col-md-4 mb-3">
                <Label for="phone">Phone</Label>
                <Input type="number" name="phone" id="phone" value={student.phone || ''}
                      onChange={handleChange} placeholder='entrez votre tel' />
              </FormGroup>

              <FormGroup className="col-md-5 mb-3 ">
                <Label for="sex">Sex</Label>
                <select name='sex' className="form-control" onChange={handleChange}  >
                      <option value="liste">Liste de choix...</option>
                        <option  value='H'  
                        autoComplete="address-level1">Homme</option>
                        <option  value='F' 
                        autoComplete="address-level1">Femme</option>     
                </select>
                
              </FormGroup>     
            <FormGroup className="col-md-3 mb-3">
              <Label for="address">Address</Label>
              <Input type="text" name="address" id="address" value={student.address || ''}
                    onChange={handleChange} placeholder='entrez votre address' />
            </FormGroup>

            
           
            
          </div>
          <FormGroup>
            <Button color="primary" type="submit ">Save</Button>
            <Button color="secondary" tag={Link} to="/students">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default StudentEdit;