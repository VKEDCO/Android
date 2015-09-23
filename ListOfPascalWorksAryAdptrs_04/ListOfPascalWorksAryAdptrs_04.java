package org.vkedco.mobappdev.listofpascalworksaryadptrs_04;

// @author: vladimir kulyukin

import android.app.ListActivity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListOfPascalWorksAryAdptrs_04 extends ListActivity {

    static String[] mPascalsTitles = null;
    static int[] mPascalsYears = null;

    static final String LOGTAG = "PascalWorks04_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_of_pascal_works_ary_adptrs_04);
        Resources res = this.getResources();
        //mPascalsTitles = res.getStringArray(R.array.pascals_titles);
        ArrayAdapter<CharSequence> adptr
                = ArrayAdapter.createFromResource(this,
                R.array.pascals_titles,
                R.layout.list_item_layout);
        this.setListAdapter(adptr);
        mPascalsYears = res.getIntArray(R.array.pascals_titles_years);

        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View child,
                                    int position, long id) {
                // 5. This is a subtle point because we cannot specify this as
                // the first argument to makeText, because we are inside
                // OnItemClickListener,
                // not inside the ListActivity where we were in
                // ListViewArrayAdapter1Act.
                // Thus, we have to getApplicationContext() explicitly.
                Toast t = Toast.makeText(getApplicationContext(),
                        Integer.toString(mPascalsYears[position]),
                        Toast.LENGTH_LONG);
                t.show();
                Log.d(LOGTAG, "onItemClick()");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_pascal_works_ary_adptrs_04, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
