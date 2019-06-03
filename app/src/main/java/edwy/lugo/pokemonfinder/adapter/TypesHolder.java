package edwy.lugo.pokemonfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edwy.lugo.pokemonfinder.R;

public class TypesHolder extends RecyclerView.ViewHolder {

    ImageView imageType;
    TextView textType;

    public TypesHolder(View view) {
        super(view);
        imageType = view.findViewById(R.id.imageView);
        textType = view.findViewById(R.id.textView);
    }
}
