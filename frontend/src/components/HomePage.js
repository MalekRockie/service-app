import { Button } from "@mui/material";
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
    const navigate = useNavigate();
    const buttonStyle = {
        width: "200px",
        backgroundColor: "#00C314",
        margin: "0 2rem 0 0",
    };

    return (
        <div>
            <div style={{ textAlign: "center", marginTop: "2rem" }}>
                <h1>ServiceMe</h1>
                <p>Your favorite local Service Provider</p>
            </div>

            <div style={{ display: "flex", justifyContent: "center", marginTop: "4rem" }}>
                <Button
                    variant="contained"
                    color="success"
                    onClick={() => navigate("/signup")}
                    style={buttonStyle}
                >
                    Sign Up
                </Button>

                <Button
                    variant="contained"
                    color="success"
                    onClick={() => navigate("/login")}
                    style={buttonStyle}
                >
                    Login
                </Button>
            </div>

            <div style={{ textAlign: "center", marginTop: "6rem", marginBottom: "6rem" }}>
                <p>Got a gig? Wanna hire someone from your locality? This app is for you!</p>
            </div>

            <div style={{ textAlign: "center", marginTop: "2rem" }}>
                <p>Want to work as a service provider?</p>
            </div>
            <div style={{ display: "flex", justifyContent: "center", marginTop: "2rem" }}>
                <Button
                    variant="contained"
                    color="success"
                    onClick={() => navigate("/ProviderSignUp")}
                    style={buttonStyle}
                >
                    Apply Here
                </Button>

                <Button
                    variant="contained"
                    color="success"
                    onClick={() => navigate("/ProviderLogIn")}
                    style={buttonStyle}
                >
                    Service Providers Portal
                </Button>
            </div>
        </div>
    );
}

export default HomePage;
