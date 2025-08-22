package com.example.Rubix;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import com.example.Rubix.kube.*;

/**
// * Created with IntelliJ IDEA.
* User: tony
* Date: 6/30/13
* Time: 3:50 PM
* To change this template use File | Settings | File Templates.
*/

/**
* Implement a simple rotation control.
*/
public class TouchSurfaceView extends GLSurfaceView {

    public TouchSurfaceView(Context context) {
        super(context);
        mRenderer = new KubeRenderer(((Kube)context).makeGLWorld(), (Kube)context);
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent e) {
        mRenderer.mAngleX += e.getX() * TRACKBALL_SCALE_FACTOR;
        mRenderer.mAngleY += e.getY() * TRACKBALL_SCALE_FACTOR;
        requestRender();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
                mRenderer.mAngleX += dx * TOUCH_SCALE_FACTOR;
                mRenderer.mAngleY += dy * TOUCH_SCALE_FACTOR;
                requestRender();
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }



    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private final float TRACKBALL_SCALE_FACTOR = 36.0f;
    private KubeRenderer mRenderer;
    private float mPreviousX;
    private float mPreviousY;
}



