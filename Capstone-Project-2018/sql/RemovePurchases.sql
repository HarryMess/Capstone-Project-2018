update TRADING_ACCOUNTS
set balance = 1000000 where user_id = 202;

update STOCKS
set owner_id = 1 where code = 'A2M';

delete from TRANSACTIONS where buyer = 1;
delete from TRANSACTIONS where seller = 1;