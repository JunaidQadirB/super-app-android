package com.moonbear.carmarz.food;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.moonbear.carmarz.activitiesandfragment.FoodActivity;
import com.moonbear.carmarz.codeclasses.MyPreferences;
import com.moonbear.carmarz.R;
import com.moonbear.carmarz.databinding.DialogResturantClosedBinding;
import com.moonbear.carmarz.model.CalculationModel;
import com.moonbear.carmarz.model.ResturantModel;

import java.util.ArrayList;

import io.paperdb.Paper;


public class ResturantClosedDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    DialogResturantClosedBinding binding;
    Bundle bundle;
    ArrayList<CalculationModel> carList = new ArrayList<>();
    ResturantModel resturantModel;

    int browserContainer;
    private BottomSheetBehavior mBehavior;

    public ResturantClosedDialog(int browserContainer) {
        this.browserContainer = browserContainer;
    }


    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(false);
        binding = DialogResturantClosedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setHideable(false);
        return  dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DialogResturantClosedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        bundle = getArguments();
        if (bundle != null) {
            carList = (ArrayList<CalculationModel>) bundle.getSerializable("carList");
            resturantModel = carList.get(0).getResturantModel();
        }

        initializeListeners();
        return view;
    }


    private void initializeListeners() {
        binding.checkoutBtn.setOnClickListener(this);
        binding.addItemBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.checkout_btn:
                Paper.book().delete("carList" + MyPreferences.getSharedPreference(getActivity()).getString(MyPreferences.USER_ID, ""));
                startActivity(new Intent(getActivity() , FoodActivity.class));
                getActivity().finish();
                break;

            case R.id.add_item_btn:
                dismiss();
                break;

            default:
                break;
        }
    }
}