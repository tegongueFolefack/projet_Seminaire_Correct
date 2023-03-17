import React from 'react';
import '../style/App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import StudentList from './StudentList';
import StudentEdit from './StudentEdit';
import ProfessorList from './ProfessorList';
import ProfessorEdit from './ProfessorEdit';
import SeminarList from './SeminarList';
import SeminarEdit from './SeminarEdit';
import GetSeminar from './GetSeminar';
import DeleteStudentToSeminar from './DeleteStudentToSeminar'
import AddStudentToSeminar from './AddStudentToSeminar';
import Home from './Home';

import AddProfessorToSeminar from './AddProfessorToSeminar';






const App = () => {
  
  
  
  return (
    
    
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/students' exact={true} element={<StudentList/>}/>
        <Route path='/students/:id' element={<StudentEdit/>}/>

        <Route path='/professors' exact={true} element={<ProfessorList/>}/>
        <Route path='/professors/:id' exact={true} element={<ProfessorEdit/>}/>

        <Route path='/seminars' exact={true} element={<SeminarList/>}/>
        <Route path='/getSeminars/:id' exact={true} element={<GetSeminar/>}/>
        <Route path='/seminars/:id' exact={true} element={<SeminarEdit/>}/>
        <Route path='/deleteStudent/:id' exact={true} element={<DeleteStudentToSeminar/>}/>
        <Route path='/addStudent/:id' exact={true} element={<AddStudentToSeminar/>}/>
        <Route path='/studentList/:id' exact={true} element={<DeleteStudentToSeminar/>}/>

        <Route path='/addProfessor/:id' exact={true} element={<AddProfessorToSeminar/>}/>

       
        
        
        
      
        

      </Routes>
    </Router>
  )

  
}

export default App;