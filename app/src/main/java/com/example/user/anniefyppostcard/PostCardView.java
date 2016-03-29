package com.example.user.anniefyppostcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by roytang on 16/3/2016.
 */
public class PostCardView extends View {

    Paint backgroundPaint;
    Paint defaultBitmapAreaBackgroundPaint;
    Paint bitmapPaint;

    RectF frame;

    Bitmap bitmap;

    private int width, height;

    private final int padding = 16;
    private RectF bitmapArea;
    private Bitmap scaledBitmap;

    public PostCardView(Context context) {
        super(context);
        init();
    }

    public PostCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PostCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        // enable shadow layer
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setShadowLayer(16, 0, 0, Color.DKGRAY);

        frame = new RectF(0,0,0,0); // l t r b

        bitmapArea = new RectF();

        defaultBitmapAreaBackgroundPaint = new Paint();
        defaultBitmapAreaBackgroundPaint.setColor(Color.parseColor("#B2EBF2"));

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        this.width = width;
        this.height = height;

        Log.d(getClass().getSimpleName(), "width: " + width + "px, height: " + height + "px");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        frame.set(0 + padding, 0 + padding, width - padding, height - padding);
        canvas.drawRoundRect(frame, 8, 8, backgroundPaint);

        // compute rect for bitmap area
        bitmapArea.set(frame.left + padding, frame.top + padding, frame.right - padding, frame.bottom - padding);
        canvas.drawRect(bitmapArea, defaultBitmapAreaBackgroundPaint);

        if (bitmap != null) {
            float scaleWidth = ((float) bitmap.getWidth()) / bitmapArea.width();
            float scaleHeight = ((float) bitmap.getHeight()) / bitmapArea.height();

//            if (scaledBitmap != null && !scaledBitmap.isRecycled()) {
//                scaledBitmap.recycle();
//                scaledBitmap = null;
//            }
            scaledBitmap = Bitmap.createScaledBitmap(bitmap, ((int) bitmapArea.width()), ((int) (bitmapArea.height())), false);
            Rect src = new Rect(0,0, ((int) bitmapArea.width()), ((int) bitmapArea.height()));
            canvas.drawBitmap(scaledBitmap, src, bitmapArea, bitmapPaint);
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.invalidate();
    }
}
