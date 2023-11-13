package dao.impl;

import parainfo.sql.ConectaDb;

public class Dao {
    protected final ConectaDb db;
    protected String message;
    
    public Dao(){
        this.db = new ConectaDb();
    }
}
