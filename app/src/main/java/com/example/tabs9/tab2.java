package com.example.tabs9;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class tab2 extends Fragment {

    SQLiteDatabase db;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xml2, container, false);

        tv = view.findViewById(R.id.tv);

        BaseDatos con = new BaseDatos(getActivity(), "Usuarios2", null, 2);
        db = con.getWritableDatabase();

        // Ejemplo para ver los datos de mi tabla en textview
        Cursor c = db.rawQuery("SELECT id, nombre FROM cliente", null);
        if (c.moveToFirst()) {
            tv.append("\n\n");
            do {
                int id = c.getInt(0);
                String nombre = c.getString(1);
                tv.append("id: " + id + " nombre: " + nombre + "\n");
            } while (c.moveToNext());
            tv.append("\nYa termino!\n");
        }

        c.close();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }
}


