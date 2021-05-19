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
[GET]/admin/show/{adminName} - get a JSON with information about administrator by name.<br>
/city/add
/city/delete/{cityName}
/city/{cityName}
/city/update/{cityName}
/city/update/name/{cityName}
/city/update/year/{cityName}
/city/update/population/{cityName}
/city/update/information/{cityName}
/city/all

