package edwy.lugo.pokemonfinder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edwy.lugo.pokemonfinder.R;
import edwy.lugo.pokemonfinder.model.Types;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private List<Types> typesList;

    public CustomAdapter(Context context, int textViewResourceId,
                         List<Types> typesList) {
        super(context, textViewResourceId, typesList);
        this.context = context;
        this.typesList = typesList;
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View layout = inflater.inflate(R.layout.spinner_custom, parent, false);

        Types types = typesList.get(position);

        TextView tvType = layout.findViewById(R.id.textType);
        String upperString = types.getName().substring(0, 1).toUpperCase() + types.getName().substring(1);
        tvType.setText(upperString);

        ImageView img = layout.findViewById(R.id.imageIcon);
        Picasso.with(getContext()).load(types.getThumbnailImage())
                .into(img);


        return layout;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
