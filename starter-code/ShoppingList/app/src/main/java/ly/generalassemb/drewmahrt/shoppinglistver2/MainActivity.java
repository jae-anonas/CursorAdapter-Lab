package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.shopping_list_view);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        Cursor cursor = ShoppingDBHelper.getInstance(this).getList();

        CursorAdapter cursorAdapter = new CursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.shopping_item_layout,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txtName = (TextView) view.findViewById(R.id.item_name);
                TextView txtDesc = (TextView) view.findViewById(R.id.item_desc);
                TextView txtPrice = (TextView) view.findViewById(R.id.item_price);
                TextView txtType = (TextView) view.findViewById(R.id.item_type);

                String name = cursor.getString(cursor.getColumnIndex(ShoppingDBHelper.COL_NAME));
                String desc = cursor.getString(cursor.getColumnIndex(ShoppingDBHelper.COL_DESCRIPTION));
                String price = cursor.getString(cursor.getColumnIndex(ShoppingDBHelper.COL_PRICE));
                String type = cursor.getString(cursor.getColumnIndex(ShoppingDBHelper.COL_TYPE));

                txtName.setText(name);
                txtDesc.setText(desc);
                txtPrice.setText("$ " + price);
                txtType.setText(type);
            }
        };

        mListView.setAdapter(cursorAdapter);

    }
}
