package com.jon.bma_dia6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// Prova de GitKraken
//http://blog.teamtreehouse.com/android-libraries-use-every-project
// CONTROL+SHIFT+N : buscar arxius
//github_laz08_filterme -> takephotono..

public class MainActivity extends AppCompatActivity {
    // Per identificar
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PICK_IMAGE = 2;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Path de on guardar/llegir l'arxiu de imatge o audio grabat.
    private static String mImageFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/image.jpg";
    private static String mAudioFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gpp";
    // Condiciona la accio dels botons Record/StopRecord i Play/Stop
    private boolean playing = false;
    private boolean recording = false;

    public static final String PATH_TO_FILE = "pathToFile";

    private ImageView mImageView;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private Bitmap imageBitmap;

    // avanç d'enviar el Intent fer un putExtra indicant MediaStore.EXTRA_OUTPUT

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Per guardar 'petites' variables. Petara en una imatge, etc
        outState.putString(PATH_TO_FILE, "blablabla");
        super.onSaveInstanceState(outState);
//        outState.putParcelable("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // carregar algunes coses guardades al pause, ...
        if (savedInstanceState != null) {
            String string = savedInstanceState.getString(PATH_TO_FILE);
        }

        mImageView = (ImageView) findViewById(R.id.imageview_photo);
        manageButtonOnClicks();
    }

    private void manageButtonOnClicks() {
        // Manage the buttons
        Button button_camera = (Button) findViewById(R.id.button_camera);
        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        Button button_gallery = (Button) findViewById(R.id.button_gallery);
        button_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGallery();
            }
        });

        Button button_filter = (Button) findViewById(R.id.button_filter);
        button_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFilter();
            }
        });

        Button button_record = (Button) findViewById(R.id.button_record);
        button_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordAudio();
            }
        });

        Button button_play = (Button) findViewById(R.id.button_play);
        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });
    }

    private void recordAudio() {
        if (recording) {
            Log.d("[recordAudio", "stopRecording()");
            stopRecording();
            recording = false;
        } else {
            Log.d("[recordAudio", "startRecording()");
            startRecording();
            recording = true;
        }
    }

    private void playAudio() {
        if (playing) {
            playing = false;
            pararAudio();
            Log.d("playAudio()", "pararAudio()");
        } else {
            playing = true;
            reproduirAudio();
            Log.d("playAudio()", "reproduirAudio()");
        }
    }

    private void reproduirAudio() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mAudioFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "reproduirAudio failed");
        }
    }

    private void pararAudio() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        // Parametres d'audio
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mAudioFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setMaxDuration(5000);

        // prepare() pot fallar
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() or start() failed");
        }
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        // Desenllaça el recurs per poder ser recollit pel garbage collector
        // IMPORTANT fer-ho per no tenir memory leakages
        mRecorder = null;
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mImageFileName = "file:" + image.getAbsolutePath();
        return image;
    }

    // Si peta al carregar la image (per memoria) es pq es massa gran. S'hauria de escalar per poderla visualitzar
    // http://developer.android.com/intl/es/training/displaying-bitmaps/load-bitmap.html
    private void loadGallery() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        startActivityForResult(chooserIntent, REQUEST_PICK_IMAGE);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Si no hi ha camera no llencis la nova Activity
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            galleryAddPic();
            Log.d("dispatchTakePicture..", "Adding to Gallery");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // comprova request code per saber desde quina activitat venim (no totes retornaran una
        //  imatge, o no es torna foto per algun problema (resultcode).
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            // Recullim miniatura
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                imageBitmap = BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(imageBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mImageFileName);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void applyFilter() {
        Random random = new Random();
        int value = random.nextInt(100);
        Bitmap result = createContrast(imageBitmap, value);
        TextView textView_value = (TextView) findViewById(R.id.textview_value);
        textView_value.setText(String.valueOf(value));
        mImageView.setImageBitmap(result);
    }

    private Bitmap createContrast(Bitmap src, double value) {
        // image size
        int width = imageBitmap.getWidth();
        int height = imageBitmap.getHeight();
        Log.d("createContrast", String.valueOf(width)+","+String.valueOf(height));
        // create output bitmap
        Bitmap bmOut = android.graphics.Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;
        // get contrast value
        double contrast = Math.pow((100 + value) / 100, 2);

        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel);
                R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if (R < 0) {
                    R = 0;
                } else if (R > 255) {
                    R = 255;
                }

                G = Color.red(pixel);
                G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if (G < 0) {
                    G = 0;
                } else if (G > 255) {
                    G = 255;
                }

                B = Color.red(pixel);
                B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if (B < 0) {
                    B = 0;
                } else if (B > 255) {
                    B = 255;
                }
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        // return final image
        return bmOut;
    }
}
