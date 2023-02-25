import React, {useEffect} from 'react';
import {useSearchParams} from "react-router-dom";
import axios from "axios";


function GithubRedirectPage() {
    const [searchParams] = useSearchParams()

    function sendCodeToBackend() {
        axios.post("/users/oauth/github", searchParams.get("code"), {
            headers: {
                'Content-Type': 'text/plain'
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