package com.imtiaj.m5education.Fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.imtiaj.m5education.Activities.NewsActivity;
import com.imtiaj.m5education.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class News extends Fragment implements View.OnClickListener {


    private Button NewsBtn;

    private ImageButton AnandaBazar,Bartoman,Protidin,Gonosokti,Kormokeshtro,Eisomoy,Telegraph,Statemans;
    private ImageView EconomicTime,BusinessStandard,Timeofindia,Thehindu,HindustanTime,IndianExpress,AsianAge;
    private ImageView ProthomAlo,BangladesProtidin, Jugantor, Somokal, Noyadigonto, AjkerPotrika, Dinkal, Bangladeserkhobor ;


    public News() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        EconomicTime = (ImageView)view.findViewById(R.id.EconomicTime_imageView);
        BusinessStandard = (ImageView)view.findViewById(R.id.BusinessStandard_imageView);
        Timeofindia = (ImageView)view.findViewById(R.id.TimesOfIndia_imageView);
        Thehindu = (ImageView)view.findViewById(R.id.TheHindu_imageView);
        HindustanTime = (ImageView)view.findViewById(R.id.HindustanTime_imageView);
        IndianExpress = (ImageView)view.findViewById(R.id.IndianExpress_imageView);
        AsianAge = (ImageView)view.findViewById(R.id.AsianAge_imageView);


        AnandaBazar = (ImageButton)view.findViewById(R.id.AnaddaBazar_imageView);
        Bartoman = (ImageButton)view.findViewById(R.id.Bortoman_imageView);
        Protidin = (ImageButton)view.findViewById(R.id.Protidin_imageView);
        Gonosokti = (ImageButton)view.findViewById(R.id.Gonosokti_imageView);
        Kormokeshtro = (ImageButton)view.findViewById(R.id.KarmaKhestra_imageView);
        Eisomoy = (ImageButton)view.findViewById(R.id.EiSomoy_imageView);
        Telegraph = (ImageButton)view.findViewById(R.id.Telegraph_imageView);
        Statemans = (ImageButton)view.findViewById(R.id.Statesman_imageView);

        ProthomAlo = (ImageView)view.findViewById(R.id.ProthomAlo_imageView);
        BangladesProtidin = (ImageView)view.findViewById(R.id.BangladeshProtidin_imageView);
        Jugantor = (ImageView)view.findViewById(R.id.Jugantor_imageView);
        Somokal = (ImageView)view.findViewById(R.id.Somokal_imageView);
        Noyadigonto = (ImageView)view.findViewById(R.id.NoyaDigonto_imageView);
        AjkerPotrika = (ImageView)view.findViewById(R.id.AjkerPotrika_imageView);
        Dinkal = (ImageView)view.findViewById(R.id.Dinkal_imageView);
        Bangladeserkhobor = (ImageView)view.findViewById(R.id.BangladeserKhobor_imageView);




        /*EconomicTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),NewsActivity.class));
            }
        });*/


        EconomicTime.setOnClickListener(this);
        BusinessStandard.setOnClickListener(this);
        Timeofindia.setOnClickListener(this);
        Thehindu.setOnClickListener(this);
        HindustanTime.setOnClickListener(this);
        IndianExpress.setOnClickListener(this);
        AsianAge.setOnClickListener(this);

        AnandaBazar.setOnClickListener(this);
        Bartoman.setOnClickListener(this);
        Protidin.setOnClickListener(this);
        Gonosokti.setOnClickListener(this);
        Kormokeshtro.setOnClickListener(this);
        Eisomoy.setOnClickListener(this);
        Telegraph.setOnClickListener(this);
        Statemans.setOnClickListener(this);


        ProthomAlo.setOnClickListener(this);
        BangladesProtidin.setOnClickListener(this);
        Jugantor.setOnClickListener(this);
        Somokal.setOnClickListener(this);
        Noyadigonto.setOnClickListener(this);
        AjkerPotrika.setOnClickListener(this);
        Dinkal.setOnClickListener(this);
        Bangladeserkhobor.setOnClickListener(this);


        return view;

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.EconomicTime_imageView:
                Intent nEconomicIntent = new Intent(getActivity(), NewsActivity.class);
                nEconomicIntent.putExtra("NewsUrl","https://economictimes.indiatimes.com/");
                startActivity(nEconomicIntent);

                break;
            case R.id.BusinessStandard_imageView:
                Intent nBusinessStandardIntent = new Intent(getActivity(),NewsActivity.class);
                nBusinessStandardIntent.putExtra("NewsUrl","https://www.business-standard.com/");
                startActivity(nBusinessStandardIntent);

                break;
            case R.id.TimesOfIndia_imageView:
                Intent nTimesofIndiaIntent = new Intent(getActivity(),NewsActivity.class);
                nTimesofIndiaIntent.putExtra("NewsUrl","https://timesofindia.indiatimes.com/");
                startActivity(nTimesofIndiaIntent);

                break;
            case R.id.TheHindu_imageView:
                Intent ntheHinduIntent = new Intent(getActivity(),NewsActivity.class);
                ntheHinduIntent.putExtra("NewsUrl","https://www.thehindu.com/");
                startActivity(ntheHinduIntent);

                break;
            case R.id.HindustanTime_imageView:
                Intent nHindustanTimeIntent = new Intent(getActivity(),NewsActivity.class);
                nHindustanTimeIntent.putExtra("NewsUrl","https://www.hindustantimes.com/");
                startActivity(nHindustanTimeIntent);


                break;
            case R.id.IndianExpress_imageView:
                Intent nIndianExpressIntent = new Intent(getActivity(),NewsActivity.class);
                nIndianExpressIntent.putExtra("NewsUrl","https://indianexpress.com/");
                startActivity(nIndianExpressIntent);

                break;
            case R.id.AsianAge_imageView:
                Intent nAsinAgeIntent = new Intent(getActivity(),NewsActivity.class);
                nAsinAgeIntent.putExtra("NewsUrl","http://www.asianage.com/");
                startActivity(nAsinAgeIntent);

                break;

            case R.id.AnaddaBazar_imageView:
                Intent nAnandaBazzarIntent = new Intent(getActivity(),NewsActivity.class);
                nAnandaBazzarIntent.putExtra("NewsUrl","https://www.anandabazar.com/");
                startActivity(nAnandaBazzarIntent);

                break;
            case R.id.Bortoman_imageView:
                Intent nBortomanIntent = new Intent(getActivity(),NewsActivity.class);
                nBortomanIntent.putExtra("NewsUrl","http://bartamanpatrika.com/");
                startActivity(nBortomanIntent);

                break;
            case R.id.Protidin_imageView:
                Intent nProtidinIntent = new Intent(getActivity(),NewsActivity.class);
                nProtidinIntent.putExtra("NewsUrl","https://www.sangbadpratidin.in/");
                startActivity(nProtidinIntent);

                break;
            case R.id.Gonosokti_imageView:
                Intent nGonosoktiIntent = new Intent(getActivity(),NewsActivity.class);
                nGonosoktiIntent.putExtra("NewsUrl","http://bangla.ganashakti.co.in/");
                startActivity(nGonosoktiIntent);

                break;
            case R.id.KarmaKhestra_imageView:
                Intent nKarmakhestroIntent = new Intent(getActivity(),NewsActivity.class);
                nKarmakhestroIntent.putExtra("NewsUrl","https://karmasangsthan.org/karmakshetra/");
                startActivity(nKarmakhestroIntent);

                break;
            case R.id.EiSomoy_imageView:
                Intent nEisomoyIntent = new Intent(getActivity(),NewsActivity.class);
                nEisomoyIntent.putExtra("NewsUrl","https://eisamay.indiatimes.com/");
                startActivity(nEisomoyIntent);

                break;
            case R.id.Telegraph_imageView:
                Intent nTelegraphIntent = new Intent(getActivity(),NewsActivity.class);
                nTelegraphIntent.putExtra("NewsUrl","https://www.telegraphindia.com/");
                startActivity(nTelegraphIntent);

                break;
            case R.id.Statesman_imageView:
                Intent nStatesmanIntent = new Intent(getActivity(),NewsActivity.class);
                nStatesmanIntent.putExtra("NewsUrl","https://www.thestatesman.com/");
                startActivity(nStatesmanIntent);

                break;

            case R.id.ProthomAlo_imageView:
                Intent nProthomAloIntent = new Intent(getActivity(),NewsActivity.class);
                nProthomAloIntent.putExtra("NewsUrl","https://www.prothomalo.com/");
                startActivity(nProthomAloIntent);

                break;
            case R.id.BangladeshProtidin_imageView:
                Intent nBangladeshProtidinIntent = new Intent(getActivity(),NewsActivity.class);
                nBangladeshProtidinIntent.putExtra("NewsUrl","http://www.bd-pratidin.com/");
                startActivity(nBangladeshProtidinIntent);

                break;
            case R.id.Jugantor_imageView:
                Intent nJugantorIntent = new Intent(getActivity(),NewsActivity.class);
                nJugantorIntent.putExtra("NewsUrl","https://www.jugantor.com/");
                startActivity(nJugantorIntent);

                break;
            case R.id.Somokal_imageView:
                Intent nSomokalIntent = new Intent(getActivity(),NewsActivity.class);
                nSomokalIntent.putExtra("NewsUrl","https://samakal.com/");
                startActivity(nSomokalIntent);

                break;
            case R.id.NoyaDigonto_imageView:
                Intent nNoyadigontoIntent = new Intent(getActivity(),NewsActivity.class);
                nNoyadigontoIntent.putExtra("NewsUrl","http://www.dailynayadiganta.com/");
                startActivity(nNoyadigontoIntent);

                break;
            case R.id.AjkerPotrika_imageView:
                Intent nAjkerPotrikaIntent = new Intent(getActivity(),NewsActivity.class);
                nAjkerPotrikaIntent.putExtra("NewsUrl","https://www.ajkerpatrika.com/");
                startActivity(nAjkerPotrikaIntent);

                break;
            case R.id.Dinkal_imageView:
                Intent nDinkalIntent = new Intent(getActivity(),NewsActivity.class);
                nDinkalIntent.putExtra("NewsUrl","http://www.dailydinkal.net/2019/03/24/index.php");
                startActivity(nDinkalIntent);

                break;
            case R.id.BangladeserKhobor_imageView:
                Intent nBangladesherKhoborIntent = new Intent(getActivity(),NewsActivity.class);
                nBangladesherKhoborIntent.putExtra("NewsUrl","http://www.bangladesherkhabor.net/");
                startActivity(nBangladesherKhoborIntent);

                break;

        }
    }

}
