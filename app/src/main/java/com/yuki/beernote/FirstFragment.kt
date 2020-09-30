package com.yuki.beernote

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var dao: BeerDao
    private lateinit var adapter: BeerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context?: return

        val db = BeerDatabase.getInstance(context)
        dao = db.beerDao()

        val beers = dao.getAll()

        adapter = BeerAdapter(beers,itemListener)
        listView.adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.addButton).setOnClickListener {
            val dialog = ParamSettingDialog(dao,adapter)
            fragmentManager?.run{
                dialog.show(this,"setting")
            }
        }
    }

    val itemListener = object : BeerItemClickListener {
        override fun onBeerNameClick(beer:Beer) {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(beer)
            findNavController().navigate(action)
        }

        override fun onEditClick(beer: Beer) {
            val dialog = UpdateParamDialog(dao,adapter,beer)
            fragmentManager?.run {
                dialog.show(this, "update")
            }
        }
        override fun onDeleteClick(beer:Beer) {
            dao.delete(beer)
            adapter.beers = dao.getAll()
        }
    }

    class BeerAdapter(beers: List<Beer>,private val listener: BeerItemClickListener): BaseAdapter() {

        var beers:List<Beer> = beers
            set(beers) {
                field = beers
                notifyDataSetChanged()
            }
        override fun getCount() = beers.size

        override fun getItem(i: Int) = beers[i]

        override fun getItemId(i: Int) = i.toLong()

        override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
            val beer = getItem(i)
            val rowView = view ?: LayoutInflater.from(viewGroup.context).inflate(R.layout.beer_items, viewGroup, false)

            rowView.findViewById<TextView>(R.id.beerName).apply {
                text = beer.name
                setOnClickListener {
                    listener.onBeerNameClick(beer)
                }
            }

            rowView.findViewById<ImageView>(R.id.beerDeleteButton).setOnClickListener{
                    listener.onDeleteClick(beer)
                }

            rowView.findViewById<ImageView>(R.id.beerEdit).setOnClickListener{
                    listener.onEditClick(beer)
            }


            return rowView
        }
    }

    interface BeerItemClickListener{
        fun onBeerNameClick(beer:Beer)
        fun onEditClick(beer:Beer)
        fun onDeleteClick(beer:Beer)
    }
}