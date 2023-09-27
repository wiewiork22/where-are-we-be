-- example migration to ensure Liquibase is working
-- migration files should begin with V1__ , V2__ etc

CREATE TABLE test_table
(
    test_column varchar(255)
);