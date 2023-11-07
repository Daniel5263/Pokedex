package com.example.pokedex;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class PokemonCursorAdapter extends CursorAdapter {
    private Context context;
    public PokemonCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_pokemon, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nationalTextView = view.findViewById(R.id.national_id);
        TextView nameTextView = view.findViewById(R.id.name_id);
        TextView speciesTextView = view.findViewById(R.id.species_id);
        TextView genderTextView = view.findViewById(R.id.gender_id);
        TextView heightTextView = view.findViewById(R.id.height_id);
        TextView weightTextView = view.findViewById(R.id.weight_id);
        TextView levelTextView = view.findViewById(R.id.level_id);
        TextView hpTextView = view.findViewById(R.id.hp_id);
        TextView attackTextView = view.findViewById(R.id.attack_id);
        TextView defenseTextView = view.findViewById(R.id.defense_id);

        int nationalColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_NATIONAL);
        int nameColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_NAME);
        int speciesColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_SPECIES);
        int genderColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_GENDER);
        int heightColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_HEIGHT);
        int weightColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_WEIGHT);
        int levelColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_LEVEL);
        int hpColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_HP);
        int attackColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_ATTACK);
        int defenseColumnIndex = cursor.getColumnIndex(PokeDB.COLUMN_DEFENSE);

        int national = cursor.getInt(nationalColumnIndex);
        String name = cursor.getString(nameColumnIndex);
        String species = cursor.getString(speciesColumnIndex);
        String gender = cursor.getString(genderColumnIndex);
        int height = cursor.getInt(heightColumnIndex);
        int weight = cursor.getInt(weightColumnIndex);
        int level = cursor.getInt(levelColumnIndex);
        int hp = cursor.getInt(hpColumnIndex);
        int attack = cursor.getInt(attackColumnIndex);
        int defense = cursor.getInt(defenseColumnIndex);

        nationalTextView.setText(String.valueOf(national));
        nameTextView.setText(name);
        speciesTextView.setText(species);
        genderTextView.setText(gender);
        heightTextView.setText(String.valueOf(height));
        weightTextView.setText(String.valueOf(weight));
        levelTextView.setText(String.valueOf(level));
        hpTextView.setText(String.valueOf(hp));
        attackTextView.setText(String.valueOf(attack));
        defenseTextView.setText(String.valueOf(defense));
    }
}
