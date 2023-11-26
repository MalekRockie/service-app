import { Button } from "@mui/material";
import { Link } from 'react-router-dom';


const HomePage = () => {
    const buttonStyle = {
        width: "200px",
        backgroundColor: "#00C314", // Change the button color to #00C314
    };



    return (
        <div>
            <div style={{ textAlign: "center", marginTop: "2rem" }}>
                <h1>ServiceMe</h1>
                <p>Your favorite local Service Provider</p>
            </div>

            <div style={{ display: "flex", justifyContent: "center", marginTop: "4rem" }}>
            <Link to="/signup" style={{ textDecoration: 'none' }}>

                <Button variant="contained" color="success" onClick={() => console.log("Sign Up clicked")} style={{ ...buttonStyle }}>
                    Sign Up
                </Button>
                </Link>

                <Button variant="contained" color="success" onClick={() => console.log("Login clicked")} style={{ marginLeft: "4rem", ...buttonStyle }}>
                    Login
                </Button>
            </div>

            <div style={{ textAlign: "center", marginTop: "6rem", marginBottom: "6rem" }}>
                <p>Got a gig? Wanna hire someone from your locality? This app is for you!</p>
            </div>

            <div style={{ textAlign: "center", marginTop: "12rem" }}>
                <p>Want to work as a service provider?</p>
            </div>

            <div style={{ display: "flex", justifyContent: "center", marginTop: "1rem" }}>
                <Button variant="contained" color="success" onClick={() => console.log("Another Button clicked")} style={{ ...buttonStyle }}>
                    Apply Here
                </Button>
            </div>
        </div>
    );
}

export default HomePage;
