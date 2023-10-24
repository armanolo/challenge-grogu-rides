\connect grogu;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v1();

CREATE TABLE grogu.vehicle(
	id uuid DEFAULT uuid_generate_v4(),
	seats integer NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE grogu.vehicle
  OWNER TO grogu;

COMMIT;
