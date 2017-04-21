package com.pifss.patient.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pifss.patient.R;
import com.pifss.patient.utils.Reports;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SMARTANS on 4/12/2017.
 */

public class ReportAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    public ArrayList<Reports> model = new ArrayList<>();
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

        final Reports reports = model.get(position);
        final ImageView DocImg = (ImageView) view.findViewById(R.id.MyReport_Docimg);
        final TextView DocName = (TextView) view.findViewById(R.id.MyRerports_DocName);
        TextView Headache = (TextView) view.findViewById(R.id.MyReports_HeadacheTV);
        TextView heart = (TextView) view.findViewById(R.id.MyReport_HeartTV);

//        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/doctor/"+reports.getDrId();
//
//
//        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(context);
//
//
//        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        String img="";
//                        JSONObject obj;
//                        try {
//
//                            obj = new JSONObject(response);
//                            img = obj.getString("imageUrl");
//                            String name = obj.getString("firstName")+ " "+obj.getString("lastName");
//                            DocName.setText(name);
//                            reports.doctorName = name;
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

        DocName.setText(reports.getName());
                        if (reports.getImageUrl() != null && reports.getImageUrl().length() >= 4 &&  reports.getImageUrl().isEmpty() == false ) {
                                 Picasso.with(context).load(reports.getImageUrl()).into(DocImg);
                             }

//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle error
//                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG);
//
//                    }
//                });

     //   queue.add(jsonReq);

        if (reports.getHeartbeatRate().equals("no"))
        heart.setText(R.string.nauseous_No);
        else heart.setText(R.string.nauseous_Yes);

        if (reports.getHeadache().equals("high"))
        Headache.setText(R.string.SendReport_High);
        else if (reports.getHeadache().equals("moderate"))
            Headache.setText(R.string.SendReport_Moderate);
        else Headache.setText(R.string.SendReport_Low);


        return view;
    }
}
