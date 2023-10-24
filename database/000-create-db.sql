\connect grogu;

CREATE SCHEMA grogu
       AUTHORIZATION grogu;

CREATE ROLE postgres;
GRANT ALL PRIVILEGES ON DATABASE grogu TO postgres;