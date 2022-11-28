package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val BD = this.intent.extras!!.getString("BD")!!

        when(BD){
            "NUBE"->{
                AlumnoInteresado(this).mostrarDatosDeLaNube(listaRegistros)
            }
            "LOCAL" ->{
                AlumnoInteresado(this).mostrarRegistrosLocales(listaRegistros)
            }
        }

        btnAtras.setOnClickListener {
            finish()
        }

    }

}