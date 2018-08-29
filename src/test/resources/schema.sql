CREATE TABLE team (
  id INTEGER PRIMARY KEY,
  name VARCHAR(128) NOT NULL,
  );
  
  CREATE TABLE employee (
  id INTEGER PRIMARY KEY,
  last_name VARCHAR(128) NOT NULL,
  first_name VARCHAR(128) NOT NULL,
  email VARCHAR(128) NULL,
  team_id INTETGER NULL,
  CONSTRAINT fk_employee_team
    FOREIGN KEY (team_id)
    REFERENCES team (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);