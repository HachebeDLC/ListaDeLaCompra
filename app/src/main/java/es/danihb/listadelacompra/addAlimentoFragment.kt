package es.danihb.listadelacompra


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_alimento.*


/**
 * A simple [Fragment] subclass.
 */
class addAlimentoFragment : Fragment() {
    private var posicion: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_alimento, container, false)
        val bt_aceptar: Button = view.findViewById(R.id.bt_aceptar)
        val et_nombre: EditText = view.findViewById(R.id.et_nombre)
        val et_precio: EditText = view.findViewById(R.id.et_precio)
        val et_cantidad: EditText = view.findViewById(R.id.et_cantidad)

        if (arguments == null) {
            et_nombre.setText("")
            et_precio.setText("")
            et_cantidad.setText("")
        } else {
            Log.d("Bundle", "El bundle está vacio")
            posicion = arguments!!.getInt("posicion")
            et_nombre.setText(arguments?.getString("nombre"))
            et_precio.setText(arguments?.getFloat("precio").toString())
            et_cantidad.setText(arguments?.getInt("cantidad").toString())
            bt_aceptar.text = "Editar"
        }
        bt_aceptar.setOnClickListener {
            when {
                et_nombre.text.toString().isBlank() -> Toast.makeText(context, "Tienes que introducir un alimento", Toast.LENGTH_SHORT).show()
                et_precio.text.toString().isBlank() -> Toast.makeText(context, "Tienes que introducir el precio", Toast.LENGTH_SHORT).show()
                et_cantidad.text.toString().isBlank() -> Toast.makeText(context, "Tienes que introducir una cantidad", Toast.LENGTH_SHORT).show()
                et_cantidad.text.toString().toInt() < 1 -> Toast.makeText(context, "Tienes que introducir una cantidad mayor que 0", Toast.LENGTH_SHORT).show()
                et_precio.text.toString().toFloat() < 0 -> Toast.makeText(context, "Tienes que introducir un precio valido", Toast.LENGTH_SHORT).show()

                else -> {
                    if (bt_aceptar.text == "Editar") {
                        alimentoFragment.editAlimentos(posicion, et_nombre.text.toString(), et_cantidad.text.toString().toInt(), et_precio.text.toString().toFloat())
                    } else {
                        alimentoFragment.addAlimentos(et_nombre.text.toString(), et_cantidad.text.toString().toInt(), et_precio.text.toString().toFloat())
                    }
                    arguments = null
                    val nextFrag = alimentoFragment()
                    activity!!.supportFragmentManager.popBackStackImmediate()

                }
            }
        }
        return view
    }


    override fun onStart() {
        if (arguments == null) {
            et_nombre.setText("")
            et_precio.setText("")
            et_cantidad.setText("")
        } else {
            Log.d("Bundle", "El bundle está vacio")
            posicion = arguments!!.getInt("posicion")
            et_nombre.setText(arguments?.getString("nombre"))
            et_precio.setText(arguments?.getFloat("precio").toString())
            et_cantidad.setText(arguments?.getInt("cantidad").toString())
            bt_aceptar.text = "Editar"
        }

        super.onStart()
    }

}// Required empty public constructor
