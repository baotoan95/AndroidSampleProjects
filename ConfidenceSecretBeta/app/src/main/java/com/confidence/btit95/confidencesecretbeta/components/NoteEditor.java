package com.confidence.btit95.confidencesecretbeta.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by baotoan on 05/07/2017.
 */

public class NoteEditor extends EditText {
    private Paint dashedLinePaint;
    private Rect reuseableRect;

    // the vertical offset scaling factor (10% of the height of the text)
    private static final float VERTICAL_OFFSET_SCALING_FACTOR = 0.1f;

    // the dashed line scale factors
    private static final float DASHED_LINE_ON_SCALE_FACTOR = 0.008f;
    private static final float DASHED_LINE_OFF_SCALE_FACTOR = 0.0125f;

    public NoteEditor(Context context) {
        super(context);
        init();
    }

    public NoteEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoteEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        dashedLinePaint = new Paint();
        dashedLinePaint.setStyle(Paint.Style.STROKE);
        dashedLinePaint.setARGB(200, 0, 0, 0);
        dashedLinePaint.setPathEffect(new DashPathEffect(new float[]{3, 3, 3, 3}, 0));

        reuseableRect = new Rect();

        setMinLines(15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // set the path effect based on the view width
        dashedLinePaint.setPathEffect(
                new DashPathEffect(
                        new float[]{
                                getWidth(),
                                getWidth()},
                        0));

        // get the height of the view
        int height = getHeight();

        // get the height of each line (not subtracting one from the line height makes lines uneven)
        int lineHeight = getLineHeight() - 1;

        // the number of lines equals the height divided by the line height
        int numberOfLines = height / lineHeight;

        // if there are more lines than what appear on screen
        if (getLineCount() > numberOfLines) {

            // set the number of lines to the line count
            numberOfLines = getLineCount();
        }

        // get the baseline for the first line
        int baseline = getLineBounds(0, reuseableRect);

        // for each line
        for (int i = 0; i < numberOfLines; i++) {
            // draw the line
            canvas.drawLine(
                    reuseableRect.left,             // left
                    baseline,      // top
                    reuseableRect.right,            // right
                    baseline,      // bottom
                    dashedLinePaint);               // paint instance

            // get the baseline for the next line
            baseline += getLineHeight();
        }

        super.onDraw(canvas);
    }
}
