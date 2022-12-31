import AddStock from "./AddStock";

function StocksDisplay({stocks, deleteStock}) {

    const test = (value) => {
        return (value < 0 ? <td key={"priceBuy"} style={{color: "red"}}>{value} %</td> :
            <td key={"priceBuy"} style={{color: "green"}}>{value} %</td>)
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
                    </tr>
                    </thead>
                    <tbody>
                    {stocks.map((stock, index) => {
                        return <tr key={index}>
                            <td key={"ticker"}>{stock.ticker}</td>
                            <td key={"companyName"}>{stock.companyName}</td>
                            <td key={"currentPrice"}>{stock.currentPrice}</td>
                            <td key={"priceBuy"}>{stock.priceBuy}</td>
                            <td>{test((1 - stock.currentPrice / stock.priceBuy) * 100, stock)}</td>
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