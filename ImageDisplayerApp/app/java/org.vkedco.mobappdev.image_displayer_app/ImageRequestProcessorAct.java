package org.vkedco.mobappdev.image_displayer_app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

// author: vladimir kulyukin

public class ImageRequestProcessorAct extends Activity {

	final static int[] mImgIds = { R.drawable.img_01, R.drawable.img_02,
			R.drawable.img_03, R.drawable.img_04, R.drawable.img_05,
			R.drawable.img_06, R.drawable.img_07, R.drawable.img_08,
			R.drawable.img_09, R.drawable.img_10 };

	ImageView mImgView = null;
	Bitmap mOrigBitmap = null;
	Bitmap mGrayBitmap = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_processor_layout);
		ImageDisplayerApp thisApp = (ImageDisplayerApp) this.getApplication();
		
		mImgView = (ImageView) this.findViewById(R.id.img_processor_view);
		mImgView.setImageDrawable(null);
		int img_id = thisApp.getRequestImgId();
		String img_oper = thisApp.getRequestImgOper();
		if (img_oper
				.equals(getResources().getString(R.string.display_oper_txt))) {
			mOrigBitmap = BitmapFactory.decodeResource(getResources(),
					mImgIds[img_id]);
			mImgView.setImageBitmap(mOrigBitmap);
		} else if (img_oper.equals(getResources().getString(
				R.string.grayscale_oper_txt))) {
			
			mOrigBitmap = BitmapFactory.decodeResource(getResources(),
					mImgIds[img_id]);
			mGrayBitmap = LuminosityConverter.convertToGrayLevel(mOrigBitmap);
			mImgView.setImageBitmap(mGrayBitmap);
			mOrigBitmap.recycle();
			mOrigBitmap = null;
		}
		thisApp.removeRequest();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_image_displayer_act01, menu);
		return true;
	}
	
	private void recycleBitmaps() {
		if ( mOrigBitmap != null ) {
			mOrigBitmap.recycle();
			mOrigBitmap = null;
		}
		
		if ( mGrayBitmap != null ) {
			mGrayBitmap.recycle();
			mGrayBitmap = null;
		}
			
	}

	@Override
	protected void onPause() {
		super.onPause();
		recycleBitmaps();
	}

	@Override
	protected void onStop() {
		super.onStop();
		recycleBitmaps();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		recycleBitmaps();
	}

}
