package org.vkedco.mobappdev.progressbarsimulatedfiledownload;

/*
============================================
@author Vladimir Kulyukin
============================================
*/

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ProgressBarSimulatedFileDownloadActivity extends Activity
    implements View.OnClickListener
{
    static final String PROGRESS_MESSAGE   = "File Downloading . . .";
    static final String COMPLETION_MESSAGE = "Download complete";
    static final String DOWNLOAD_FILE_BUTTON_CLICKED = "Download file button clicked";
    static final String LOG_TAG = ProgressBarSimulatedFileDownloadActivity.class.getSimpleName() + "_LOG_TAG";

    Button mBtnStartProgress;
    ProgressDialog mProgressBar;
    private int mProgressBarStatus=0;
    // Handler to post messages on the main thread from a non-main threads.
    private Handler mProgressBarHandlerOnMainThread = new Handler();
    private long mFileSize=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_simulated_file_download);

        mBtnStartProgress =	(Button) findViewById(R.id.btnStartProgress);
        mBtnStartProgress.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_progress_bar_simulated_file_download, menu);
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
        Log.v(LOG_TAG, DOWNLOAD_FILE_BUTTON_CLICKED);
        // 1. Construct ProgressDialog object
        mProgressBar = new ProgressDialog(v.getContext());
        // 2. Let the user cancel it if necessary
        mProgressBar.setCancelable(true);
        // 3. Set the message of the Progress Dialog object to
        // File Downloading...
        mProgressBar.setMessage(PROGRESS_MESSAGE);
        // 4. This is a horizontal ProgressDialog
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 5. Set the progress to 0, because we have not downloaded anything.
        mProgressBar.setProgress(0);
        // 5. The progress indicator will go from 0 and upto 99. This is
        // the progress scale.
        mProgressBar.setMax(100);
        // 6. Show the bar
        mProgressBar.show();

        //reset status
        mProgressBarStatus=0;

        //reset fileSize
        mFileSize=0;

        // Create a thread and spawn it off the main thread
        Thread thread = new Thread(){
            public void run(){
                simulateFileDownload();
            }
        };

        // Start the spawned thread
        thread.start();

    }

    // method that simulates file download
    public void simulateFileDownload() {
        while( mProgressBarStatus < 100 ) {
            // do some task; downLoadFile method simulates
            // a file download.
            mProgressBarStatus = downLoadFile();
            // sleep for 500ms to simulate network traffic
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Use the post method of the handler object
            // to send messages to the progress bar object
            // on the main thread.
            mProgressBarHandlerOnMainThread.post(new Runnable() {
                @Override
                public void run() {
                    // show the status
                    mProgressBar.setProgress(mProgressBarStatus);
                    if (mProgressBarStatus >= 100)
                        mProgressBar.setMessage(COMPLETION_MESSAGE);
                }
            });
        }

        //after Download is complete
        if(mProgressBarStatus>=100) {
            // sleep 2 seconds, so that you can see the 100%
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // get rid of the progress bar object.
            mProgressBar.dismiss();
        }
    }


    //File download simulator
    private int downLoadFile() {

        // pretend that the file size is 1,000,000 bytes = about 1MG
        while (mFileSize <= 1000000) {

            mFileSize++;

            // scale the progress bar status, depending on the current
            // size of the file.
            if (mFileSize == 100000) {
                return 10;
            } else if (mFileSize == 200000) {
                return 20;
            } else if (mFileSize == 300000) {
                return 30;
            }else if (mFileSize == 400000) {
                return 40;
            }else if (mFileSize == 500000) {
                return 50;
            }else if (mFileSize == 600000) {
                return 60;
            }else if (mFileSize == 700000) {
                return 70;
            }else if (mFileSize == 800000) {
                return 80;
            }else if (mFileSize == 900000) {
                return 90;
            }
        }

        // the file has been downloaded
        return 100;
    }
}
