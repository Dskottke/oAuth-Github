import React, {useEffect, useState} from 'react';
import axios from "axios";
import "./Home.css"

function Home() {
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");


    useEffect(() => {
        getCurrentUser()
    }, [])

    function getCurrentUser() {
        axios.get("/users/")
            .then((response) => {
                setName(response.data);
            })
    }

    function navigateToGithub() {
        window.open("https://github.com/login/oauth/authorize?client_id=a9805e8917a81b2f906a", "_self");
    }

    function handleNameChange(e: React.ChangeEvent<HTMLInputElement>) {
        setUsername(e.target.value);
    }

    function handlePasswordChange(e: React.ChangeEvent<HTMLInputElement>) {
        setPassword(e.target.value);
    }

    function handleSubmitLogin(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        axios.post("/users/login", {
            username: username,
            password: password
        })
            .then((response) => {
                console.log(response.data);
            })
            .then(() => getCurrentUser())

    }
    function handleLogout() {
        axios.get("/users/logout")
            .then(() => {
                setName("");
            })
    }

    return (
        <div>
            <h1>{name}</h1>
            {name && <button onClick={handleLogout}>Logout</button>}
            <form className={"login-form"} onSubmit={handleSubmitLogin}>
                <div className={"login-form name"}>
                    <label htmlFor="name">Name</label>
                    <input type="text" placeholder="Enter your name" onChange={handleNameChange}/>
                </div>
                <div className={"login-form password"}>
                    <label htmlFor="password">password</label>
                    <input type="password" placeholder="Enter your password" onChange={handlePasswordChange}/>
                </div>
                <button type="submit">Submit</button>
            </form>


            <p>Click the button below to navigate to my Github page.</p>
            <button onClick={navigateToGithub}>Github</button>

        </div>
    );
}

export default Home;