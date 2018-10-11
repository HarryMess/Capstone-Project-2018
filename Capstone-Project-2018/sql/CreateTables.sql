CREATE TABLE Users (
	Email varchar (255) NOT NULL,
	Password varchar (255) NOT NULL,
	isAdmin BOOLEAN NOT NULL,
	PRIMARY KEY (Email)
);

CREATE TABLE Trade_Accounts (
	id int NOT NULL,
	Name varchar (50),
	Balance float NOT NULL,
	Hours_active int,
	Email varchar (255),
	PRIMARY KEY (id),
	FOREIGN KEY (Email) REFERENCES Users(Email)
);

CREATE TABLE Stock (
	Code varchar(5) NOT NULL,
	Name varchar (255) NOT NULL,
	Trade_Accountsid int NOT NULL,
	PRIMARY KEY Code,
	FOREIGN KEY (Trade_Accountsid) REFERENCES Trade_Accounts(id),
);

CREATE TABLE Stock_History(
	date_time TIMESTAMP NOT NULL,
	company_id int NOT NULL,
	market_price float NOT NULL,
	profit_per_hour float NOT NULL,
	PRIMARY KEY (date_time,company_id),
	FOREIGN KEY (company_id) REFERENCES Stock(company_id)
);

CREATE TABLE Stock_Transaction(
	date_time TIMESTAMP NOT NULL,
	buyer int NOT NULL,
	seller int NOT NULL,
	company_code varchar(5) NOT NULL,
	price float NOT NULL,
	amount int,
	PRIMARY KEY (date_time, buyer, seller),
	FOREIGN KEY (buyer) REFERENCES Trade_Accounts(id),
	FOREIGN KEY (seller) REFERENCES Trade_Accounts(id),
	FOREIGN KEY (company_code) REFERENCES Stock(code)
);

CREATE TABLE Trade_History(
	date_time TIMESTAMP NOT NULL,
	account_id int NOT NULL,
	balance float NOT NULL,
	share_value float NOT NULL,
	PRIMARY KEY (date_time,account_id),
	FOREIGN KEY account_id REFERENCES Trade_Accounts(id)
);