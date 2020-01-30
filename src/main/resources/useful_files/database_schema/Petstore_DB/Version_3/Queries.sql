SELECT
    * --pet.pet_name, pet_category.category_name 
FROM
    pet
    INNER JOIN pet_category ON pet_category.id = pet.pet_category_id;

SELECT
    *
FROM
    pet
    INNER JOIN pet_tag ON pet_tag.pet_id = pet.id
    INNER JOIN pet_category ON pet_category.id = pet.pet_category_id;

SELECT
    *
FROM
    pet
    INNER JOIN pet_tag ON pet_tag.pet_id = pet.id
    INNER JOIN pet_category ON pet_category.id = pet.pet_category_id
    INNER JOIN photo_url ON photo_url.pet_id = pet.id
    INNER JOIN status ON status.id = pet.status_id;

SELECT
    *
FROM
    pet
    LEFT OUTER JOIN pet_tag ON pet_tag.pet_id = pet.id
    LEFT OUTER JOIN pet_category ON pet_category.id = pet.pet_category_id
    LEFT OUTER JOIN photo_url ON photo_url.pet_id = pet.id
    LEFT OUTER JOIN status ON status.id = pet.status_id;

SELECT
    pet.pet_name,
    photo_url.url_name,
    pet_category.category_name
FROM
    pet
    INNER JOIN pet_tag ON pet_tag.pet_id = pet.id
    INNER JOIN pet_category ON pet_category.id = pet.pet_category_id
    INNER JOIN photo_url ON photo_url.pet_id = pet.id;