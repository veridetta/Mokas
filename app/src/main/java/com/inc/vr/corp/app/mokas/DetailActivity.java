package com.inc.vr.corp.app.mokas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.inc.vr.corp.app.mokas.adapter.GambarAdapter;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private ActionBar toolbar;
    ViewPager viewPager;
    GambarAdapter gambarAdapter;
    Intent intent;
    LinearLayout shareLY;
    String nama, harga, kategori, deskripsi, kontak, stok, gambar, kode_barang;
    String[] part_gambar;
    TextView txt_nama, txt_deskripsi, txt_harga, txt_kategori;
    Button phone, wa;
    private ArrayList<String> card_gambar = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = getSupportActionBar();
        //toolbar.setTitle("Home");
        toolbar.hide();

        intent = getIntent();
        Bundle b = intent.getExtras();
        nama = b.getString("nama");
        harga = b.getString("harga");
        kategori = b.getString("kategori");
        deskripsi=b.getString("deskripsi");
        kode_barang = b.getString("kode_barang");
        stok = b.getString("stok");
        kontak=b.getString("kontak");
        gambar=b.getString("gambar");
        part_gambar = gambar.split("vrcorp2021");
        txt_nama = findViewById(R.id.txt_nama);
        txt_nama.setText(nama);
        txt_deskripsi=findViewById(R.id.txt_deskripsi);
        txt_deskripsi.setText(deskripsi);
        txt_harga=findViewById(R.id.txt_harga);
        txt_harga.setText(FormatBaru(harga));
        txt_kategori=findViewById(R.id.kategori);
        txt_kategori.setText(kategori);
        phone=findViewById(R.id.btn_phone);
        wa= findViewById(R.id.btn_wa);
        shareLY = findViewById(R.id.isi);
        wa.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      openWhatsApp(kontak, nama, kode_barang);
                                  }
                              });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhoneNumber(kontak);
            }
        });
        for(int o=0;o<part_gambar.length;o++){
            if(o<1){

            }else{
                card_gambar.add(part_gambar[o]);
            }
        }
        gambarAdapter = new GambarAdapter(getApplicationContext(), card_gambar);
        viewPager =  findViewById(R.id.viewPagerBanner);
        viewPager.setAdapter(gambarAdapter);
    }
    public String FormatBaru(String duit){
        String[] debitArray = duit.split("");
        String debitFinal="";
        Integer hd=0;
        for(int d=0;d<debitArray.length;d++){
            debitFinal+= debitArray[debitArray.length-d-1];
            if(hd==2){
                if(debitArray.length-d-1==0){

                }else{
                    debitFinal+= ".";
                    hd=0;
                }
            }else{
                hd++;
            }

        }
        Log.d("DEBIT FINAL", "onBindViewHolder: "+debitFinal);
        return "Rp. "+new StringBuilder(debitFinal).reverse().toString();
    }
    public void callPhoneNumber(String konn)
    {
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(DetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + konn));
                startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + konn));
                startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = getIntent();
                Bundle b = intent.getExtras();
                String non = b.getString("kontak");
                callPhoneNumber(non);
            }
            else
            {
                Log.e("Phone", "Permission not Granted");
            }
        }
    }
    private void openWhatsApp(String nomor, String nama, String kode_barang) {
        String smsNumber = "628817769047"; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Saya tertarik" +
                "dengan "+nama+"\n kode produk: "+kode_barang);
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n" , Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);
    }
}