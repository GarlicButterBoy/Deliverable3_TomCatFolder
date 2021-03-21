--Nick Sturch-Flint (100303769)
--January 26, 2021
--WEBD4201
--Creates a DB Table for faculty

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (
id BIGINT PRIMARY KEY,
school_code VARCHAR(5),
school_description VARCHAR(255),
office VARCHAR(5),
phone_extension INT,
FOREIGN KEY(id) REFERENCES users(id)
);

INSERT INTO faculty (id, school_code, school_description, office, phone_extension) VALUES (100555666, 'HW', 'Hogwarts School of Wizarding', 'F-122', 1232);

INSERT INTO faculty (id, school_code, school_description, office, phone_extension) VALUES (100858989, 'SR-AX', 'The Southern Reach', 'B-231', 2345);

INSERT INTO faculty (id, school_code, school_description, office, phone_extension) VALUES (100600700, 'SGL', 'Saul Goodmans Lawyering School', 'L-333', 9999);

SELECT * FROM faculty;