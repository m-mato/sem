#REST
Available on `http://localhost:8080/pa165/rest`


**Sportsmans**
- return all sportsmans from DB

`curl http://localhost:8080/pa165/rest/sportsmans/`

 - return sportsman with id 1
 
`curl http://localhost:8080/pa165/rest/sportsmans/1`

- create new sportsman

`curl -H "Content-Type: application/json" -X POST -d '{"name":"newSportman","surname":"newSportman", "birthDate":"1994-02-27 19:05", "email":"newSportman@email.com", "password": "newPass", "isManager": false}'  http://localhost:8080/pa165/rest/sportsmans/create` 

- update sportsman

`curl -H "Content-Type: application/json" -X PUT -d '{"id":7,"name":"newSportman","surname":"newSportman", "birthDate":"1994-02-27 19:05", "email":"newSportman@email.com", "password": "newPass", "isManager": true}'  http://localhost:8080/pa165/rest/sportsmans/update`

 - delete sportsman with id 7
 
`curl -X DELETE http://localhost:8080/pa165/rest/sportsmans/7`


**Results**
- return all results from DB

`curl http://localhost:8080/pa165/rest/results/`

 - return result with id 1
 
`curl http://localhost:8080/pa165/rest/results/1`

 - delete result with id 1
 
`curl -X DELETE http://localhost:8080/pa165/rest/results/1`

**Events**
- return all event from DB

`curl http://localhost:8080/pa165/rest/events/`

 - return event with id 1
 
`curl http://localhost:8080/pa165/rest/events/1`

 - delete event with id 1
 
`curl -X DELETE http://localhost:8080/pa165/rest/events/1`

**Sports**
 - return all sports from DB
 
`curl http://localhost:8080/pa165/rest/sports/`

- return sport with id 1

`curl http://localhost:8080/pa165/rest/sports/1`

- create new sport

`curl -H "Content-Type: application/json" -X POST -d '{"name":"newSport","description":"new sport"}'  http://localhost:8080/pa165/rest/sports/create`

- update sport

`curl -H "Content-Type: application/json" -X PUT -d '{"id":6,"name":"newSport","description":"new sport updated"}'  http://localhost:8080/pa165/rest/sports/update`

- delete sport with id 1

`curl -X DELETE http://localhost:8080/pa165/rest/sports/6`
