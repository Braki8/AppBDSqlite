package com.example.tabs9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class tab1 extends Fragment {
    SQLiteDatabase db;
    TextView tv;
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xml1, container, false);

        tv = view.findViewById(R.id.tv);
        editText = view.findViewById(R.id.edTitulo);

        BaseDatos con = new BaseDatos(getActivity(), "Usuarios2", null, 2);
        db = con.getWritableDatabase();

        Button btnGuardar = view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        return view;
    }

    public void guardar() {
        String titulo = editText.getText().toString();

        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("nombre", titulo);
            long id = db.insert("cliente", null, valores);
            // Aquí puedes utilizar el ID retornado si lo necesitas

            // Opcionalmente, puedes limpiar el EditText después de guardar los datos
            editText.setText("");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }
}

