import React, {useEffect, useState} from "react"
import Login, {WelcomeMessage} from "./Login";
import StocksDisplay from "../StocksDisplay";
import AddStock from "../AddStock";
import {useNavigate} from "react-router-dom";
import axios from "axios";

/*const UserData = () => {
    const fetchUserData = () => {
        axios.get("http://localhost:8080/api/getUser?userId=uid1")
            .then(r => { console.log(r)});
    }
    useEffect(() => {
        fetchUserData();
    }, []);
}

const UserStocks = () => {
    const fetchUserStocks = () => {
        axios.get("http://localhost:8080/api/getStocks?userId=uid1")
            .then(r => { console.log(r)});
    }
    useEffect(() => {
        fetchUserStocks();
    }, []);
}*/

function Home({isAuth}) {

    // creating a state data and a function setData to alter the data
    const [data, setData] = useState({stocks: []});
    //const stocksCollectionRef = collection(db, "stocks")
    let navigate = useNavigate();

    useEffect(() => {
        if (!isAuth) {
            navigate("/login");
        }
    }, []);


    // GET all stocks of one user
/*     useEffect( () => {
         axios.get("http://localhost:8080/api/getStocks?userId=uid1")
         .then(r => console.log(r))
         .then((data) => setData({stocks: data}));
     }, []);*/

    // GET user data
    useEffect(() => {
        console.log("effect run")
        axios.get("http://localhost:8080/api/getUser?userId=HjdmpS5BokWyO7XpQtqriZYlx2g2").then(r => console.log(r))
    }, []);

    // POST delete one stock
    // const deleteStock = (stock) => {
    //     const stocks = data["stocks"];
    //     const requestOptions = {
    //         method: "DELETE"
    //     };
    //     // when using back ticks we can embed an expression in a string with ${}
    //     fetch(`http://localhost:8080/api/stocks/${stock.id}`, requestOptions)
    //         .then((response) => {
    //                 // check if the response was ok and only then delete the stock from the frontend
    //                 if (response.ok) {
    //                     const idx = stocks.indexOf(stock);
    //                     stocks.splice(idx, 1);
    //                     setData({stocks: stocks});
    //                 }
    //             }
    //         );
    // };


    // POST add a stock
    // const addStock = (stock) => {
    //     let stocks = data["stocks"];
    //
    //     const requestOptions = {
    //         method: "POST",
    //         headers: {
    //             "Content-Type": "application/json",
    //         },
    //         body: JSON.stringify(stock)
    //     };
    //     fetch("http://localhost:8080/api/addStock", requestOptions)
    //         .then((response) => response.json())
    //         .then((data) => {
    //             stocks.push(data);
    //             setData({stocks: stocks});
    //         });
    //
    // };

    return (
        <div className="container">
            <div className="row mt-5 text-center">
                <WelcomeMessage></WelcomeMessage>
            </div>
            <div>
                <AddStock></AddStock>
            </div>


{/*            <div className="row mt-3">
                <StocksDisplay deleteStock={deleteStock} stocks={data["stocks"]}></StocksDisplay>
            </div>
            <div className="row mt-3">
                <AddStock addStock={addStock}></AddStock>
            </div>*/}
        </div>
    )
}

export default Home