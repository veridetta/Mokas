package com.inc.vr.corp.app.mokas;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhl.gridpagersnaphelper.GridPagerSnapHelper;
import com.inc.vr.corp.app.mokas.adapter.MotorAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class FirstFragment extends Fragment {
    View view;
    Button next,prev;
    TextView coba;
    ShimmerLayout sh_home;
    SearchView cari;
    RecyclerView mRecyclerView;
    private ArrayList<String> card_nama = new ArrayList<>();
    private ArrayList<String> card_harga = new ArrayList<String>();
    private ArrayList<String> card_kategori = new ArrayList<>();
    private ArrayList<String> card_kode_barang = new ArrayList<>();
    private ArrayList<String> card_stok = new ArrayList<>();
    private ArrayList<String> card_deskripsi = new ArrayList<>();
    private ArrayList<String> card_kontak = new ArrayList<>();
    private ArrayList<String> card_gambar = new ArrayList<>();

    private static final String TAG = "MainActivity";
    private String url, kunci = "",cariVal="";
    Integer halaman = 0;
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);
        cari = view.findViewById(R.id.cari_input);
        coba = view.findViewById(R.id.coba);
        mRecyclerView = view.findViewById(R.id.rc_home);
        mRecyclerView.setHasFixedSize(true);
        sh_home = view.findViewById(R.id.shimmer_recent_update);
        next = view.findViewById(R.id.btn_next);
        prev= view.findViewById(R.id.btn_prev);
        cari.setQueryHint("Masukan Kata Kunci");
        cari.onActionViewExpanded();
        cari.setIconified(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cari.clearFocus();
            }
        }, 300);
        cari.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cariVal = cari.getQuery().toString();
                if (cariVal.length() < 3) {
                    Toast.makeText(getContext(), "Minimal kata minimal 3 huruf", Toast.LENGTH_LONG).show();
                } else if (cariVal.length() > 10) {
                    Toast.makeText(getContext(), "Maksimal 10 huruf", Toast.LENGTH_LONG).show();
                } else {
                    //Intent intent = new Intent(getApplicationContext(), LaunchActivity.class);
                    //intent.putExtra("url", urlnya);
                    //intent.putExtra("judul", "Hasil cari dari kata " + cariVal);
                    //startActivity(intent);
                    getSaldoList(cariVal,0);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
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
                    Toast.makeText(getActivity(),"Tidak ada hasil",Toast.LENGTH_LONG).show();
                }else{
                    Integer tambah=halaman-10;
                    getSaldoList(cariVal,tambah);
                }
            }
        });
        getSaldoList("",halaman);
        return view;
    }
    private void getSaldoList(String kun, Integer hal) {
        sh_home.startShimmerAnimation();
        sh_home.setVisibility(View.VISIBLE);
        url = "http://apps.mokascirebon.com/api/get2.php?draw=1&" +
                "columns%5B0%5D%5Bdata%5D=0&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=true&columns%5B0%5D%5Borderable%5D=true&" +
                "columns%5B0%5D%5Bsearch%5D%5Bvalue%5D="+kun+"&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B1%5D%5Bdata%5D=1&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&" +
                "columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B2%5D%5Bdata%5D=2&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&" +
                "columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B3%5D%5Bdata%5D=3&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&" +
                "columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B4%5D%5Bdata%5D=4&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=true&" +
                "columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "columns%5B5%5D%5Bdata%5D=5&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&" +
                "columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&" +
                "order%5B0%5D%5Bcolumn%5D=0&order%5B0%5D%5Bdir%5D=asc&start="+hal+"&length=10&search%5Bvalue%5D=&search%5Bregex%5D=false&_=1619202758689";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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
                    Toast.makeText(getActivity(),"Tidak ada hasil",Toast.LENGTH_LONG).show();
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
                    Log.wtf("URL nama", card_nama.toString() + "");
                    MotorAdapter mDataAdapter = new MotorAdapter(getContext(), card_nama, card_harga, card_kategori, card_kode_barang,card_deskripsi,card_kontak,card_stok,card_gambar );
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
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
    }
    @Override
    public void onResume() {
        super.onResume();
        sh_home.startShimmerAnimation();

    }

    @Override
    public void onPause() {
        sh_home.stopShimmerAnimation();
        super.onPause();
    }
}