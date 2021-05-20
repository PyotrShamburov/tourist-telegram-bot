# tourist-telegram-bot
Telegram-Bot, which tell info about cities.<br>
Name of bot: tourist_help_bot <br>
Token: 1660590905:AAEtrtqTVzM96x36s_6BfEhNGfs0-M_iJqI <br>
<br>
In project used follows technologies: <br>
- Java 8,<br>
- SpringBoot<br>
- SpringDataJPA<br>
- Hibernate<br>
- H2 database<br>
- Hibernate-Validator<br>
- Validation-API<br>
- Lombok<br>
- JUnit5<br>
- Slf4J<br>
<br>
External library, for work with telegram: <br>
- telegrambots-spring-boot-starter , version 5.0.1.1<br>
<br>
Cities are stored in the H2 database, adding, deleting and changing is done through REST web services.<br>
Only registered and authorized in the system administrator can perform operations with data.<br>
After authorization, the administrator receives a token with which he can work with data.<br>
It is necessary to place the token in the header of the HTTP request after the "X-Token:". Without the token you will see response code 403.<br>
<br>
URL paths implements in project:<br>
 - Access performed without token:<br>
[POST]/admin/add - registration and addition new administrator in database.<br>
JSON example for registration:<br>
"adminName": "Mike",<br>
  "password": "Pass2021"<br>
  <br>
[POST]/admin/auth - authorization, after it step you take token.<br>
JSON example for authorization:<br>
"adminName": "Mike",<br>
  "password": "Pass2021"<br>
<br>
 - Access performed only with token:<br>
[PUT]/admin/update/{adminName} - update existing administrator by name.<br>
JSON example :<br>
"adminName": "Mike",<br>
  "password": "Pass2021"<br>
  <br>
[DELETE]/admin/delete/{adminName} - delete administrator by name.<br>
<br>
[GET]/admin/show/{adminName} - get a JSON with information about administrator by name.<br>
<br>
[POST]/city/add - save new city in database.<br>
Population must be indicated in thousands.<br>
JSON example :<br>
  "name": "Minsk",<br>
  "yearOfFoundation": 1234,<br>
  "population": 1000,<br>
  "information": "Do not forget visit National Library and Gallery!"<br>
  <br>
[POST]/city/delete/{cityName} - delete city from database by name.<br>
<br>
[GET]/city/{cityName} - get city from database by name.<br>
<br>
[PUT]/city/update/{cityName} - update all fields of city by name.<br>
JSON example :<br>
"name": "Minsk",<br>
  "yearOfFoundation": 1234,<br>
  "population": 1000,<br>
  "information": "Do not forget visit National Library and Gallery!"<br>
  <br>
[PUT]/city/update/name/{cityName} - update only name of city by old name.<br>
JSON example :<br>
"name": "Minsk"<br>
<br>
[PUT]/city/update/year/{cityName} - update only year of foundation by name of city.<br>
JSON example :<br>
"yearOfFoundation": 1234,<br>
<br>
[PUT]/city/update/population/{cityName} - update only population by name of city.<br>
Population must be indicated in thousands.<br>
JSON example :<br>
"population": 1000,<br>
<br>
[POST]/city/update/information/{cityName} - update information about existing in database city by name.<br>
JSON example :<br>
"information": "Do not forget visit National Library."<br>
<br>
[GET]/city/all - get list of all cities from database in JSON.<br>

