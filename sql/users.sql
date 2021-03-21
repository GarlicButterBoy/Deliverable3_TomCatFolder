--Nick Sturch-Flint (100303769)
--January 26, 2021
--WEBD4201
--Creates a DB Table for all generic users

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
id BIGINT PRIMARY KEY,
password VARCHAR(40) NOT NULL,
first_name VARCHAR(128),
last_name VARCHAR(128),
email_address VARCHAR(255) UNIQUE,
last_access TIMESTAMP,
enrol_date TIMESTAMP,
enabled BOOLEAN,
type VARCHAR(2)
);

INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type)
VALUES (100222222, ENCODE(DIGEST('password','sha1'), 'hex'), 'Robert', 'McReady', 'bob.mcready@dcmail.ca', '2016-03-07', '2015-09-03', true, 's');

INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type)
VALUES (100303769, ENCODE(DIGEST('100303769', 'sha1'), 'hex'), 'Nick', 'Sturch-Flint', 'nicholas.sturchflint@dcmail.ca', '2020-02-03', '2020-01-01', true, 's');

INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type)
VALUES (100111111, ENCODE(DIGEST('password', 'sha1'), 'hex'), 'Mike', 'Jones', 'mjones@email.com', '2020-01-02', '2021-01-28', true, 's');

INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type)
VALUES (100555666, ENCODE(DIGEST('100555666', 'sha1'), 'hex'), 'Severus', 'Snape', 'severus@hwfaculty.co.uk', '2019-01-02', '2020-01-08', true, 'f');

INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type)
VALUES (100858989, ENCODE(DIGEST('100858989', 'sha1'), 'hex'), 'John', 'Rodriguez', 'control@southernreach.gov', '2012-01-02', '2020-01-01', true, 'f');

INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type)
VALUES (100600700, ENCODE(DIGEST('100600700', 'sha1'), 'hex'), 'Saul', 'Goodman', 'goodman@bestlawyer.com', '2018-03-05', '2020-01-01', true, 'f');

SELECT * FROM users;