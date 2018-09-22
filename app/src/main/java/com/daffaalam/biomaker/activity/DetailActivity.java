package com.daffaalam.biomaker.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.daffaalam.biomaker.R;
import com.daffaalam.biomaker.function.AllFunction;
import com.daffaalam.biomaker.helper.DatabaseHelper;
import com.daffaalam.biomaker.model.AddEditModel;
import com.daffaalam.biomaker.model.ShareModel;
import com.daffaalam.biomaker.network.ClientAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AllFunction {

    private String s_id;
    private String s_name;
    private String s_bday;
    private String s_address;
    private String s_phone;
    private String s_mail;
    private String s_website;
    private String s_shoes;
    private String s_facebook;
    private String s_twitter;
    private String s_line;
    private String s_telegram;
    private String s_instagram;
    private String s_whatsapp;
    private String s_gender;
    private String s_blood;
    private String s_religion;

    private List<AddEditModel> addEditModelList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv_name_detail = findViewById(R.id.tv_name_detail);
        TextView tv_bday_detail = findViewById(R.id.tv_birth_detail);
        TextView tv_gender_detail = findViewById(R.id.tv_gender_detail);
        TextView tv_address_detail = findViewById(R.id.tv_address_detail);
        TextView tv_phone_detail = findViewById(R.id.tv_phone_detail);
        TextView tv_mail_detail = findViewById(R.id.tv_email_detail);
        TextView tv_religion_detail = findViewById(R.id.tv_religion_detail);
        TextView tv_blood_detail = findViewById(R.id.tv_blood_detail);
        TextView tv_website_detail = findViewById(R.id.tv_website_detail);
        TextView tv_shoes_detail = findViewById(R.id.tv_shoes_detail);

        s_id = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_ID);
        s_name = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_NAME);
        s_bday = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_BDAY);
        s_gender = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_GENDER);
        s_address = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_ADDRESS);
        s_phone = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_PHONE);
        s_mail = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_MAIL);
        s_religion = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_RELIGION);
        s_blood = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_BLOOD);
        s_website = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_WEBSITE);
        s_shoes = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_SHOES);
        s_facebook = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_FACEBOOK);
        s_twitter = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_TWITTER);
        s_line = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_LINE);
        s_telegram = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_TELEGRAM);
        s_instagram = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_INSTAGRAM);
        s_whatsapp = Objects.requireNonNull(getIntent().getExtras()).getString(DatabaseHelper.COLUMN_WHATSAPP);

        tv_name_detail.setText(s_name);
        tv_bday_detail.setText(s_bday);
        tv_gender_detail.setText(s_gender);
        tv_address_detail.setText(s_address);
        tv_phone_detail.setText("+" + s_phone);
        tv_mail_detail.setText(s_mail);
        tv_religion_detail.setText(s_religion);
        tv_blood_detail.setText(s_blood);
        tv_website_detail.setText(s_website);
        tv_shoes_detail.setText(s_shoes);

        addEditModelList = new ArrayList<>();
        addEditModelList.clear();

        setTitle(s_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).getString("FROM"))) {
            case "HOME":
                getMenuInflater().inflate(R.menu.menu_share, menu);
                break;
            case "PUBLIC":
                getMenuInflater().inflate(R.menu.menu_download, menu);
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                if (s_name.contains("\'") || s_name.contains("\"")) {
                    MyToast("tidak boleh menggunakan \' atau \"");
                } else {
                    new ClientAPI().getEndPoint().sharePublicBio(
                            s_id,
                            s_name,
                            s_bday,
                            s_gender,
                            s_address,
                            s_phone,
                            s_mail,
                            s_religion,
                            s_blood,
                            s_website,
                            s_shoes,
                            s_facebook,
                            s_twitter,
                            s_line,
                            s_telegram,
                            s_instagram,
                            s_whatsapp
                    ).enqueue(new Callback<ShareModel>() {
                        @Override
                        public void onResponse(Call<ShareModel> call, Response<ShareModel> response) {
                            if (response.isSuccessful()) {
                                switch (Objects.requireNonNull(response.body()).getPesan()) {
                                    case "1":
                                        MyToast("berhasil mengirim ke publik");
                                        break;
                                    case "0":
                                        MyToast("gagal terkirim ke publik");
                                        break;
                                }
                            } else {
                                MyToast("gagal: " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<ShareModel> call, Throwable t) {
                            MyToast("gagal: " + t.getMessage());
                        }
                    });
                    break;
                }
            case R.id.menu_download:
                if (!s_id.isEmpty()) {
                    findData(s_id);
                    if (addEditModelList.isEmpty()) {
                        new DatabaseHelper(this).createData(new AddEditModel(
                                s_id,
                                s_name,
                                s_bday,
                                s_gender,
                                s_address,
                                s_phone,
                                s_mail,
                                s_religion,
                                s_blood,
                                s_website,
                                s_shoes,
                                s_facebook,
                                s_twitter,
                                s_line,
                                s_telegram,
                                s_instagram,
                                s_whatsapp
                        ));
                        MyToast("berhasil mengunduh data");
                    }
                } else {
                    MyToast("gagal mendapatkan data");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findData(String id) {
        for (AddEditModel data : new DatabaseHelper(this).readDetailData(id)) {
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
    }

    public void sms(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW).setType("vnd.android-dir/mms-sms").putExtra("address", "+" + s_phone));
    }

    public void call(View view) {
        MyIntentView(Uri.parse("tel:+" + s_phone));
    }

    public void facebook(View view) {
        MyIntentView(Uri.parse(s_facebook));
    }

    public void twitter(View view) {
        MyIntentView(Uri.parse(s_twitter));
    }

    public void line(View view) {
        if (s_line.equals(getResources().getString(R.string.text_line))) {
            MyIntentView(Uri.parse("https://line.me/"));
        } else {
            MyIntentView(Uri.parse(s_line));
        }
    }

    public void telegram(View view) {
        MyIntentView(Uri.parse(s_telegram));
    }

    public void instagram(View view) {
        MyIntentView(Uri.parse(s_instagram));
    }

    public void whatsapp(View view) {
        MyIntentView(Uri.parse(s_whatsapp));
    }
}
