--Accounts
INSERT INTO tp2.accounts ("email","address","firstName","lastName","password","phone","type") VALUES
("emerick123@gmail.com", "123 Rang Nowhere", "Émerick", "Poulin", "reallyStrongPassword", "450-454-1221", "ADMIN");

INSERT INTO tp2.accounts ("email","address","firstName","lastName","password","phone","type") VALUES
("fabory456@gmail.com", "69 Lmao St.", "Fabory", "Bangoura", "someKindOfPassword", "514-223-1695", "ADMIN");

INSERT INTO tp2.accounts ("email","address","firstName","lastName","password","phone","type") VALUES
("someTrader420@gmail.com", "666 Oof Blv.", "Damien", "Deschamps", "passwordThatIWontRemember", "223-438-3368", "TRADER");

INSERT INTO tp2.accounts ("email","address","firstName","lastName","password","phone","type") VALUES
("niceClient133@gmail.com", "314B Oracle Rd.", "Steve", "Desroches", "yetAnotherPassword", "438-556-8723", "CLIENT");

--Admins

INSERT INTO tp2.admins ("email") VALUES
("emerick123@gmail.com");

INSERT INTO tp2.admins ("email") VALUES
("fabory456@gmail.com");

--Carts
INSERT INTO tp2.carts ("status") VALUES
("ACTIVE");

INSERT INTO tp2.carts ("status") VALUES
("ACTIVE");

--Clients
INSERT INTO tp2.clients ("email", "cart_id") VALUES
("niceClient133@gmail.com", 0);

--Traders
INSERT INTO tp2.traders ("email", "cart_id") VALUES
("someTrader420@gmail.com", 1);

--Products

INSERT INTO tp2.products
("available_stock", "price", "product_name", "id") VALUES
(5, 45.32, "27 inches Screen", );