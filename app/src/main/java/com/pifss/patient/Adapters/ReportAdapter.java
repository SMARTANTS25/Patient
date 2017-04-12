package com.pifss.patient.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pifss.patient.R;
import com.pifss.patient.utils.Reports;

import java.util.ArrayList;

/**
 * Created by SMARTANS on 4/12/2017.
 */

public class ReportAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    ArrayList<Reports> model = new ArrayList<>();
    Activity context;

    public ReportAdapter(ArrayList<Reports> model, Activity context) {
        this.model = model;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        View view = inflater.inflate(R.layout.my_report_list , null);

        Reports reports = model.get(position);
        ImageView DocImg = (ImageView) view.findViewById(R.id.MyReport_Docimg);
        TextView Headache = (TextView) view.findViewById(R.id.MyReports_HeadacheTV);
        TextView heart = (TextView) view.findViewById(R.id.MyReport_HeartTV);





        return view;
    }
}
