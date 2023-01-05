import {useState} from "react";
import {auth} from "./Firebase";

function AddStock() {
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

            // TODO: check if stock is already in database and only POST data if not (or check server side)

            fetch(`http://localhost:8080/api/createStock?userId=${userId}&ticker=${ticker}`, requestOptions)
                .then(() => window.location.reload());

        }
    ;

    return (
        <div className="container">
            <div className="row mt-1">
                <div className="column">
                    <input id="isin-field"
                           type="text"
                           className="form-control"
                           value={ticker}
                           placeholder="Ticker"
                        // grabing the value of the input whenever there is a change
                           onChange={(e) => setTicker(e.target.value)}
                    />
                </div>
                <div className="column">
                    <button type="button" className="btn btn-secondary" onClick={addStock}>Add Stock</button>
                </div>
            </div>
        </div>
    );
}

export default AddStock;
