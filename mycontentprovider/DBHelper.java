package gr.hua.android.mycontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "_USERS_DB";
    public static final String TABLE_NAME = "_USERS";
    public static final String USEID = "_USEID";
    public static final String USERNAME = "_USERNAME";
    public static final String CURRENT_LOCATION = "_CURRENT_LOCATION";
    private static final String SQL_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USERNAME + " TEXT,"
                    +CURRENT_LOCATION+" TEXT);";

    //constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create sqldatabase
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static String getTableName(){
        return TABLE_NAME;
    }

}
