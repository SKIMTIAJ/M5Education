package com.imtiaj.m5education.AdapterClass;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.imtiaj.m5education.Activities.YoutubeVideoPlayfull;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.YoutubeConfig;
import com.imtiaj.m5education.modelClass.YoutubeUploadPOjo;

import java.util.ArrayList;

public class YoutubeVideoadapter extends RecyclerView.Adapter<YoutubeVideoadapter.YoutubeViewHolder> {


    private ArrayList<YoutubeUploadPOjo> AdapterClassArrayList;
    private Context ctx;


    public YoutubeVideoadapter() {
    }

    public YoutubeVideoadapter(ArrayList<YoutubeUploadPOjo> adapterClassArrayList, Context ctx) {
        AdapterClassArrayList = adapterClassArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater MyInflate = LayoutInflater.from(ctx);
        View v = MyInflate.inflate(R.layout.youtube_video_row_layout,viewGroup,false);
        return new YoutubeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder youtubeViewHolder, int i) {
        Log.d("YoutubeAdapter Class","Into the Bind Service");

        final YoutubeUploadPOjo youtubeUploadPOjogetCount= AdapterClassArrayList.get(i);
        /*youtubeViewHolder.YoutubeView.loadData(youtubeUploadPOjogetCount.getYoutubeVideoUrl(),"text/html","utf-8");*/
        youtubeViewHolder.videoTitle.setText(youtubeUploadPOjogetCount.getTitle());
        youtubeViewHolder.videoDuration.setText(youtubeUploadPOjogetCount.getDuration());

        youtubeViewHolder.YoutubeThumnailView.initialize(YoutubeConfig.Youtube_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(youtubeUploadPOjogetCount.getVideoId());

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {

                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                        Toast.makeText(ctx,"Youtube Thumbnail Error",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                Toast.makeText(ctx,"Youtube Initialization Faliure ",Toast.LENGTH_LONG).show();
            }
        });


        youtubeViewHolder.YoutubeParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent YoutubeVideoIdSent = new Intent(ctx, YoutubeVideoPlayfull.class);
                YoutubeVideoIdSent.putExtra("YoutubeVideoId",youtubeUploadPOjogetCount.getVideoId());
                //YoutubeVideoIdSent.putExtra("Title_url",youtubeUploadPOjogetCount.getTitle());
                //YoutubeVideoIdSent.putExtra("Desc_url",youtubeUploadPOjogetCount.getDuration());
                //Log.d("MyfragAdepter",.toString());
                ctx.startActivity(YoutubeVideoIdSent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return AdapterClassArrayList !=null ? AdapterClassArrayList.size():0;
    }

    public class YoutubeViewHolder extends RecyclerView.ViewHolder {

        YouTubeThumbnailView YoutubeThumnailView;
        TextView videoTitle,videoDuration;
        LinearLayout YoutubeParentLayout;

        public YoutubeViewHolder(@NonNull View itemView) {

            super(itemView);
            YoutubeThumnailView = (YouTubeThumbnailView)itemView.findViewById(R.id.video_thumbnail_image_view);
            videoTitle = (TextView)itemView.findViewById(R.id.video_title_label);
            videoDuration = (TextView)itemView.findViewById(R.id.video_duration_label);
            YoutubeParentLayout = (LinearLayout)itemView.findViewById(R.id.YoutubeWebframeParentLayout);

          /*  YoutubeView.getSettings().setJavaScriptEnabled(true);
            YoutubeView.getSettings().setAppCacheEnabled(true);
            YoutubeView.getSettings().setSaveFormData(true);
            YoutubeView.setWebViewClient(new WebViewClient());
            YoutubeView.setWebChromeClient(new WebChromeClient(){


            });*/

        }
    }



}
