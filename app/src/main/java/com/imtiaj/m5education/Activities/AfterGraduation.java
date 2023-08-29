package com.imtiaj.m5education.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.imtiaj.m5education.R;

public class AfterGraduation extends AppCompatActivity {

    private Button BtechButton,MBBSButton,CommonDegreeButton,TeachingButton,OtherButton;

    private Button MtechButton,MBAButton,JobAfterBtechButton,MDButton,MSButton,PracticeButton,JOBAfterMbbsButton,BAButton,BSCButton,
            BCOMButton,LLBButton,BCAButton,BBAButton,BEDButton,MEDButton,PHDButton,AfterTeachingJobButton,PostGraduationButton,JobButton;

    private LinearLayout BetechLinearLay, MBBsLinearLay,CommonDegreeLinearLay,TeachingLinearLay,OtherLinearlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_graduation);


        BtechButton = (Button)findViewById(R.id.AfterBtechMainButton);
        MBBSButton = (Button)findViewById(R.id.AfterMbbsMainButton);
        CommonDegreeButton =(Button)findViewById(R.id.AfterCommonDegreeMainButton);
        TeachingButton = (Button)findViewById(R.id.AfterTeachingMainButton);
        OtherButton = (Button)findViewById(R.id.AfterOtherMainButton);



        /* ***************************************INNER BUTTON IS DECLARE BELLOW********************************************* */

        MtechButton = (Button)findViewById(R.id.MtechidAfterBtech);
        MBAButton = (Button)findViewById(R.id.MBAidAfterBtech);
        JobAfterBtechButton = (Button)findViewById(R.id.JOBidAfterBtech);

        MDButton = (Button)findViewById(R.id.MDidAfterMBBS);
        MSButton = (Button)findViewById(R.id.MSidAfterMBBS);
        PracticeButton = (Button)findViewById(R.id.PracticeidAfterMBBS);
        JOBAfterMbbsButton = (Button)findViewById(R.id.JOBidAfterMBBS);

        BAButton = (Button)findViewById(R.id.BA_AfterCommonDegreeid);
        BSCButton = (Button)findViewById(R.id.BSC_AfterCommonDegreeid);
        BCOMButton = (Button)findViewById(R.id.BCom_AfterCommonDegreeid);
        LLBButton = (Button)findViewById(R.id.LLB_AfterCommonDegreeid);
        BCAButton = (Button)findViewById(R.id.BCA_AfterCommonDegreeid);
        BBAButton = (Button)findViewById(R.id.BBA_AfterCommonDegreeid);


        BEDButton = (Button)findViewById(R.id.BEd_AfterTeachingid);
        MEDButton = (Button)findViewById(R.id.MEd_AfterTeachingid);
        PHDButton = (Button)findViewById(R.id.PHd_AfterTeachingid);
        AfterTeachingJobButton = (Button)findViewById(R.id.JOb_AfterTeachingid);


        PostGraduationButton = (Button)findViewById(R.id.PostGraduate_AfterOtherid);
        JobButton = (Button)findViewById(R.id.BEd_AfterOtherJobid);

        BetechLinearLay =(LinearLayout)findViewById(R.id.AfterBtechBellowLay);
        MBBsLinearLay =(LinearLayout)findViewById(R.id.AfterMBBSBellowLay);
        CommonDegreeLinearLay =(LinearLayout)findViewById(R.id.AfterCommonDegreeBellowLay);
        TeachingLinearLay =(LinearLayout)findViewById(R.id.AfterTeachingBellowLay);
        OtherLinearlay =(LinearLayout)findViewById(R.id.AfterOtherBellowLay);




        BAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(AfterGraduation.this,"button is properly Clicked",Toast.LENGTH_SHORT).show();
                PopupMenu BAButtonPopUp = new PopupMenu(AfterGraduation.this,BAButton);
                BAButtonPopUp.getMenuInflater().inflate(R.menu.bastudentmenu_for,BAButtonPopUp.getMenu());

                BAButtonPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
                BAButtonPopUp.show();
            }
        });

        BSCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu BSCButtonPopup = new PopupMenu(AfterGraduation.this,BSCButton);
                BSCButtonPopup.getMenuInflater().inflate(R.menu.bscstudentmenu_for,BSCButtonPopup.getMenu());

                BSCButtonPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
                BSCButtonPopup.show();
            }
        });

        BCOMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu BComButtuonPopup = new PopupMenu(AfterGraduation.this,BCOMButton);
                BComButtuonPopup.getMenuInflater().inflate(R.menu.bocomstudentmenu_for,BComButtuonPopup.getMenu());

                BComButtuonPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
                BComButtuonPopup.show();
            }
        });

        LLBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu LLBButtonPopup = new PopupMenu(AfterGraduation.this,LLBButton);
                LLBButtonPopup.getMenuInflater().inflate(R.menu.llbstudentmenu_for,LLBButtonPopup.getMenu());

                LLBButtonPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
                LLBButtonPopup.show();
            }
        });

        BCAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu BCAButtopnPopup = new PopupMenu(AfterGraduation.this,BCAButton);
                BCAButtopnPopup.getMenuInflater().inflate(R.menu.bcastudentmenu_for,BCAButtopnPopup.getMenu());

                BCAButtopnPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
                BCAButtopnPopup.show();
            }
        });

        BBAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu BBAButtonPopup = new PopupMenu(AfterGraduation.this,BBAButton);
                BBAButtonPopup.getMenuInflater().inflate(R.menu.bba_or_bbmstudentmenu_for,BBAButtonPopup.getMenu());

                BBAButtonPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
                BBAButtonPopup.show();
            }
        });




        BtechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BetechLinearLay.setVisibility(View.VISIBLE);
                MBBsLinearLay.setVisibility(View.GONE);
                CommonDegreeLinearLay.setVisibility(View.GONE);
                TeachingLinearLay.setVisibility(View.GONE);
                OtherLinearlay.setVisibility(View.GONE);
            }
        });


        MBBSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BetechLinearLay.setVisibility(View.GONE);
                MBBsLinearLay.setVisibility(View.VISIBLE);
                CommonDegreeLinearLay.setVisibility(View.GONE);
                TeachingLinearLay.setVisibility(View.GONE);
                OtherLinearlay.setVisibility(View.GONE);

            }
        });


        CommonDegreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BetechLinearLay.setVisibility(View.GONE);
                MBBsLinearLay.setVisibility(View.GONE);
                CommonDegreeLinearLay.setVisibility(View.VISIBLE);
                TeachingLinearLay.setVisibility(View.GONE);
                OtherLinearlay.setVisibility(View.GONE);

            }
        });


        TeachingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(AfterGraduation.this,"Teaching Button has Clicked",Toast.LENGTH_SHORT).show();
                BetechLinearLay.setVisibility(View.GONE);
                MBBsLinearLay.setVisibility(View.GONE);
                CommonDegreeLinearLay.setVisibility(View.GONE);
                TeachingLinearLay.setVisibility(View.VISIBLE);
                OtherLinearlay.setVisibility(View.GONE);

            }
        });


        OtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AfterGraduation.this,"Other Button has Clicked",Toast.LENGTH_SHORT).show();

                BetechLinearLay.setVisibility(View.GONE);
                MBBsLinearLay.setVisibility(View.GONE);
                CommonDegreeLinearLay.setVisibility(View.GONE);
                TeachingLinearLay.setVisibility(View.GONE);
                OtherLinearlay.setVisibility(View.VISIBLE);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_course_after_graduation_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.Coursemenuid:

                startActivity(new Intent(AfterGraduation.this, All_PostGraduate_Course.class ));

                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
