INSERT INTO role (name)
VALUES ('ROLE_ADMIN'), -- 1
       ('ROLE_STAFF'), -- 2
       ('ROLE_USER');  -- 3

-- password == first_name (Bcrypt encrypted hash)
INSERT INTO account (first_name, last_name, email, password)
VALUES ('Admin', 'Initial', 'admin@admin.com', '$2a$10$k.c6wbftfBq7U0tSwvA2JeKb3iqJjjpu2fkM0wJtXircVgMK0GY4y'), -- 1
       ('Staff', 'Initial', 'staff@staff.com', '$2a$10$kYHiVb.4aqKclk8Jg9M5eeg4SRkYg.mhcT4T1oCYsdt2bZFSPt2oa'), -- 2
       ('User', 'Initial', 'user@user.com', '$2a$10$/bjML3MiPPBpxP3NsStpietLh4eRmuZ.d0N418J8CBhqlzCaLqfRi');    -- 3

INSERT INTO account_role (account_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO genre (name)
VALUES ('Fiction'),    -- 1
       ('Nonfiction'); -- 2

INSERT INTO author (full_name)
VALUES ('Bruce Eckel'),      -- 1
       ('Joshua Bloch'),     -- 2
       ('Robert C. Martin'), -- 3
       ('George Orwell');    -- 4

INSERT INTO book (title, pages, price_in_cents, genre_id, author_id)
VALUES ('Thinking in Java', 1150, 5506, 2, 1),
       ('Effective Java', 416, 4674, 2, 2),
       ('Clean Code: A Handbook of Agile Software Craftsmanship', 464, 4499, 2, 3),
       ('1984', 328, 999, 1, 4),
       ('Animal Farm', 96, 999, 1, 4);