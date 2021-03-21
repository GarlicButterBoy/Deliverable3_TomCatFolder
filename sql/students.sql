--Nick Sturch-Flint (100303769)
--January 26, 2021
--WEBD4201 
--Creates a DB Table for Students

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS students;
CREATE TABLE students (
id BIGINT PRIMARY KEY,
program_code VARCHAR(4),
program_description VARCHAR(255),
year INT,
FOREIGN KEY(id) REFERENCES users(id)
);

INSERT INTO students (id, program_code, program_description, year) VALUES (100222222, 'CPA', 'Computer Programmer Analyst', 3);

INSERT INTO students (id, program_code, program_description, year) VALUES (100303769, 'CPA', 'Computer Programmer Analyst', 3);

INSERT INTO students (id, program_code, program_description, year) VALUES (100111111, 'CSTY', 'Computer System Technology', 3);

SELECT * FROM students;