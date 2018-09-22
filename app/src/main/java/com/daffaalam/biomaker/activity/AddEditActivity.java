package com.daffaalam.biomaker.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.daffaalam.biomaker.R;
import com.daffaalam.biomaker.function.AllFunction;
import com.daffaalam.biomaker.helper.DatabaseHelper;
import com.daffaalam.biomaker.model.AddEditModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddEditActivity extends AllFunction {

    private EditText et_id_ae;
    private TextInputEditText tiet_name_ae;
    private TextInputEditText tiet_bday_ae;
    private TextInputEditText tiet_address_ae;
    private TextInputEditText tiet_phone_ae;
    private TextInputEditText tiet_mail_ae;
    private TextInputEditText tiet_website_ae;
    private TextInputEditText tiet_shoes_ae;
    private TextInputEditText tiet_facebook_ae;
    private TextInputEditText tiet_twitter_ae;
    private TextInputEditText tiet_line_ae;
    private TextInputEditText tiet_telegram_ae;
    private TextInputEditText tiet_instagram_ae;
    private RadioGroup rg_gender_ae;
    private RadioGroup rg_blood_ae;
    private RadioButton rb_gender_man_ae;
    private RadioButton rb_gender_woman_ae;
    private RadioButton rb_blood_a_ae;
    private RadioButton rb_blood_b_ae;
    private RadioButton rb_blood_ab_ae;
    private RadioButton rb_blood_o_ae;
    private Spinner spin_religion_ae;
    private FloatingActionButton fab_clear_ae;
    private FloatingActionButton fab_delete_ae;
    private FloatingActionButton fab_edit_ae;
    private FloatingActionButton fab_new_ae;

    private String s_hinterror;
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
    private Pattern pathPhone;
    private Pattern pathFacebook;
    private Pattern pathTwitter;
    private Pattern pathLine;
    private Pattern pathTelegram;
    private Pattern pathInstagram;

    private Calendar calendar;

    private DatabaseHelper databaseHelper;

    private boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        et_id_ae = findViewById(R.id.et_id_ae);
        tiet_name_ae = findViewById(R.id.tiet_name_ae);
        tiet_bday_ae = findViewById(R.id.tiet_bday_ae);
        tiet_address_ae = findViewById(R.id.tiet_address_ae);
        tiet_phone_ae = findViewById(R.id.tiet_phone_ae);
        tiet_mail_ae = findViewById(R.id.tiet_email_ae);
        tiet_website_ae = findViewById(R.id.tiet_website_ae);
        tiet_shoes_ae = findViewById(R.id.tiet_shoes_ae);
        tiet_facebook_ae = findViewById(R.id.tiet_facebook_ae);
        tiet_twitter_ae = findViewById(R.id.tiet_twitter_ae);
        tiet_line_ae = findViewById(R.id.tiet_line_ae);
        tiet_telegram_ae = findViewById(R.id.tiet_telegram_ae);
        tiet_instagram_ae = findViewById(R.id.tiet_instagram_ae);
        rg_gender_ae = findViewById(R.id.rg_gender_ae);
        rg_blood_ae = findViewById(R.id.rg_blood_ae);
        rb_gender_man_ae = findViewById(R.id.rb_gender_man_ae);
        rb_gender_woman_ae = findViewById(R.id.rb_gender_woman_ae);
        rb_blood_a_ae = findViewById(R.id.rb_blood_a_ae);
        rb_blood_b_ae = findViewById(R.id.rb_blood_b_ae);
        rb_blood_ab_ae = findViewById(R.id.rb_blood_ab_ae);
        rb_blood_o_ae = findViewById(R.id.rb_blood_o_ae);
        spin_religion_ae = findViewById(R.id.spin_religion_ae);
        fab_clear_ae = findViewById(R.id.fab_clear_ae);
        fab_delete_ae = findViewById(R.id.fab_delete_ae);
        fab_edit_ae = findViewById(R.id.fab_edit_ae);
        fab_new_ae = findViewById(R.id.fab_new_ae);

        getEditData();

        databaseHelper = new DatabaseHelper(this);

        s_hinterror = "tidak boleh kosong.";

        pathPhone = Pattern.compile("([0+]).*");
        pathFacebook = Pattern.compile("(http|https)://(facebook.com|fb.me)(/|).*");
        pathTwitter = Pattern.compile("(http|https)://twitter.com(/|).*");
        pathLine = Pattern.compile("(http|https)://line.me(/ti/p/~|/|).*");
        pathTelegram = Pattern.compile("(http|https)://(t.me|telegram.me)(/|).*");
        pathInstagram = Pattern.compile("(http|https)://instagram.com(/|).*");

        tiet_shoes_ae.setText(String.valueOf(40));
        tiet_bday_ae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        findViewById(R.id.fab_cancel_ae).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab_clear_ae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });
        fab_delete_ae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInput();
            }
        });
        fab_edit_ae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator();
                if (valid) {
                    editInput();
                    finish();
                }
            }
        });
        fab_new_ae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator();
                if (valid) {
                    inputData();
                    finish();
                }
            }
        });
    }

    private void getStringAll() {
        s_name = tiet_name_ae.getText().toString();
        s_bday = tiet_bday_ae.getText().toString();
        s_address = tiet_address_ae.getText().toString();
        s_phone = tiet_phone_ae.getText().toString();
        s_mail = tiet_mail_ae.getText().toString();
        s_religion = spin_religion_ae.getSelectedItem().toString();
        s_website = tiet_website_ae.getText().toString();
        s_shoes = tiet_shoes_ae.getText().toString();
        s_facebook = tiet_facebook_ae.getText().toString();
        s_twitter = tiet_twitter_ae.getText().toString();
        s_line = tiet_line_ae.getText().toString();
        s_telegram = tiet_telegram_ae.getText().toString();
        s_instagram = tiet_instagram_ae.getText().toString();
        s_whatsapp = getResources().getString(R.string.text_whatsapp) + s_phone;
    }

    private void getEditData() {

        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            fab_delete_ae.setVisibility(View.GONE);
            fab_edit_ae.setVisibility(View.GONE);
        } else {
            fab_clear_ae.setVisibility(View.GONE);
            fab_new_ae.setVisibility(View.GONE);
            et_id_ae.setText(bundle.getString(DatabaseHelper.COLUMN_ID));
            tiet_name_ae.setText(bundle.getString(DatabaseHelper.COLUMN_NAME));
            tiet_bday_ae.setText(bundle.getString(DatabaseHelper.COLUMN_BDAY));
            tiet_address_ae.setText(bundle.getString(DatabaseHelper.COLUMN_ADDRESS));
            tiet_phone_ae.setText(bundle.getString(DatabaseHelper.COLUMN_PHONE));
            tiet_mail_ae.setText(bundle.getString(DatabaseHelper.COLUMN_MAIL));
            tiet_website_ae.setText(bundle.getString(DatabaseHelper.COLUMN_WEBSITE));
            tiet_shoes_ae.setText(bundle.getString(DatabaseHelper.COLUMN_SHOES));
            tiet_facebook_ae.setText(bundle.getString(DatabaseHelper.COLUMN_FACEBOOK));
            tiet_twitter_ae.setText(bundle.getString(DatabaseHelper.COLUMN_TWITTER));
            tiet_line_ae.setText(bundle.getString(DatabaseHelper.COLUMN_LINE));
            tiet_telegram_ae.setText(bundle.getString(DatabaseHelper.COLUMN_TELEGRAM));
            tiet_instagram_ae.setText(bundle.getString(DatabaseHelper.COLUMN_INSTAGRAM));

            switch (Objects.requireNonNull(bundle.getString(DatabaseHelper.COLUMN_GENDER))) {
                case "Perempuan":
                    rb_gender_woman_ae.setChecked(true);
                    break;
                case "Laki-Laki":
                    rb_gender_man_ae.setChecked(true);
                    break;
            }

            switch (Objects.requireNonNull(bundle.getString(DatabaseHelper.COLUMN_RELIGION))) {
                case "Islam":
                    spin_religion_ae.setSelection(1);
                    break;
                case "Protestan":
                    spin_religion_ae.setSelection(2);
                    break;
                case "katolik":
                    spin_religion_ae.setSelection(3);
                    break;
                case "Hindu":
                    spin_religion_ae.setSelection(4);
                    break;
                case "Budha":
                    spin_religion_ae.setSelection(5);
                    break;
                case "Konghuchu":
                    spin_religion_ae.setSelection(6);
                    break;
            }

            switch (Objects.requireNonNull(bundle.getString(DatabaseHelper.COLUMN_BLOOD))) {
                case "A":
                    rb_blood_a_ae.setChecked(true);
                    break;
                case "B":
                    rb_blood_b_ae.setChecked(true);
                    break;
                case "AB":
                    rb_blood_ab_ae.setChecked(true);
                    break;
                case "O":
                    rb_blood_o_ae.setChecked(true);
                    break;
            }
        }
    }

    public void decreaseShoes(View view) {
        if (Integer.valueOf(tiet_shoes_ae.getText().toString()) <= 34) {
            MyToast("ukuran sepatu terlalu kecil");
        } else {
            tiet_shoes_ae.setText(String.valueOf(Integer.valueOf(tiet_shoes_ae.getText().toString()) - 1));
        }
    }

    public void increaseShoes(View view) {
        if (Integer.valueOf(tiet_shoes_ae.getText().toString()) >= 48) {
            MyToast("ukuran sepatu terlalu besar");
        } else {
            tiet_shoes_ae.setText(String.valueOf(Integer.valueOf(tiet_shoes_ae.getText().toString()) + 1));
        }
    }

    private void getDate() {
        calendar = Calendar.getInstance();
        new DatePickerDialog(AddEditActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tiet_bday_ae.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.US).format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setGender() {
        switch (rg_gender_ae.getCheckedRadioButtonId()) {
            case -1:
                s_gender = "";
                break;
            case R.id.rb_gender_man_ae:
                s_gender = "Laki-Laki";
                break;
            case R.id.rb_gender_woman_ae:
                s_gender = "Perempuan";
                break;
        }
    }

    private void setBlood() {
        switch (rg_blood_ae.getCheckedRadioButtonId()) {
            case -1:
                s_blood = "";
                break;
            case R.id.rb_blood_a_ae:
                s_blood = "A";
                break;
            case R.id.rb_blood_b_ae:
                s_blood = "B";
                break;
            case R.id.rb_blood_ab_ae:
                s_blood = "AB";
                break;
            case R.id.rb_blood_o_ae:
                s_blood = "O";
                break;
        }
    }

    private void validator() {

        getStringAll();
        setGender();
        setBlood();

        if (s_name.isEmpty()) {
            tiet_name_ae.setError(s_hinterror);
            tiet_name_ae.requestFocus();
        } else if (s_bday.isEmpty()) {
            MyToast("harap pilih Tanggal Lahir Anda");
            getDate();
        } else if (s_gender.equals("")) {
            MyToast("harap pilih Jenis Kelamin Anda");
        } else if (s_address.isEmpty()) {
            tiet_address_ae.setError(s_hinterror);
            tiet_address_ae.requestFocus();
        } else if (s_phone.isEmpty()) {
            tiet_phone_ae.setError(s_hinterror);
            tiet_phone_ae.requestFocus();
        } else if (!Patterns.PHONE.matcher(s_phone).matches()) {
            tiet_phone_ae.setError("nomor tidak valid");
            tiet_phone_ae.requestFocus();
        } else if (pathPhone.matcher(s_phone).matches()) {
            tiet_phone_ae.setError(getResources().getString(R.string.error_phone));
            tiet_phone_ae.requestFocus();
        } else if (s_mail.isEmpty()) {
            tiet_mail_ae.setError(s_hinterror);
            tiet_mail_ae.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s_mail).matches()) {
            tiet_mail_ae.setError("email tidak valid");
            tiet_mail_ae.requestFocus();
        } else if (s_religion.isEmpty()) {
            MyToast("harap pilih Agama Anda");
        } else if (s_blood.equals("")) {
            MyToast("harap pilih Golongan Darah Anda");
        } else if (!s_website.isEmpty() && !Patterns.WEB_URL.matcher(s_website).matches()) {
            tiet_website_ae.setError("website tidak valid");
            tiet_website_ae.requestFocus();
        } else if (!s_facebook.isEmpty() && !pathFacebook.matcher(s_facebook).matches()) {
            tiet_facebook_ae.setText(String.format("%s%s", getResources().getString(R.string.text_facebook), s_facebook));
            tiet_facebook_ae.requestFocus();
        } else if (!s_twitter.isEmpty() && !pathTwitter.matcher(s_twitter).matches()) {
            tiet_twitter_ae.setText(String.format("%s%s", getResources().getString(R.string.text_twitter), s_twitter));
            tiet_twitter_ae.requestFocus();
        } else if (!s_line.isEmpty() && !pathLine.matcher(s_line).matches()) {
            tiet_line_ae.setText(String.format("%s%s", getResources().getString(R.string.text_line), s_line));
            tiet_line_ae.requestFocus();
        } else if (!s_telegram.isEmpty() && !pathTelegram.matcher(s_telegram).matches()) {
            tiet_telegram_ae.setText(String.format("%s%s", getResources().getString(R.string.text_telegram), s_telegram));
            tiet_telegram_ae.requestFocus();
        } else if (!s_instagram.isEmpty() && !pathInstagram.matcher(s_instagram).matches()) {
            tiet_instagram_ae.setText(String.format("%s%s", getResources().getString(R.string.text_instagram), s_instagram));
            tiet_instagram_ae.requestFocus();
        } else {

            if (s_website.isEmpty()) {
                s_website = "https://google.com/";
            }
            if (s_facebook.isEmpty()) {
                s_facebook = getResources().getString(R.string.text_facebook);
            }
            if (s_twitter.isEmpty()) {
                s_twitter = getResources().getString(R.string.text_twitter);
            }
            if (s_line.isEmpty()) {
                s_line = getResources().getString(R.string.text_line);
            }
            if (s_telegram.isEmpty()) {
                s_telegram = getResources().getString(R.string.text_telegram);
            }
            if (s_instagram.isEmpty()) {
                s_instagram = getResources().getString(R.string.text_instagram);
            }

            valid = true;
        }
    }

    private void inputData() {
        String s_id = new SimpleDateFormat("yyMMddHHmmssSS", Locale.US).format(Calendar.getInstance().getTime());
        databaseHelper.createData(new AddEditModel(
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
    }

    private void editInput() {
        databaseHelper.updateData(new AddEditModel(
                null,
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
        ), et_id_ae.getText().toString());
    }

    private void clearInput() {
        tiet_name_ae.setText("");
        tiet_bday_ae.setText("");
        rg_gender_ae.clearCheck();
        tiet_address_ae.setText("");
        tiet_phone_ae.setText("");
        tiet_mail_ae.setText("");
        spin_religion_ae.setSelection(0);
        rg_blood_ae.clearCheck();
        tiet_website_ae.setText("");
        tiet_shoes_ae.setText(String.valueOf(40));
        tiet_facebook_ae.setText("");
        tiet_twitter_ae.setText("");
        tiet_line_ae.setText("");
        tiet_telegram_ae.setText("");
        tiet_instagram_ae.setText("");
    }

    private void deleteInput() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_delete)
                .setTitle("Hapus Data")
                .setMessage("Yakin ingin menghapus " + tiet_name_ae.getText().toString() + "?")
                .setCancelable(false)
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.deleteData(et_id_ae.getText().toString());
                        finish();
                    }
                })
                .show();
    }
}
