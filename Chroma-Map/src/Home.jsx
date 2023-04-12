import React, { useState } from "react";

export const Home = (props) => {
  const [name, setName] = useState("");
  const [pass, setPass] = useState("");
  const [data, setData] = useState({});
  const handleSubmit = async (e) => {
    e.preventDefault();
   const response = await fetch(`http://localhost:8080/api/v1/person?name=${name}`);
   const data = await response.json();
   setData(data);
  };
    return (
    <div className="map">
      <p>{data.name}</p>
    </div>
  );
};
