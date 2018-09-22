package com.daffaalam.biomaker.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daffaalam.biomaker.R;
import com.daffaalam.biomaker.adapter.PublicAdapter;
import com.daffaalam.biomaker.function.AllFunction;
import com.daffaalam.biomaker.model.ReadPublicModel;
import com.daffaalam.biomaker.network.ClientAPI;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicActivity extends AllFunction {

    private SwipeRefreshLayout sr_public;
    private RecyclerView rv_public;
    private NetworkInfo info;
    private boolean internet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);

        rv_public = findViewById(R.id.rv_public);
        rv_public.setHasFixedSize(true);
        rv_public.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        sr_public = findViewById(R.id.srl_public);
        sr_public.setRefreshing(true);
        sr_public.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (checkConnectivity()) {
                    readPublicData();
                } else {
                    DialogNotConnect();
                }
            }
        });

        if (checkConnectivity()) {
            readPublicData();
        } else {
            DialogNotConnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkConnectivity()) {
            readPublicData();
        } else {
            DialogNotConnect();
        }
    }

    private void readPublicData() {
        findViewById(R.id.tv_public).setVisibility(View.GONE);
        findViewById(R.id.rv_public).setVisibility(View.VISIBLE);
        new ClientAPI().getEndPoint().readPublicBio().enqueue(new Callback<List<ReadPublicModel>>() {
            @Override
            public void onResponse(Call<List<ReadPublicModel>> call, Response<List<ReadPublicModel>> response) {
                sr_public.setRefreshing(false);
                if (response.isSuccessful()) {
                    rv_public.setAdapter(new PublicAdapter(PublicActivity.this, response.body()));
                } else {
                    findViewById(R.id.tv_public).setVisibility(View.VISIBLE);
                    findViewById(R.id.rv_public).setVisibility(View.INVISIBLE);
                    MyToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ReadPublicModel>> call, Throwable t) {
                sr_public.setRefreshing(false);
                findViewById(R.id.tv_public).setVisibility(View.VISIBLE);
                findViewById(R.id.rv_public).setVisibility(View.INVISIBLE);
                MyToast(t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_public, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                MyIntentClass(HomeActivity.class);
                finish();
                break;
            case R.id.menu_public:
                break;
            case R.id.menu_about:
                DialogAboutMe();
                break;
            case R.id.menu_exit:
                DialogExitApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkConnectivity() {
        info = ((ConnectivityManager) Objects.requireNonNull(this.getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();
        internet = (info != null && info.isConnected() && info.isAvailable());
        return internet;
    }

    private void DialogNotConnect() {
        sr_public.setRefreshing(false);
        new AlertDialog.Builder(this).setTitle("WARNING!!!").setMessage("NO INTERNET CONNECTION").create().show();
    }
}
