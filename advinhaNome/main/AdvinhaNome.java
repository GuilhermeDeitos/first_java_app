package advinhaNome.main;

import advinhaNome.main.R;
import advinhaNome.main.R.id;
import advinhaNome.main.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Color;

public class AdvinhaNome extends Activity {
    private EditText etName;
    private TextView tvResultado;
    private Button btCalcular;
    private Button btSair;
    private Button btAdvinhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btAdvinhar = (Button) findViewById(R.id.btAdvinhar);
        btSair = (Button) findViewById(R.id.btSair);

        btAdvinhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdivinhar();
            }
        });


        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btSairOnClick();
            }
        });

    }

    protected void btnAdivinhar() {
        String name = etName.getText().toString();
        if (name.equals("")) {
            Toast.makeText(getApplicationContext(), "Campo [Nome] deve ser preenchido.", Toast.LENGTH_LONG).show();
            etName.requestFocus();
            return;
        }

        String newName = genNameOnClick(name);
        tvResultado.setVisibility(View.VISIBLE);
        
        if (newName.equals(name)) {
            tvResultado.setText("Acertei seu nome: " + newName);
            btAdvinhar.setText("Acertei!");
            btAdvinhar.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Eu acertei! Parabéns para mim!", Toast.LENGTH_LONG).show();
        tvResultado.setTextColor(Color.GREEN);

        } else {
            btAdvinhar.setText("Tentar novamente");
            tvResultado.setText(newName
                    + "? acho que errei seu nome ;-;, posso tentar novamente?");
            tvResultado.setTextColor(Color.RED);
        }

    }

    protected String genNameOnClick(String newName) {
        String[] names = { "João", "Maria", "José", "Ana", "Pedro", "Mariana", "Carlos", "Paula", "Lucas", "Fernanda",
                newName };
        int index = (int) (Math.random() * names.length);
        return names[index];
    }


    protected void btSairOnClick() {
        finish();
    }
}