import {useState} from "react";
import {auth} from "./Firebase";

function AddStock(props) {
    // useState always returns an array with two values, the first being the state, the second a function which allows updating the current state
    const [ticker, setTicker] = useState("");

    const addStock = () => {

            const userId = auth.currentUser.uid;
            // console.log(userId)

            const requestOptions = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
            }

            // TODO: check if stock is already in database and only POST data if not

            fetch(`http://localhost:8080/api/createStock?userId=${userId}&ticker=${ticker}`, requestOptions)
                .then((response) => console.log(response));
        }
    ;


    return (
        <div className="container">
            <div className="row mt-4 text-center">
                <h2>Aktie hinzufügen</h2>
            </div>
            <div className="row col-sm-4 mx-auto mt-2">
                <label htmlFor="isin-field" className="text-center">Ticker:</label>
                <input id="isin-field"
                       type="text"
                       className="form-control"
                       value={ticker}
                    // grabing the value of the input whenever there is a change
                       onChange={(e) => setTicker(e.target.value)}
                />
            </div>
            <div className="row col-sm-2 mx-auto mt-3">
                <button type="button" className="btn btn-outline-primary" onClick={addStock}>
                    Add Stock
                </button>
            </div>
        </div>
    );
}

export default AddStock;
