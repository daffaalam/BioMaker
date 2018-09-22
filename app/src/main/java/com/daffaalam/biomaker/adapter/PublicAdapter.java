package com.daffaalam.biomaker.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daffaalam.biomaker.R;
import com.daffaalam.biomaker.activity.DetailActivity;
import com.daffaalam.biomaker.helper.DatabaseHelper;
import com.daffaalam.biomaker.model.ReadPublicModel;

import java.util.List;

public class PublicAdapter extends RecyclerView.Adapter<PublicAdapter.PublicHolder> {

    private final Context context_public;
    private final List<ReadPublicModel> readPublicModelList;
    private Bundle bundle_public;

    public PublicAdapter(Context context_public, List<ReadPublicModel> readPublicModelList) {
        this.context_public = context_public;
        this.readPublicModelList = readPublicModelList;
    }

    @NonNull
    @Override
    public PublicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PublicHolder(LayoutInflater.from(context_public).inflate(R.layout.item_public, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PublicHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tv_name_public_adapter.setText(readPublicModelList.get(position).getName());
        holder.tv_bday_public_adapter.setText(readPublicModelList.get(position).getBday());
        holder.cv_item_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context_public)
                        .setIcon(R.drawable.ic_edit)
                        .setTitle(readPublicModelList.get(position).getName())
                        .setCancelable(false)
                        .setItems(new String[]{"Lihat", "Batal"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bundle_public = new Bundle();
                                bundle_public.putString("FROM", "PUBLIC");
                                bundle_public.putString(DatabaseHelper.COLUMN_ID, readPublicModelList.get(position).getId());
                                bundle_public.putString(DatabaseHelper.COLUMN_NAME, readPublicModelList.get(position).getName());
                                bundle_public.putString(DatabaseHelper.COLUMN_BDAY, readPublicModelList.get(position).getBday());
                                bundle_public.putString(DatabaseHelper.COLUMN_GENDER, readPublicModelList.get(position).getGender());
                                bundle_public.putString(DatabaseHelper.COLUMN_ADDRESS, readPublicModelList.get(position).getAddress());
                                bundle_public.putString(DatabaseHelper.COLUMN_PHONE, readPublicModelList.get(position).getPhone());
                                bundle_public.putString(DatabaseHelper.COLUMN_MAIL, readPublicModelList.get(position).getMail());
                                bundle_public.putString(DatabaseHelper.COLUMN_RELIGION, readPublicModelList.get(position).getReligion());
                                bundle_public.putString(DatabaseHelper.COLUMN_BLOOD, readPublicModelList.get(position).getBlood());
                                bundle_public.putString(DatabaseHelper.COLUMN_WEBSITE, readPublicModelList.get(position).getWebsite());
                                bundle_public.putString(DatabaseHelper.COLUMN_SHOES, readPublicModelList.get(position).getShoes());
                                bundle_public.putString(DatabaseHelper.COLUMN_FACEBOOK, readPublicModelList.get(position).getFacebook());
                                bundle_public.putString(DatabaseHelper.COLUMN_TWITTER, readPublicModelList.get(position).getTwitter());
                                bundle_public.putString(DatabaseHelper.COLUMN_LINE, readPublicModelList.get(position).getLine());
                                bundle_public.putString(DatabaseHelper.COLUMN_TELEGRAM, readPublicModelList.get(position).getTelegram());
                                bundle_public.putString(DatabaseHelper.COLUMN_INSTAGRAM, readPublicModelList.get(position).getInstagram());
                                bundle_public.putString(DatabaseHelper.COLUMN_WHATSAPP, readPublicModelList.get(position).getWhatsapp());
                                switch (which) {
                                    case 0:
                                        context_public.startActivity(new Intent(context_public, DetailActivity.class).putExtras(bundle_public));
                                        break;
                                    case 2:
                                        break;
                                }
                            }
                        }).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return readPublicModelList.size();
    }

    class PublicHolder extends RecyclerView.ViewHolder {

        final CardView cv_item_public;
        final TextView tv_name_public_adapter;
        final TextView tv_bday_public_adapter;

        PublicHolder(View itemView) {
            super(itemView);
            cv_item_public = itemView.findViewById(R.id.cv_item_public);
            tv_name_public_adapter = itemView.findViewById(R.id.tv_name_item_public);
            tv_bday_public_adapter = itemView.findViewById(R.id.tv_bday_item_public);
        }
    }
}
