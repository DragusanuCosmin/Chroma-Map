# PJP
Chroma-Map is an application built with Java Spring and React that allows users to contribute to drawing on a 100x100 canvas to create an image with other users.
Users can add color, modify their chosen block or delete it.
# Installation
To run CodeWave locally, follow these steps:
```
Clone the repository: git clone https://github.com/DragusanuCosmin/Chroma-Map.git
```
Install dependencies for the client and server:
```
cd CodeWave/client
npm install
cd ../server
mvn install
```
Create a .env file in the server directory and add your MySQL connection details:
```
spring.datasource.url=jdbc:mysql://localhost:3306/codewave?useSSL=false
spring.datasource.username=your_mysql_username_here
spring.datasource.password=your_mysql_password_here
```
Create the MySQL database and tables by running the SQL script 
```
CodeWave/server/src/main/resources/db_migrations/CodeWave.sql.
Run the client and server:
cd ../client
npm start
cd ../server
mvn spring-boot:run
```
