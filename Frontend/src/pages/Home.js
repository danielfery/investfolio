import React, {useEffect, useState} from "react"
import StocksDisplay from "../StocksDisplay";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {auth} from "../Firebase";
import "../Home.css"


function Home({isAuth}) {

    // creating a state data and a function setData to alter the data
    const [data, setData] = useState([]);

    let navigate = useNavigate();

    useEffect(() => {
        if (!isAuth) {
            navigate("/login");
        }
    }, []);

    // GET all stocks of one user
    useEffect(() => {
        // fetch data
        const dataFetch = async () => {
            const data = await (
                await fetch(
                    "http://localhost:8080/api/getStocks?userId=HjdmpS5BokWyO7XpQtqriZYlx2g2"
                )
            ).json();
            // set state when the data received
            setData(data);
            console.log(data)
        };
        dataFetch();
    }, []);

    // GET user data
    useEffect(() => {
      // console.log("effect run")
        axios.get("http://localhost:8080/api/getUser?userId=HjdmpS5BokWyO7XpQtqriZYlx2g2").then(r => console.log(r))
    }, []);


    const deleteStock = (stock) => {

        const userId = auth.currentUser.uid;

        const requestOptions = {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
        }
         // when using back ticks we can embed an expression in a string with ${}
        fetch(`http://localhost:8080/api/deleteStock?userId=${userId}&ticker=${stock.ticker}`, requestOptions)
            .then(() => window.location.reload());
    }

    return (
        <div className="home_bg">
            <div className="row mt-5">
                <StocksDisplay deleteStock={deleteStock} stocks={data}></StocksDisplay>
            </div>
            <br/>
        </div>
    );
}

export default Home