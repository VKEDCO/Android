package org.vkedco.mobappdev.progressbarnetworkfiledownload;

/*
==============================================
@author Vladimir Kulyukin
==============================================
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ProgressBarNetworkFileDownloadActivity extends Activity
    implements View.OnClickListener {

    static final String PROGRESS_MESSAGE   = "File Downloading . . .";
    static final String COMPLETION_MESSAGE = "Download complete";
    static final String DOWNLOAD_FILE_BUTTON_CLICKED = "Download file button clicked";
    static final String LOG_TAG = ProgressBarNetworkFileDownloadActivity.class.getSimpleName() + "_LOG_TAG";
    static final String DYERS_FILE_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/dyers.jpg";
    static final String FESTIVAL_FILE_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/festival.jpg";
    static final String FILE_PATH = Environment.getExternalStorageDirectory()
            .toString() + "/downloadedfile.jpg";

    Button mBtnStartProgress;
    ImageView mImageView;
    ProgressDialog mProgressBar;
    Handler mProgressBarHandler = new Handler();
    int mProgressBarStatus = 0;
    long mFileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_network_file_download);

        mBtnStartProgress = (Button) findViewById(R.id.btnStartProgress);
        mImageView = (ImageView) findViewById(R.id.FileDownloadimageView);
        mBtnStartProgress.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_progress_bar_network_file_download, menu);
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
        // Prepare the progressbar
        mProgressBar = new ProgressDialog(v.getContext());
        mProgressBar.setCancelable(true);
        mProgressBar.setMessage(PROGRESS_MESSAGE);
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressBar.setProgress(0);
        mProgressBar.setMax(100);
        mProgressBar.show();

        // reset status
        mProgressBarStatus = 0;

        // reset fileSize
        mFileSize = 0;

        Thread thread = new Thread() {
            public void run() {
                networkFileDownload();
            }
        };
        thread.start();

    }

    public void networkFileDownload() {
        Log.v(LOG_TAG, FILE_PATH);
        while (mProgressBarStatus != 100) {
            int count;
            try {
                URL url = new URL(DYERS_FILE_URL);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();
                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);
                // Output stream to write file
                OutputStream output = new FileOutputStream(FILE_PATH);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    output.write(data, 0, count);
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    mProgressBarStatus = (int) ((total * 100) / lenghtOfFile);
                    // update the progress bar
                    mProgressBarHandler.post(new Runnable() {

                        @Override
                        public void run() {

                            mProgressBar.setProgress(mProgressBarStatus);
                            if (mProgressBarStatus == 100) {
                                mProgressBar.setMessage(COMPLETION_MESSAGE);
                                mImageView.setImageDrawable(Drawable
                                        .createFromPath(FILE_PATH));
                            }
                        }
                    });
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage());
            }

        }
        // after download is complete
        if (mProgressBarStatus >= 100) {
            // sleep 2 seconds, so that you can see the 100%
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            mProgressBar.dismiss();
        }
    }

}
