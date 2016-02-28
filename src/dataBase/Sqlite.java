package dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper
{  

	public Sqlite(Context context, String dataBaseName, CursorFactory factory,
			int version) 
	{super(context,dataBaseName, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{String sql_1="create table if not exists contact (name varchar)";
		db.execSQL(sql_1);	
	 
	String sql_2="create table if not exists msg (msgId varchar,body varchar,threadId varchar)";
	    db.execSQL(sql_2);
	
	String sql_3="create table if not exists switch(key varchar,status varcha);";
	   db.execSQL(sql_3);
	    
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
