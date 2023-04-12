import React, { useState ,useContext } from "react";
import { AuthContext } from "./AuthContext";
export const Login = (props) => {
  const [name, setName] = useState("");
  const [pass, setPass] = useState("");
  const authContext = useContext(AuthContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await fetch(
      `http://localhost:8080/api/v1/person/${name}`
    );
    const data = await response.json();
    if(response.ok)
    if (name === data.name && pass === data.password) {
      authContext.login();
      props.onFormSwitch("home");
      console.log("logged in");
    }
  };
  return (
    <div className="auth-form-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="name">Username</label>
        <input
          value={name}
          onChange={(e) => setName(e.target.value)}
          type="name"
          placeholder="Username"
          id="name"
          name="name"
        />
        <label htmlFor="password">Password</label>
        <input
          value={pass}
          onChange={(e) => setPass(e.target.value)}
          type="password"
          id="password"
          name="password"
        />
        <button type="submit">Log In</button>
      </form>
      <button
        className="linkbutton"
        onClick={() => props.onFormSwitch("register")}
      >
        Don't have an account? Register
      </button>
    </div>
  );
};
