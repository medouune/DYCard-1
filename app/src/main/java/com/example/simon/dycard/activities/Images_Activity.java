package com.example.simon.dycard.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.util.MySingleton;




/*public class TonAdapter extends ArrayAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null)
        {
            LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //le layout représentant la ligne dans le listView
            view = li.inflate(R.layout.list_view, null);
        }

        // maintenant tu peux travailler ta view qui correspond à une ligne
        // si la ligne correspond à l'élément sélectionnée préalablement tu change son fond .


        return view;

    }
} */

public class Images_Activity extends AppCompatActivity {

    private Commande commande;
   // ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_);

        //listView=(ListView)findViewById(R.id.);

        commande = MySingleton.getInstance(getApplicationContext()).getCommande();

    }

    public void diamant(View v){
        commande.setForme("diamant");
    }

    public void etoile(View v){
        commande.setForme("etoile");
    }

    public void coeur(View v){
        commande.setForme("coeur");
    }

    public void octo(View v){
        commande.setForme("octo");
    }

    public void cadreplantes(View v){
        commande.setForme("cadre_plantes");
    }

    public void chat(View v){
        commande.setForme("chat");
    }

    public void ovale(View v){
        commande.setForme("ovale");
    }

    //listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){



    public void imagePrecedent(View v) {
        startActivity(new Intent(Images_Activity.this, Etape1_Activity.class));
        finish();
    }

    public void imageSuivant(View v) {
        Intent intent = new Intent(Images_Activity.this, Choose_photo_activity.class);
        MySingleton.getInstance(getApplicationContext()).setCommande(commande);
        startActivity(intent);
        finish();
    }
}
