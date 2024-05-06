import CreateAndUpdateProject from "components/Projects/CreateAndUpdateProject";
import ListProject from "components/Projects/ListProject";
import { useState } from "react";
import { Button } from "react-bootstrap";

function ProjectAuction() {
  const [count, setCount] = useState(0);
  const [isCreatingProject, setIsCreatingProject] = useState(false);

  const handleProjectCreated = () => {
    setCount(count + 1);
  };

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return ( <>
    <Button
      className="mb-4 bg-color-btn-main"
      variant="primary"
      onClick={() => {
        handleShow();
        setIsCreatingProject(true); // Set isEditing to true when the button is clicked
      }}>
      Tạo dự án
    </Button>

    {isCreatingProject && (
      <CreateAndUpdateProject
        onProjectCreated={handleProjectCreated}
        showPopup={show}
        closePopup={handleClose}
      />
    )}

    <ListProject onProjectUpdate={handleProjectCreated} onCount={count} />
  </> );
}

export default ProjectAuction;