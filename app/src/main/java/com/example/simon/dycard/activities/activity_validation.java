package com.example.simon.dycard.activities;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.util.MySingleton;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class activity_validation extends AppCompatActivity {

    PayPalConfiguration m_configuration;
    // the id is the link to the paypal account, we have to create an app and get its id
    String m_paypalClientId = "Ac8JADvJe2XWMK2ypAk736v91rY7OuY4UO6B2m-cOjn6Qx1vfosbTUC84QIlYhAHX7v_bYUyh2YRcOfy";
    Intent m_service;
    int m_paypalRequestCode = 999; // or any number you want

    private ImageView faceAvant, formeFinale;
    private TextView texte;
    private Commande commande;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);



        commande = MySingleton.getInstance(activity_validation.this).getCommande();
        //forme=R.drawable
        //commande.getForme();
        //int forme pour récupérer la fore R.drawable en fonctin de ce que la personne a choisi
        //bcommande.getforme(lien vers la forme choisie au départ)

        faceAvant = (ImageView) findViewById(R.id.activity_validation_photo);
        formeFinale = (ImageView) findViewById(R.id.forme);
        texte = (TextView) findViewById(R.id.activity_validation_texte);

       // listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         //   @Override
          //  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

          //  }
      //  })

//récupérer la commande finqale (par rapport à la forme
        Bitmap shape= BitmapFactory.decodeResource(getResources(),commande.getFormes());
        //redonner une taille à un bitmap
        shape = Bitmap.createScaledBitmap(shape,255,255,false);


        Bitmap image = Bitmap.createScaledBitmap(commande.getPhoto(),255,255, false);
       faceAvant.setBackground(new BitmapDrawable(getResources(), image));
        //faceAvant.getLayoutParams().height = shape.getHeight();
        //faceAvant.getLayoutParams().width = shape.getWidth();
        faceAvant.setImageBitmap(shape);


        //formeFinale.setBackground(getResources().getDrawable(R.drawable.chat_v2));
        texte.setText(commande.getTexte());

        m_configuration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) // sandbox for test, production for real
                .clientId(m_paypalClientId);

        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration); // configuration above
        startService(m_service); // paypal service, listening to calls to paypal app
    }


    public void modifierparametres(View v) {
        Intent intent = new Intent(activity_validation.this, Images_Activity.class);
        startActivity(intent);
    }


    public void payer(View view)
    {
        PayPalPayment payment = new PayPalPayment(new BigDecimal(commande.getPrix()), "EUR", "Test payment with Paypal",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, m_paypalRequestCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == m_paypalRequestCode)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                // we have to confirm that the payment worked to avoid fraud
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if(confirmation != null)
                {
                    String state = confirmation.getProofOfPayment().getState();

                    if(state.equals("approved")) { // if the payment worked, the state equals approved
                        Toast.makeText(this, "paiement accepter", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(activity_validation.this, Etape1_Activity.class));
                    }
                    else
                        Toast.makeText(this, "erreur lors du paiement", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "paiement annulé", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
