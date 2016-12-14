INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Pista', 'Lakatos', '1990-08-30 19:05:00', 'pista@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', false);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('John', 'Doe', '1990-08-30 19:05:00', 'john@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', false);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Matej', 'Majdis', '1994-02-27 19:05:00', 'mato@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Kamil', 'Triscik', '1990-08-30 19:05:00', 'kamil@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Vit', 'Hovezak', '1990-08-30 19:05:00', 'vit@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
INSERT INTO sportsman(name, surname, birth_date, email, password, is_manager) VALUES ('Veronika', 'Aksamitova', '1990-08-30 19:05:00', 'veronika@example.com', '$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2', true);
--$2a$06$ZLWy4mNTyNWVJ5h7nVsWzut9G3mtIGI05uxGGne22Tx/RFvYjNhb2 = supersilneheslo

INSERT INTO sport(name, description) VALUES ('Triathlon', 'Triathlon description');
INSERT INTO sport(name, description) VALUES ('Swimming', 'Swimming description');

INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Triathlon Brno', 'Triathlon in Brno', '2016-08-30 19:05:00', 1, 100, 'Brno', 'Brno, 60200 - CR', 1);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Triathlon Brno2', 'Triathlon in Brno', '2016-09-30 19:05:00', 1, 100, 'Brno', 'Brno, 60200 - CR', 2);
INSERT INTO event(name, description, event_date, sport, capacity, city, address, admin) VALUES ('Triathlon Brno3', 'Triathlon in Brno', '2016-10-30 19:05:00', 1, 100, 'Brno', 'Brno, 60200 - CR', 2);

INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (20220.2, 1, 1, 1, 'Winner', 1);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (5456.2, 1, 2, 1, 'silver medal', 2);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (6.2, 4, 1, 6, 'gold medal', 2);
INSERT INTO result(performance,  performance_unit, position, sportsman, note, event) VALUES (7.2, 4, 1, 6, 'gold', 3);

INSERT INTO invitation(event, invitee, state) VALUES(1, 6, 4);