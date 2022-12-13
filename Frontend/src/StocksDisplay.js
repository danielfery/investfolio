function StocksDisplay({stocks, deleteStock}) {
    const showStock = (stock) => {
        return (
            <tr>
                <th key="{stock}">{stock.isin}</th>
                <td>{stock.name}</td>
                <td>{stock.price}</td>
                <td><button className="btn btn-danger btn-sm"
                onClick={() => deleteStock(stock)}>
                    Löschen</button></td>
            </tr>
        );
    };

    return <div className="container">
        <div className="row"><h2>Meine Aktien</h2></div>
        <div className="row">
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">ISIN</th>
                    <th scope="col">Name</th>
                    <th scope="col">Preis</th>
                </tr>
                </thead>
                <tbody>
                {stocks?.map(showStock)}
                </tbody>
            </table>
        </div>
    </div>
}

export default StocksDisplay;