import AddStock from "./AddStock";
import "./StocksDisplay.css"
import {useNavigate} from "react-router-dom";

function StocksDisplay({stocks, deleteStock}) {

    const navigate = useNavigate();

    const yieldInPercent = (value) => {
        return (value < 0 ? <td key={"priceBuy"} style={{color: "red"}}>{Math.round(value * 100) / 100} %</td> :
            <td key={"priceBuy"} style={{color: "green"}}>{Math.round(value * 100) / 100} %</td>)
    }

    return (<div className="container">
            <div className="row">
                <div className="column mb-4"><h1>Meine Aktien</h1></div>
                <div className="column"><AddStock></AddStock></div>
            </div>
            <div>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Ticker</th>
                        <th scope="col">Unternehmensname</th>
                        <th scope="col">Kaufpreis</th>
                        <th scope="col">Aktueller Preis</th>
                        <th scope="col">Aktuelle Rendite</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {stocks.map((stock, index) => {
                        return <tr key={index}>
                            <td key={"ticker"}>{stock.ticker}</td>
                            <td key={"companyName"}>{stock.companyName}</td>
                            <td key={"currentPrice"}>{stock.priceBuy}</td>
                            <td key={"priceBuy"}>{stock.currentPrice}</td>
                            <td>{yieldInPercent((1 - stock.priceBuy / stock.currentPrice) * 100, stock)}</td>
                            <td>
                                <button className="btn btn-secondary btn-sm" onClick={ () => navigate(`/news?ticker=${stock.ticker}`)}>News
                                </button>
                            </td>
                            <td>
                                <button className="btn btn-danger btn-sm" onClick={() => deleteStock(stock)}>Löschen
                                </button>
                            </td>
                        </tr>
                    })}
                    </tbody>
                </table>
            </div>
        </div>

    );

}

export default StocksDisplay;