SELECT * FROM Stocks
INNER JOIN Trading_Accounts ON Stocks.owner_id = Trading_Accounts.user_id
INNER JOIN USERS ON Users.id = TRADING_ACCOUNTS.USER_ID
WHERE users.email = 'admin@asx.com.au';

SELECT * FROM Trading_Accounts WHERE user_id = 1;

SELECT * FROM Account_History 
INNER JOIN Trading_Accounts ON Account_History.account_id = Trading_Accounts.user_id
WHERE Trading_accounts.user_id = 1;

SELECT * FROM Transactions
INNER JOIN Trading_Accounts ON Transactions.buyer = Trading_Accounts.user_id
OR Transactions.seller = Trading_Accounts.user_id
WHERE trading_accounts.user_id = 1;