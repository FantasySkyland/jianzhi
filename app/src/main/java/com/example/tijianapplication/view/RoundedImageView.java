package com.example.tijianapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.example.tijianapplication.R;


/**
 * Created by CosWind on 2014/6/13.
 */
public class RoundedImageView extends androidx.appcompat.widget.AppCompatImageView {
  private float mCornerRadius;
  private RectF rectF;
  private PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

  public RoundedImageView(final Context context) {
    super(context);
  }

  public RoundedImageView(final Context context, final AttributeSet attributes) {
    super(context, attributes);
    TypedArray array = context.obtainStyledAttributes(attributes, R.styleable.RoundedImageView);

    if (array != null) {
      mCornerRadius = array.getDimension(R.styleable.RoundedImageView_corner_radius, 0);
      array.recycle();
    }
  }

  /**
   * 设置CornerRadius
   */
  public void setCornerRadius(final float cornerRadius) {
    mCornerRadius = cornerRadius;
  }

  @Override
  protected void onDraw(final Canvas canvas) {
    Drawable maiDrawable = getDrawable();
    if (maiDrawable instanceof BitmapDrawable && mCornerRadius > 0) {
      Paint paint = ((BitmapDrawable) maiDrawable).getPaint();
      final int color = 0xff000000;

      if (rectF == null) {
        Rect bitmapBounds = maiDrawable.getBounds();
        rectF = new RectF(bitmapBounds);
      }

      int saveCount =
          canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG);

      getImageMatrix().mapRect(rectF);

      paint.setAntiAlias(true);
      canvas.drawARGB(0, 0, 0, 0);
      paint.setColor(color);
      canvas.drawRoundRect(rectF, mCornerRadius, mCornerRadius, paint);

      Xfermode oldMode = paint.getXfermode();
      paint.setXfermode(porterDuffXfermode);

      super.onDraw(canvas);

      paint.setXfermode(oldMode);
      canvas.restoreToCount(saveCount);
    } else {
      super.onDraw(canvas);
    }
  }
}
