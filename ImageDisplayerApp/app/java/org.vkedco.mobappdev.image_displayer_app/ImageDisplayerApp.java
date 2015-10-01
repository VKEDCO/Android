package org.vkedco.mobappdev.image_displayer_app;

import android.app.Application;

// author: vladimir kulyukin

public class ImageDisplayerApp extends Application {
	
	protected int mImgId = -1;
	protected String mImgOper = "";
	
	public ImageDisplayerApp() {
		mImgId = -1;
		mImgOper = "";
	}
	
	public void storeRequest(int img_id, String oper) {
		mImgId = img_id;
		mImgOper = oper;
	}
	
	public void removeRequest() {
		mImgId = -1;
		mImgOper = "";
	}
	
	public int getRequestImgId() {
		return mImgId;
	}
	
	public String getRequestImgOper() {
		return mImgOper;
	}
}

