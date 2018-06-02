package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String test = "{\"name\":{\"mainName\":\"Ham and cheese sandwich\",\"alsoKnownAs\":[]},\"placeOfOrigin\":\"\",\"description\":\"A ham and cheese sandwich is a common type of sandwich. It is made by putting cheese and sliced ham between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables like lettuce, tomato, onion or pickle slices can also be included. Various kinds of mustard and mayonnaise are also  common.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",\"ingredients\":[\"Sliced  bread\",\"Cheese\",\"Ham\"]}";

    public static Sandwich parseSandwichJson(String json)
    {
        Sandwich toReturn = null;

        if (json != null && !json.isEmpty())
        {
            try
            {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject name = jsonObject.getJSONObject("name");
                String mainName = name.getString("mainName");
                String placeOfOrigin = jsonObject.getString("placeOfOrigin");
                String description = jsonObject.getString("description");
                String image = jsonObject.getString("image");
                JSONArray alsoKnownAsArrJson = name.getJSONArray("alsoKnownAs");
                List<String> alsoKnownAs = new ArrayList<String>();
                for(int i = 0; i < alsoKnownAsArrJson.length(); i++)
                    alsoKnownAs.add(alsoKnownAsArrJson.getString(i));
                JSONArray ingredientsAsArrJson = jsonObject.getJSONArray("ingredients");
                List<String> ingredients = new ArrayList<String>();
                for(int i = 0; i < ingredientsAsArrJson.length(); i++)
                    ingredients.add(ingredientsAsArrJson.getString(i));

                toReturn = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description,image,ingredients);

            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }
        else
        {
            System.out.println("The json string is empty or null");
        }

        return toReturn;
    }

}
