package com.example.lab4task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    String [] memeArray = {"meme1","meme2","meme3","meme4","meme5"};
    int currentIndex = 1;
    ImageView imageView ;
    private float mScaleFactor = 1.0f;
    GestureDetectorCompat gestureDetectorCompat;
    GestureDetector gestureDetector ;
    ScaleGestureDetector scaleGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(new GestureListener());
        gestureDetectorCompat = new GestureDetectorCompat(this,this);
        scaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());
        imageView = findViewById(R.id.memeView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                scaleGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        //int id = getResources().getIdentifier("com.example.lab4task1:drawable/"+memeArray[currentIndex],null,null);
        setImage(currentIndex);

    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                if(currentIndex<memeArray.length-1) {
                    currentIndex++;
                    setImage(currentIndex);
                }
                    return false; // Right to left
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                if(currentIndex>0) {
                    currentIndex--;
                    setImage(currentIndex);
                }
                return false; // Left to right
            }

            return false;
        }
    }
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
    public  void setImage(int imageIndex){
        int id = getResources().getIdentifier(memeArray[imageIndex], "drawable", getPackageName() );
        imageView.setImageResource(id);
        Toast.makeText(this,"New image Loaded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        float y = motionEvent1.getY()-motionEvent.getY();
        float x = motionEvent.getX() - motionEvent.getX();
        Toast.makeText(getApplicationContext(),"In the fling",Toast.LENGTH_SHORT).show();
        currentIndex++;
        setImage(currentIndex);
        return false;
    }
}
