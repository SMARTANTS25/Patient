package com.pifss.patient.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pifss.patient.R;
import com.pifss.patient.utils.Doctor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HelgenHills on 3/27/17.
 */

public class DoctorAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    ArrayList<Doctor> model;
    Activity context;

    public DoctorAdapter(ArrayList<Doctor> model, Activity context) {
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

        View v = inflater.inflate(R.layout.doctor_item_list,null);

        ImageView docProfile= (ImageView) v.findViewById(R.id.DoctorItemList_DoctorProfileImage);
        TextView docSpecialty= (TextView) v.findViewById(R.id.DoctorItemList_DoctorSpecialtyTV);
        TextView docGender = (TextView) v.findViewById(R.id.DoctorItemList_DoctorGenderTV);
        TextView docName= (TextView) v.findViewById(R.id.DoctorItemList_DoctorNameTV);

        Doctor m=model.get(position);

        // omg its a change
        //docProfile.setImageResource(m.getImageUrl());

        if (m.getImageUrl() != null && m.getImageUrl().length() >= 4 &&  m.getImageUrl().isEmpty() == false ) {
            Picasso.with(context).load(m.getImageUrl()).into(docProfile);
        }
        docSpecialty.setText(m.getSpecialityId());

        if (m.getGender().length() > 0 && (m.getGender().charAt(0)+"").equalsIgnoreCase("f")){
            docGender.setText(R.string.FemaleReg_RadioButton);
        }else{
            docGender.setText(R.string.MaleReg_RadioButton);
        }


        docName.setText(m.getFirstName() + " " + m.getMiddleName() + " " +m.getLastName());


        /*docProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Doctor in the HOUSE", Toast.LENGTH_SHORT).show();
            }
        });*/

        return v;
    }
}
