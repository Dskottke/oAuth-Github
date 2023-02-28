import React, {useEffect} from 'react';
import {useSearchParams} from "react-router-dom";
import axios from "axios";
import {TokenRequest} from "./TokenRequest";


function GithubRedirectPage() {
    const [searchParams] = useSearchParams()


    function sendCodeToBackend() {

        axios.post("/api/v1/auth/oauth/github", searchParams.get("code"), {
            headers: {
                'Content-Type': 'text/plain',
            }
        }).then((response) => {
            if (response.data) {
                const token: TokenRequest = response.data;
                localStorage.setItem("token", token.token);
            }
        })
            .then(() => {
                window.open("/", "_self");
            })
    }


    useEffect(() => {
        sendCodeToBackend();
    }, [])


    return (
        <div>

        </div>
    );
}

export default GithubRedirectPage;