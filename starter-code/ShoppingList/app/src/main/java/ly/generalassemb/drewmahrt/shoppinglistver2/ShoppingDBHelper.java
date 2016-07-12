package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by roosevelt on 7/12/16.
 */
public class ShoppingDBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "SHOPPING_DB";
    private static final String TABLE_NAME = "SHOPPING_LIST";
    private static final int DATABASE_VERSION = 7;
    public static final String COL_NAME = "ITEM_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    private static ShoppingDBHelper sInstance;

    public static ShoppingDBHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new ShoppingDBHelper(context);
        return sInstance;
    }

    private ShoppingDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getList(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME,null,null,null,null,null,null,null);
    }
}
