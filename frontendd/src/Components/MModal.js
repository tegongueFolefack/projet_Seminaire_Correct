import React from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

const  MModal = ({show,id, handleClose,remove}) => {
   const confirm = ()=>{
    remove(parseInt(id))
    handleClose()
   }
  return (
    <div>
      <Modal show={show}  onHide={handleClose} >
        <Modal.Header closeButton>
          <Modal.Title>confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>Voulez-vous vraiment supprimer</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="primary" onClick={confirm}>
            confirm
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default MModal;