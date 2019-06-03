package edwy.lugo.pokemonfinder.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edwy.lugo.pokemonfinder.R;
import edwy.lugo.pokemonfinder.adapter.PokemonsAdapter;
import edwy.lugo.pokemonfinder.adapter.TypesAdapter;
import edwy.lugo.pokemonfinder.listener.RecyclerItemClickListener;
import edwy.lugo.pokemonfinder.model.Pokemons;
import edwy.lugo.pokemonfinder.model.Types;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerTypes, recyclerPokemons;
    private List<Types> listTypes;
    private List<Pokemons> pokemonsList = new ArrayList<>();
    private List<Pokemons> pokemonsFavoriteList = new ArrayList<>();
    private String favorite;
    private TypesAdapter adapterTypes;
    private PokemonsAdapter adapterPokemons;
    private MaterialSearchView searchView;
    private ImageView sortImage;
    private TextView sortText;
    private boolean ascending = true;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Config init components
        initializeViews();

        //Config Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.getBackground().setAlpha(255);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    private void initializeViews() {

        Intent intent = getIntent();
        favorite = intent.getStringExtra("favorite");

        recyclerTypes = findViewById(R.id.listTypes);
        recyclerPokemons = findViewById(R.id.listPokemons);
        searchView = findViewById(R.id.materialSearchView);

        sortImage = findViewById(R.id.imageArrow);
        sortText = findViewById(R.id.textNameOrderby);

        sortImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortData(ascending);
                ascending = !ascending;
            }
        });

        sortText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortData(ascending);
                ascending = !ascending;

            }
        });


        JsonObject();

        listTypes = (List<Types>) intent.getSerializableExtra("types");

        adapterTypes = new TypesAdapter(listTypes, this);
        recyclerTypes.setHasFixedSize(true);
        recyclerTypes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerTypes.setAdapter(adapterTypes);


        //Config Event
        recyclerTypes.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerTypes, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                filterPokemon(listTypes.get(position).getName());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        filterPokemon(favorite);

    }

    public void filterPokemon(String type) {
        pokemonsFavoriteList.clear();
        for (int i = 0; i < pokemonsList.size(); i++) {
            if (String.valueOf(pokemonsList.get(i).getType().get(0)).equalsIgnoreCase(type)) {
                pokemonsFavoriteList.add(pokemonsList.get(i));
            }

        }

        if (listTypes.size() > 0) {
            Collections.sort(pokemonsFavoriteList, (object1, object2) -> object1.getName().compareTo(object2.getName()));
        }

        adapterPokemons = new PokemonsAdapter(pokemonsFavoriteList, this);
        recyclerPokemons.setHasFixedSize(true);
        recyclerPokemons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerPokemons.setAdapter(adapterPokemons);

        //Config Event
        recyclerPokemons.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerPokemons, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("pokemons.json");
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
            JSONObject jobj = null;
            JSONArray my_jsonArray = new JSONArray(loadJSONFromAsset());


            for (int i = 0; i < my_jsonArray.length(); i++) {
                jobj = my_jsonArray.getJSONObject(i);
                String name = jobj.getString("name");
                String url = jobj.getString("thumbnailImage");
                JSONArray jsonArrayTypes = jobj.getJSONArray("type");
                List<String> listTypes = new ArrayList<>();
                listTypes.add(jsonArrayTypes.get(0).toString());

                Pokemons pokemons = new Pokemons();
                pokemons.setName(name);
                pokemons.setThumbnailImage(url);
                pokemons.setType(listTypes);
                pokemonsList.add(pokemons);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);


        final EditText editText = (EditText) searchView.findViewById(com.miguelcatalan.materialsearchview.R.id.searchTextView);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });
        editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_ACTION_DONE);

        //Config SearchView;
        searchView.setHint("Search your favorite Pokemon");
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterText(newText);
                recyclerTypes.setVisibility(View.GONE);
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                recyclerTypes.setVisibility(View.VISIBLE);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //Sort
    private void sortData(boolean asc) {

        Matrix matrix1 = new Matrix();
        sortImage.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix1.postRotate((float) 180f, sortImage.getDrawable().getBounds().width() / 2, sortImage.getDrawable().getBounds().height() / 2);


        Matrix matrix2 = new Matrix();
        sortImage.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix2.postRotate((float) 0f, sortImage.getDrawable().getBounds().width() / 2, sortImage.getDrawable().getBounds().height() / 2);

        if (asc) {
            Collections.sort(pokemonsFavoriteList, (object1, object2) -> object1.getName().compareTo(object2.getName()));
            sortImage.setImageMatrix(matrix1);
        } else {
            Collections.reverse(pokemonsFavoriteList);
            sortImage.setImageMatrix(matrix2);

        }

        adapterPokemons = new PokemonsAdapter(pokemonsFavoriteList, this);
        recyclerPokemons.setHasFixedSize(true);
        recyclerPokemons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerPokemons.setAdapter(adapterPokemons);

    }


    public void filterText(String filter) {

        pokemonsFavoriteList.clear();


        for (int i = 0; i < pokemonsList.size(); i++) {
            if (String.valueOf(pokemonsList.get(i).getName().toLowerCase()).contains(filter.toLowerCase())) {
                pokemonsFavoriteList.add(pokemonsList.get(i));
            }

        }

        adapterPokemons = new PokemonsAdapter(pokemonsFavoriteList, this);
        recyclerPokemons.setHasFixedSize(true);
        recyclerPokemons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerPokemons.setAdapter(adapterPokemons);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toolbar.getBackground().setAlpha(0);
    }
}
