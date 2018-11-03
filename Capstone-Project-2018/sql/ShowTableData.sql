select * from USERS;
select * from TRADING_ACCOUNTS;
select * from STOCKS;
select Code, Name, OWNER_ID as Owner, current_price as Price from STOCKS;
select * from TRANSACTIONS;
select * from ACCOUNT_HISTORY;
select Date_time, Company_Code, Market_price from STOCK_HISTORY