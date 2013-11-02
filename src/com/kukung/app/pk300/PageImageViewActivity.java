package com.kukung.app.pk300;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

import java.util.StringTokenizer;

import com.kukung.app.pk300.R;
import com.kukung.app.model.TOC;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

public class PageImageViewActivity extends Activity {
	static Bitmap pageBitmap;
	ImageViewTouch imageViewTouch;
	
	public static final String EXTRA_KEY_PAGE_ID = "fileId";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_image_view);
        imageViewTouch = (ImageViewTouch)findViewById(R.id.imageViewer);
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, 1.0f);
        imageViewTouch.setImageMatrix(matrix);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	cleanPageBitmap();
		loadPageBitmap();
		imageViewTouch.setImageBitmap(pageBitmap);
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	
    	cleanPageBitmap();
    }

	private void cleanPageBitmap() {
		if (pageBitmap != null) {
			pageBitmap.recycle();
	    	pageBitmap = null;
		}
	}

	private void loadPageBitmap() {
		String fileId = null;
    	if (getIntent().hasExtra(EXTRA_KEY_PAGE_ID)) {
    		fileId = getIntent().getStringExtra(EXTRA_KEY_PAGE_ID);
    	}
    	
    	if (fileId == null || fileId.equals("")) {
    		Log.e("pk111", "There is no fileId at Message.");
    	}
    	
		try {
			cleanPageBitmap();
			pageBitmap = BitmapFactory.decodeFile(TOC.getImageFilePathAtSDCard(fileId));
		} catch (Exception e) {
			Log.e("pk111", "Fail at loading the page image.", e);
		}
        
	}
}