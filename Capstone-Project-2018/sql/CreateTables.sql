CREATE TABLE Users (
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Email varchar (255) UNIQUE,
	Password varchar (255) NOT NULL,
	isAdmin BOOLEAN NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Trade_Accounts (
	User_id int NOT NULL,
	Email varchar (255),
	Name varchar (50),
	Balance float NOT NULL,
	Hours_active int,
	PRIMARY KEY (id),
	FOREIGN KEY (User_id) REFERENCES Users(id)
	FOREIGN KEY (Email) REFERENCES Users(Email)
);

CREATE TABLE Stock (
	Code varchar(5) NOT NULL,
	Name varchar (255) NOT NULL,
	Owner_id int NOT NULL,
	PRIMARY KEY (Code),
	FOREIGN KEY (Owner_id) REFERENCES Trade_Accounts(id),
);

CREATE TABLE Stock_History(
	date_time TIMESTAMP NOT NULL,
	company_id int NOT NULL,
	market_price float NOT NULL,
	profit_per_hour float NOT NULL,
	PRIMARY KEY (date_time,company_id),
	FOREIGN KEY (company_id) REFERENCES Stock(company_id)
);

CREATE TABLE Transaction(
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
	date_time TIMESTAMP NOT NULL,
	buyer int NOT NULL,
	seller int NOT NULL,
	company_code varchar(5) NOT NULL,
	price float NOT NULL,
	amount int,
	PRIMARY KEY (id),
	FOREIGN KEY (buyer) REFERENCES Trade_Accounts(id),
	FOREIGN KEY (seller) REFERENCES Trade_Accounts(id),
	FOREIGN KEY (company_code) REFERENCES Stock(code)
);

CREATE TABLE Trade_History(
	date_time TIMESTAMP NOT NULL,
	account_id int NOT NULL,
	balance float NOT NULL,
	share_value float NOT NULL,
	PRIMARY KEY (date_time, account_id),
	FOREIGN KEY account_id REFERENCES Trade_Accounts(id)
);