# taskSimpleTable

The project involves exposing four REST returning data from a single database table with fields:
- Date & Timestamp
- Value (Float)
- Engineering Units
- Quality (Good, Bad)

Return rest:
- REST /getLastValue - Return the latest value, along with timestamp and quality
- REST /getAvgValue - Return the average value between two date/times
- REST /getGoodValues - Return the series of good values between two date/times
- REST /getInterValues - Return a series of the interpolated values at X minute intervals between two date/times

The project was created using Hibernate/ JPA and MSSQL Server

To run the project, change the configuration to MSSQL Server in the application.properties file.

Parameters to change (instance name, datatbase name, username, password):
spring.datasource.jdbc-url=jdbc:sqlserver://localhost\\instance;database=DB
spring.datasource.username=****
spring.datasource.password=****
