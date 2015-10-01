package org.vkedco.mobappdev.image_displayer_app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Random;

// author: vladimir kulyukin

public class ImageRequestAct extends Activity {
	
	Button mBtnDisplay = null;
	Button mBtnGrayscale = null;
	// This is the custom application object.
	ImageDisplayerApp mApp = null;
	Resources mRes = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_displayer_act01);
        mBtnDisplay = (Button) this.findViewById(R.id.btn_display);
        mBtnGrayscale = (Button) this.findViewById(R.id.btn_grayscale);
        // Casts the Application object to ImageDisplayerApp object.
        mApp = (ImageDisplayerApp) this.getApplication();
        mRes = getResources();

        mBtnDisplay.setOnClickListener(
        	new OnClickListener() {
				@Override
				public void onClick(View v) {
					int img_id = new Random().nextInt(10);
					// explicit use of storeRequest method
					mApp.storeRequest(img_id, mRes.getString(R.string.display_oper_txt));
					startActivity(new Intent(mApp, ImageRequestProcessorAct.class));
				}
			}
        );
        
        mBtnGrayscale.setOnClickListener(
        		new OnClickListener() {

    				@Override
    				public void onClick(View v) {
    					int img_id = new Random().nextInt(10);
    					mApp.storeRequest(img_id, mRes.getString(R.string.grayscale_oper_txt));
    					startActivity(new Intent(mApp, ImageRequestProcessorAct.class));
    				}	
            	}
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_image_displayer_act01, menu);
        return true;
    }
    
   
}
