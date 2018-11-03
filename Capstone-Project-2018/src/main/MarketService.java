package main;

import java.sql.SQLException;

import model.database.StocksTable;

public class MarketService implements Runnable
{

    public void run()
    {
        StocksTable st = StocksTable.getInstance();
        try {
			st.updateMarket();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
