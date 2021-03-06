package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView mPlaceOfOriginTextView = findViewById(R.id.origin_tv);
        TextView mItemDescriptionTextView = findViewById(R.id.description_tv);
        TextView mItemAlsoKnowAsTextView = findViewById(R.id.also_known_tv);
        TextView mItemIngredientsTextView = findViewById(R.id.ingredients_tv);

        TextView mPlaceOfOriginLabelTextView = findViewById(R.id.place_of_origin_tv_label);
        TextView mItemDescriptionLabelTextView = findViewById(R.id.description_tv_label);
        TextView mItemAlsoKnowAsLabelTextView = findViewById(R.id.also_known_tv_label);
        TextView mItemIngredientsLabelTextView = findViewById(R.id.ingredients_tv_label);


        if (sandwich != null) {

            if ( sandwich.getAlsoKnownAs().size() > 0 && sandwich.getAlsoKnownAs() != null) {
                mItemAlsoKnowAsTextView.setVisibility(View.VISIBLE);
                mItemAlsoKnowAsLabelTextView.setVisibility(View.VISIBLE);
                mItemAlsoKnowAsTextView.append(" " +  TextUtils.join(", ",
                                                sandwich.getAlsoKnownAs()));
            }
            else {
                mItemAlsoKnowAsTextView.setVisibility(View.GONE);
                mItemAlsoKnowAsLabelTextView.setVisibility(View.GONE);

            }

            if (sandwich.getPlaceOfOrigin() != null && !sandwich.getPlaceOfOrigin().isEmpty()) {
                mPlaceOfOriginTextView.setVisibility(View.VISIBLE);
                mPlaceOfOriginLabelTextView.setVisibility(View.VISIBLE);
                mPlaceOfOriginTextView.append(" " + sandwich.getPlaceOfOrigin());
            }
            else {
                mPlaceOfOriginTextView.setVisibility(View.GONE);
                mPlaceOfOriginLabelTextView.setVisibility(View.GONE);
            }

            if ( !sandwich.getDescription().isEmpty() && sandwich.getDescription() != null) {
                mItemDescriptionTextView.setVisibility(View.VISIBLE);
                mItemDescriptionLabelTextView.setVisibility(View.VISIBLE);

                mItemDescriptionTextView.append(" " + sandwich.getDescription());
            }
            else {
                mItemDescriptionTextView.setVisibility(View.GONE);
                mItemDescriptionLabelTextView.setVisibility(View.GONE);


            }
            if (sandwich.getIngredients() != null && sandwich.getIngredients().size() > 0) {
                mItemIngredientsTextView.setVisibility(View.VISIBLE);
                mItemIngredientsLabelTextView.setVisibility(View.VISIBLE);

                mItemIngredientsTextView.append(" " + TextUtils.join(", ",
                                                sandwich.getIngredients()));
            }
            else {
                mItemIngredientsTextView.setVisibility(View.GONE);
                mItemIngredientsLabelTextView.setVisibility(View.GONE);
            }
        }
    }
}
