package com.example.cesa;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btEnviar = findViewById(R.id.btEnviar);
        btEnviar.setOnClickListener(this);

        //Esto obliga a tener que escribir el codigo entero
        final EditText etCod = findViewById(R.id.etCodigo);
        etCod.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etCod.isFocused())
                    etCod.setText("");
            }
        });

        //Compruebo si ya ha introducido un CIF como prefencia
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString(getString(R.string.preferencia_cif), null) != null){

            //Al tener guardado el CIF se lo pone por defecto
            EditText etCif = findViewById(R.id.etCif);
            etCif.setText(prefs.getString(getString(R.string.preferencia_cif), null));
        }

        //DatePickers para fechas
        EditText etFec = findViewById(R.id.etFechaFac);
        etFec.setOnClickListener(this);
        EditText etVen = findViewById(R.id.etFechaVen);
        etVen.setOnClickListener(this);
    }

    //Infla el Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cambiar_cif, menu);
        return true;
    }

    //Controla el Action Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.itemAjustes:
                final SharedPreferences.Editor editor = prefs.edit();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.titulo_cif);

                // Set up the input
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setMaxWidth(9);
                if (prefs.getString(getString(R.string.preferencia_cif), null) != null){
                    input.setText(prefs.getString(getString(R.string.preferencia_cif), null));
                }
                builder.setView(input);

                builder.setPositiveButton(R.string.guardar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString(getString(R.string.preferencia_cif), input.getText().toString());
                        editor.commit();

                        //Al tener guardado el CIF se lo pone por defecto
                        EditText etCif = findViewById(R.id.etCif);
                        etCif.setText(prefs.getString(getString(R.string.preferencia_cif), null));
                    }
                });
                builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btEnviar:
                if (validar(v)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(R.string.pregunta_envio)
                            .setPositiveButton((getString(R.string.si)),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            try {
                                                enviarFactura();
                                            } catch (ParseException | IOException e) {
                                                Toast.makeText(MainActivity.this, R.string.error_desconocido, Toast.LENGTH_LONG);
                                            }
                                        }
                                    })
                            .setNegativeButton(R.string.no,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //No hace nada
                                        }
                                    });
                    builder.create().show();
                }

                break;

            case R.id.etFechaFac:
                guardarFecha((EditText) findViewById(R.id.etFechaFac), getString(R.string.fecha_de_factura));
                validar(v);
                break;

            case R.id.etFechaVen:
                guardarFecha((EditText) findViewById(R.id.etFechaVen), getString(R.string.fecha_de_vencimiento));
                validar(v);
                break;

            default: break;
        }
    }

    public boolean validar(View v){
        boolean valido = true;

        //Recojo datos
        EditText etCod = findViewById(R.id.etCodigo);
        EditText etCif = findViewById(R.id.etCif);
        EditText etNum = findViewById(R.id.etNumero);
        EditText etBas = findViewById(R.id.etBase);
        EditText etIva = findViewById(R.id.etIva);
        EditText etTot = findViewById(R.id.etTotal);
        EditText etFec = findViewById(R.id.etFechaFac);
        EditText etVen = findViewById(R.id.etFechaVen);

        //Recojo datos
        TextInputLayout tilCod = findViewById(R.id.tilCodigo);
        TextInputLayout tilCif = findViewById(R.id.tilCif);
        TextInputLayout tilNumero = findViewById(R.id.tilNumero);
        TextInputLayout tilBase = findViewById(R.id.tilBase);
        TextInputLayout tilIva = findViewById(R.id.tilIva);
        TextInputLayout tilTotal = findViewById(R.id.tilTotal);
        TextInputLayout tilFechaFac = findViewById(R.id.tilFechaFac);
        TextInputLayout tilFechaVen = findViewById(R.id.tilFechaVen);

        //Recorro todos los layouts y en caso de que estén vacios muestro el error
        EditText[] edits = {etCod, etCif, etNum, etBas, etIva, etTot, etFec, etVen};
        TextInputLayout[] layouts = {tilCod, tilCif, tilNumero, tilBase, tilIva, tilTotal, tilFechaFac, tilFechaVen};

        for (int i = 0; i < edits.length; i++){
            if (TextUtils.isEmpty(edits[i].getText())) {
                mostrarError(layouts[i], getString(R.string.obligatorio));
                valido = false;

            }else
                mostrarError(layouts[i], null);
        }

        return valido;
    }

    private void enviarFactura() throws ParseException, IOException {
        //Recojo datos
        EditText etCif = findViewById(R.id.etCif);
        EditText etRaz = findViewById(R.id.etRazon);
        EditText etDes = findViewById(R.id.etDescripcion);
        EditText etNum = findViewById(R.id.etNumero);
        EditText etBas = findViewById(R.id.etBase);
        EditText etIva = findViewById(R.id.etIva);
        EditText etTot = findViewById(R.id.etTotal);
        EditText etFec = findViewById(R.id.etFechaFac);
        EditText etVen = findViewById(R.id.etFechaVen);


        //Los meto en una factura
        Factura factura = new Factura();
        factura.setCif(String.valueOf(etCif.getText()));
        factura.setRazon(String.valueOf(etRaz.getText()));
        factura.setDescripcion(String.valueOf(etDes.getText()));
        factura.setNum(Integer.parseInt(String.valueOf(etNum.getText())));
        factura.setBase(Float.parseFloat(String.valueOf(etBas.getText())));
        factura.setIva(Float.parseFloat(String.valueOf(etIva.getText())));
        factura.setTotal(Float.parseFloat(String.valueOf(etTot.getText())));
        factura.setFechaFac(StringToDate(String.valueOf(etFec.getText())));
        factura.setFechaVen(StringToDate(String.valueOf(etVen.getText())));

        limpiar();

        /*//Obtengo el fichero y lo envio por email
        Uri uri = Uri.fromFile(factura.toFile());
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("application/txt");

        //Agrega email o emails de destinatario.
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { "email@dominio.com" });
        i.putExtra(Intent.EXTRA_SUBJECT, "Nombre fichero");
        i.putExtra(Intent.EXTRA_STREAM,  uri);
        startActivity(Intent.createChooser(i, "Enviar e-mail mediante:"));*/
    }

    private void guardarFecha(final EditText et, String titulo){
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(titulo);
        final MaterialDatePicker materialDatePicker = builder.build();
        materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Date fecha = new Date((long) materialDatePicker.getSelection());
                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(getString(R.string.tipo_fecha));
                et.setText(format.format(fecha));
            }
        });
    }

    private void limpiar(){
        //Limpiar texto
        EditText etCod = findViewById(R.id.etCodigo);
        EditText etCif = findViewById(R.id.etCif);
        EditText etRaz = findViewById(R.id.etRazon);
        EditText etDes = findViewById(R.id.etDescripcion);
        EditText etNum = findViewById(R.id.etNumero);
        EditText etBas = findViewById(R.id.etBase);
        EditText etIva = findViewById(R.id.etIva);
        EditText etTot = findViewById(R.id.etTotal);
        EditText etFec = findViewById(R.id.etFechaFac);
        EditText etVen = findViewById(R.id.etFechaVen);


        EditText[] edits = {etCod, etCif, etRaz, etDes, etNum, etBas, etIva, etTot, etFec, etVen};
        for (int i = 0; i < edits.length; i++)
            edits[i].setText("");

        //Limpiar errores
        TextInputLayout tilCod = findViewById(R.id.tilCodigo);
        TextInputLayout tilCif = findViewById(R.id.tilCif);
        TextInputLayout tilNumero = findViewById(R.id.tilNumero);
        TextInputLayout tilBase = findViewById(R.id.tilBase);
        TextInputLayout tilIva = findViewById(R.id.tilIva);
        TextInputLayout tilTotal = findViewById(R.id.tilTotal);
        TextInputLayout tilFechaFac = findViewById(R.id.tilFechaFac);
        TextInputLayout tilFechaVen = findViewById(R.id.tilFechaVen);
        TextInputLayout[] layouts = {tilCod, tilCif, tilNumero, tilBase, tilIva, tilTotal, tilFechaFac, tilFechaVen};
        for (int i = 0; i < layouts.length; i++)
            mostrarError(layouts[i], null);
    }

    private Date StringToDate(String cad) throws ParseException {
        @SuppressLint("SimpleDateFormat") Date date = new SimpleDateFormat(getString(R.string.tipo_fecha)).parse(cad);
        return date;
    }

    private static void mostrarError(@NonNull TextInputLayout textInputLayout, String msg) {
        textInputLayout.setError(msg);
        if (msg == null) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
        }
    }
}
