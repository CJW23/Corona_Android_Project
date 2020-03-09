package com.example.corona;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.ViewHolder> {

    private List<PatientData> patientDataList;
    private Context context;

    PatientListAdapter(List<PatientData> patientDataList, Context context) {
        this.patientDataList = patientDataList;
        this.context = context;
    }
    @NonNull
    @Override
    public PatientListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientListAdapter.ViewHolder holder, final int position) {
        final PatientData patientData = patientDataList.get(position);
        holder.patientid.setText(patientData.getPatientId());
        holder.age.setText("나이 : " + Integer.toString(patientData.getAge()));
        holder.sex.setText("성별 : " + patientData.getSex());
        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PatientDetailActivity.class);
                intent.putExtra("detail", patientData.getDetail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView patientid;
        TextView age;
        TextView sex;
        final View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            patientid = (TextView) itemView.findViewById(R.id.patientid);
            age = (TextView)itemView.findViewById(R.id.age);
            sex = (TextView)itemView.findViewById(R.id.sex);
            mView = itemView;
        }
    }
}
