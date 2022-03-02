package com.inc.vr.corp.app.mokas.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inc.vr.corp.app.mokas.DetailActivity;
import com.inc.vr.corp.app.mokas.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;


public class MotorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE = 1;
    private Context context;
    private ArrayList<String> namaList= new ArrayList<>(), hargaList = new ArrayList<>(), kategoriList = new ArrayList<>()
            ,kodeList= new ArrayList<>(), deskripsiList= new ArrayList<>(), kontakList= new ArrayList<>(), stokList= new ArrayList<>(),
    gambarList= new ArrayList<>();

    public MotorAdapter(Context context, ArrayList<String> namaList,ArrayList<String> hargaList, ArrayList<String> kategoriList,
                        ArrayList<String> kodeList,ArrayList<String> deskripsiList, ArrayList<String> kontakList,ArrayList<String>stokList,
                        ArrayList<String>gambarList) {
        this.context = context;
        this.namaList = namaList;
        this.hargaList = hargaList;
        this.kategoriList = kategoriList;
        this.kodeList = kodeList;
        this.deskripsiList = deskripsiList;
        this.kontakList = kontakList;
        this.stokList = stokList;
        this.gambarList=gambarList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView nama,kategori,harga,terjual;
        private ImageView img;
        CardView cardhome;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.nama);
            kategori = (TextView) itemView.findViewById(R.id.kategori);
            harga = (TextView) itemView.findViewById(R.id.txt_harga);
            terjual = itemView.findViewById(R.id.terjual);
            img= itemView.findViewById(R.id.img_home);
            cardhome = itemView.findViewById(R.id.card_home);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.small_content, viewGroup, false);
        return new ItemViewHolder((layoutView));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        //Motor holidays = (Motor) listRecyclerItem.get(i);
        itemViewHolder.nama.setText(namaList.get(i));
        itemViewHolder.kategori.setText(kategoriList.get(i));
        itemViewHolder.harga.setText(FormatBaru(hargaList.get(i)));
        //itemViewHolder.img.glid
        String[] parts = gambarList.get(i).split("vrcorp2021");
        String gambar="http://apps.mokascirebon.com/ajax/image"+parts[1];
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mokaspng)
                .error(R.drawable.mokaspng);
        Glide.with(context).load(gambar).apply(options).into(itemViewHolder.img);
        Integer stokk = Integer.valueOf(stokList.get(i));
        if(stokk>0){

        }else{
            itemViewHolder.terjual.setText("Terjual");
        }
        itemViewHolder.cardhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", namaList.get(i));
                intent.putExtra("harga", hargaList.get(i));
                intent.putExtra("kategori", kategoriList.get(i));
                intent.putExtra("kode_barang", kodeList.get(i));
                intent.putExtra("gambar", gambarList.get(i));
                intent.putExtra("deskripsi", deskripsiList.get(i));
                intent.putExtra("kontak", kontakList.get(i));
                intent.putExtra("stok", stokList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return namaList.size();
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
}
