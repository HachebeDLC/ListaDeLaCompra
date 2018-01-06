package es.danihb.listadelacompra


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 */

class alimentoFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var total: TextView
    private lateinit var presupuesto: EditText
    private lateinit var falta: TextView
    private var totalgastado: Float = 0F
    private var v_presupuesto: Int = 0

    companion object {
        var alimentos: ArrayList<Alimento> = ArrayList()
        fun addAlimentos(nombre: String, cantidad: Int, precio: Float) {
            alimentos.add(Alimento(nombre, cantidad, precio))
            Log.d("Nombre:", nombre)
            Log.d("Cantidad:", cantidad.toString())
            Log.d("Precio:", precio.toString())
        }

        fun editAlimentos(posicion: Int, nombre: String, cantidad: Int, precio: Float) {
            alimentos[posicion] = Alimento(nombre, cantidad, precio)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alimento_list, container, false)
        listView = view!!.findViewById(R.id.list)
        val adapter = alimentoAdapter(context, alimentos)
        listView.adapter = adapter

        return view
    }

    override fun onStart() {
        presupuesto.setText(v_presupuesto.toString())
        calculartotal()
        super.onStart()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        presupuesto = view!!.findViewById(R.id.et_presupuesto)
        falta = view!!.findViewById(R.id.tv_falta)
        total = view!!.findViewById(R.id.tv_indicador)
        listView = view!!.findViewById(R.id.list)

        val adapter = alimentoAdapter(context, alimentos)
        listView.adapter = adapter

        presupuesto.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                v_presupuesto = presupuesto.text.toString().toInt()
                calculartotal()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val nextFrag = addAlimentoFragment()
            val args = Bundle()
            args.putInt("cantidad", alimentos[position].getCantidad())
            args.putFloat("precio", alimentos[position].getPrecio())
            args.putString("nombre", alimentos[position].getName())
            args.putInt("posicion", position)
            nextFrag.arguments = args
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentView, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit()
            //editAlimentos(position, "Prueba", 5, 4.5F)
            actualizarVista()
        }
        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->

            alimentos.removeAt(position)
            actualizarVista()
            true
        }

        super.onActivityCreated(savedInstanceState)
    }

    private fun actualizarVista() {
        val adapter = alimentoAdapter(context, alimentos)
        listView.adapter = adapter
        calculartotal()
    }

    fun calculartotal() {

        totalgastado = alimentos
                .map { it.getPrecio() * it.getCantidad() }
                .sum()
        total.text = "Total: $totalgastado"
        if (presupuesto.text.toString().isNotEmpty()) {


            falta.text = "Falta: ${presupuesto.text.toString().toFloat() - totalgastado}"
        }
    }

}// Required empty public constructor
