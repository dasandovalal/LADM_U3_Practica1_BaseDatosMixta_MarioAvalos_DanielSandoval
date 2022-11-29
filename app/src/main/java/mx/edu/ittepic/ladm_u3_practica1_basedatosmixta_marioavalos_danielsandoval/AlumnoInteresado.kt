package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import android.R
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.sql.Date

data class AlumnoInteresado(val m:AppCompatActivity){

    private var baseDatos = BaseDatos(m,"REGISTROS",null,1)

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
            Toast.makeText(m,"REGISTRO GUARDADO LOCALMENTE", Toast.LENGTH_LONG).show()
        }
        return true
    }

    fun guardarEnLaNube(){
        val nube = Firebase.firestore
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

                nube.collection("ALUMNO_INTERESADO")
                    .add(datos)
                    .addOnSuccessListener {
                        Toast.makeText(m,"GUARDADO EN LA NUBE",Toast.LENGTH_LONG).show()
                        Log.d("~Success","$it")
                    }
                    .addOnFailureListener {
                        Log.w("~Error","Error",it)
                        AlertDialog.Builder(m)
                            .setTitle("ERROR EN FIREBASE")
                            .setMessage(it.message!!)
                            .show()
                    }

                eliminarPorID(resultado.getInt(0).toString())

            } while (resultado.moveToNext())

        } else {
            AlertDialog.Builder(m)
                .setTitle("ERROR")
                .setMessage("NO HAY REGISTROS LOCALES")
                .show()
        }

    }

    fun mostrarRegistrosLocales(listaParaMostrar:ListView){
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
        listaParaMostrar.adapter = ArrayAdapter<String>(m,
            android.R.layout.simple_list_item_1, lista)
    }

    fun mostrarDatosDeLaNube(listaParaMostrar:ListView){
        FirebaseFirestore.getInstance()
            .collection("ALUMNO_INTERESADO")
            .addSnapshotListener { value, error ->
                if(error != null){
                    AlertDialog.Builder(m)
                        .setTitle("ERROR")
                        .setMessage("ERROR EN FIREBASE")
                        .show()
                    return@addSnapshotListener
                }
                var lista = ArrayList<String>()
                for(documento in value!!){
                    var datos = "\n" + documento.getString("NOMBRE")!! +
                            "\n" + documento.getString("ESCUELA_ACTUAL") +
                            "\n" + documento.getString("TELEFONO") +
                            "\n" + documento.getString("CARRERA_UNO") +
                            "\n" + documento.getString("CARRERA_DOS") +
                            "\n" + documento.getString("CORREO") +
                            "\n" + documento.getString("FECHA") +
                            "\n" + documento.getString("HORA") + "\n"
                    lista.add(datos)
                }
                listaParaMostrar.adapter = ArrayAdapter<String>(m,
                    android.R.layout.simple_list_item_1, lista)
            }
    }

    fun eliminarPorID(idRegistro: String){
        var resultado = baseDatos.writableDatabase
            .delete("ALUMNO_INTERESADO","ID=?", arrayOf(idRegistro))
    }

    fun settearDatosParaActualizar(indexSeleccionado: Int){
        var dbID = FirebaseFirestore.getInstance()
            .collection("ALUMNO_INTERESADO")
            .addSnapshotListener { value, error ->
                var listaID = ArrayList<String>()
                for (document in value!!){
                    listaID.add(document.id)
                }
                var idRegistro = listaID[indexSeleccionado]
                var dbDocument = FirebaseFirestore.getInstance()
                    .collection("ALUMNO_INTERESADO")
                    .document(idRegistro)
                    .addSnapshotListener { value, error ->
                        var otraVentana = Intent(m,MainActivity3::class.java)
                        otraVentana.putExtra("NOMBRE",value!!.getString("NOMBRE"))
                        otraVentana.putExtra("ESCUELA",value!!.getString("ESCUELA_ACTUAL"))
                        otraVentana.putExtra("TELEFONO",value!!.getString("TELEFONO"))
                        otraVentana.putExtra("CARRERA_UNO",value!!.getString("CARRERA_UNO"))
                        otraVentana.putExtra("CARRERA_DOS",value!!.getString("CARRERA_DOS"))
                        otraVentana.putExtra("CORREO",value!!.getString("CORREO"))
                        m.startActivity(otraVentana)
                    }

            }
    }



}
