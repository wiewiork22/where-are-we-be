CREATE TABLE image_file (
        employee_id int PRIMARY KEY,
        image_data BYTEA,
        FOREIGN KEY (employee_id) REFERENCES employee_table(id)
);