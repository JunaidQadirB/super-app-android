package com.moonbear.carmarz.parcel.fragmentandactivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.moonbear.carmarz.Interface.AdapterClickListener;
import com.moonbear.carmarz.model.GrabCarModel;
import com.moonbear.carmarz.model.RideTypeModel;
import com.moonbear.carmarz.model.VehicleTypeModel;
import com.moonbear.carmarz.R;
import com.moonbear.carmarz.databinding.FragmentVehicleTypeBinding;
import com.moonbear.carmarz.parcel.adapter.VehicleTypeAdapter;

import java.util.ArrayList;


public class VehicleTypeFragment extends BottomSheetDialogFragment {

    public static String selectItem = "";

    ArrayList<VehicleTypeModel> vehicleTypeModelArrayList = new ArrayList<>();
    VehicleTypeAdapter vehicleTypeAdapter;
    ArrayList<GrabCarModel> vehicleList = new ArrayList<>();
    Bundle bundle;
    String selectedGrab, rideType;
    ArrayList<RideTypeModel> vehicleCategoriesList = new ArrayList<>();
    FragmentVehicleTypeBinding binding;
    public VehicleTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVehicleTypeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        if (bundle != null) {
            vehicleList = (ArrayList<GrabCarModel>) bundle.getSerializable("vehicleList");
            vehicleCategoriesList = (ArrayList<RideTypeModel>) bundle.getSerializable("vehicleCategoriesList");
            selectedGrab = bundle.getString("selectedGrab");
            rideType = bundle.getString("rideType");
        }

        methodSetVehicleTypeAdapter();

        return view;
    }


    private void methodSetVehicleTypeAdapter() {

        vehicleTypeAdapter = new VehicleTypeAdapter(getActivity(), vehicleTypeModelArrayList, new AdapterClickListener() {
            @Override
            public void onItemClickListener(int position, Object model, View view) {

                VehicleTypeModel hospitalClinicModel = (VehicleTypeModel) model;


                switch (view.getId()) {

                    case R.id.mainLayout:

                        selectItem = hospitalClinicModel.getId();
                        vehicleTypeAdapter.notifyDataSetChanged();

                        break;

                    default:
                        break;
                }
            }
        });

        binding.vehicleTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.vehicleTypeRecyclerView.setAdapter(vehicleTypeAdapter);
        vehicleTypeAdapter.notifyDataSetChanged();


    }


}