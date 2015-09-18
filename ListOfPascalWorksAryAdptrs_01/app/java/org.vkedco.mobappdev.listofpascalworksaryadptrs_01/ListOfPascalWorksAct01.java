package org.vkedco.mobappdev.listofpascalworksaryadptrs_01;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfPascalWorksAct01 extends ListActivity
    implements AdapterView.OnItemClickListener
{

    String[] mPascalsWorks = null;
    Resources mRes = null;
    final static int NUM_WORKS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_of_pascal_works_act01);

        this.mPascalsWorks = new String[NUM_WORKS];
        this.mRes = this.getResources();

        this.mPascalsWorks[0] = this.mRes.getString(R.string.discourses);
        this.mPascalsWorks[1] = this.mRes.getString(R.string.geometrical_spirit);
        this.mPascalsWorks[2] = this.mRes.getString(R.string.persuasion);
        this.mPascalsWorks[3] = this.mRes.getString(R.string.vacuum_01);
        this.mPascalsWorks[4] = this.mRes.getString(R.string.vacuum_02);

        ListView lv = this.getListView();
        ArrayAdapter<String> adptr = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mPascalsWorks
        );
        lv.setAdapter(adptr);
        lv.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_pascal_works_act01, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View child, int position, long id) {
        // TODO Auto-generated method stub
        String msg = "";
        msg += "PARENT = " + parent.toString() + "\n";
        msg += "VIEW = " + child.toString() + "\n";
        msg += "VIEW's TEXT = " + ((TextView) child).getText().toString() + "\n";
        msg += "POSITION = " + Integer.toString(position) + "\n";
        msg += "ID = " + Long.toString(id);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
