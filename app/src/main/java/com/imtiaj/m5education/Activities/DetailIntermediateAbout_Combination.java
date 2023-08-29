package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class DetailIntermediateAbout_Combination extends AppCompatActivity {

    private int CheckScroll;

    private ScrollView AirtsScroll,ScienceScroll;

    private TextView AirtsFirstLanguage,AirtsSecondLanguage,AirtsCombinationGuid,AirtsSetOneCaption,AirtsSetOneText
            ,AirtsSetTwoCaption,AirtsSetTwoText,AirtsSetThreeCaption,AirtsSetThreeText;

    private TextView SciFirstLanguage,SciSecondLanguage,SciCombinationGuide,SciSetOneCaption,SciSetOneText,
            SciSetTwoCaption,SciSetTwoText,SciSetThreeCaption,SciSetThreeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intermediate_about__combination);

        Bundle getIncomingIntent_for_check_Scroll = getIntent().getExtras();
        CheckScroll = getIncomingIntent_for_check_Scroll.getInt("checkTypeView");

        AirtsScroll = (ScrollView)findViewById(R.id.AirtsCombinationScrollid);
        ScienceScroll = (ScrollView)findViewById(R.id.ScienceCombinationScrollid);

        AirtsFirstLanguage = (TextView)findViewById(R.id.IntermediateAirts_FirstLanguageId);
        AirtsSecondLanguage = (TextView)findViewById(R.id.IntermediateAirts_SecondLanguageId);
        AirtsCombinationGuid = (TextView)findViewById(R.id.SetCombinationguidText);
        AirtsSetOneCaption = (TextView)findViewById(R.id.SetCombinationSetCaption1);
        AirtsSetOneText = (TextView)findViewById(R.id.SetCombinationSet1Text);
        AirtsSetTwoCaption = (TextView)findViewById(R.id.SetCombinationSetCaption2);
        AirtsSetTwoText = (TextView)findViewById(R.id.SetCombinationSet2Text);
        AirtsSetThreeCaption = (TextView)findViewById(R.id.SetCombinationSetCaption3);
        AirtsSetThreeText = (TextView)findViewById(R.id.SetCombinationSet3Text);


        SciFirstLanguage = (TextView)findViewById(R.id.IntermediateScience_FirstLanguageId);
        SciSecondLanguage = (TextView)findViewById(R.id.IntermediateScience_SecondLanguageId);
        SciCombinationGuide = (TextView)findViewById(R.id.SetCombinationguidText_forScience);
        SciSetOneCaption = (TextView)findViewById(R.id.ScienceCombinationSetCaption1);
        SciSetOneText = (TextView)findViewById(R.id.ScienceCombinationSet1Text);
        SciSetTwoCaption = (TextView)findViewById(R.id.ScienceCombinationSetCaption2);
        SciSetTwoText = (TextView)findViewById(R.id.ScienceCombinationSet2Text);
        SciSetThreeCaption = (TextView)findViewById(R.id.ScienceCombinationSetCaption3);
        SciSetThreeText = (TextView)findViewById(R.id.ScienceCombinationSet3Text);


        String SetNoOneText = getResources().getString(R.string.IntermediateAirtsSetOneText);
        String SetNoTwoText = getResources().getString(R.string.IntermediateAirtsSetTwoText);
        String SetNoThreeText = getResources().getString(R.string.IntermediateAirtsSetThreeText);
        String AirtsGuideText = getResources().getString(R.string.Intermediate_Airts_Combination_guide_text);
        String airtsFirstLanguage = getResources().getString(R.string.Intermediate_Airts_FirstLanguage);
        String airtsSecondLanguage = getResources().getString(R.string.Intermediate_Airts_SecondLanguage);

        String ScienceguideText = getResources().getString(R.string.Intermediate_Science_Combination_guide_text);
        String ScienceSetOneText = getResources().getString(R.string.IntermediateScienceSetOneText);

        AirtsCombinationGuid.setText(AirtsGuideText);
        AirtsSetOneText.setText(SetNoOneText);
        AirtsSetTwoText.setText(SetNoTwoText);
        AirtsSetThreeText.setText(SetNoThreeText);
        AirtsFirstLanguage.setText(airtsFirstLanguage);
        AirtsSecondLanguage.setText(airtsSecondLanguage);


        SciFirstLanguage.setText(airtsFirstLanguage);
        SciSecondLanguage.setText(airtsSecondLanguage);
        SciCombinationGuide.setText(ScienceguideText);
        SciSetOneText.setText(ScienceSetOneText);

        switch (CheckScroll){

            case 1:
                AirtsScroll.setVisibility(View.VISIBLE);
                ScienceScroll.setVisibility(View.GONE);
                break;

            case 2:
                AirtsScroll.setVisibility(View.GONE);
                ScienceScroll.setVisibility(View.VISIBLE);

                break;
        }


    }
}
