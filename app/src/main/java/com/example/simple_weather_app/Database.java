package com.example.simple_weather_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String WEATHER_TABLE = "weather_data";
    public static final String CITY = "city";
    public static final String TEMPERATURE = "temperature";
    public static final String COUNTRY_NAME = "country";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String FEELS_LIKE = "feels_like";
    public static final String DESCRIPTION = "description";
    public static final String WIND_SPEED = "speed";
    public static final String CLOUDINESS = "clouds";

    public Database(@Nullable Context context){
        super(context, "weather.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + WEATHER_TABLE + " " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + CITY + " TEXT, " + COUNTRY_NAME + " TEXT, " + TEMPERATURE +
                " REAL, " + PRESSURE + " INTEGER, " + HUMIDITY + " INTEGER, " + DESCRIPTION + " TEXT, " +
                WIND_SPEED + " REAL, " + FEELS_LIKE + " REAL, " + CLOUDINESS + " TEXT) ";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addToDatabase(String city, String countryName, double temp, float pressure, double feelsLike, int humidity, String description, String wind, String clouds){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CITY, city);
        cv.put(COUNTRY_NAME, countryName);
        cv.put(TEMPERATURE, temp);
        cv.put(PRESSURE, pressure);
        cv.put(FEELS_LIKE, feelsLike);
        cv.put(HUMIDITY, humidity);
        cv.put(DESCRIPTION, description);
        cv.put(WIND_SPEED, wind);
        cv.put(CLOUDINESS, clouds);

        long insert = db.insert(WEATHER_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }

    }
}
