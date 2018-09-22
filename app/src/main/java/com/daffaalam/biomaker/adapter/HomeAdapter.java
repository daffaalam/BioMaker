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
import com.daffaalam.biomaker.activity.AddEditActivity;
import com.daffaalam.biomaker.activity.DetailActivity;
import com.daffaalam.biomaker.helper.DatabaseHelper;
import com.daffaalam.biomaker.model.AddEditModel;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private final Context context_home;
    private final List<AddEditModel> addEditModelList;
    private Bundle bundle_home;

    public HomeAdapter(Context context_home, List<AddEditModel> addEditModelList) {
        this.context_home = context_home;
        this.addEditModelList = addEditModelList;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeHolder(LayoutInflater.from(context_home).inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tv_name_home_adapter.setText(addEditModelList.get(position).getNameModel());
        holder.tv_bday_home_adapter.setText(addEditModelList.get(position).getBdayModel());
        holder.cv_item_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context_home)
                        .setIcon(R.drawable.ic_edit)
                        .setTitle(addEditModelList.get(position).getNameModel())
                        .setCancelable(false)
                        .setItems(new String[]{"Lihat", "Edit/Hapus", "Batal"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bundle_home = new Bundle();
                                bundle_home.putString("FROM", "HOME");
                                bundle_home.putString(DatabaseHelper.COLUMN_ID, addEditModelList.get(position).getIdModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_NAME, addEditModelList.get(position).getNameModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_BDAY, addEditModelList.get(position).getBdayModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_GENDER, addEditModelList.get(position).getGenderModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_ADDRESS, addEditModelList.get(position).getAddressModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_PHONE, addEditModelList.get(position).getPhoneModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_MAIL, addEditModelList.get(position).getMailModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_RELIGION, addEditModelList.get(position).getReligionModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_BLOOD, addEditModelList.get(position).getBloodModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_WEBSITE, addEditModelList.get(position).getWebsiteModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_SHOES, addEditModelList.get(position).getShoesModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_FACEBOOK, addEditModelList.get(position).getFacebookModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_TWITTER, addEditModelList.get(position).getTwitterModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_LINE, addEditModelList.get(position).getLineModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_TELEGRAM, addEditModelList.get(position).getTelegramModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_INSTAGRAM, addEditModelList.get(position).getInstagramModel());
                                bundle_home.putString(DatabaseHelper.COLUMN_WHATSAPP, addEditModelList.get(position).getWhatsappModel());
                                switch (which) {
                                    case 0:
                                        context_home.startActivity(new Intent(context_home, DetailActivity.class).putExtras(bundle_home));
                                        break;
                                    case 1:
                                        context_home.startActivity(new Intent(context_home, AddEditActivity.class).putExtras(bundle_home));
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
        return addEditModelList.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {

        final CardView cv_item_home;
        final TextView tv_name_home_adapter;
        final TextView tv_bday_home_adapter;

        HomeHolder(View itemView) {
            super(itemView);
            cv_item_home = itemView.findViewById(R.id.cv_item_home);
            tv_name_home_adapter = itemView.findViewById(R.id.tv_name_item_home);
            tv_bday_home_adapter = itemView.findViewById(R.id.tv_bday_item_home);
        }
    }
}
