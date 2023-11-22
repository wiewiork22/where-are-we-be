CREATE DATABASE where_are_we;
\connect where_are_we;
CREATE SCHEMA app;

CREATE USER dbuser WITH PASSWORD 'pass';
ALTER ROLE dbuser SET search_path = app;

GRANT ALL ON DATABASE where_are_we TO dbuser;
GRANT ALL ON SCHEMA app TO dbuser;
