package edwy.lugo.pokemonfinder.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import edwy.lugo.pokemonfinder.R;
import edwy.lugo.pokemonfinder.helper.Functions;

public class RegisterNameActivity extends AppCompatActivity {

    private EditText editName;
    private ImageView imageNext;
    private Functions func = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeViews();
    }

    private void initializeViews() {

        editName = findViewById(R.id.editName);
        imageNext = findViewById(R.id.imageNext);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            imageNext.setLayoutParams(params);
            imageNext.setAdjustViewBounds(false);
            imageNext.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        } else {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.editName);
            imageNext.setLayoutParams(params);
            imageNext.setAdjustViewBounds(false);
            imageNext.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        }


        imageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editName.getText().toString().isEmpty() || !editName.getText().toString().equals("")) {
                    Intent intent = new Intent(RegisterNameActivity.this, RegisterSelectionPokemonActivity.class);
                    intent.putExtra("name", editName.getText().toString());
                    startActivity(intent);
                } else {
                    func.toast(RegisterNameActivity.this, R.string.ts_name_empty);
                }
            }
        });

    }


}
