package com.inpt.messagingapp.helpers.sqliteHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.inpt.messagingapp.wrapper.controllers.ControllerLocale;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Reponse;
import com.inpt.messagingapp.wrapper.models.User;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqliteConnector extends SQLiteOpenHelper implements ControllerLocale {
    private Context context;
    private static final String DATABASE_LOCALE_NAME = "BD_LOCALE";

    private static final String TABLE_COURS_LOCALE_NAME = "cour";
    private static final String COL_COUR_L_1 = "idCour";
    private static final String COL_COUR_L_2 = "idTeacher";
    private static final String COL_COUR_L_3 = "description";
    private static final String COL_COUR_L_4 = "titre";
    private static final String COL_COUR_L_5 = "file";
    private static final String COL_COUR_L_6 = "idDevoirs";

    private static final String TABLE_USER_LOCALE_NAME = "user";
    private static final String COL_USER_L_1 = "idUser";
    private static final String COL_USER_L_2 = "nom";
    private static final String COL_USER_L_3 = "prenom";
    private static final String COL_USER_L_4 = "username";
    private static final String COL_USER_L_5 = "email";
    private static final String COL_USER_L_6 = "type";

    private static final String TABLE_DEVOIR_LOCALE_NAME = "devoir";
    private static final String COL_DEVOIR_L_1 = "idDevoir";
    private static final String COL_DEVOIR_L_2 = "date";
    private static final String COL_DEVOIR_L_3 = "file";

    private static final String TABLE_REPONSE_LOCALE_NAME = "Reponse";
    private static final String COL_REPONSE_L_1 = "idReponse";
    private static final String COL_REPONSE_L_2 = "student";
    private static final String COL_REPONSE_L_3 = "file";

    public SqliteConnector(@Nullable Context context) {
        super(context, DATABASE_LOCALE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DATABASE_LOCALE_NAME) {
        String sql1 = "CREATE TABLE " + TABLE_COURS_LOCALE_NAME + " ( "
                + COL_COUR_L_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_COUR_L_2 + " TEXT, "
                + COL_COUR_L_3 + " TEXT, "
                + COL_COUR_L_4 + " TEXT, "
                + COL_COUR_L_5 + " TEXT, "
                + COL_COUR_L_6 + " TEXT ) ";
        DATABASE_LOCALE_NAME.execSQL(sql1);

        String sql2 = " CREATE TABLE " + TABLE_USER_LOCALE_NAME + " ( "
                + COL_USER_L_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USER_L_2 + " TEXT, "
                + COL_USER_L_3 + " TEXT, "
                + COL_USER_L_4 + " TEXT, "
                + COL_USER_L_5 + " TEXT, "
                + COL_USER_L_6 + " TEXT ) ";
        DATABASE_LOCALE_NAME.execSQL(sql2);

        String sql3 = " CREATE TABLE " + TABLE_DEVOIR_LOCALE_NAME + " ( "
                + COL_DEVOIR_L_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_DEVOIR_L_2 + " TEXT, "
                + COL_DEVOIR_L_3 + " TEXT ) ";
        DATABASE_LOCALE_NAME.execSQL(sql3);

        String sql4 = " CREATE TABLE " + TABLE_REPONSE_LOCALE_NAME + " ( "
                + COL_REPONSE_L_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_REPONSE_L_2 + " TEXT, "
                + COL_REPONSE_L_3 + " TEXT ) ";
        DATABASE_LOCALE_NAME.execSQL(sql4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DATABASE_LOCALE_NAME, int i, int i1) {
        String sql = " DROP TABLE IF EXISTS " + TABLE_COURS_LOCALE_NAME;
        DATABASE_LOCALE_NAME.execSQL(sql);
        onCreate(DATABASE_LOCALE_NAME);
    }

    @Override
    public void addLocale(Cour cour) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, cour.getTeacher());
        cv.put(COL_COUR_L_3, cour.getDescription());
        cv.put(COL_COUR_L_4, cour.getTitre());
        cv.put(COL_COUR_L_5, cour.getFile());
        long rs = DATABASE_LOCALE_NAME.insert(TABLE_COURS_LOCALE_NAME, null, cv);
        if (rs == -1) {
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addLocale(User user) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, user.getName());
        cv.put(COL_COUR_L_3, user.getPrenom());
        cv.put(COL_COUR_L_4, user.getUsername());
        cv.put(COL_COUR_L_5, user.getEmail());
        cv.put(COL_COUR_L_6, user.getType().toString());
        long rs = DATABASE_LOCALE_NAME.insert(TABLE_COURS_LOCALE_NAME, null, cv);
        if (rs == -1) {
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addLocale(Devoir devoir) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, devoir.getDate());
        cv.put(COL_COUR_L_3, devoir.getFile());
        long rs = DATABASE_LOCALE_NAME.insert(TABLE_COURS_LOCALE_NAME, null, cv);
        if (rs == -1) {
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addLocale(Reponse reponse) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, reponse.getStudent());
        cv.put(COL_COUR_L_3, reponse.getFile());
        long rs = DATABASE_LOCALE_NAME.insert(TABLE_COURS_LOCALE_NAME, null, cv);
        if (rs == -1) {
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public List<Cour> allCourL() {
        String sql = "select * from " + TABLE_COURS_LOCALE_NAME;
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        Cursor cursor = null;
        if (DATABASE_LOCALE_NAME != null) {
            cursor = DATABASE_LOCALE_NAME.rawQuery(sql, null);
        }
        List<Cour> cours = new ArrayList<>();
      if (cursor.moveToFirst()) {
            do {
                Cour cour = new Cour();
                cour.setIdCour(cursor.getString(0));
                cour.setTeacher(cursor.getString(1));
                cour.setDescription(cursor.getString(2));
                cour.setTitre(cursor.getString(3));
                cour.setStudents(null);
                cour.setFile(cursor.getString(4));
                cour.setDevoirs(null);
                // Adding contact to list
                cours.add(cour);
            } while (cursor.moveToNext());
        }

        return cours;
    }

    @Override
    public Cursor allDevoirL() {
        String sql = "select * from " + TABLE_DEVOIR_LOCALE_NAME;
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        Cursor cursor = null;
        if (DATABASE_LOCALE_NAME != null) {
            cursor = DATABASE_LOCALE_NAME.rawQuery(sql, null);
        }
        return cursor;
    }

    @Override
    public Cursor allUserL() {
        String sql = "select * from " + TABLE_USER_LOCALE_NAME;
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        Cursor cursor = null;
        if (DATABASE_LOCALE_NAME != null) {
            cursor = DATABASE_LOCALE_NAME.rawQuery(sql, null);
        }
        return cursor;
    }

    @Override
    public Cursor allReponse() {
        String sql = "select * from " + TABLE_REPONSE_LOCALE_NAME;
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        Cursor cursor = null;
        if (DATABASE_LOCALE_NAME != null) {
            cursor = DATABASE_LOCALE_NAME.rawQuery(sql, null);
        }
        return cursor;
    }

    @Override
    public void deleteCourL(String idCour) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        long rs = DATABASE_LOCALE_NAME.delete(TABLE_COURS_LOCALE_NAME,"idCour=?", new String[]{idCour});
        if (rs == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleteDevoirL(String idDevoir) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        long rs = DATABASE_LOCALE_NAME.delete(TABLE_DEVOIR_LOCALE_NAME,"idDevoir=?", new String[]{idDevoir});
        if (rs == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleteUserL(String idUser) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        long rs = DATABASE_LOCALE_NAME.delete(TABLE_USER_LOCALE_NAME,"idUser=?", new String[]{idUser});
        if (rs == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleteReponseL(String idReponse) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        long rs = DATABASE_LOCALE_NAME.delete(TABLE_REPONSE_LOCALE_NAME,"idReponse=?", new String[]{idReponse});
        if (rs == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateCourL(Cour cour) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, cour.getTeacher());
        cv.put(COL_COUR_L_3, cour.getDescription());
        cv.put(COL_COUR_L_4, cour.getTitre());
        cv.put(COL_COUR_L_5, cour.getFile());
        long rs = DATABASE_LOCALE_NAME.update(TABLE_COURS_LOCALE_NAME, cv, "idCour=?", new String[]{cour.getIdCour()});
        if (rs == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully updated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateDevoirL(Devoir devoir) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, devoir.getDate());
        cv.put(COL_COUR_L_3, devoir.getFile());
        long rs = DATABASE_LOCALE_NAME.update(TABLE_COURS_LOCALE_NAME, cv, "idDevoir=?", new String[]{devoir.getIdDevoir()});
        if (rs == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully updated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateUserL(User user) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, user.getName());
        cv.put(COL_COUR_L_3, user.getPrenom());
        cv.put(COL_COUR_L_4, user.getUsername());
        cv.put(COL_COUR_L_5, user.getEmail());
        cv.put(COL_COUR_L_6, user.getType().toString());
        long rs = DATABASE_LOCALE_NAME.update(TABLE_USER_LOCALE_NAME, cv, "idUser=?", new String[]{user.getIdUser()});
        if (rs == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully updated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateReponseL(Reponse reponse) {
        SQLiteDatabase DATABASE_LOCALE_NAME = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COUR_L_2, reponse.getStudent());
        cv.put(COL_COUR_L_3, reponse.getFile());
        long rs = DATABASE_LOCALE_NAME.update(TABLE_COURS_LOCALE_NAME, cv, "idReponse=?", new String[]{reponse.getIdReponse()});
        if (rs == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully updated", Toast.LENGTH_SHORT).show();
        }
    }


}

