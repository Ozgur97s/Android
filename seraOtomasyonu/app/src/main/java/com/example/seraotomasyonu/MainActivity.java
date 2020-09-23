package com.example.seraotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button tnemb, hsıcb, hnemb;
    TextView tnemot, tnemd, hsıcot, hsıcd, hnemot, hnemd, eror, süre;
    public long tnemo = 80, hsıc = 33, hnem = 15;

    //textview ve button id tanımlamaları
    private void inc() {
        süre = findViewById(R.id.saniye);
        tnemot = findViewById(R.id.tnemot);
        tnemd = findViewById(R.id.tnemd);
        hsıcot = findViewById(R.id.hsıcot);
        hsıcd = findViewById(R.id.hsıcd);
        hnemot = findViewById(R.id.hnemot);
        hnemd = findViewById(R.id.hnemd);
        eror = findViewById(R.id.eror);
        tnemb = findViewById(R.id.tnemb);
        hsıcb = findViewById(R.id.hsıcb);
        hnemb = findViewById(R.id.hnemb);
    }


    //gerçek değer yakın olması için belli bir sure geçen saniye sonunda olması gereken kurallar
    public void testing() {

        CountDownTimer cdt = new CountDownTimer(10000, 1000) {
            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                long saniye = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                //sanıye 0 oldugunda sureklı başa sarıp tekrardan geriye dogru sayması için
                if (saniye == 0) {
                    testing();
                }
                //tnemot.setText("" + String.format("%d ", tnemo));
                süre.setText("" + String.format("%d", saniye));
                // toprak nem degişkeni zaman gectikce topraktaki nem azalması için if komutları ile kontrol edildi
                if (saniye == 1) tnemo = tnemo - 2;
                if (tnemo < 15) tnemo = 80;
                tnemot.setText("%" + String.format("%d Nem", tnemo));
                //hava sıcaklıgı degişkenleri
                if (saniye == 1) hsıc = hsıc - 1;
                if (hsıc < 20) hsıc = 33;
                hsıcot.setText("" + String.format("%d C° sıcaklık", hsıc));
                //hava nem degişkenleri
                if (saniye == 1) hnem = hnem + 2;
                if (hnem > 60) hnem = 15;
                hnemot.setText("%" + String.format("%d nem", hnem));


                if (saniye == 9) {
                    eror.setText("Sistem eksiksiz çalışıyor");
                    //tnem durum textleri
                    if (tnemo < 26) tnemd.setText("Bitki su ihtiyacı var");
                    else if (tnemo <= 40) tnemd.setText("Bitki su sulana bilir");
                    else if (tnemo <= 60) tnemd.setText("Bitkiler su ihtiyacı yok");
                    else tnemd.setText("Bitki sulanmaması tavsiye edilir");
                    //hsıc durum textleri
                    if (hsıc < 20) hsıcd.setText("Hava sıcaklıgı düşük seviyede");
                    else if (hsıc <= 27) hsıcd.setText("Hava sıcaklığı stabil seviyede");
                    else hsıcd.setText("Hava sıcalığı biraz yüksek seviyede");
                    //hnem durum textleri
                    if (hnem < 30) hnemd.setText("Havalandırma acılmaması tavsiye edilir");
                    else if (hnem < 45) hnemd.setText("Hava nem stabil seviyede");
                    else hnemd.setText("Havalandırma acılması tavsiye edilir");
                }

            }


            public void onFinish() {

            }
        }.start();

    }

    private void işlevver() {
        tnemb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tnemo + 10 > 80) {
                    eror.setText("bitkiler için daha fazla sulama işlemi gerçeklestirilemez");
                } else {
                    tnemd.setText("sulama gerçekleşti");
                    tnemo = tnemo + 10;
                }
            }
        });
        hsıcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hsıc + 4 > 33) {
                    eror.setText("Bitkiler için daha fazla sıcaklık arttırılamaz");
                } else {
                    hsıcd.setText("Isınma işlemi gerçekleşti");
                    hsıc = hsıc + 4;
                }
            }
        });
        hnemb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hnem - 3 < 15) {
                    eror.setText("Havalandırmalar acılamaz");
                } else {
                    hnemd.setText("Havalandırma işlemi gerçekleşti");
                    hnem = hnem - 5;
                }

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inc();
        testing();
        işlevver();
    }
}
