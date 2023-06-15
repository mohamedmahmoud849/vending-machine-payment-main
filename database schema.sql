CREATE DATABASE IF NOT exists card_db;
USE card_db;

CREATE TABLE IF NOT exists CardInformation (
  cardNumber VARCHAR(16) NOT NULL,
  balance DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (cardNumber)
);


INSERT INTO CardInformation (cardNumber, balance)
VALUES
  ('1234567890123456', 1000.00),
  ('2345678901234567', 500.00),
  ('3456789012345678', 1500.00),
  ('4567890123456789', 2000.00);