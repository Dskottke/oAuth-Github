import React, {useEffect, useState} from 'react';
import axios from "axios";

function Home() {
    const [name, setName] = useState("");

    useEffect(() => {
        axios.get("/users/oauth/github")
            .then((response) => {
                setName(response.data);
            })
    }, [])

    function navigateToGithub() {
        window.open("https://github.com/login/oauth/authorize?client_id=a9805e8917a81b2f906a", "_self");
    }

    return (
        <div>
            <h1>{name}</h1>
            <p>Click the button below to navigate to my Github page.</p>
            <button onClick={navigateToGithub}>Github</button>
        </div>
    );
}

export default Home;