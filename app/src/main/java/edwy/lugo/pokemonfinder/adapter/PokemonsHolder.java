package edwy.lugo.pokemonfinder.adapter;

        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import edwy.lugo.pokemonfinder.R;

public class PokemonsHolder extends RecyclerView.ViewHolder {

    ImageView imagePokemon;
    TextView textPokemon;

    public PokemonsHolder(@NonNull View view) {
        super(view);
        imagePokemon = view.findViewById(R.id.imagePokemon);
        textPokemon = view.findViewById(R.id.textPokemon);


    }
}
