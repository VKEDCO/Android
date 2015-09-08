package org.vkedco.mobappdev.internationaldatedisplayforkitkat;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class InternationalDateDisplayForKitKatAct extends Activity
    implements View.OnClickListener
{

    final static String LOG_TAG = InternationalDateDisplayForKitKatAct.class.getSimpleName() + "LOG";

    Button mBtnEN = null;
    Button mBtnIT = null;
    Button mBtnFR = null;
    Button mBtnRU = null;

    EditText mEdTxtDateDisplay  = null;
    DateFormat mDateFormatUS    = null;
    DateFormat mDateFormatIT    = null;
    DateFormat mDateFormatFR    = null;
    Resources mRes              = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_date_display_for_kit_kat);

        mDateFormatUS = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, Locale.US);
        mDateFormatIT = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, Locale.ITALY);
        mDateFormatFR = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, Locale.FRANCE);

        mRes = this.getResources();

        mEdTxtDateDisplay = (EditText) this.findViewById(R.id.edTxtDate);
        mBtnEN = (Button) this.findViewById(R.id.btnEN);
        mBtnIT = (Button) this.findViewById(R.id.btnIT);
        mBtnFR = (Button) this.findViewById(R.id.btnFR);

        mBtnRU = (Button) this.findViewById(R.id.btnRU);

        mBtnEN.setOnClickListener(this);
        mBtnIT.setOnClickListener(this);
        mBtnFR.setOnClickListener(this);
        mBtnRU.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_international_date_display_for_kit_kat, menu);
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
    public void onClick(View v) {
        final int ID = v.getId();
        String dateStr = null;
        switch ( ID ) {
            case R.id.btnEN:
                dateStr = mRes.getString(R.string.btnUSLogMsg);
                Log.v(LOG_TAG, dateStr);
                this.mEdTxtDateDisplay.setText(this.mDateFormatUS.format(new Date()));
                break;
            case R.id.btnIT:
                dateStr = mRes.getString(R.string.btnITLogMsg);
                Log.v(LOG_TAG, dateStr);
                this.mEdTxtDateDisplay.setText(this.mDateFormatIT.format(new Date()));
                break;
            case R.id.btnFR:
                dateStr = mRes.getString(R.string.btnFRLogMsg);
                Log.v(LOG_TAG, dateStr);
                this.mEdTxtDateDisplay.setText(this.mDateFormatFR.format(new Date()));
                break;
            case R.id.btnRU:
                displayRUDate();
                break;
        }
    }

    void displayRUDate() {
        Log.v(LOG_TAG, mRes.getString(R.string.btnRULogMsg));
        Calendar cal = new GregorianCalendar();
        final int month = cal.get(Calendar.MONTH);
        final int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        final int year = cal.get(Calendar.YEAR);
        String monthName = null;
        switch ( month ) {
            case Calendar.JANUARY:
                monthName = mRes.getString(R.string.jan_ru); break;
            case Calendar.FEBRUARY:
                monthName = mRes.getString(R.string.feb_ru); break;
            case Calendar.MARCH:
                monthName = mRes.getString(R.string.mar_ru); break;
            case Calendar.APRIL:
                monthName = mRes.getString(R.string.apr_ru); break;
            case Calendar.MAY:
                monthName = mRes.getString(R.string.may_ru); break;
            case Calendar.JUNE:
                monthName = mRes.getString(R.string.jun_ru); break;
            case Calendar.JULY:
                monthName = mRes.getString(R.string.jul_ru); break;
            case Calendar.AUGUST:
                monthName = mRes.getString(R.string.aug_ru); break;
            case Calendar.SEPTEMBER:
                monthName = mRes.getString(R.string.spt_ru); break;
            case Calendar.OCTOBER:
                monthName = mRes.getString(R.string.oct_ru); break;
            case Calendar.NOVEMBER:
                monthName = mRes.getString(R.string.nov_ru); break;
            case Calendar.DECEMBER:
                monthName = mRes.getString(R.string.dec_ru); break;
            default: break;
        }
        this.mEdTxtDateDisplay.setText(dayOfMonth + " " + monthName + " " + year);
    }
}
