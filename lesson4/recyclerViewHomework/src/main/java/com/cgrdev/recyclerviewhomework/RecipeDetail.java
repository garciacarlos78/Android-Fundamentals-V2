package com.cgrdev.recyclerviewhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // Get the position passed in the intent
        int position = getIntent().getIntExtra("position", -1);

        // Check if there is a correct position
        if (position != -1) setViewData(position + 1);
    }

    private void setViewData(int position) {

        // Get the resources ids of the strings
        int ingredientsId = getResources().getIdentifier("recipe_ingredients_" + position, "string", getPackageName());
        int procedureId = getResources().getIdentifier("recipe_procedure_" + position, "string", getPackageName());

        // Obtain the TextViews
        TextView ingredients = findViewById(R.id.ingredients_content);
        TextView procedure = findViewById(R.id.procedure_content);

        // Get the strings to put in the TextViews
        String ingredients_string = getString(ingredientsId);
        String procedure_string = getString(procedureId);

        // Set the strings in the TextViews
        ingredients.setText(ingredients_string);
        procedure.setText(procedure_string);

        // Set the image
        ImageView image = findViewById(R.id.imageView);

        switch (position - 1) {
            case 0:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cheeseburger));
                break;
            case 1:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.double_cheeseburger));
                break;
            case 2:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.triple_cheeseburger));
                break;
            case 3:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fish_chips));
                break;
            case 4:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.omelette));
                break;
            case 5:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.t_bone));
                break;
            case 6:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yogurt));
                break;
            case 7:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.migas));
                break;
            case 8:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.paella));
                break;
            case 9:
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cod));
                break;
            default:
                Toast.makeText(this, "Received position: " + position, Toast.LENGTH_SHORT);
        }
    }
}
