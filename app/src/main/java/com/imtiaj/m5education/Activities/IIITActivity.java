package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class IIITActivity extends AppCompatActivity {

    private TextView MainCaption, Maintext, EligiblityHead,EligiblityText, AnnualFeesHead, AnnualFeesText,
            SelectionHead,SelectionText, CollegeListHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iiit);

        MainCaption = (TextView)findViewById(R.id.IIIT_Page_CaptionMainid);
        Maintext = (TextView)findViewById(R.id.IIIT_Page_MainTextid);
        EligiblityHead = (TextView)findViewById(R.id.IIIT_Page_EligiblityHeadid);
        EligiblityText = (TextView)findViewById(R.id.IIIT_Page_Eligiblitytextid);
        AnnualFeesHead = (TextView)findViewById(R.id.IIIT_Page_AnnualFees_Headid);
        AnnualFeesText = (TextView)findViewById(R.id.IIIT_Page_AnnualFees_textid);
        SelectionHead = (TextView)findViewById(R.id.IIIT_Page_Selection_Headid);
        SelectionText = (TextView)findViewById(R.id.IIIT_Page_Selection_textid);


        String maintextString = getResources().getString(R.string.IIIT_page_main_text);
        String eligiblitytextString = getResources().getString(R.string.IIIT_page_eligiblity_text);
        String annualfeestextString = getResources().getString(R.string.IIIT_page_annualFees_text);
        String selectiontextString = getResources().getString(R.string.IIIT_page_selection_text);


        Maintext.setText(maintextString);
        EligiblityText.setText(eligiblitytextString);
        AnnualFeesText.setText(annualfeestextString);
        SelectionText.setText(selectiontextString);

    }
}
