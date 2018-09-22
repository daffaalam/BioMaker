package com.daffaalam.biomaker.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daffaalam.biomaker.R;
import com.daffaalam.biomaker.adapter.HomeAdapter;
import com.daffaalam.biomaker.function.AllFunction;
import com.daffaalam.biomaker.helper.DatabaseHelper;
import com.daffaalam.biomaker.model.AddEditModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AllFunction {

    private RecyclerView rv_home;
    private List<AddEditModel> addEditModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.fab_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntentClass(AddEditActivity.class);
            }
        });

        rv_home = findViewById(R.id.rv_home);
        addEditModelList = new ArrayList<>();
        addEditModelList.clear();
        readAllData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addEditModelList.clear();
        readAllData();
    }

    private void readAllData() {

        rv_home.setHasFixedSize(true);
        rv_home.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_home.setAdapter(new HomeAdapter(this, addEditModelList));

        for (AddEditModel data : new DatabaseHelper(this).readAllData()) {
            AddEditModel addEditModel = new AddEditModel();
            addEditModel.setIdModel(data.getIdModel());
            addEditModel.setNameModel(data.getNameModel());
            addEditModel.setBdayModel(data.getBdayModel());
            addEditModel.setGenderModel(data.getGenderModel());
            addEditModel.setAddressModel(data.getAddressModel());
            addEditModel.setPhoneModel(data.getPhoneModel());
            addEditModel.setMailModel(data.getMailModel());
            addEditModel.setReligionModel(data.getReligionModel());
            addEditModel.setBloodModel(data.getBloodModel());
            addEditModel.setWebsiteModel(data.getWebsiteModel());
            addEditModel.setShoesModel(data.getShoesModel());
            addEditModel.setFacebookModel(data.getFacebookModel());
            addEditModel.setTwitterModel(data.getTwitterModel());
            addEditModel.setLineModel(data.getLineModel());
            addEditModel.setTelegramModel(data.getTelegramModel());
            addEditModel.setInstagramModel(data.getInstagramModel());
            addEditModel.setWhatsappModel(data.getWhatsappModel());
            addEditModelList.add(addEditModel);
        }

        if (addEditModelList.isEmpty()) {
            findViewById(R.id.tv_home).setVisibility(View.VISIBLE);
            MyToast(String.valueOf(getResources().getText(R.string.nodata)));
        } else {
            findViewById(R.id.tv_home).setVisibility(View.GONE);
        }
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
                break;
            case R.id.menu_public:
                MyIntentClass(PublicActivity.class);
                finish();
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
}
