package com.coursera.cpayanf.proycontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationActivity extends AppCompatActivity {
	TextView lblNombre;
	TextView lblTelefono;
	TextView lblEmail;
	TextView lblDescripcion;
	TextView lblFecNac;
	Button btnEditar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation);

		try {
			CreaBtnEditar();

			lblNombre = findViewById(R.id.lblValNombre);
			lblTelefono = findViewById(R.id.lblValTelefono);
			lblEmail = findViewById(R.id.lblValEmail);
			lblFecNac = findViewById(R.id.lblValFecNac);
			lblDescripcion = findViewById(R.id.lblValDescripcion);

			Bundle loParams = getIntent().getExtras();
			String lsNombre = loParams.getString("psNombreContacto");
			String lsTelefono = loParams.getString("psTelefonoContacto");
			String lsEmail = loParams.getString("psEmailContacto");
			String lsFecNac = loParams.getString("psFecNacContacto");
			String lsDescripcion = loParams.getString("psDescripcionContacto");

			lblNombre.setText(lsNombre);
			lblTelefono.setText(lsTelefono);
			lblEmail.setText(lsEmail);
			lblFecNac.setText(lsFecNac);
			lblDescripcion.setText(lsDescripcion);
		}
		catch (Exception ex) {
			Toast.makeText(getBaseContext(), getResources().getString(R.string.MainActCreateErr), Toast.LENGTH_SHORT).show();
		}
	}

	protected void CreaBtnEditar(){
		btnEditar = findViewById(R.id.btnEditar);
		btnEditar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
					Intent loIntent = new Intent(ConfirmationActivity.this, MainActivity.class);
					loIntent.putExtra("psNombreContacto", lblNombre.getText().toString());
					loIntent.putExtra("psTelefonoContacto", lblTelefono.getText().toString());
					loIntent.putExtra("psEmailContacto", lblEmail.getText().toString());
					loIntent.putExtra("psFecNacContacto", lblFecNac.getText().toString());
					loIntent.putExtra("psDescripcionContacto", lblDescripcion.getText().toString());
					startActivity(loIntent);
					finish();
				}
				catch (Exception ex) {
					Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent loIntent = new Intent(ConfirmationActivity.this, MainActivity.class);
			startActivity(loIntent);
			finish();
		}
		return super.onKeyDown(keyCode, event);

	}
}