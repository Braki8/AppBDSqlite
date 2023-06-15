package com.example.tabs9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<Cliente> clienteList;
    private boolean showEditButton; // Bandera para indicar si se debe mostrar el botón de edición
    private OnEditButtonClickListener editButtonClickListener; // Listener para el botón de edición

    public CustomAdapter(Context context, List<Cliente> clienteList, boolean showEditButton) {
        this.context = context;
        this.clienteList = clienteList;
        this.showEditButton = showEditButton;
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

        // Verificar si se debe mostrar el botón de edición
        if (showEditButton) {
            holder.containerEditar.setVisibility(View.VISIBLE);

            holder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Lógica para manejar el evento de clic en el botón de edición
                    // Puedes abrir una actividad de edición, mostrar un diálogo, etc.
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        // Notificar el evento de clic al listener
                        if (editButtonClickListener != null) {
                            editButtonClickListener.onEditButtonClick(adapterPosition);
                        }
                    }
                }
            });
        } else {
            holder.containerEditar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }

    // Interfaz para el listener del botón de edición
    public interface OnEditButtonClickListener {
        void onEditButtonClick(int position);
    }

    public void setOnEditButtonClickListener(OnEditButtonClickListener listener) {
        this.editButtonClickListener = listener;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDescripcion;
        ImageView imgPreview;
        FrameLayout containerEditar;
        Button btnEditar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            imgPreview = itemView.findViewById(R.id.imgPreview);
            containerEditar = itemView.findViewById(R.id.containerEditar);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }
}






