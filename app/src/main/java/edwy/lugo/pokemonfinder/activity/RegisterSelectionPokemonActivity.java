package edwy.lugo.pokemonfinder.activity;


import android.content.Intent;
import android.content.res.Configuration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edwy.lugo.pokemonfinder.R;
import edwy.lugo.pokemonfinder.adapter.CustomAdapter;
import edwy.lugo.pokemonfinder.helper.Functions;
import edwy.lugo.pokemonfinder.model.Types;

public class RegisterSelectionPokemonActivity extends AppCompatActivity {

    private String name;
    private Spinner spinner;
    private ImageView imageNext;
    private TextView textTitleName;
    private Functions func = new Functions();
    private List<Types> typesList = new ArrayList<Types>();
    private String favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_selection_pokemon);

        //Config init components
        initializeViews();


        //Config Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getBackground().setAlpha(0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    private void initializeViews() {

        //get Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
        }

        imageNext = findViewById(R.id.imageNext);
        spinner = findViewById(R.id.spinner);
        textTitleName = findViewById(R.id.textTitleName);
        textTitleName.setText("Hello, " + name + "!");


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            imageNext.setLayoutParams(params);
            imageNext.setAdjustViewBounds(false);
            imageNext.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        } else {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.spinner);
            imageNext.setLayoutParams(params);
            imageNext.setAdjustViewBounds(false);
            imageNext.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        }

        JsonObject();


        imageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterSelectionPokemonActivity.this, HomeActivity.class);
                intent.putExtra("types", (Serializable) typesList);
                intent.putExtra("favorite", favorite);
                startActivity(intent);
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("types.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void JsonObject() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray my_jsonArray = obj.getJSONArray("results");

            for (int i = 0; i < my_jsonArray.length(); i++) {
                JSONObject jobj = my_jsonArray.getJSONObject(i);
                String name = jobj.getString("name");
                String url_value = jobj.getString("thumbnailImage");
                Types types = new Types();
                types.setName(name);
                types.setThumbnailImage(url_value);
                typesList.add(types);
            }

            spinner.setAdapter(new CustomAdapter(RegisterSelectionPokemonActivity.this, R.layout.spinner_custom,
                    typesList));

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    favorite = typesList.get(i).getName();
                    func.toastContext(getApplicationContext(), typesList.get(i).getName() + " has been selected!");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
