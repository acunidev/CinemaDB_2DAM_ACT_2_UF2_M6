# Activitat 2 UF2 - M6

## Objectius: Implementar una BBDD i un programa amb JDBC.

#### Alex Cunillera

> Tot el que demana l'activitat està implementat, però a més a més he volgut utilitzar la pràctica per afegir més coses
> pel meu compte com el “DAO Pattern” i el CRUD, ja que a primer vam fer una activitat semblant, però ara fer-ho amb una
> mica més d'idea del que faig jaja.

> **[1 punt]** Quines instruccions heu utilitzat per crear la BBDD, cada entitat i els camps de cada entitat? Poseu tot
> seguit
> aquí les instruccions tal qual les heu codificat al fitxer .sql.

### Instruccions al fitxer ***initCinemaDB.sql***

---
> [1 punt] Afegiu 3 registres a la taula Director, per exemple, Steven Spielberg, George Lucas, Gareth Edwards, etc.
> Cerq.
> Poseu tot seguit la instrucció que heu fet servir per insertar els registres.

```mysql
INSERT INTO director (name, birth_year, country)
VALUES ('Christopher Nolan', 1970, 'United Kingdom'),
       ('Quentin Tarantino', 1963, 'United States'),
       ('Martin Scorsese', 1942, 'United States');
```

---
> [1 punt] Afegiu 6 registres a la taula Film, per exemple, “Indiana Jones y el templo maldito”, “La amenaza fantasma”,
> etc. Poseu tot seguit la instrucció que heu fet servir per insertar els registres.

```mysql
INSERT INTO film(title, release_date, country, director_id)
VALUES ('Inception', '2010-08-06', 'Spain', 1),
       ('Interstellar', '2014-11-07', 'Spain', 1),
       ('Inglorious Basterds', '2009-07-28', 'Spain', 2),
       ('Django Unchained', '2009-07-28', 'Spain', 2),
       ('Shutter Island', '2010-02-19', 'Spain', 3),
       ('The Wolf of Wall Street', '2014-01-17', 'Spain', 3);
```

---
> [1 punt] Es pot millorar la clau primària de cadascuna de les taules? Com?
> Es podria millorar la clau primària treien el “NOT NULL” a la declaració de la clau primària de les entitats, ja que
> en
> utilitzar “auto-increment” MySQL ja crearà la taula amb valor NOT NULL.

#### Es podria millorar la clau primària treien el “NOT NULL” a la declaració de la clau primària de les entitats, ja que en utilitzar “auto-increment” MySQL ja crearà la taula amb valor NOT NULL.

---
> [1,5 punts] Pel·lícules estrenades en un interval d’anys en concret (per exemple entre 1978 i 1990). Aquest interval
> el pensareu vosaltres a partir dels registres que hagueu ficat a les taules. Formateu adequadament.

- Java Class d’interès
    - ***Main*** (Mètode per executar el cercador de pel·lícules entre un interval d'anys en concret + les preguntes a
      l'usuari per consola).
        - ***filmsBetweenTwoDates*** <br/>&nbsp;

        - ***FilmDaoImplementation*** (Conte tota la logica de ***CRUD*** sobre la taula film de la base de dades
          ***cinema_db***)
            - Nom del mètode => ***getAllFilmsBetweenYears***

---
> [2 punts] Pel·lícules de cada director, però sols mostrar per pantalla el títol de la pel·lícula i any d’estrena (sols
> any). Formateu adequadament.

- Java Class d’interès
    - ***Main*** (Mètode per executar el cercador de pel·lícules d'un director en concret + les preguntes a
      l'usuari per consola).
        - ***filmsByDirectorShowTitleYear*** <br/>&nbsp;

        - ***FilmDaoImplementation*** (Conte tota la logica de ***CRUD*** sobre la taula film de la base de dades
          ***cinema_db***)
            - Nom del mètode => ***getAllFilmsByDirectorTitleYear***

---
> [2,5 punts] Inserir noves pel·lícules a l’entitat Films. Caldrà controlar prèviament que no existeixi a la taula,
> mirant
> que no hi hagi altre registre amb el mateix títol (per fer-ho simple).

- ## Java Class d’interès
    - ***Main*** (Mètode per afegir pel·lícules + les preguntes a l'usuari).
        - ***insertFilmDatabaseWithDirectorName*** <br/>&nbsp;

    - ***FilmDaoImplementation*** (Conte tota la logica de ***CRUD*** sobre la taula film de la base de dades
      ***cinema_db***)
        - Nom del mètode => ***add***

---