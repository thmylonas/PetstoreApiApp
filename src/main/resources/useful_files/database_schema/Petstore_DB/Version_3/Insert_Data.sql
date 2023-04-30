-- ------------------- INSERT INTO status ----------------------------------------------------------------------------------
INSERT INTO status
VALUES (1, 'AVAILABLE');
INSERT INTO status
VALUES (2, 'PENDING');
INSERT INTO status
VALUES (3, 'SOLD');

--DELETE FROM status;

-- ------------------- INSERT INTO pet_category -----------------------------------------------------------------------------
INSERT INTO pet_category
VALUES (1, 'Dog');
INSERT INTO pet_category
VALUES (2, 'Cat');
INSERT INTO pet_category
VALUES (3, 'Frog');
INSERT INTO pet_category
VALUES (4, 'Snake');
INSERT INTO pet_category
VALUES (5, 'Fish');

--DELETE FROM pet_category;

-- ------------------- INSERT INTO pet -------------------------------------------------------------------------------------
INSERT INTO pet
VALUES (1, 'Bo', 1, 1);
INSERT INTO pet
VALUES (2, 'Azor', 1, 2);
INSERT INTO pet
VALUES (3, 'Zizi', 2, 1);
INSERT INTO pet
VALUES (4, 'Coco', 5, 3);

--ALTER TABLE pet DROP COLUMN pet_age;

-- ------------------- INSERT INTO photo_url --------------------------------------------------------------------------------
INSERT INTO photo_url
VALUES (1, 'https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg', 1);
INSERT INTO photo_url
VALUES (2,
        'https://www.nationalgeographic.com/content/dam/animals/thumbs/rights-exempt/mammals/d/domestic-dog_thumb.jpg',
        2);
INSERT INTO photo_url
VALUES (3, 'https://icatcare.org/app/uploads/2018/06/Layer-1704-1920x840.jpg', 3);
INSERT INTO photo_url
VALUES (4, 'https://www.dw.com/image/50267514_303.jpg', 3);
INSERT INTO photo_url
VALUES (5, 'https://cdn0.wideopenpets.com/wp-content/uploads/2019/10/Fish-Names-770x405.png', 4);

--RENAME photo_urls TO photo_url;

--DELETE FROM photo_url;
--ALTER TABLE photo_url 
--  ADD CONSTRAINT fk_delete_cascade 
--  FOREIGN KEY (pet_id) 
--  REFERENCES pet ( id ) 
--  ON DELETE CASCADE;

-- ------------------- INSERT INTO pet_tag ---------------------------------------------------------------------------------
INSERT INTO pet_tag
VALUES (1, 'Aaa', 1);
INSERT INTO pet_tag
VALUES (2, 'Bbb', 1);
INSERT INTO pet_tag
VALUES (3, 'Ccc', 2);
INSERT INTO pet_tag
VALUES (4, 'Ddd', 4);
INSERT INTO pet_tag
VALUES (5, 'Eee', 4);
INSERT INTO pet_tag
VALUES (6, 'Fff', 4);

--UPDATE pet_tag SET tag_name='Fff' WHERE id=6;
