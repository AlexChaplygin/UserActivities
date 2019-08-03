# UserActivities test task

Создать WEB интерфейс, отображающий статистику активности пользователя за указанный период времени. 
Лог активности пользователя находится в базе в таблице T_USER_ACTIVITY_LOG со следующими полями

Create WEB interface to show user's statistic for particular time period.
All activities are in "T_USER_ACTIVITY_LOG" table.
Information about users is in "T_USER" table


Page has to provide following filter:

1. Start date - input field or drop-down list.
2. End date - input field or drop-down list.
3. User - list of usernames. The user can choose several elements.
4. Step - drop-down list with these values: «hour», «day», «week», «month», «year»

Example for step «day»:

| Period | User1 | User2 |
|---| ----- | --- |
|27/02/2003| 12 | 0 |
|28/02/2003| 1 | 0 |
|01/03/2003| 2 | 114 |
|02/03/2003| 0 | 0 |

---

### TECHNOLOGIES AND TOOLS

| # | Title |
|---| ----- |
|1| Java |
|2| Spring Boot |
|3| Spring Data |
|4| JQuery |
|5| PostgreSQL |

### DATABASE DUMP (see database_init.sql file)