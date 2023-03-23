package com.example.patientproject.presentation.features.patients.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.presentation.databinding.ItemPatientBinding

class PatientsAdapter(private val patientsList: List<PatientsRemoteModel>) :
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

    private var indexLastSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding = ItemPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.bind(patientsList[position], position)
    }

    override fun getItemCount(): Int {
        return patientsList.size
    }

    inner class PatientsViewHolder(private val binding: ItemPatientBinding) :
        ViewHolder(binding.root) {
        fun bind(patient: PatientsRemoteModel, position: Int) {
            binding.data = patient
            binding.root.setOnClickListener {
                if (position != indexLastSelected) {
                    if (indexLastSelected != -1) {
                        patientsList[indexLastSelected].selected = false
                        notifyItemChanged(indexLastSelected)
                    }
                }
                indexLastSelected = position
                patientsList[position].selected = true
                notifyItemChanged(position)
            }
        }
    }

}