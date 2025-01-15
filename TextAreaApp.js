import React, { useState } from "react"; // Import React and useState

// Main App Component
function App() {
  const [textValue, setTextValue] = useState(""); // State for the text area

  const handleInputChange = (event) => {
    setTextValue(event.target.value); // Update state on text change
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
      <h1>React Form State Example</h1>

      {/* Text Area */}
      <textarea
        value={textValue}
        onChange={handleInputChange}
        rows="5"
        cols="50"
        placeholder="Type something here..."
        style={{ marginBottom: "20px", padding: "10px", fontSize: "16px" }}
      ></textarea>

      {/* Display updated text */}
      <div>
        <h3>Updated Value:</h3>
        <p style={{ whiteSpace: "pre-wrap", backgroundColor: "#f9f9f9", padding: "10px", border: "1px solid #ddd" }}>
          {textValue || "(Start typing to see the value here)"}
        </p>
      </div>
    </div>
  );
}

export default App; // Correctly export the App component
