package com.example.tabs9;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class tab3 extends Fragment implements CustomAdapter.OnEditButtonClickListener {

    SQLiteDatabase db;
    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xml3, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        BaseDatos con = new BaseDatos(getActivity(), "Usuarios6", null, 6);
        db = con.getWritableDatabase();

        // Obtener los datos de la tabla cliente
        Cursor c = db.rawQuery("SELECT id, nombre, descripcion, imagen FROM cliente", null);

        List<Cliente> clienteList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String nombre = c.getString(1);
                String descripcion = c.getString(2);
                byte[] imagenBytes = c.getBlob(3);
                Bitmap imagen = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagen.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytesImagen = stream.toByteArray();

                Cliente cliente = new Cliente(id, nombre, descripcion, bytesImagen);
                clienteList.add(cliente);


            } while (c.moveToNext());
        }

        c.close();

        // Configurar el RecyclerView y el adaptador
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CustomAdapter(getActivity(), clienteList, true); // Pasar true para mostrar el botón de edición
        recyclerView.setAdapter(adapter);

        // Configurar el listener para el botón de edición
        adapter.setOnEditButtonClickListener(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }

    @Override
    public void onEditButtonClick(int position) {
        // Lógica para manejar el evento de clic en el botón de edición
        // Puedes abrir el Activity "ActualizarDB" aquí pasando los datos necesarios
        List<Cliente> clienteList = adapter.getClienteList();
        if (position >= 0 && position < clienteList.size()) {
            Cliente cliente = clienteList.get(position);
            Intent intent = new Intent(getActivity(), actualizarDB.class);
            intent.putExtra("cliente", cliente);
            startActivity(intent);
        }
    }
}


