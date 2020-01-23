package com.example.saycheese;

import androidx.annotation.Nullable;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE=1; //Identifier for our camera intent
    ImageView myImageView;
    Button captureButton;
    Button invertButton;
    Button filterButton;
    Bitmap capturedPhoto;

Drawable [] layers=new Drawable[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImageView=(ImageView)findViewById(R.id.myImageView);
        captureButton=(Button)findViewById(R.id.captureButton);
        invertButton=(Button)findViewById(R.id.invertImage);
        filterButton=(Button)findViewById(R.id.filterButton);


        //Disable the button if the user doesn't have a camera

        if(!hasCamera())
        {
            captureButton.setEnabled(false);
        }
        //Launching the camera app

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Take a picture and pass the result to onActivityResult
                startActivityForResult(myIntent,REQUEST_IMAGE_CAPTURE);//We assign the id(which we created earlier) to our intent
            }
        });





    }
    //If you want to return the image taken:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //Check if image was successfully captured...
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
        {
                //Get the photo
                final Bundle extras=data.getExtras();
               capturedPhoto=(Bitmap)extras.get("data");
                myImageView.setImageBitmap(capturedPhoto);

                //Save the image in the gallery
                MediaStore.Images.Media.insertImage(getContentResolver(),capturedPhoto,"Henlo","Sample Description");

                invertButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap invertedPhoto=invertImage(capturedPhoto);
                        myImageView.setImageBitmap(invertedPhoto);
                    }
                });

                filterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        layers[0]=(Drawable)extras.get("data");
                        layers[1]= ContextCompat.getDrawable(getApplicationContext(),R.drawable.perfect);
                        LayerDrawable layerDrawable= new LayerDrawable(layers);
                        myImageView.setImageDrawable(layerDrawable);

                    }
                });



        }
        else
        {
            invertButton.setEnabled(false);
            filterButton.setEnabled(false);
        }
    }

    //Inverting a BitMap image

    public static Bitmap invertImage(Bitmap original)
    {
       Bitmap finalImage=Bitmap.createBitmap(original.getWidth(),original.getHeight(),original.getConfig());
       //Storing the Alpha, Red, Green, Blue values

        int A,R,G,B;
        int pixelColor;
        int height=original.getHeight();
        int width=original.getWidth();

        for(int y=0;y<height;y++)
        {
            for(int x=0;x<width;x++)
            {
                pixelColor=original.getPixel(x,y);

                //Inverting the image
                A=Color.alpha(pixelColor);
                R=255-Color.red(pixelColor);
                G=255-Color.green(pixelColor);
                B=255-Color.blue(pixelColor);
                finalImage.setPixel(x,y,Color.argb(A,R,G,B));

            }
        }



       return finalImage;
    }

    //Check if the user has a camera
    private boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
}
