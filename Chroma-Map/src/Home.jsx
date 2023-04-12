import React, { useState, useEffect } from "react";
import "./Home.css";

const GRID_ROW_LENGTH = 100;
const GRID_COL_LENGTH = 100;

export const Home = (props) => {
  const [gridData, setGridData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/v1/locations");
        const data = await response.json();
        setGridData(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  const grid = [];
  for (let row = 0; row < GRID_ROW_LENGTH; row++) {
    const currentRow = [];
    for (let col = 0; col < GRID_COL_LENGTH; col++) {
      currentRow.push(createNode(col, row));
    }
    grid.push(currentRow);
  }
function createNode(col, row) {
  const location = gridData.find(loc => loc.x === col && loc.y === row);
  return {
    col,
    row,
    isWall: !!location,
    color: location ? location.color : ''
  };
}
return (
  <div className="map">
    <div className="grid">
      {grid.map((row, rowId) => (
        <div key={rowId} className="grid-row">
          {row.map((node, nodeId) => (
            <div
              key={nodeId}
              className={`node ${node.isWall ? "node-wall" : ""}`}
              style={{backgroundColor: node.color}}
            ></div>
          ))}
        </div>
      ))}
    </div>
  </div>
);

};
