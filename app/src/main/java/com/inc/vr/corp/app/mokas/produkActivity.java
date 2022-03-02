package com.inc.vr.corp.app.mokas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hhl.gridpagersnaphelper.GridPagerSnapHelper;
import com.inc.vr.corp.app.mokas.adapter.MotorAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class produkActivity extends AppCompatActivity {
    Button next,prev;
    TextView coba, kategori;
    ShimmerLayout sh_home;
    ImageView img_kat;
    LinearLayout sport, bebek, metik, moge, trail, classic;
    RecyclerView mRecyclerView;
    private ArrayList<String> card_nama = new ArrayList<>();
    private ArrayList<String> card_harga = new ArrayList<String>();
    private ArrayList<String> card_kategori = new ArrayList<>();
    private ArrayList<String> card_kode_barang = new ArrayList<>();
    private ArrayList<String> card_stok = new ArrayList<>();
    private ArrayList<String> card_deskripsi = new ArrayList<>();
    private ArrayList<String> card_kontak = new ArrayList<>();
    private ArrayList<String> card_gambar = new ArrayList<>();
    private ActionBar toolbar;
    private static final String TAG = "MainActivity";
    private String url, kunci = "",cariVal="";
    Integer halaman = 0;
    Intent intent;
    String katagori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);
        toolbar = getSupportActionBar();
        //toolbar.setTitle("Home");
        toolbar.hide();
        coba = findViewById(R.id.coba);
        mRecyclerView = findViewById(R.id.rc_home);
        mRecyclerView.setHasFixedSize(true);
        sh_home = findViewById(R.id.shimmer_recent_update);
        next = findViewById(R.id.btn_next);
        prev= findViewById(R.id.btn_prev);
        kategori = findViewById(R.id.kategori_text);
        intent = getIntent();
        Bundle b = intent.getExtras();
        katagori = b.getString("nama");
        kategori.setText("Menampilkan kategori "+katagori);
        img_kat = findViewById(R.id.img_kat);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mokaspng)
                .error(R.drawable.mokaspng);
        switch(katagori){
            case "bebek":
                Glide.with(this).load(R.drawable.bebek).apply(options).into(img_kat);
            case "sport":
                Glide.with(this).load(R.drawable.sport).apply(options).into(img_kat);
            case "metik":
                Glide.with(this).load(R.drawable.metik).apply(options).into(img_kat);
            case "moge":
                Glide.with(this).load(R.drawable.moge).apply(options).into(img_kat);
            case "trail":
                Glide.with(this).load(R.drawable.trail).apply(options).into(img_kat);
            case "classic":
                Glide.with(this).load(R.drawable.classic).apply(options).into(img_kat);
            default:
                Glide.with(this).load(R.drawable.mokaspng).apply(options).into(img_kat);
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer tambah=halaman+10;
                getSaldoList(cariVal,tambah);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(halaman<1){
                    Toast.makeText(produkActivity.this,"Tidak ada hasil",Toast.LENGTH_LONG).show();
                }else{
                    Integer tambah=halaman-10;
                    getSaldoList(cariVal,tambah);
                }
            }
        });
        getSaldoList(katagori,halaman);
    }
    private void getSaldoList(String kun, Integer hal) {
        sh_home.startShimmerAnimation();
        sh_home.setVisibility(View.VISIBLE);
        url = "http://apps.mokascirebon.com/api/get2.php?draw=1&" +
                "columns%5B0%5D%5Bdata%5D=0&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=true&columns%5B0%5D%5Borderable%5D=true&" +
                "columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B1%5D%5Bdata%5D=1&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&" +
                "columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B2%5D%5Bdata%5D=2&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&" +
                "columns%5B2%5D%5Bsearch%5D%5Bvalue%5D="+kun+"&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B3%5D%5Bdata%5D=3&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&" +
                "columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B4%5D%5Bdata%5D=4&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=true&" +
                "columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B5%5D%5Bdata%5D=5&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&" +
                "columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "order%5B0%5D%5Bcolumn%5D=0&order%5B0%5D%5Bdir%5D=asc&start="+hal+"&length=10&search%5Bvalue%5D=&search%5Bregex%5D=false&_=1619202758689";
        RequestQueue requestQueue = Volley.newRequestQueue(produkActivity.this);
        Log.wtf("URL Called", url + "");
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, response -> {
            //Log.e(MainActivity.class.getSimpleName(), "Auth Response: " +url+ response);
            Log.wtf("URL Called", response + "");
            try {
                JSONObject jsonObject = new JSONObject(response);
                String dataa =jsonObject.getString("count");
                if (dataa.equals("no-data")) {
                    sh_home.stopShimmerAnimation();
                    sh_home.setVisibility(View.GONE);
                    Toast.makeText(produkActivity.this,"Tidak ada hasil",Toast.LENGTH_LONG).show();
                    Log.wtf("URL Called", response + "gada data");
                } else {
                    JSONArray jArray = jsonObject.getJSONArray("data");
                    card_nama.clear();
                    card_harga.clear();
                    card_kategori.clear();
                    card_kode_barang.clear();
                    card_deskripsi.clear();
                    card_kontak.clear();
                    card_stok.clear();
                    card_gambar.clear();
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jsonObject1 = jArray.getJSONObject(i);
                        String tnama = jsonObject1.getString("nama");
                        String tkode = jsonObject1.getString("kode_barang");
                        String tharga = jsonObject1.getString("harga");
                        String tkategori = jsonObject1.getString("kategori");
                        String tdeskripsi = jsonObject1.getString("deskripsi");
                        String tkontak = jsonObject1.getString("kontak");
                        String tstok = jsonObject1.getString("stok");
                        String tgambar = jsonObject1.getString("gambar");
                        //Motor holidays = new Motor(tnama,tharga,tkategori);
                        //viewItems.add(holidays);
                        card_nama.add(tnama);
                        card_harga.add(tharga);
                        card_kategori.add(tkategori);
                        card_kode_barang.add(tkode);
                        card_deskripsi.add(tdeskripsi);
                        card_kontak.add(tkontak);
                        card_stok.add(tstok);
                        card_gambar.add(tgambar);
                    }
                    MotorAdapter mDataAdapter = new MotorAdapter(produkActivity.this, card_nama, card_harga, card_kategori, card_kode_barang,card_deskripsi,card_kontak,card_stok,card_gambar );
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(produkActivity.this, 2, LinearLayoutManager.VERTICAL, false);
                    //attachToRecyclerView
                    GridPagerSnapHelper gridPagerSnapHelper = new GridPagerSnapHelper();
                    gridPagerSnapHelper.setRow(5).setColumn(2);
                    mRecyclerView.setOnFlingListener(null);
                    gridPagerSnapHelper.attachToRecyclerView(mRecyclerView);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mDataAdapter);
                    sh_home.stopShimmerAnimation();
                    sh_home.setVisibility(View.GONE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
            Log.i(TAG, "Error :" + error.toString());
            //sh_home.stopShimmerAnimation();
            //finish();
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
        Log.wtf("URL nama", card_nama.toString() + "");

    }
}