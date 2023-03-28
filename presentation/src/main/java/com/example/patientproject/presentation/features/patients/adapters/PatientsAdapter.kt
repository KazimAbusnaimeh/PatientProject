package com.example.patientproject.presentation.features.patients.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.patientproject.domain.models.patient.PatientsRemoteModel
import com.example.patientproject.presentation.databinding.ItemPatientBinding

class PatientsAdapter :
    ListAdapter<PatientsRemoteModel, PatientsAdapter.PatientsViewHolder>(PatientDiffUtil()) {

    private var indexLastSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding = ItemPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }


    inner class PatientsViewHolder(private val binding: ItemPatientBinding) :
        ViewHolder(binding.root) {
        fun bind(patient: PatientsRemoteModel, position: Int) {
            binding.data = patient
            binding.root.setOnClickListener {
                if (position != indexLastSelected) {
                    if (indexLastSelected != -1) {
                        getItem(position).selected = false
                        notifyItemChanged(indexLastSelected)
                    }
                }
                indexLastSelected = position
                getItem(position).selected = true
                notifyItemChanged(position)
            }
        }
    }

    class PatientDiffUtil : DiffUtil.ItemCallback<PatientsRemoteModel>() {
        override fun areItemsTheSame(
            oldItem: PatientsRemoteModel,
            newItem: PatientsRemoteModel
        ): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: PatientsRemoteModel,
            newItem: PatientsRemoteModel
        ): Boolean {
            return newItem == oldItem
        }

    }

}