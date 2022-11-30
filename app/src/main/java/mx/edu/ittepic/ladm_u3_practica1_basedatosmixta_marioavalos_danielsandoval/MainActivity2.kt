package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val BD = this.intent.extras!!.getString("BD")!!

        when(BD){
            "NUBE"->{
                btnBuscar.isVisible = true
                AlumnoInteresado(this).mostrarDatosDeLaNube(listaRegistros)

            }
            "LOCAL" ->{
                AlumnoInteresado(this).mostrarRegistrosLocales(listaRegistros)
            }
        }

        btnAtras.setOnClickListener {
            finish()
        }

        listaRegistros.setOnItemClickListener { adapterView, view, i, l ->
            val otraActivity = Intent(this,MainActivity3::class.java)
            otraActivity.putExtra("INDEX",i)
            startActivity(otraActivity)
        }

        btnBuscar.setOnClickListener {
            //Codigo para construir un Linear Layout
            var layin = LinearLayout(this)
            var comboCampos = Spinner(this)
            var itemsCampos = ArrayList<String>()
            var claveBusqueda = EditText(this)

            itemsCampos.add("Fecha")
            itemsCampos.add("Carrera 1")
            itemsCampos.add("Carrera 2")
            itemsCampos.add("Escuela")

            comboCampos.adapter = ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1,
                itemsCampos)
            claveBusqueda.hint = "En caso de buscar fecha: yyyy-mm-dd.\nPuede ser año, mes, dia o combinado"

            layin.orientation = LinearLayout.VERTICAL

            layin.addView(comboCampos)
            layin.addView(claveBusqueda)
            AlertDialog.Builder(this).setTitle("ATENCION")
                .setMessage("ELIJA CAMPO PARA BUSQUEDA")
                .setView(layin)
                .setPositiveButton("BUSCAR"){d,i->
                    AlumnoInteresado(this).consulta(comboCampos,claveBusqueda,listaRegistros)
                }
                .setNeutralButton("CANCELAR"){d,i->}
                .show()
        }

    }

    override fun onRestart() {
        super.onRestart()
        AlumnoInteresado(this).mostrarDatosDeLaNube(listaRegistros)
    }

}