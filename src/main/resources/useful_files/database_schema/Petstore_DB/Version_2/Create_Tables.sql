-- pet_category, status, pet, pet_tag
CREATE TABLE pet (
    id                INTEGER PRIMARY KEY NOT NULL,
    pet_name          VARCHAR(25) NOT NULL,
    photo_urls        CLOB NOT NULL,
    pet_category_id   INTEGER,
    status_id         INTEGER,
    FOREIGN KEY ( pet_category_id )
        REFERENCES pet_category ( id ), -- pet (n) <-----> pet_category (1)
    FOREIGN KEY ( status_id )
        REFERENCES status ( id ) -- pet (n) <-----> status (1)
);

CREATE TABLE pet_category (
    id              INTEGER PRIMARY KEY NOT NULL,
    category_name   VARCHAR(25) NOT NULL
);

CREATE TABLE pet_tag (
    id         INTEGER PRIMARY KEY NOT NULL,
    tag_name   VARCHAR(20) NOT NULL,
    pet_id     INTEGER,
    FOREIGN KEY ( pet_id )
        REFERENCES pet ( id ) -- pet_tag (n) <-----> pet(1)
);

CREATE TABLE status (
    id            INTEGER PRIMARY KEY NOT NULL,
    status_name   VARCHAR(15) NOT NULL
);
