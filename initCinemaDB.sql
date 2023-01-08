CREATE DATABASE cinema_db;
use cinema_db;

CREATE TABLE director
(
    director_id INT(11)      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    birth_year  INT(11)      NOT NULL,
    country     VARCHAR(255) NOT NULL,
    PRIMARY KEY (director_id)
);

CREATE TABLE film
(
    film_id      INT(11)      NOT NULL AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    release_date DATE         NOT NULL,
    country      VARCHAR(255) NOT NULL,
    director_id  INT(11)      NOT NULL,
    PRIMARY KEY (film_id),
    FOREIGN KEY (director_id) REFERENCES director (director_id)
);

INSERT INTO director (name, birth_year, country)
VALUES ('Christopher Nolan', 1970, 'United Kingdom'),
       ('Quentin Tarantino', 1963, 'United States'),
       ('Martin Scorsese', 1942, 'United States')
;

INSERT INTO film(title, release_date, country, director_id)
VALUES ('Inception', '2010-08-06', 'Spain', 1),
       ('Interstellar', '2014-11-07', 'Spain', 1),
       ('Inglorious Basterds', '2009-07-28', 'Spain', 2),
       ('Django Unchained', '2009-07-28', 'Spain', 2),
       ('Shutter Island', '2010-02-19', 'Spain', 3),
       ('The Wolf of Wall Street', '2014-01-17', 'Spain', 3)
;
