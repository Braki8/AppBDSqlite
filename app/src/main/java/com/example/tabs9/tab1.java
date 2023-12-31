package com.example.tabs9;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class tab1 extends Fragment {
    SQLiteDatabase db;
    TextView tv;
    EditText editText;
    EditText edDescripcion;
    ImageView imgPreview;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri uri = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xml1, container, false);


        editText = view.findViewById(R.id.edTitulo);
        edDescripcion = view.findViewById(R.id.edDescripcion);
        imgPreview = view.findViewById(R.id.imgPreview);

        BaseDatos con = new BaseDatos(getActivity(), "Usuarios6", null, 6);
        db = con.getWritableDatabase();

        Button btnGuardar = view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        Button btnSeleccionarImagen = view.findViewById(R.id.btnSelectImage);
        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        return view;
    }

    public void guardar() {
        String titulo = editText.getText().toString();
        String descripcion = edDescripcion.getText().toString();

        if (db != null && uri != null) {
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                byte[] imagen = getBytes(inputStream);
                inputStream.close();

                ContentValues valores = new ContentValues();
                valores.put("nombre", titulo);
                valores.put("descripcion", descripcion);
                valores.put("imagen", imagen);
                long id = db.insert("cliente", null, valores);

                // Aquí puedes utilizar el ID retornado si lo necesitas

                // Opcionalmente, puedes limpiar los EditText después de guardar los datos
                editText.setText("");
                edDescripcion.setText("");
                imgPreview.setImageBitmap(null);
                uri = null;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public void abrirGaleria() {
        // Crear un intent para abrir la galería
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        // Verificar si hay una aplicación disponible para manejar la acción de seleccionar una imagen
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
// Iniciar la actividad de selección de imagen
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Obtener la URI de la imagen seleccionada
            Uri imageUri = data.getData();

            // Establecer la imagen en el ImageView
            imgPreview.setImageURI(imageUri);
            uri = imageUri;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }}


