import React from 'react';
import { Container, Navbar, Nav, Row, Col, Card } from 'react-bootstrap';
import aboutUsImage from '../images/crafts.jpg';
import categoriesImage from '../images/jewellery.jpg';
import contactUsImage from '../images/snacks.jpg';
import { Link } from 'react-router-dom';

const HomePage = () => {
  return (
    <Container fluid>
      {/* Navbar */}
      {/* <Navbar bg="dark" variant="dark" expand="lg">
        <Navbar.Brand href="#home">HandyMart</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto">
            <Nav.Link href="#about">About Us</Nav.Link>
            <Nav.Link href="#categories">Categories</Nav.Link>
            <Nav.Link href="#contact">Contact Us</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar> */}

<Navbar bg="dark" variant="dark" expand="lg">
  <Container>
    <Navbar.Brand as={Link} to="/">HandyMart</Navbar.Brand>
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav">
      <Nav className="me-auto">
        <Nav.Link href="#about">About Us</Nav.Link>
        <Nav.Link href="#categories">Categories</Nav.Link>
        <Nav.Link href="#contact">Contact Us</Nav.Link>
      </Nav>
      <Nav>
        <Nav.Link as={Link} to="/login">Login</Nav.Link>
        <Nav.Link as={Link} to="/customer-reg">Register (Customer)</Nav.Link>
        <Nav.Link as={Link} to="/vendor-registration">Register (Vendor)</Nav.Link>
      </Nav>
    </Navbar.Collapse>
  </Container>
</Navbar>

      {/* Welcome Section */}
      <div className="jumbotron jumbotron-fluid  text-center">
        <Container>
          <h1 className="display-4">Welcome to HandyMart</h1>
          <p className="lead">Your one-stop-shop for all your needs!</p>
        </Container>
      </div>

      {/* About Us Section */}
      <Container id="about" className="py-5">
        <Row className="justify-content-center">
          <Col md={8}>
            <h2 className="text-center mb-4">About Us</h2>
            <p>
              HandyMart is dedicated to providing a convenient and secure platform for vendors and customers. Our mission is to empower small businesses and offer customers a seamless shopping experience.
            </p>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md={8}>
            <img src={aboutUsImage} alt="About Us" className="img-fluid" />
          </Col>
        </Row>
      </Container>

      {/* Product Categories Section */}
      <Container id="categories" fluid className="bg-success text-white text-center py-5">
        <h2>Explore Our Categories</h2>
        <Row className="justify-content-center">
          <Col md={4} className="mb-4">
            <Card bg="info" text="white">
              <Card.Body>
                <Card.Title>snacks</Card.Title>
              </Card.Body>
            </Card>
          </Col>
          <Col md={4} className="mb-4">
            <Card bg="info" text="white">
              <Card.Body>
                <Card.Title>crafts</Card.Title>
              </Card.Body>
            </Card>
          </Col>
          <Col md={4} className="mb-4">
            <Card bg="info" text="white">
              <Card.Body>
                <Card.Title>Clothes</Card.Title>
              </Card.Body>
            </Card>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md={8}>
            <img src={categoriesImage} alt="Categories" className="img-fluid" />
          </Col>
        </Row>
      </Container>

      {/* Contact Us Section */}
      <Container id="contact" className="py-5">
        <Row className="justify-content-center">
          <Col md={8}>
            <h2 className="text-center mb-4">Contact Us</h2>
            <p>Have questions or need assistance? Reach out to us!</p>
            <p>Email: info@handymart.com</p>
            <p>Phone: +1 234-567-8901</p>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md={8}>
            <img src={contactUsImage} alt="Contact Us" className="img-fluid" />
          </Col>
        </Row>
      </Container>
    </Container>
  );
};

export default HomePage;
