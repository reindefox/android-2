package com.example.android_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Camera;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Camera mCamera;

    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";

    public int mCurrentScore = 5;
    public int mCurrentLevel = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Вызываем метод базового класса
        super.onCreate(savedInstanceState);

        // Проверяем на существовавние предыдущего состояния
        if (savedInstanceState != null) {
            mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        } else {
        // Инициализация со стандартными значениями
        }

        setContentView(R.layout.activity_main);

        ((TextView) findViewById(R.id.textView)).setText(String.valueOf(Build.VERSION.SDK_INT));
    }

    private void setUpActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Debug.stopMethodTracing();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mCamera != null) {
            mCamera = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            initializeCamera();
        }
    }

    private void initializeCamera() {
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Сохранение текущих данных, т.к. явление останавливается
        ContentValues values = new ContentValues();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Явление запущено/перезапущено, убеждаемся, что GPS работает
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            // Если GPS отключен
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Сохраняем текущее состояние пользователя в игре
        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

        // Вызываем метод базового для сохранения разметки
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Восстанавливаем сохраненные данные
        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
    }
}