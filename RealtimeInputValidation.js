import React, { useState, useEffect } from "react";

const App = () => {
  const [inputValue, setInputValue] = useState("");
  const [isValid, setIsValid] = useState(true);

  useEffect(() => {
    const validateInput = () => {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      setIsValid(emailRegex.test(inputValue));
    };

    validateInput();
  }, [inputValue]);

  const handleChange = (e) => {
    setInputValue(e.target.value);
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Email Validation Example</h1>
      <input
        type="text"
        value={inputValue}
        onChange={handleChange}
        placeholder="Enter your email"
        style={{
          padding: "10px",
          borderRadius: "5px",
          border: `2px solid ${isValid ? "green" : "red"}`,
        }}
      />
      <p
        style={{
          color: isValid ? "green" : "red",
          fontWeight: "bold",
          marginTop: "10px",
        }}
      >
        {isValid ? "Valid Email" : "Invalid Email"}
      </p>
    </div>
  );
};

export default App;
