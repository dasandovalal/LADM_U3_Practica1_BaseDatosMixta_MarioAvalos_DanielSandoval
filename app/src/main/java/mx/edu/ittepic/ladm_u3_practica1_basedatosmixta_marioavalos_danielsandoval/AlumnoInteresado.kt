package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import android.R
import android.content.ContentValues
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.sql.Date

data class AlumnoInteresado(val m:AppCompatActivity){

    private var baseDatos = BaseDatos(m,"REGISTROS_9",null,1)

    fun registrarAlumnoInteresado(nombre:EditText,escuela:EditText,
                                  telefono:EditText,carreraUno:Spinner,
                                  carreraDos:Spinner,correo:EditText):Boolean {

        val NOMBRE = nombre.text.toString()
        val ESCUELA = escuela.text.toString()
        val TELEFONO = telefono.text.toString()
        val CARRERA_UNO = carreraUno.selectedItem.toString()
        val CARRERA_DOS = carreraDos.selectedItem.toString()
        val CORREO = correo.text.toString()

        var datos = ContentValues()
        datos.put("NOMBRE",NOMBRE)
        datos.put("ESCUELA_ACTUAL",ESCUELA)
        datos.put("TELEFONO",TELEFONO)
        datos.put("CARRERA_UNO",CARRERA_UNO)
        datos.put("CARRERA_DOS",CARRERA_DOS)
        datos.put("CORREO",CORREO)

        var res = baseDatos.writableDatabase.insert("ALUMNO_INTERESADO","ID",datos)
        if (res == -1L){
            AlertDialog.Builder(m)
                .setTitle("ERROR")
                .setMessage("NO SE PUDO GUARDAR").show()
        }else{
            Toast.makeText(m,"Se INSERTO CON EXITO", Toast.LENGTH_LONG).show()
        }
        return true
    }

    fun mostrarRegistros(){
        var registros = baseDatos.readableDatabase
        val lista = ArrayList<String>()
        var resultado = registros.query("ALUMNO_INTERESADO", arrayOf("*"), null,
            null, null, null, null)

        if (resultado.moveToFirst()) {
            do {
                val datos = "${resultado.getString(1)}" +
                        "\n${resultado.getString(2)}" +
                        "\n${resultado.getString(3)}" +
                        "\n${resultado.getString(4)}" +
                        "\n${resultado.getString(5)}" +
                        "\n${resultado.getString(6)}" +
                        "\n${resultado.getString(7)}" +
                        "\n${resultado.getString(8)}"
                lista.add(datos)
                //IDs.add(resultado.getInt(0))
            } while (resultado.moveToNext())

        } else {
            lista.add("LA TABLA ESTA VACIA")
        }

        m.listaRegistros.adapter = ArrayAdapter<String>(m,
            R.layout.simple_list_item_1, lista)
    }


    /*fun guardarEnLaNube(){
        var registros = baseDatos.readableDatabase
        var resultado = registros.query("ALUMNO_INTERESADO", arrayOf("*"), null,
            null, null, null, null)

        if (resultado.moveToFirst()) {
            do {
                var datos = hashMapOf(
                    "NOMBRE" to resultado.getString(1),
                    "ESCUELA_ACTUAL" to resultado.getString(2),
                    "TELEFONO" to resultado.getString(3),
                    "CARRERA_UNO" to resultado.getString(4),
                    "CARRERA_DOS" to resultado.getString(5),
                    "CORREO" to resultado.getString(6),
                    "FECHA" to resultado.getString(7),
                    "HORA" to resultado.getString(8)
                )

                FirebaseFirestore.getInstance().collection("ALUMNO_INTERESADO")
                    .add(datos)
                    .addOnSuccessListener {
                        Toast.makeText(m,"GUARDADO EN LA NUBE",Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        AlertDialog.Builder(m)
                            .setTitle("ERROR EN FIREBASE")
                            .setMessage(it.message!!)
                    }
            } while (resultado.moveToNext())

        } else {
            AlertDialog.Builder(m)
                .setTitle("ERROR")
                .setMessage("NO HAY REGISTROS LOCALES O NO HAY CONEXION A INTERNET.")
        }

    }

     */
}
