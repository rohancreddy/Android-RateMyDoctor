package com.virinchi.www.ratemydoctor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by rohan on 9/30/2016.
 */
public class AdapterDoctor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    List<DataDoctor> data = Collections.emptyList();
    DataDoctor current;

    public AdapterDoctor(Context context, List<DataDoctor> data){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.container_doctor, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        DataDoctor current = data.get(position);
        myHolder.textDoctorName.setText(current.doctorName);
        myHolder.textSpeciality.setText(current.speciality);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textDoctorName;
        TextView textSpeciality;

        public MyHolder(View itemView) {
            super(itemView);
            textDoctorName = (TextView)itemView.findViewById(R.id.textDoctorName);
            textSpeciality = (TextView)itemView.findViewById(R.id.textSpeciality);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "You Clicked ", Toast.LENGTH_SHORT).show();
        }
    }
}
