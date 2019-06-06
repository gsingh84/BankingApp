drop table if exists transactions;
drop table if exists accounts;
drop table if exists address;
drop table if exists users;

create table users
(
	id INT not null AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(30) NOT NULL,
	birthdate DATE NOT NULL,
	email VARCHAR(50),
	password VARCHAR(255),
	usertype VARCHAR(10) default 'CUSTOMER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table address
(
  id INT AUTO_INCREMENT PRIMARY KEY,
	customer_id INT,
	street VARCHAR(255) DEFAULT NULL,
	city VARCHAR(20),
	state VARCHAR(20), 
	zip VARCHAR(10),
	FOREIGN KEY (customer_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table accounts
(
  id INT AUTO_INCREMENT PRIMARY KEY,
	customer_id INT NOT NULL,
	accountType VARCHAR(20) default 'CHECKING',
	accountnumber VARCHAR(20) NOT NULL,
	pin VARCHAR(4) default 1001,
	bank VARCHAR(20) default 'LAB BANK',
	interest decimal(15,2) default 0.3,
	FOREIGN KEY (customer_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table transactions
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_id INT NOT NULL,
  amount decimal(15,2),
  FOREIGN KEY (account_id) REFERENCES accounts (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert some employees
insert into users (firstname, lastname, birthdate, email, password, usertype)
values ('Sam', 'Josh', '1993-7-04', 'sam@gmail.com', SHA1('password01'), 'EMPLOYEE'),
('Juan', 'Bose', '1987-8-05', 'juan@ymail.com', SHA1('password01'), 'EMPLOYEE'),
('Sunny', 'Singh', '1978-12-03', 'sunny@outlook.com', SHA1('password01'), 'EMPLOYEE'),
('Alex', 'Martin', '1967-9-01', 'alex@yahoo.com', SHA1('password01'), 'EMPLOYEE')