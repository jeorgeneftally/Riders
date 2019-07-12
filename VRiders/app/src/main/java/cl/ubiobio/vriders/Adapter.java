package cl.ubiobio.vriders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.RutasViewHolders> {
    List<Rutas> listarutas;

    public Adapter(List<Rutas> listRutas) {
        this.listarutas = listRutas;
    }

    @NonNull
    @Override
    public RutasViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        RutasViewHolders holder=new RutasViewHolders(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RutasViewHolders holder, int position) {
        Rutas rutas= listarutas.get(position);
        holder.nombre.setText(rutas.getNombre());


    }

    @Override
    public int getItemCount() {
        return listarutas.size();
    }

     class RutasViewHolders extends RecyclerView.ViewHolder {

        public TextView nombre;

        public RutasViewHolders(View itemView){
            super(itemView);
            nombre=itemView.findViewById(R.id.ITEMVIEW);
            String var=nombre.toString();
            Log.e("",var);

        }

    }
}
