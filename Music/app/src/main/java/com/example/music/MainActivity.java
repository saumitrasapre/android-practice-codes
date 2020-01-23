package com.example.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final String[] PERMISSIONS={
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int REQUEST_PERMISSIONS=12345;
    private static final int PERMISSIONS_COUNT=1;
    private  boolean isMusicPlayerInit;
    private List <String> musicFilesList;

    private boolean arepermissionsdenied()
    {
        for(int i=0;i<PERMISSIONS_COUNT;i++)
        {
            if(checkSelfPermission(PERMISSIONS[i])!= PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(arepermissionsdenied())
        {
            ((ActivityManager) (this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
            recreate();
        }
        else
        {
            onResume();
        }
    }

    private void  addMusicFilesFrom(String dirPath)
    {
        final File musicDir= new File(dirPath);

        if(!musicDir.exists())
        {
            musicDir.mkdir();
            return;
        }
        final File[] files=musicDir.listFiles();

        for(File loopvariable: files)
        {
            final String path=loopvariable.getAbsolutePath();
            if(path.endsWith(".mp3"))
            {
                musicFilesList.add(path);
            }
        }
    }

    private void fillMusicList()
    {
        musicFilesList.clear();
        addMusicFilesFrom(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)));
        addMusicFilesFrom(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
    }

    private  MediaPlayer mp;
    private  int  playMusicFile(String path)
    {
        mp=new MediaPlayer();
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mp.getDuration();
    }

       private int songPosition;
        private  Thread mythread;



    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && arepermissionsdenied()) //If the sdk of the device is greater than marshmallow, and permission is denied
        {
            requestPermissions(PERMISSIONS,REQUEST_PERMISSIONS);
            return;
        }

        if(!isMusicPlayerInit)
        {
            ListView listView=findViewById(R.id.listview);
            TextAdapter textAdapter=new TextAdapter();
            musicFilesList=new ArrayList<>();
            fillMusicList();
            textAdapter.setData(musicFilesList);
            listView.setAdapter(textAdapter);
            final SeekBar seekBar=findViewById(R.id.seekbar);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                int songProgress;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    songProgress=progress;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                    mp.seekTo(songProgress);

                }
            });

            final TextView songPositionTextView=findViewById(R.id.currentPosition);
            final TextView songDurationTextView= findViewById(R.id.songDuration);
            final Button pauseButton=findViewById(R.id.pausebutton);


            pauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mp!=null)
                    {
                        if(mp.isPlaying())
                        {
                            mp.pause();
                        }
                        else
                        {
                            mp.start();
                        }
                    }
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(mp!=null && mythread!=null)
                    {
                        mp.stop();
                        mp.release();
                        mp=null;
                        mythread.interrupt();
                        seekBar.setVisibility(View.GONE);
                        mythread=null;
                    }
                    final String musicFilePath=musicFilesList.get(position);
                    final int songDuration=playMusicFile(musicFilePath);
                    seekBar.setMax(songDuration);
                    pauseButton.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                    songDurationTextView.setText(String.valueOf(songDuration/1000));

                  mythread =new Thread()
                    {
                        @Override
                        public void run() {

                            songPosition=0;
                            while(songPosition<songDuration) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                songPosition = seekBar.getProgress() + 1000;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        seekBar.setProgress(songPosition);
                                        songPositionTextView.setText(String.valueOf(songPosition / 1000));
                                    }
                                });

                            }

                        }
                    };
                  mythread.start();


                }
            });


            isMusicPlayerInit=true;
        }
    }

    class TextAdapter extends BaseAdapter
    {
        private List <String> data= new ArrayList<>();
         void setData(List <String> mData)
         {
             data.clear();
             data.addAll(mData);
             notifyDataSetChanged();
         }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public String getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

             if(convertView==null)
             {
                convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
                convertView.setTag(new ViewHolder((TextView)convertView.findViewById(R.id.myItem)));
             }

             ViewHolder holder=(ViewHolder) convertView.getTag();
             String item= data.get(position);
             holder.info.setText(item.substring(item.lastIndexOf('/')+1));
            return convertView;
        }
    }
    class ViewHolder
    {
        TextView info;

        ViewHolder(TextView mInfo)
        {
            info=mInfo;
        }
    }
}

