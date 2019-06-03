package edwy.lugo.pokemonfinder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import edwy.lugo.pokemonfinder.R;
import edwy.lugo.pokemonfinder.model.Types;

public class TypesAdapter extends RecyclerView.Adapter {

    private List<Types> typesList;
    private Context context;


    public TypesAdapter(List<Types> typesList, Context context) {
        this.typesList = typesList;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_types_pokemon, parent, false);

        TypesHolder holder = new TypesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        TypesHolder typeHolder = (TypesHolder) viewHolder;
        Types types = typesList.get(position);

        String upperString = types.getName().substring(0, 1).toUpperCase() + types.getName().substring(1);

        typeHolder.textType.setText(upperString);
        Picasso.with(context).load(types.getThumbnailImage())
                .into(typeHolder.imageType);

    }

    @Override
    public int getItemCount() {
        return typesList.size();

    }
}
