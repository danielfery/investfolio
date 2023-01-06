// here goes everything connected to firebase (e.g. the database)

// import {getFirestore, collection, getDocs} from 'firebase/firestore/lite';
import {getAuth, GoogleAuthProvider, signInWithPopup} from "firebase/auth";

// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyB2_z3_nFG860PIqyqEfEJRZi19aoHqjDQ",
    authDomain: "investfolio-f4bed.firebaseapp.com",
    projectId: "investfolio-f4bed",
    storageBucket: "investfolio-f4bed.appspot.com",
    messagingSenderId: "898432821383",
    appId: "1:898432821383:web:1cd6a7bc81d2cf4b1bf022"
};

// Initialize Firebase
// app represents all the firebase connections
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
// export const db = getFirestore(app);

export const provider = new GoogleAuthProvider();
