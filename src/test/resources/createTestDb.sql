CREATE DATABASE banktestdb;
CREATE TABLE depositor
(
  email VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);
CREATE TABLE account
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  amount INTEGER NOT NULL,
  bank_name VARCHAR(255),
  country VARCHAR(255),
  profitability INTEGER NOT NULL,
  time_constraints INTEGER NOT NULL,
  type_deposit INTEGER,
  depositor VARCHAR(255) NOT NULL,
  CONSTRAINT "FKpa3ek4j9lx16j61g9q0ulomat" FOREIGN KEY (depositor) REFERENCES depositor (email)
);