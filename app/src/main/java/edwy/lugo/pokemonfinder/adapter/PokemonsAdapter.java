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
import edwy.lugo.pokemonfinder.model.Pokemons;

public class PokemonsAdapter extends RecyclerView.Adapter {
    private List<Pokemons> pokemonsList;
    private Context context;

    public PokemonsAdapter(List<Pokemons> pokemonList, Context context) {
        this.pokemonsList = pokemonList;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_pokemons, parent, false);

        PokemonsHolder holder = new PokemonsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        PokemonsHolder holder = (PokemonsHolder) viewHolder;
        Pokemons pokemons = pokemonsList.get(i);

        String upperString = pokemons.getName().substring(0, 1).toUpperCase() + pokemons.getName().substring(1);
        holder.textPokemon.setText(upperString);
        Picasso.with(context).load(pokemons.getThumbnailImage())
                .into(holder.imagePokemon);

    }

    @Override
    public int getItemCount() {
        return pokemonsList.size();
    }
}
