-- create table
--   user (id integer not null auto_increment, email varchar(255),
--   username varchar(255), password VARCHAR(255), role varchar(10),
--   firstname VARCHAR(50), lastname VARCHAR(50), city VARCHAR(50), country VARCHAR(50),
--   primary key (id));
--
-- -- CREATE table news (
-- --   id integer not null AUTO_INCREMENT, title VARCHAR(30),
-- --   description VARCHAR(255), section_id INTEGER, tag_id INTEGER, text VARCHAR,
-- --   creation VARCHAR(50), user_id INTEGER, author VARCHAR(255),
-- --   PRIMARY KEY (id)
-- -- );
--
-- CREATE table news (
--   id integer not null AUTO_INCREMENT, title VARCHAR(30),
--   description VARCHAR(255), section_id INTEGER, tag_id INTEGER, text VARCHAR,
--   creation_date VARCHAR(50),
--   PRIMARY KEY (id)
-- );
--
--
-- CREATE table tags (
--   id integer not null AUTO_INCREMENT, name VARCHAR(30),
--   PRIMARY KEY (id)
-- );
--
-- CREATE TABLE newstag (
--   news_id INTEGER NOT NULL, tag_id INTEGER NOT NULL
-- );
--
-- create TABLE comments
--   (id integer not null AUTO_INCREMENT, author VARCHAR(50),
--   dateCreation VARCHAR(50), news_id INTEGER,
--   likes INTEGER,text VARCHAR,
--   PRIMARY KEY (id)
-- );