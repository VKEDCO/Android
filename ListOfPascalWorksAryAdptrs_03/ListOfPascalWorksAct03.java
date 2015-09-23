package org.vkedco.mobappdev.listofpascalworksaryadptrs_03;

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

public class ListOfPascalWorksAct03 extends ListActivity {

    static String[] mPascalsWorks = null;
    static int[] mPascalsYears = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_of_pascal_works_act03);

        Resources res = this.getResources();
        // This is how you can inflate array objects directly from XML
        mPascalsWorks = res.getStringArray(R.array.pascals_titles);
        mPascalsYears = res.getIntArray(R.array.pascals_titles_years);

        setListAdapter(new ArrayAdapter<String>(this,
                R.layout.list_item_layout,
                mPascalsWorks));
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View child, int position, long id) {
                // 6. This is a subtle point: we cannot specify this as the
                // first argument to makeText, because we are inside OnItemClickListener.
                // Thus, we have to getApplicationContext() explicitly.
                Toast t = Toast.makeText(getApplicationContext(),
                        Integer.toString(mPascalsYears[position]),
                        Toast.LENGTH_LONG);
                t.show();
                Log.d("MY TAG", Integer.toString(mPascalsYears[position]));
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_pascal_works_act03, menu);
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
