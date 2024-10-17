package com.example.fitnesstracking;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class ProgressFragment extends Fragment {

    private LineChart progressChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("ProgressFragment", "onCreateView: ProgressFragment view created");
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        progressChart = view.findViewById(R.id.progress_chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 10f));
        entries.add(new Entry(1f, 20f));
        entries.add(new Entry(2f, 30f));

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        LineData lineData = new LineData(dataSet);
        progressChart.setData(lineData);
        progressChart.invalidate();

        return view;
    }
}
