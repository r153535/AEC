import React, { useState } from "react";

const StudentPerformance = () => {
  const studentsPercentages = [
    90, 78, 85, 92, 88, 70, 65, 60, 83, 80, 58, 45, 89, 91, 95, 72, 67, 69, 61, 84,
    55, 77, 62, 64, 68, 73, 87, 93, 79, 88
  ];

  const calculateCounts = (percentages) => {
    const distinctionCount = percentages.filter((p) => p >= 85).length;
    const firstClassCount = percentages.filter((p) => p >= 60 && p < 85).length;
    return { distinctionCount, firstClassCount };
  };

  const { distinctionCount, firstClassCount } = calculateCounts(studentsPercentages);

  return (
    <div style={{ fontFamily: "Arial", padding: "20px" }}>
      <h1>Student Performance Analysis</h1>
      <p>
        <strong>Distinction (85% and above):</strong> {distinctionCount}
      </p>
      <p>
        <strong>First Class (60% to 84%):</strong> {firstClassCount}
      </p>
    </div>
  );
};

export default StudentPerformance;
