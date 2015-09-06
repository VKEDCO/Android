package hellojellybean42.mobappdev.android.vkedco.org.hellojellybean42;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class HelloJellyBean42 extends Activity {

    final String LOG_TAG = HelloJellyBean42.class.getSimpleName() + "LOG_TAG";
    Button mFirstJellyBeanButton = null;
    String mToastStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jelly_bean42);
        mToastStr = getResources().getString(R.string.toast_string);

        mFirstJellyBeanButton = (Button) this.findViewById(R.id.btnJellyBean);
        mFirstJellyBeanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(LOG_TAG, "HelloJellyBean42.onClick()");
                Toast
                        .makeText(getApplicationContext(), mToastStr, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_jelly_bean42, menu);
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
