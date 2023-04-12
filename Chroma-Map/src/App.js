import React, { useState } from "react";
import { Login } from "./Login";
import { Register } from "./Register";
import { Home } from "./Home";
import { AuthProvider } from "./AuthContext";
import "./App.css";

function App() {
  const [currentForm, setCurrentForm] = useState("login");
  const toggleForm = (formName) => {
    setCurrentForm(formName);
  };
  return (
    <AuthProvider>
      <div className="App">
        {currentForm === "login" ? (
          <Login onFormSwitch={toggleForm} />
        ) : currentForm === "register" ? (
          <Register onFormSwitch={toggleForm} />
        ) : (
          <Home onFormSwitch={toggleForm} />
        )}
      </div>
    </AuthProvider>
  );
}

export default App;
