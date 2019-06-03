package edwy.lugo.pokemonfinder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edwy.lugo.pokemonfinder.R;
import edwy.lugo.pokemonfinder.helper.Functions;

public class MainActivity extends AppCompatActivity {

    private Functions func = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void cadastroUm(View view) {
        func.alterActivity(MainActivity.this, RegisterNameActivity.class);
    }

}
