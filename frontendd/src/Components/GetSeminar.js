import React, {useEffect,useState} from "react";
import { useParams } from "react-router-dom";
import {  Container, Table } from 'reactstrap';


const GetSeminars = () => {
  
  const [seminars,setSeminars] = useState([]);
  const [loading, setLoading] = useState(false);
 
  const {id} = useParams();

  useEffect(() => {
    setLoading(true);
    if (id !== null) {
      fetch(`http://localhost:9000/api/getseminar/${id}`)
        .then(response => response.json())
        .then(data => {
          setSeminars(data);
          setLoading(false);
         
        } );
        
    }
  }, [id]);

  if (loading) {
    return <p>Loading...</p>;
  }

  const GetSeminar = seminars.map(seminar=>{
    return <tr key={seminar.id}>
        <td>{seminar.fees}</td>
        <td>{seminar.name}</td>
      </tr>
  });


  return (
    <div>
      {
        !loading && seminars.length === 0 ? <h1 style={{textAlign:"center"}}>No Item found</h1> :
         <Container fluid>

          <h4  style={{ textAlign: 'center',
                        margin:50 }}>SeminarList</h4>
         <Table>
           <tr>
             <th width="20%">Fees</th>
             <th width="20%">Name</th>
           </tr>
           <tbody>
           {GetSeminar}
           </tbody>
         </Table>
       </Container>
      }
     </div>
   );
      
     
     
}
export default GetSeminars;