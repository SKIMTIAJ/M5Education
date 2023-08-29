package com.imtiaj.m5education.Activities;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.YoutubeConfig;

public class YoutubeVideoPlayfull extends YouTubeBaseActivity {

    private YouTubePlayerView Youtubeplayer;
    private String VIDEO_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Youtubeplayer = (YouTubePlayerView)findViewById(R.id.youtube_player_view);
        VIDEO_ID = getIntent().getStringExtra("YoutubeVideoId");
        initializeYoutubePlayer();


    }


    private void initializeYoutubePlayer() {
        Youtubeplayer.initialize(YoutubeConfig.Youtube_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean wasRestored) {

                //if initialization success then load the video id to youtube player
                if (!wasRestored) {
                    //set the player style here: like CHROMELESS, MINIMAL, DEFAULT
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    //load the video
                    youTubePlayer.loadVideo(VIDEO_ID);

                    //OR

                    //cue the video
                    //youTubePlayer.cueVideo(VIDEO_ID);

                    //if you want when activity start it should be in full screen uncomment below comment
                    //youTubePlayer.setFullscreen(true);

                    //If you want the video should play automatically then uncomment below comment
                    youTubePlayer.play();

                    //If you want to control the full screen event you can uncomment the below code
                    //Tell the player you want to control the fullscreen change
                   /*player.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                    //Tell the player how to control the change
                    player.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean arg0) {
                            // do full screen stuff here, or don't.
                            Log.e(TAG,"Full screen mode");
                        }
                    });*/

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Toast.makeText(YoutubeVideoPlayfull.this,"Youtube Player View initialization failed",Toast.LENGTH_LONG).show();
                //Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }




}
