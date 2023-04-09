package com.moonbear.carmarz.parcel.fragmentandactivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.moonbear.carmarz.Interface.AdapterClickListener;
import com.moonbear.carmarz.Interface.FragmentCallBack;
import com.moonbear.carmarz.R;
import com.moonbear.carmarz.api.Singleton;
import com.moonbear.carmarz.codeclasses.Functions;
import com.moonbear.carmarz.databinding.FragmentTotalWeightBinding;
import com.moonbear.carmarz.parcel.adapter.TotalWeightAdapter;
import com.moonbear.carmarz.parcel.model.PackagesSizeSelectionModel;
import com.squareup.retrofitplus.api.RetrofitRequest;
import com.squareup.retrofitplus.interfaces.ApiCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class TotalWeightFragment extends BottomSheetDialogFragment{

    FragmentTotalWeightBinding binding;
    ArrayList<PackagesSizeSelectionModel> totalWeightModelArrayList = new ArrayList<>();
    TotalWeightAdapter totalWeightAdapter ;
    Boolean isFromWeight;
    private final FragmentCallBack callBack;
    public static String selectedItemWeight = "" ;
    public TotalWeightFragment(Boolean isFromWeight, FragmentCallBack callBack) {
        this.isFromWeight = isFromWeight;
        this.callBack = callBack;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTotalWeightBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        methodSetTotalWeightAdapter();
        getTotalWeightData();
        return view ;
    }

    private void getTotalWeightData() {
        binding.shimmerFrameContainer.shimmerViewContainer.setVisibility(View.VISIBLE);
        binding.shimmerFrameContainer.shimmerViewContainer.startShimmer();
        RetrofitRequest.JsonPostRequest(binding.getRoot().getContext(),
                new JSONObject().toString(),
                Singleton.getApiCall(binding.getRoot().getContext()).showPackageSize(new JSONObject().toString()), new ApiCallback() {
                    @Override
                    public void onResponce(String resp,boolean isSuccess) {
                        binding.shimmerFrameContainer.shimmerViewContainer.setVisibility(View.GONE);
                        binding.shimmerFrameContainer.shimmerViewContainer.stopShimmer();
                        if (isSuccess)
                        {
                            if (resp != null) {
                                try {
                                    JSONObject responseObj = new JSONObject(resp);
                                    int code = responseObj.optInt("code");
                                    if (code == 200) {
                                        JSONArray msgObject = responseObj.getJSONArray("msg");
                                        totalWeightModelArrayList.clear();
                                        for(int i = 0 ; i < msgObject.length(); i++){
                                            JSONObject obj = msgObject.getJSONObject(i).getJSONObject("PackageSize");

                                            PackagesSizeSelectionModel model = new PackagesSizeSelectionModel();
                                            model.setId(obj.optString("id"));
                                            model.setTitle(obj.optString("title"));
                                            model.setDescription(obj.optString("description"));
                                            model.setImage(obj.optString("image"));
                                            model.setPrice(obj.optString("price"));
                                            model.setSelected(i == 0);
                                            totalWeightModelArrayList.add(model);
                                        }

                                        totalWeightAdapter.notifyDataSetChanged();
                                    } else {
                                        Functions.dialouge(getActivity(), "" + getActivity().getString(R.string.total_weight_1), "" + responseObj.getString("msg"));
                                    }
                                } catch (Exception e) {
                                    Functions.logDMsg("Exception: "+e);
                                }

                            }
                        }
                        else
                        {

                        }
                    }
                });

    }

    private void methodSetTotalWeightAdapter() {

        totalWeightAdapter = new TotalWeightAdapter(getActivity(), isFromWeight ,  totalWeightModelArrayList, new AdapterClickListener() {
            @Override
            public void onItemClickListener(int position, Object model, View view) {

                switch (view.getId()) {

                    case R.id.mainLayout:

                        PackagesSizeSelectionModel totalWeightModel = (PackagesSizeSelectionModel) model;

                        selectedItemWeight = totalWeightModel.getTitle();

                        Functions.hideSoftKeyboard(getActivity());
                        Bundle bundle = new Bundle();
                        bundle.putString("item_weight", selectedItemWeight);
                        bundle.putString("item_price", totalWeightModel.getPrice());
                        bundle.putString("item_id", totalWeightModel.getId());
                        callBack.onItemClick(bundle);
                        dismiss();

                        break;

                    default:
                        break;
                }
            }
        });

        binding.totalWeightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.totalWeightRecyclerView.setAdapter(totalWeightAdapter);
        totalWeightAdapter.notifyDataSetChanged();

    }
}