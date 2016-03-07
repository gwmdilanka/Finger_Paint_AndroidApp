package com.example.fingerpaint;




import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ZoomControls;


public class MainActivity extends Activity implements OnClickListener {
	
	//create instance of the custom View class and that can added to the layout
	private FingerPaint fingerPaint;
		
	//Buttons of the Finger Paint
	private ImageButton exitBtn;
	private ImageButton resetBtn;	
	
	//To save the color tag number as string
	private String color;
	
	//Color buttons
	private Button blackBtn;
	private Button limeBtn;
	private Button greyBtn;
	private Button whiteBtn;
	private Button pinkBtn;
	private Button purpleBtn;
	private Button redBtn;
	private Button maroonBtn;
	private Button yellowBtn;
	private Button greenBtn;
	private Button aquaBtn;
	private Button tealBtn;
	private Button blueBtn;
	private Button navyBtn;
	private Button orangeBtn;
	private Button amberBtn;
	
	//ImageButtons	
	private ImageButton triangle;
	private ImageButton circle;
	private ImageButton square;	
	private ImageButton save;
	private ImageButton email;	
	
	//To hold shape as a number (shapeNum 1 = Triangle, shapeNum 2 = Circle, shapeNum 3 = Rectangle)
	int shapeNum;	
	
	//the Uri for the file if the scanning operation succeeded 
	//and the file was added to the media database, or null if scanning failed.
	private Uri bitmapUri;
	
	private String bitmapSaved = null;
	private File file = null;
	final Context context = this;	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*Instantiate the fingerPaint variable by retrieving a reference 
        to it from the layout.
        This can use to call the methods in the FingerPaint class.*/
        fingerPaint = (FingerPaint)findViewById(R.id.draw_view);        
        
        //Set click listener to the function buttons
        exitBtn = (ImageButton) findViewById(R.id.btnExit);
        exitBtn.setOnClickListener(this);
        
        resetBtn = (ImageButton) findViewById(R.id.btnReset);
        resetBtn.setOnClickListener(this);  
        
        //Set click listener to the color buttons
        blackBtn = (Button)findViewById(R.id.btnBlack);
        blackBtn.setOnClickListener(this);
        
        limeBtn = (Button)findViewById(R.id.btnLime);
        limeBtn.setOnClickListener(this); 
        
        greyBtn = (Button)findViewById(R.id.btnGrey);
        greyBtn.setOnClickListener(this); 
        
        whiteBtn = (Button)findViewById(R.id.btnWhite);
        whiteBtn.setOnClickListener(this); 
        
        pinkBtn = (Button)findViewById(R.id.btnPink);
        pinkBtn.setOnClickListener(this); 
        
        purpleBtn = (Button)findViewById(R.id.btnPurple);
        purpleBtn.setOnClickListener(this); 
        
        redBtn = (Button)findViewById(R.id.btnRed);
        redBtn.setOnClickListener(this); 
        
        maroonBtn = (Button)findViewById(R.id.btnMaroon);
        maroonBtn.setOnClickListener(this); 
        
        yellowBtn = (Button)findViewById(R.id.btnYellow);
        yellowBtn.setOnClickListener(this); 
        
        greenBtn = (Button)findViewById(R.id.btnGreen);
        greenBtn.setOnClickListener(this); 
        
        aquaBtn = (Button)findViewById(R.id.btnAqua);
        aquaBtn.setOnClickListener(this); 
        
        tealBtn = (Button)findViewById(R.id.btnTeal);
        tealBtn.setOnClickListener(this); 
        
        blueBtn = (Button)findViewById(R.id.btnBlue);
        blueBtn.setOnClickListener(this); 
        
        navyBtn = (Button)findViewById(R.id.btnNavy);
        navyBtn.setOnClickListener(this); 
        
        orangeBtn = (Button)findViewById(R.id.btnOrange);
        orangeBtn.setOnClickListener(this); 
        
        amberBtn = (Button)findViewById(R.id.btnAmber);
        amberBtn.setOnClickListener(this);         
        
        //Set click listener to the shape buttons
        triangle = (ImageButton)findViewById(R.id.triangle);
        triangle.setOnClickListener(this);
        
        circle = (ImageButton)findViewById(R.id.circle);
        circle.setOnClickListener(this);
        
