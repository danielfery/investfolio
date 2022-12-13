import './App.css';
import {useState} from "react";
import Login from "./pages/Login";
import {BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import Home from "./pages/Home";
import {signOut} from "firebase/auth"
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {auth} from "./Firebase";

function App() {

    // determines if one is logged in
    const [isAuth, setIsAuth] = useState(localStorage.getItem("isAuth"));

    const signUserOut = () => {
        signOut(auth).then(() => {
            localStorage.clear();
            setIsAuth(false);
            // we are outside the Router component, so we can't use useNavigate() or anything from the react-router-dom library here
            window.location.pathname = "/login";
        });
    };

    return (
        <Router>
            <Navbar bg="primary" variant="dark">
                <Container>
                    <Navbar.Brand>Investfolio</Navbar.Brand>
                    <Nav className="me-auto">
                        {!isAuth
                            ? <Nav.Link href="/login">Login</Nav.Link>
                            : <>
                                <Nav.Link href="/">Home</Nav.Link>
                                <Nav.Link onClick={signUserOut}>Logout</Nav.Link>
                            </>

                        }
                    </Nav>
                </Container>
            </Navbar>
            <Routes>
                <Route path="/" element={<Home isAuth={isAuth}></Home>}></Route>
                {/* passing the state as a prop to the login component */}
                <Route path="/login" element={<Login setIsAuth={setIsAuth}></Login>}></Route>
            </Routes>
        </Router>
    );
}

export default App;
