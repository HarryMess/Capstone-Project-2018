package database;

import java.util.List;

public abstract class DatabaseTable {

	public abstract List<?> getAll();
	public abstract Object getRow(String column, String queryString);
	public abstract void insert(String values);
}
