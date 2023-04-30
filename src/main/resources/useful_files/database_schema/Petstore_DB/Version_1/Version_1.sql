CREATE TABLE pet
(
    id       INTEGER PRIMARY KEY NOT NULL,
    pet_name VARCHAR(25)         NOT NULL,
    pet_type VARCHAR(20)         NOT NULL,
    pet_age  INTEGER             NOT NULL
);

INSERT INTO pet
VALUES (1, 'Bo', 'Dog', 3);
INSERT INTO pet
VALUES (2, 'Azor', 'Dog', 5);
INSERT INTO pet
VALUES (3, 'Zizi', 'Cat', 2);

SELECT *
FROM pet;

-- DROP TABLE pet;