        square = (ImageButton)findViewById(R.id.square);
        square.setOnClickListener(this);  
        
        //Save Image as bitmap
        save = (ImageButton)findViewById(R.id.btnSave);
        save.setOnClickListener(this);
        
        //Email Image
        email = (ImageButton) findViewById(R.id.btnEmail);
        email.setOnClickListener(this);

    }
    
    //Exit the application
    @Override
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    	case R.id.btnExit:
    		exitApplication();    		
    		break;
    	case R.id.btnReset:    		
    		resetPaint();    		
    		break;    
    	case R.id.btnBlack:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnLime:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnGrey:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnWhite:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnPink:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnPurple:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnRed:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnMaroon:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnYellow:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnGreen:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnAqua:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnTeal:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnBlue:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnNavy:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnOrange:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;
    	case R.id.btnAmber:
    		color = v.getTag().toString();
    		fingerPaint.setColors(color);
    		break;    		
    	case R.id.triangle:
    		shapeNum = 1;
    		fingerPaint.setShape(shapeNum);
    		break;
    	case R.id.circle:
    		shapeNum = 2;
    		fingerPaint.setShape(shapeNum);
    		break;
    	case R.id.square:
    		shapeNum = 3;
    		fingerPaint.setShape(shapeNum);
    		break; 
    	case R.id.btnSave:    		
    		AlertDialog.Builder saveDialog = new AlertDialog.Builder(context);
    		saveDialog.setTitle("Save Painting");
    		saveDialog.setMessage("Save painting to Gallery?");
    		saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
    		    public void onClick(DialogInterface dialog, int which){
    		        //save Painting
    		    	saveImg();
    		    	//Feed back to the users
    	    		if(bitmapSaved!=null){
    	    		    Toast savedToast = Toast.makeText(getApplicationContext(), 
    	    		        "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
    	    		    savedToast.show();
    	    		}
    	    		else{
    	    		    Toast unsavedToast = Toast.makeText(getApplicationContext(), 
    	    		        "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
    	    		    unsavedToast.show();
    	    		}
    	    		//Destroy the drawing cache so that 
    	    		//any future drawings saved won't use the existing cache
    	    		fingerPaint.destroyDrawingCache();
    		    }
    		});
    		saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
    		    public void onClick(DialogInterface dialog, int which){
    		        dialog.cancel();
    		    }
    		});
    		saveDialog.show();    		
    		break;
    	case R.id.btnEmail:
    		Intent intent = new Intent();
    		saveImg();
    		intent.setAction(Intent.ACTION_SEND);
    		intent.putExtra(Intent.EXTRA_TEXT, "Image");
    		intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
    		intent.setType("image/png");
    		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    		startActivity(intent);    
    		break;
    	}    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //Exit the application
    public void exitApplication()
    {
    	finish();
    	System.exit(0);    
    }
    
    //Reset the paint
    public void resetPaint()
    {
    	fingerPaint.clear();
    }    
    
    //Saving Bitmap image to the gallery
    public void saveImg()
    {
    	fingerPaint.setDrawingCacheEnabled(true);//validate the bitmap image    	
    	FileOutputStream fos = null;
    	//Return the primary external storage directory
    	File mediaDry = new File(Environment.getExternalStorageDirectory().getPath()
    			+ "/DCIM/Camera/");    	
    	//To creating missing parent directories if necessary
    	mediaDry.mkdirs();    	
    	//File constructor with randomly generated UUID string for 
    	//the filename with PNG extension
    	file = new File (mediaDry,UUID.randomUUID().toString()+".png");
    	try
    	{
    		fos = new FileOutputStream(file);
    		fingerPaint.getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100, fos);
    		fos.flush();
    		fos.close();
    		bitmapSaved = "success";
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	//To show the image in gallery, setup a MediaScannerConnection 
    	//for the bitmap we are saving        
        MediaScannerConnection.scanFile(context, 
        		new String[]{file.toString()},null, 
        		new MediaScannerConnection.OnScanCompletedListener() {  
            public void onScanCompleted(String path, Uri uri) {  
                Log.i("ExternalStorage", "Scanned " + path + ":");  
                Log.i("ExternalStorage", "-> uri=" + uri);  
                bitmapUri = uri;
            }  
        });        
    }
    
    //Screen orientation in runtime
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();        
        }
    }
}
