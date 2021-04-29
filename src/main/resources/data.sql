
--                      CLIENTS
INSERT INTO clients(first_name, last_name, phone, email, passport)
  VALUES('Alex', 'Peterson', '123-456-78-78','alex@alex.com','11111');
INSERT INTO clients(first_name, last_name, phone, email, passport)
  VALUES('Colen', 'Frodo', '356-634-67-67','colen@alex.com','22222');
INSERT INTO clients(first_name, last_name, phone, email, passport)
  VALUES('Smith', 'Fox', '233-463-66-67','smith@alex.com','33333');
INSERT INTO clients(first_name, last_name, phone, email, passport)
  VALUES('Felix', 'James', '555-000-11-11','felix@alex.com','44444');


--                      CREDITS
INSERT INTO credits(name,credit_limit,interest_rate)
  VALUES('Classic credit',1500000,'10.2');
INSERT INTO credits(name,credit_limit,interest_rate)
  VALUES('Salary credit',1600000,'6.9');
INSERT INTO credits(name,credit_limit,interest_rate)
  VALUES('Fantastic credit',1700000,'3.2');
INSERT INTO credits(name,credit_limit,interest_rate)
  VALUES('Home credit',1000000,'7.9');
INSERT INTO credits(name,credit_limit,interest_rate)
  VALUES('Car credit',2000000,'6.0');

--                        CREDIT-OFFERS

INSERT INTO credit_offers(client_id,credit_id,loan_amount,loan_term_year)
 VALUES(2,2,300000,6);
INSERT INTO credit_offers(client_id,credit_id,loan_amount,loan_term_year)
 VALUES(1,3,50000,1);
INSERT INTO credit_offers(client_id,credit_id,loan_amount,loan_term_year)
 VALUES(1,4,20000,2);
INSERT INTO credit_offers(client_id,credit_id,loan_amount,loan_term_year)
 VALUES(1,1,10000,3);
INSERT INTO credit_offers(client_id,credit_id,loan_amount,loan_term_year)
 VALUES(3,3,100000,6);
 INSERT INTO credit_offers(client_id,credit_id,loan_amount,loan_term_year)
 VALUES(2,5,2000000,15);



