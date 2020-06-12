package vn.edu.ntu.chauhoang.sharedpreferences59cntt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtBirthday, edtPhone;
    RadioGroup rdgSex;
    RadioButton rdbMale, rdbFemale;
    Button btnSave, btnRead, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
        addEvents();
    }

    private void addViews()
    {
        edtName = findViewById(R.id.edtName);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtPhone = findViewById(R.id.edtPhone);
        rdgSex = findViewById(R.id.rdgSex);
        rdbMale = findViewById(R.id.rdbMale);
        rdbFemale = findViewById(R.id.rdbFemale);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        btnClear = findViewById(R.id.btnClear);
    }

    @Override
    protected void onResume() {
        super.onResume();
        readSharePref();
    }

    private void addEvents()
    {
        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnSave: saveSharePref(); break;
            case R.id.btnRead: readSharePref(); break;
            case R.id.btnClear: clearScreen(); break;
        }
    }

    private void saveSharePref()
    {
        SharedPreferences preferences = getSharedPreferences("mySharePref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", edtName.getText().toString());
        editor.putString("birthday", edtBirthday.getText().toString());
        editor.putString("phone", edtPhone.getText().toString());
        editor.putBoolean("male", rdbMale.isChecked());
        editor.putBoolean("female", rdbFemale.isChecked());
        editor.commit();
    }

    private void readSharePref()
    {
        SharedPreferences preferences = getSharedPreferences("mySharePref", Context.MODE_PRIVATE);
        if(preferences != null)
        {
            edtName.setText(preferences.getString("name", "Không tên"));
            edtBirthday.setText(preferences.getString("birthday", "Chưa sinh"));
            edtPhone.setText(preferences.getString("phone", "Không có sđt"));
            rdbMale.setChecked(preferences.getBoolean("male", true));
            rdbFemale.setChecked(preferences.getBoolean("female", false));
        }
    }

    private void clearScreen()
    {
        edtName.setText("");
        edtBirthday.setText("");
        edtPhone.setText("");
        rdbMale.setChecked(true);
    }
}