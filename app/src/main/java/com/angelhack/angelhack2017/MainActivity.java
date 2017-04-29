package com.angelhack.angelhack2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        relativeLayout = new RelativeLayout(this);
        setContentView(relativeLayout);
        new SendImage().execute("Hello");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPreview = new CameraPreview(this, 0, CameraPreview.LayoutMode.FitToParent);
        RelativeLayout.LayoutParams previewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.addView(mPreview,0,previewLayoutParams);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mPreview.stop();
        relativeLayout.removeView(mPreview); // This is necessary.
        mPreview = null;
    }
}
