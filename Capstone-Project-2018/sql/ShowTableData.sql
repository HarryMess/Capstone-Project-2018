select * from USERS;
select * from TRADING_ACCOUNTS;
select * from STOCKS;
select Code, Name, OWNER_ID as Owner, current_price as Price from STOCKS;
select * from TRANSACTIONS;

delete from TRADING_ACCOUNTS where User_ID = ;
delete from USERS where ID = ;