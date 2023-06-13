package com.example.tabs9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<Cliente> clienteList;

    public CustomAdapter(Context context, List<Cliente> clienteList) {
        this.context = context;
        this.clienteList = clienteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente cliente = clienteList.get(position);

        holder.tvNombre.setText(cliente.getNombre());
        holder.tvDescripcion.setText(cliente.getDescripcion());

        byte[] imagenBytes = cliente.getImagen();
        Bitmap imagen = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
        holder.imgPreview.setImageBitmap(imagen);
    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDescripcion;
        ImageView imgPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            imgPreview = itemView.findViewById(R.id.imgPreview);
        }
    }
}

