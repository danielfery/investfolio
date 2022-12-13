import React, {useEffect} from "react"
import {auth, provider} from "../Firebase";
import {signInWithPopup} from "firebase/auth";
import {useNavigate} from "react-router-dom";
import axios from "axios";

export function WelcomeMessage() {
    return (
        <h4>{"Willkommen zurück " + localStorage.getItem("name") + ", " + localStorage.getItem("email") + "!"}</h4>
    )
}

function Login({setIsAuth}) {

    let navigate = useNavigate();

    const sendUserData = (userId, email) => {

        const requestOptions = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(userId, email)
        };

        // TODO: check if user is already in database and only POST user data if not
        const currentUser = axios.get(`http://localhost:8080/api/getUser?userId=${userId}`)
            .then(response => response.data)
            .then(response => console.log(response))

        fetch(`http://localhost:8080/api/createUser?userId=${userId}&eMail=${email}`, requestOptions)
            .then((response) => response.json());
    };

    // whenever someone signs in, this function is triggered
    const signInWithGoogle = () => {
        signInWithPopup(auth, provider)
            .then((result) => {
                localStorage.setItem("isAuth", true);
                setIsAuth(true)
                const name = result.user.displayName;
                const email = result.user.email;
                const profilePic = result.user.photoURL;
                const uid = result.user.uid;

                localStorage.setItem("name", name);
                localStorage.setItem("email", email);
                localStorage.setItem("profilePic", profilePic);
                localStorage.setItem("uid", uid);

                // sendUserData(uid, email)

                navigate("/")
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="container">
            <div className="row mt-4 text-center">
                <h2>Login</h2>
            </div>
            <div className="row col-sm-4 mx-auto mt-3">
                <button type="button" className="login-with-google-btn" onClick={signInWithGoogle}>
                    Sign in with Google
                </button>
            </div>
        </div>
    )
}

export default Login;