import React, {useEffect, useState} from "react";
import {useNavigate, useSearchParams} from "react-router-dom";
import Card from 'react-bootstrap/Card';
import "../StocksNews.css"

function StocksNews(isAuth) {

    let navigate = useNavigate();

    const [searchParams, setSearchParams] = useSearchParams();
    //searchParams.get("ticker")
    //console.log(searchParams.get("ticker"))

    const crawl_with_ticker = `https://eodhistoricaldata.com/api/news?api_token=63b7db619845e5.05869140&s=${searchParams.get("ticker")}.US&offset=0&limit=10`
    const crawl_demo = "https://eodhistoricaldata.com/api/news?api_token=demo&s=AAPL.US&offset=0&limit=5"
    const apple_logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAA2CAMAAACLF4vOAAAAY1BMVEUAAAD///9RUVEzMzNKSkpZWVn8/PwwMDCkpKS6urrf39/U1NTCwsLFxcVjY2Pu7u46OjqsrKz09PS0tLTl5eWFhYUICAiTk5Oenp5oaGh5eXmNjY1zc3NFRUV/f39tbW0YGBgmESipAAABcElEQVRIiY3V2ZKDIBAF0IvjGmPc0Jht4v9/5YCAQccKt9+EU0hj20B8i9M1TrxHfKEyAhBzeIKOG4Xvi8XE4KuxOBG4tPZHEDiz+EzgytpIEPhiccpga6UgcG320O2XOMRS0eb8b/gYp20hD4Y/uOuHJEoelfBGno066Vsud/jk8gcGM1cl6whepY8H+NFc219s473itEEwstTiLGyXKtE4CUsdlcY9Z+WyMkXHbkkwZ9fVmMouN+csGTvbL0ill1u8/1SHUVvMHPLsConJz3UljAS+OMyE+8cxE9h1GhDVuXYEXMJUF5zBQ5iuGaJlsO2lKMMSLkWkFMbTlGjMa7b4VX+oFa5JDDzUP0juQzUahQsWV2CLSVe1xmSKrWlfTE1jtL2O2nXhWm4UtvHan7swrj/NvArZ3r8mAmX93Nwp37fdbC8gIV7eZBbHfj/J0j0WNzsVFctcenYvc21jc2mW73mMJu+2lFPSvO7l+vwH31wMWSQbw34AAAAASUVORK5CYII="

    const [news, setNews] = useState([]);

    useEffect(() => {
        if (!isAuth) {
            navigate("/login");
        }
    }, []);


    useEffect(() => {
        const dataFetch = async () => {
            const news = await (
                await fetch(
                    crawl_demo
                )
            ).json();
            setNews(news);
            console.log(news)
        };
        dataFetch();
    }, []);


    return (<div className="container">
            <div className="row">
                <div className="column mb-4 mt-4"><h1 className="title">Recent News on {searchParams.get("ticker")}</h1>
                </div>
            </div>
            <div>
                {news.map((news, index) => {
                    return (
                        <Card key={index} className="card_margin">

{/*                            {(searchParams.get("ticker") === "AAPL")
                                ? <>
                                    <Card.Img className="card_image" variant="top" src={apple_logo} />
                                </>
                                : <></>
                            }*/}
<Card.Header>

                                <Card.Title className="card_title">{news.title}
                                    {(searchParams.get("ticker") === "AAPL")
                                        ? <>
                                            <Card.Img className="card_image" src={apple_logo} />
                                        </>
                                        : <></>
                                    }
                                </Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">{news.date}</Card.Subtitle>
</Card.Header>
                            <Card.Body>
                                <Card.Text className="card_text">{news.content}</Card.Text>
                                <a className="btn btn-secondary btn-sm" href={news.link} target="_blank"
                                   rel="noopener noreferrer">Go to Article</a>
                            </Card.Body>
                        </Card>
                    )
                })}
            </div>
        </div>

    );
}

export default StocksNews;