INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Pista', 'Lakatos', '1990-08-30 19:05:00', 'pista@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', false);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('John', 'Doe', '1990-08-30 19:05:00', 'john@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', false);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Matej', 'Majdis', '1994-02-27 19:05:00', 'mato@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Kamil', 'Triscik', '1990-08-30 19:05:00', 'kamil@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Vit', 'Hovezak', '1990-08-30 19:05:00', 'vit@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Veronika', 'Aksamitova', '1990-08-30 19:05:00', 'veronika@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
--$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2 = supersilneheslo

INSERT INTO sport(name, description) VALUES ('Triathlon', 'Triathlon description');
INSERT INTO sport(name, description) VALUES ('Polo', 'Water sport with ball');
INSERT INTO sport(name, description) VALUES ('Tennis', 'Tennis description');
INSERT INTO sport(name, description) VALUES ('Baseball', 'Baseball description');
INSERT INTO sport(name, description) VALUES ('Swimming', 'Swimming description');

INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Triathlon Brno', 'Triathlon in Brno', '2017-08-30 19:05:00', 1, 100, 'Brno', 'Brno, 60200 - CR', 1);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Triathlon Brno2', 'Triathlon in Brno', '2017-09-30 19:05:00', 1, 100, 'Brno', 'Brno, 60200 - CR', 2);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Triathlon Brno3', 'Triathlon in Brno', '2017-10-30 19:05:00', 1, 100, 'Brno', 'Brno, 60200 - CR', 2);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Tennis 1 Brno', 'Tennis in Brno', '2017-10-28 18:05:00', 3, 80, 'Brno', 'Brno, 60200 - CR', 1);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Baseball 1 Brno', 'Fresh new event', '2017-10-29 17:05:00', 4, 70, 'Brno', 'Brno, 60200 - CR', 2);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Tennis 2 Brno', 'Tennis in Brno for the second time', '2017-10-29 18:05:00', 3, 80, 'Brno', 'Brno, 60200 - CR', 1);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Baseball 2 Brno', 'New event for baseball fans', '2017-10-30 17:05:00', 4, 70, 'Brno', 'Brno, 60200 - CR', 2);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Swimming Brno', 'New event for fans of swimming', '2017-11-29 17:05:00', 5, 70, 'Brno', 'Brno, 60200 - CR', 2);

INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (20220.2, 1, 1, 1, 'Winner', 1);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (20220.5, 1, 2, 6, 'silver medal', 1);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (20220.2, 1, 1, 5, 'gold medal', 2);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (54256.2, 1, 2, 1, 'silver medal', 2);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (12, 5, 1, 3, 'gold', 4);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (7, 2, 3, 4, 'third place', 8);

INSERT INTO invitation(event, invitee, state) VALUES(4, 6, 1);
INSERT INTO invitation(event, invitee, state) VALUES(5, 6, 2);
INSERT INTO invitation(event, invitee, state) VALUES(6, 6, 3);
INSERT INTO invitation(event, invitee, state) VALUES(7, 6, 4);
INSERT INTO invitation(event, invitee, state) VALUES(8, 6, 5);