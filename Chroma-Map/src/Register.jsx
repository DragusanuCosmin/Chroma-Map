import React, { useState } from 'react';
import axios from 'axios';
import { v4 as uuidv4 } from 'uuid';
import Icon from '@mdi/react';
import { mdiCheck ,mdiAlertBox} from '@mdi/js';
export const Register = (props) => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [pass, setPass] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/person/${name}`);
      if (response.data.length > 0) {
        console.log('A person with the same name or email already exists.');
        console.log(response.data);
      } else {
        const uuid = uuidv4();
        await axios.post('http://localhost:8080/api/v1/person', { name:name, email:email, password:pass });
        props.onFormSwitch("home");
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <form className="register-form" onSubmit={handleSubmit}>
        <label htmlFor="name">Username</label>
        <div className="input-container">
          <input
            value={name}
            onChange={(e) => setName(e.target.value)}
            type="text"
            placeholder="Username"
            id="name"
            name="name"
          />
          {name.length > 0 && (
            <div className="validation-icon-container">
              {<Icon path={mdiAlertBox} size={1} />
              }
              {
                <Icon path={mdiCheck} size={1} />
              }
            </div>
          )}
          </div>
        <label htmlFor="email">Email</label>
        <input
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          type="email"
          placeholder="Email"
          id="email"
          name="email"
        />
        <label htmlFor="password">password</label>
        <input
          value={pass}
          onChange={(e) => setPass(e.target.value)}
          type="password"
          id="password"
          name="password"
        />
        <button type="submit">Register</button>
      </form>
      <button
        className="linkbutton"
        onClick={() => props.onFormSwitch("login")}
      >
        Already have an account? Login
      </button>
    </div>
  );
};
