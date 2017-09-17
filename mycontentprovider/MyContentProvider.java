package gr.hua.android.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private DBHelper dbHelper;
    private static final String AUTHORITY = "gr.hua.android.mycontentprovider";
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        sUriMatcher.addURI(AUTHORITY, DBHelper.getTableName(), 1);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //insert values in database
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.insert(dbHelper.TABLE_NAME, null, values);
        if(result==-1){
            try {
                throw(new Exception("Failed to insert data"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Uri.parse(uri.toString()+"/"+result);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = null;
        switch (sUriMatcher.match(uri)) {
            case 1:
                c = db.query(DBHelper.getTableName(),null,null,null,null,null,null);
            default:
                try {
                    //show exception
                    throw(new Exception("Method not supported"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

