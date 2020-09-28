package com.yuki.beernote

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val beerData = args.BeerParams
        val chart:RadarChart = view.findViewById(R.id.BeerRadarChart)
        var entryList = mutableListOf<RadarEntry>()

        entryList.add(RadarEntry(beerData.body))
        entryList.add(RadarEntry(beerData.sharpness))
        entryList.add(RadarEntry(beerData.sour))
        entryList.add(RadarEntry(beerData.sweet))
        entryList.add(RadarEntry(beerData.bitter))

        val beerDataSet: RadarDataSet = RadarDataSet(entryList,"ビールの味の評価")
        val beerDataSets: IRadarDataSet = beerDataSet
        val data: RadarData = RadarData(beerDataSets)
        chart.data = data

        chart.description.isEnabled = false

        chart.xAxis.apply {
            textSize = 9f
            yOffset = 0f
            xOffset = 0f
            valueFormatter = object : ValueFormatter(){
                private val beerParams = arrayOf("コク","キレ","酸味","甘味","苦味")
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return beerParams[value.toInt() % beerParams.size]
                }
            }
        }

        chart.yAxis.apply{
            textSize = 9f
            axisMinimum = 0f
            axisMaximum = 5f
        }
        chart.setTouchEnabled(false)
        chart.legend.isEnabled = false

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}