package com.example.fingerpaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;


public class FingerPaint extends View{
	
	//Drawing path
	private Path drawPath;
	//drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	//Canvas bitmap
	private Bitmap offScreenBitmap;
	//Canvas
	private Canvas offScreenCanvas;
	//initial red color
	private int paintColor = 0xFFFF0000;
	//Shape number
	private int shape =1;	
	// To map integers to objects
	private SparseArray<PointF> activePointers; 
	
	/*float pressure = 0; //Touch pressure
    float size = 0;  //Touch size
    final static float PRESET_PRESSURE = 0xFF;
    final static float PRESET_SIZE = 20;*/
	
	public FingerPaint(Context context, AttributeSet attrs)
	{
		super(context, attrs);		
		setup();
	}
	
	public void setup()
	{
		drawPaint = new Paint();		
		drawPaint.setColor(paintColor);			
	    activePointers = new SparseArray<PointF>();
	    canvasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    canvasPaint.setStyle(Paint.Style.FILL_AND_STROKE);   
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawBitmap(offScreenBitmap, 0, 0, canvasPaint);
		
		//Draw all pointers
		for (int size = activePointers.size(), i = 0; i < size; i++) {
				PointF point = activePointers.valueAt(i);
			if (point != null)
			{
				 
				switch(shape){
				case 1:
				//drawing Triangle
				Path drawPath = new Path();				drawPath.moveTo(point.x, point.y);				drawPath.lineTo(point.x+40, point.y);				drawPath.lineTo(point.x+20, point.y-40);				drawPath.lineTo(point.x, point.y);				drawPath.close();				offScreenCanvas.drawPath(drawPath, drawPaint);				
				break;
				case 2:
					//Drawing Circle
					/*canvasPaint.setColor(0xFF000000
						      + ((int)(PRESET_PRESSURE * pressure) <<16)
						      + ((int)(PRESET_PRESSURE * pressure) << 8)
						      + (int)(PRESET_PRESSURE * pressure));
					 offScreenCanvas.drawCircle(point.x, point.y, (PRESET_SIZE * size), drawPaint);*/
					offScreenCanvas.drawCircle(point.x, point.y, 20, drawPaint);
					break;
				case 3:
					//Drawing Rectangle
					offScreenCanvas.drawRect(point.x, point.y, point.x+40, point.y+40, drawPaint);
					break;
				}
			}
		}
	}	
	
	//Setting the shape
	public void setShape(int newShape){
		invalidate();
		shape = newShape;
	}	

	//Multi touch screen function
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{		
		//Get the finger pointer index from the event object
		int pointerIndex = event.getActionIndex();
		
		// get pointer ID
	    int pointerId = event.getPointerId(pointerIndex);

	    // get masked (not specific to a pointer) action
	    int maskedAction = event.getActionMasked();
	    
	    /*pressure = event.getPressure();
	    if(pressure > 1){
	     pressure = 1;
	    }	     
	    size =event.getSize();	*/   
	  
	    //int maskedAction = event.getAction();
	    switch (maskedAction) {
	    
	    case MotionEvent.ACTION_DOWN:
	    case MotionEvent.ACTION_POINTER_DOWN: {
	    //pressure[pointerId] = event.getPressure(pointerIndex);
	    	PointF f = new PointF();
	    	f.x = event.getX(pointerIndex);
	    	f.y = event.getY(pointerIndex);
	    	activePointers.put(pointerId, f);
	    	break;
	    }
	    case MotionEvent.ACTION_MOVE: { //Pointer moving
	    	for (int size = event.getPointerCount(), i = 0; i < size; i++) {
	    		PointF point = activePointers.get(event.getPointerId(i));
	    		if (point != null) {
	    			point.x = event.getX(i);
	    			point.y = event.getY(i);
	    		}
	    	}
	    	break;
	    }
	    case MotionEvent.ACTION_UP:
	    case MotionEvent.ACTION_POINTER_UP:
	    case MotionEvent.ACTION_CANCEL: {
	    	activePointers.remove(pointerId);
	    	break;
	    }   
	    
	    }
	    invalidate();
	    return true;

	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		offScreenBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
		offScreenCanvas = new Canvas(offScreenBitmap);
	}
	
	//Clear the paint canvas
	public void clear()
	{
		offScreenCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
	}
	
	//setting colors
	public void setColors(String color)
	{
		invalidate();
		paintColor = Color.parseColor(color);
		drawPaint.setColor(paintColor);
		
	}	
}
