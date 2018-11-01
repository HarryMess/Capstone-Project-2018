CREATE TABLE Users (
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Email varchar (255) UNIQUE,
	Password varchar (255) NOT NULL,
	isAdmin BOOLEAN NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Trading_Accounts (
	User_id INTEGER NOT NULL,
	Email varchar (255),
	Name varchar (50),
	Balance float NOT NULL,
	Hours_active int,	
	FOREIGN KEY (User_id) REFERENCES Users(id),
	PRIMARY KEY (User_id)
);

CREATE TABLE Stocks (
	Code varchar(5) NOT NULL,
	Name varchar (255) NOT NULL,
	Owner_id int NOT NULL,
	current_price double,
	Amount int,
	PRIMARY KEY (Code),
	FOREIGN KEY (Owner_id) REFERENCES Trading_Accounts(User_id)
);

CREATE TABLE Stock_History(
	date_time TIMESTAMP NOT NULL,
	company_code varchar(5) NOT NULL,
	market_price float NOT NULL,
	profit_per_hour float,
	FOREIGN KEY (company_code) REFERENCES Stocks(Code),
	PRIMARY KEY (date_time, company_code)
);

CREATE TABLE Transactions (
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	date_time TIMESTAMP NOT NULL,
	buyer int NOT NULL,
	seller int NOT NULL,
	company_code varchar(5) NOT NULL,
	price float NOT NULL,
	amount int,
	PRIMARY KEY (id),
	FOREIGN KEY (buyer) REFERENCES Trading_Accounts(user_id),
	FOREIGN KEY (seller) REFERENCES Trading_Accounts(user_id),
	FOREIGN KEY (company_code) REFERENCES Stocks(code)
);

CREATE TABLE Account_History(
	date_time TIMESTAMP NOT NULL,
	account_id int NOT NULL,
	balance float NOT NULL,
	share_value float NOT NULL,
	PRIMARY KEY (date_time, account_id),
	FOREIGN KEY (account_id) REFERENCES Trading_Accounts(user_id)
);