package com.example.pokedex;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblist);

        listView = findViewById(R.id.listView);

        String[] fromColumns = {
                PokeDB.COLUMN_NATIONAL,
                PokeDB.COLUMN_NAME,
                PokeDB.COLUMN_SPECIES,
                PokeDB.COLUMN_GENDER,
                PokeDB.COLUMN_HEIGHT,
                PokeDB.COLUMN_WEIGHT,
                PokeDB.COLUMN_LEVEL,
                PokeDB.COLUMN_HP,
                PokeDB.COLUMN_ATTACK,
                PokeDB.COLUMN_DEFENSE
        };

        int[] toViews = {
                R.id.nationalTextView,
                R.id.nameTextView,
                R.id.speciesTextView,
                R.id.genderTextView,
                R.id.heightTextView,
                R.id.weightTextView,
                R.id.levelTextView,
                R.id.hpTextView,
                R.id.attackTextView,
                R.id.defenseTextView
        };

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item_pokemon,
                null,
                fromColumns,
                toViews,
                0
        );

        listView.setAdapter(adapter);

        loadPokemonData();
    }

    private void loadPokemonData() {
        Uri uri = PokeDB.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            adapter.swapCursor(cursor);
        }
    }
}
