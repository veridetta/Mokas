package com.inc.vr.corp.app.mokas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class SecondFragment extends Fragment {
    View view;
    Button next,prev;
    TextView coba, kategori;
    ShimmerLayout sh_home;
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

    private static final String TAG = "MainActivity";
    private String url, kunci = "",cariVal="";
    Integer halaman = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);

        bebek = view.findViewById(R.id.bebek);
        sport=view.findViewById(R.id.sport);
        metik=view.findViewById(R.id.metik);
        moge=view.findViewById(R.id.moge);
        trail=view.findViewById(R.id.trail);
        classic=view.findViewById(R.id.classic);
        bebek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), produkActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", "bebek");
                startActivity(intent);
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), produkActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", "sport");
                startActivity(intent);
            }
        });
        metik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), produkActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", "metik");
                startActivity(intent);
            }
        });
        moge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), produkActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", "moge");
                startActivity(intent);
            }
        });
        trail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), produkActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", "trail");
                startActivity(intent);
            }
        });
        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), produkActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", "classic");
                startActivity(intent);
            }
        });

        return view;

    }
    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {

        super.onPause();
    }
}