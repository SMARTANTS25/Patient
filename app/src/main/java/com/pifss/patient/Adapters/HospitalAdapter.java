package com.pifss.patient.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pifss.patient.R;

import java.util.ArrayList;

/**
 * Created by SMARTANS on 3/27/2017.
 */

public class HospitalAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    ArrayList<String> model;
    Activity context;
    public HospitalAdapter(ArrayList<String> model, Activity context) {


        this.model = model;
        this.context = context;


        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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


        View view = inflater.inflate(R.layout.listhospitalview,null);


       // ImageView imgHospitsl= (ImageView) view.findViewById(R.id.imageViewHospital);
        TextView tvHospitalTitle= (TextView) view.findViewById(R.id.textViewHospitalTitle);
        TextView tvHospitalDescription= (TextView) view.findViewById(R.id.textViewDescription);
        TextView tvHospitalEmail= (TextView) view.findViewById(R.id.textViewYear);

        String pos = model.get(position);


      //  imgHospitsl.setImageResource(hospital.getI);

        tvHospitalTitle.setText(pos.toString());
        tvHospitalDescription.setText(pos.toString());
        tvHospitalEmail.setText(pos.toString());
        return view;
    }
}
