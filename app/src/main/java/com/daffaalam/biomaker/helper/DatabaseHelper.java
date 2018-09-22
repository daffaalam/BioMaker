package com.daffaalam.biomaker.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.daffaalam.biomaker.model.AddEditModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_biomaker.db";
    private static final Integer DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tbl_bio";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BDAY = "bday";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_MAIL = "mail";
    public static final String COLUMN_RELIGION = "religion";
    public static final String COLUMN_BLOOD = "blood";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_SHOES = "shoes";
    public static final String COLUMN_FACEBOOK = "facebook";
    public static final String COLUMN_TWITTER = "twitter";
    public static final String COLUMN_LINE = "line";
    public static final String COLUMN_TELEGRAM = "telegram";
    public static final String COLUMN_INSTAGRAM = "instagram";
    public static final String COLUMN_WHATSAPP = "whatsapp";

    private SQLiteDatabase sqLiteDatabase;
    private ContentValues contentValues;
    private List<AddEditModel> addEditModelList;
    private AddEditModel addEditModel;
    private Cursor cursor;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_BDAY + " TEXT NOT NULL, " +
                COLUMN_GENDER + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL, " +
                COLUMN_PHONE + " TEXT NOT NULL, " +
                COLUMN_MAIL + " TEXT NOT NULL, " +
                COLUMN_RELIGION + " TEXT NOT NULL, " +
                COLUMN_BLOOD + " TEXT NOT NULL, " +
                COLUMN_WEBSITE + " TEXT NOT NULL, " +
                COLUMN_SHOES + " TEXT NOT NULL, " +
                COLUMN_FACEBOOK + " TEXT NOT NULL, " +
                COLUMN_TWITTER + " TEXT NOT NULL, " +
                COLUMN_LINE + " TEXT NOT NULL, " +
                COLUMN_TELEGRAM + " TEXT NOT NULL, " +
                COLUMN_INSTAGRAM + " TEXT NOT NULL, " +
                COLUMN_WHATSAPP + " TEXT NOT NULL )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createData(AddEditModel addEditModel) {
        sqLiteDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, addEditModel.getIdModel());
        contentValues.put(COLUMN_NAME, addEditModel.getNameModel());
        contentValues.put(COLUMN_BDAY, addEditModel.getBdayModel());
        contentValues.put(COLUMN_GENDER, addEditModel.getGenderModel());
        contentValues.put(COLUMN_ADDRESS, addEditModel.getAddressModel());
        contentValues.put(COLUMN_PHONE, addEditModel.getPhoneModel());
        contentValues.put(COLUMN_MAIL, addEditModel.getMailModel());
        contentValues.put(COLUMN_RELIGION, addEditModel.getReligionModel());
        contentValues.put(COLUMN_BLOOD, addEditModel.getBloodModel());
        contentValues.put(COLUMN_WEBSITE, addEditModel.getWebsiteModel());
        contentValues.put(COLUMN_SHOES, addEditModel.getShoesModel());
        contentValues.put(COLUMN_FACEBOOK, addEditModel.getFacebookModel());
        contentValues.put(COLUMN_TWITTER, addEditModel.getTwitterModel());
        contentValues.put(COLUMN_LINE, addEditModel.getLineModel());
        contentValues.put(COLUMN_TELEGRAM, addEditModel.getTelegramModel());
        contentValues.put(COLUMN_INSTAGRAM, addEditModel.getInstagramModel());
        contentValues.put(COLUMN_WHATSAPP, addEditModel.getWhatsappModel());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<AddEditModel> readAllData() {
        addEditModelList = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                addEditModel = new AddEditModel();
                addEditModel.setIdModel(cursor.getString(0));
                addEditModel.setNameModel(cursor.getString(1));
                addEditModel.setBdayModel(cursor.getString(2));
                addEditModel.setGenderModel(cursor.getString(3));
                addEditModel.setAddressModel(cursor.getString(4));
                addEditModel.setPhoneModel(cursor.getString(5));
                addEditModel.setMailModel(cursor.getString(6));
                addEditModel.setReligionModel(cursor.getString(7));
                addEditModel.setBloodModel(cursor.getString(8));
                addEditModel.setWebsiteModel(cursor.getString(9));
                addEditModel.setShoesModel(cursor.getString(10));
                addEditModel.setFacebookModel(cursor.getString(11));
                addEditModel.setTwitterModel(cursor.getString(12));
                addEditModel.setLineModel(cursor.getString(13));
                addEditModel.setTelegramModel(cursor.getString(14));
                addEditModel.setInstagramModel(cursor.getString(15));
                addEditModel.setWhatsappModel(cursor.getString(16));
                addEditModelList.add(addEditModel);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return addEditModelList;
    }

    public List<AddEditModel> readDetailData(String idModel) {
        addEditModelList = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "='" + idModel + "'", null);
        if (cursor.moveToFirst()) {
            do {
                addEditModel = new AddEditModel();
                addEditModel.setIdModel(cursor.getString(0));
                addEditModel.setNameModel(cursor.getString(1));
                addEditModel.setBdayModel(cursor.getString(2));
                addEditModel.setGenderModel(cursor.getString(3));
                addEditModel.setAddressModel(cursor.getString(4));
                addEditModel.setPhoneModel(cursor.getString(5));
                addEditModel.setMailModel(cursor.getString(6));
                addEditModel.setReligionModel(cursor.getString(7));
                addEditModel.setBloodModel(cursor.getString(8));
                addEditModel.setWebsiteModel(cursor.getString(9));
                addEditModel.setShoesModel(cursor.getString(10));
                addEditModel.setFacebookModel(cursor.getString(11));
                addEditModel.setTwitterModel(cursor.getString(12));
                addEditModel.setLineModel(cursor.getString(13));
                addEditModel.setTelegramModel(cursor.getString(14));
                addEditModel.setInstagramModel(cursor.getString(15));
                addEditModel.setWhatsappModel(cursor.getString(16));
                addEditModelList.add(addEditModel);
            } while (cursor.moveToNext());
        }
        /**
         if (cursor != null) {
         cursor.moveToFirst();
         addEditModel = new AddEditModel();
         addEditModel.setIdModel(cursor.getString(0));
         addEditModel.setNameModel(cursor.getString(1));
         addEditModel.setBdayModel(cursor.getString(2));
         addEditModel.setGenderModel(cursor.getString(3));
         addEditModel.setAddressModel(cursor.getString(4));
         addEditModel.setPhoneModel(cursor.getString(5));
         addEditModel.setMailModel(cursor.getString(6));
         addEditModel.setReligionModel(cursor.getString(7));
         addEditModel.setBloodModel(cursor.getString(8));
         addEditModel.setWebsiteModel(cursor.getString(9));
         addEditModel.setShoesModel(cursor.getString(10));
         addEditModel.setFacebookModel(cursor.getString(11));
         addEditModel.setTwitterModel(cursor.getString(12));
         addEditModel.setLineModel(cursor.getString(13));
         addEditModel.setTelegramModel(cursor.getString(14));
         addEditModel.setInstagramModel(cursor.getString(15));
         addEditModel.setWhatsappModel(cursor.getString(16));
         addEditModelList.add(addEditModel);
         }
         **/
        sqLiteDatabase.close();
        return addEditModelList;
    }

    public void updateData(AddEditModel addEditModel, String idModel) {
        sqLiteDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, addEditModel.getNameModel());
        contentValues.put(COLUMN_BDAY, addEditModel.getBdayModel());
        contentValues.put(COLUMN_GENDER, addEditModel.getGenderModel());
        contentValues.put(COLUMN_ADDRESS, addEditModel.getAddressModel());
        contentValues.put(COLUMN_PHONE, addEditModel.getPhoneModel());
        contentValues.put(COLUMN_MAIL, addEditModel.getMailModel());
        contentValues.put(COLUMN_RELIGION, addEditModel.getReligionModel());
        contentValues.put(COLUMN_BLOOD, addEditModel.getBloodModel());
        contentValues.put(COLUMN_WEBSITE, addEditModel.getWebsiteModel());
        contentValues.put(COLUMN_SHOES, addEditModel.getShoesModel());
        contentValues.put(COLUMN_FACEBOOK, addEditModel.getFacebookModel());
        contentValues.put(COLUMN_TWITTER, addEditModel.getTwitterModel());
        contentValues.put(COLUMN_LINE, addEditModel.getLineModel());
        contentValues.put(COLUMN_TELEGRAM, addEditModel.getTelegramModel());
        contentValues.put(COLUMN_INSTAGRAM, addEditModel.getInstagramModel());
        contentValues.put(COLUMN_WHATSAPP, addEditModel.getWhatsappModel());
        sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{idModel});
        sqLiteDatabase.close();
    }

    public void deleteData(String idModel) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{idModel});
        sqLiteDatabase.close();
    }
}
