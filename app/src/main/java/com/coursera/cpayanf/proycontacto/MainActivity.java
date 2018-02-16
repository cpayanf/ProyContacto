package com.coursera.cpayanf.proycontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	EditText txtNombre;
	EditText txtTelefono;
	DatePicker dtpFechaNacimiento;
	EditText txtEmail;
	EditText txtDescripcion;
	Button btnSiguiente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String lsNombre;
		String lsTelefono;
		String lsEmail;
		String lsFecNac;
		String lsDescripcion;
		String[] lsFecha;
		try
		{
			CreaBtnSiguiente();

			txtNombre = findViewById(R.id.txtValNombre);
			txtTelefono = findViewById(R.id.txtValTelefono);
			txtEmail = findViewById(R.id.txtValEmail);
			dtpFechaNacimiento = findViewById(R.id.dtpFechaNacimiento);
			txtDescripcion = findViewById(R.id.txtValDescripcion);

			txtNombre.setSelectAllOnFocus(true);
			txtTelefono.setSelectAllOnFocus(true);
			txtEmail.setSelectAllOnFocus(true);
			txtDescripcion.setSelectAllOnFocus(true);

			Bundle loParams = getIntent().getExtras();
			lsNombre = loParams.getString("psNombreContacto");
			lsTelefono = loParams.getString("psTelefonoContacto");
			lsEmail = loParams.getString("psEmailContacto");
			lsFecNac = loParams.getString("psFecNacContacto");
			lsDescripcion = loParams.getString("psDescripcionContacto");

			lsFecha = lsFecNac.split("-");
			txtNombre.setText(lsNombre);
			txtTelefono.setText(lsTelefono);
			txtEmail.setText(lsEmail);
			dtpFechaNacimiento.updateDate(Integer.parseInt(lsFecha[2]),
					Integer.parseInt(lsFecha[1]),
					Integer.parseInt(lsFecha[0]));
			txtDescripcion.setText(lsDescripcion);
		}
		catch (Exception ex) {
			Toast.makeText(getBaseContext(), getResources().getString(R.string.MainActCreateErr), Toast.LENGTH_SHORT).show();
		}
	}

	protected void CreaBtnSiguiente(){
		btnSiguiente = findViewById(R.id.btnSiguiente);
		btnSiguiente.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Intent loIntent = new Intent(MainActivity.this, ConfirmationActivity.class);
					loIntent.putExtra("psNombreContacto", txtNombre.getText().toString());
					loIntent.putExtra("psTelefonoContacto", txtTelefono.getText().toString());
					loIntent.putExtra("psEmailContacto", txtEmail.getText().toString());
					loIntent.putExtra("psFecNacContacto", "" + dtpFechaNacimiento.getDayOfMonth()
							+ "-" + (dtpFechaNacimiento.getMonth() + 1)
							+ "-" + dtpFechaNacimiento.getYear());
					loIntent.putExtra("psDescripcionContacto", txtDescripcion.getText().toString());
					startActivity(loIntent);
					finish();
				}
				catch (Exception ex)
				{
					Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
