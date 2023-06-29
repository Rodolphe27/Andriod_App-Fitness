package com.example.a07;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_test);

        LineChart chart = (LineChart) findViewById(R.id.chart);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>(getDataSet());
        LineData data = new LineData(dataSets);

        Description description = new Description();
        description.setText("My Chart");
        chart.setDescription(description);

        chart.setData(data);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(getXAxisValues()));
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private ArrayList<LineDataSet> getDataSet() {
        ArrayList<LineDataSet> dataSets = new ArrayList<>();

        ArrayList<Entry> valueSet1 = new ArrayList<>();
        valueSet1.add(new Entry(0, 110.000f)); // Jan
        valueSet1.add(new Entry(1, 40.000f)); // Feb
        valueSet1.add(new Entry(2, 60.000f)); // Mar
        valueSet1.add(new Entry(3, 30.000f)); // Apr
        valueSet1.add(new Entry(4, 90.000f)); // May
        valueSet1.add(new Entry(5, 100.000f)); // Jun
        valueSet1.add(new Entry(6, 20.000f)); // Jul
        valueSet1.add(new Entry(7, 80.000f)); // Aug
        valueSet1.add(new Entry(8, 60.000f)); // Sep
        valueSet1.add(new Entry(9, 30.000f)); // Oct
        valueSet1.add(new Entry(10, 90.000f)); // Nov
        valueSet1.add(new Entry(11, 100.000f)); // Dec

        ArrayList<Entry> valueSet2 = new ArrayList<>();
        valueSet2.add(new Entry(0, 150.000f)); // Jan
        valueSet2.add(new Entry(1, 90.000f)); // Feb
        valueSet2.add(new Entry(2, 120.000f)); // Mar
        valueSet2.add(new Entry(3, 60.000f)); // Apr
        valueSet2.add(new Entry(4, 20.000f)); // May
        valueSet2.add(new Entry(5, 80.000f)); // Jun
        valueSet2.add(new Entry(6, 90.000f)); // Jul
        valueSet2.add(new Entry(7, 30.000f)); // Aug
        valueSet2.add(new Entry(8, 60.000f)); // Sep
        valueSet2.add(new Entry(9, 90.000f)); // Oct
        valueSet2.add(new Entry(10, 100.000f)); // Nov
        valueSet2.add(new Entry(11, 110.000f)); // Dec

        LineDataSet lineDataSet1 = new LineDataSet(valueSet1, "Brand 1");
        // set color of line to purple
        lineDataSet1.setColor(Color.rgb(255, 0, 255));
        LineDataSet lineDataSet2 = new LineDataSet(valueSet2, "Brand 2");
        lineDataSet2.setColors(ColorTemplate.PASTEL_COLORS);

        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        xAxis.add("SEP");
        xAxis.add("OCT");
        xAxis.add("NOV");
        xAxis.add("DEC");
        return xAxis;
    }
}
