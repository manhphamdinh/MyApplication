package com.example.note;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    Button saveButton;
    EditText noteEditText;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((calendarView, year, month, day) -> {
            fileName = String.format("%02d_%02d_%04d", day, month + 1, year);
            Toast.makeText(this, fileName, Toast.LENGTH_SHORT).show();
        });

        saveButton = findViewById(R.id.saveButton);

        noteEditText = findViewById(R.id.noteEditText);
//        saveButton.setOnClickListener(view -> {
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(new Date(calendarView.getDate()));
//            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
//            int month = cal.get(Calendar.MONTH);
//            int year = cal.get(Calendar.YEAR);
//            Toast.makeText(this, String.format("%02d_%02d_%04d", dayOfMonth, month + 1, year), Toast.LENGTH_LONG).show();
//            String noteContent = noteEditText.getText().toString();
//            try {
//                // Ghi nội dung vào file
//                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
//                fos.write(noteContent.getBytes());
//                fos.close();
//                Toast.makeText(this, "Đã lưu ghi chú", Toast.LENGTH_LONG).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Lỗi khi lưu ghi chú", Toast.LENGTH_LONG).show();
//            }
//        });
    }
}