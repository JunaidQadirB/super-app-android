package com.moonbear.carmarz.activitiesandfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moonbear.carmarz.adapter.SelectLanguageAdapter;
import com.moonbear.carmarz.codeclasses.Functions;
import com.moonbear.carmarz.codeclasses.RootFragment;
import com.moonbear.carmarz.codeclasses.MyPreferences;
import com.moonbear.carmarz.Interface.AdapterClickListener;
import com.moonbear.carmarz.R;
import com.moonbear.carmarz.codeclasses.Variables;
import com.moonbear.carmarz.databinding.FragmentSelectLanguageBinding;
import com.moonbear.carmarz.model.LanguageModel;

import java.util.ArrayList;
import java.util.List;


public class SelectLanguageF extends RootFragment implements View.OnClickListener {

    public static String selectedLanguage = "";
    private final List<LanguageModel> dataList =new ArrayList<>();
    private SelectLanguageAdapter adapter;
    FragmentSelectLanguageBinding binding;

    public SelectLanguageF() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectLanguageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        initControl();
        actionControl();
        methodSetadapter();
        return view;
    }

    private void actionControl() {
        binding.backBtn.setOnClickListener(this);
    }

    private void initControl() {

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL , false);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        selectedLanguage = MyPreferences.getSharedPreference(getActivity()).getString(MyPreferences.KEY_LOCALE_NAME, "English");
        String[] localeName = binding.getRoot().getContext().getResources().getStringArray(R.array.app_language);
        String[] localeKey = binding.getRoot().getContext().getResources().getStringArray(R.array.app_language_code);

        dataList.clear();

        for (int i = 0; i < localeName.length; i++) {
            LanguageModel model = new LanguageModel();
            model.setName(localeName[i]);
            model.setKey(localeKey[i]);
            dataList.add(model);
        }

    }


    private void methodSetadapter() {

        adapter = new SelectLanguageAdapter(getContext(), dataList, new AdapterClickListener() {

            @Override
            public void onItemClickListener(int postion, Object model, View view) {
                LanguageModel languageModel = (LanguageModel) model;
                setLocale(languageModel.getName(), languageModel.getKey());
            }

        });

        binding.recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


    private void setLocale(String key, String lang) {
        MyPreferences.getSharedPreference(getActivity()).edit().putString(MyPreferences.setlocale, lang).commit();
        MyPreferences.getSharedPreference(getActivity()).edit().putString(MyPreferences.KEY_LOCALE_NAME, key).commit();

        Functions.setLocale(MyPreferences.getSharedPreference(binding.getRoot().getContext()).getString(MyPreferences.setlocale, Variables.DEFAULT_LANGUAGE_CODE)
                , getActivity(), HomeActivity.class,true);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.backBtn:
                getActivity().onBackPressed();
                break;

            default:
                break;
        }
    }


    @Override
    public void onDetach() {
        Functions.hideSoftKeyboard(getActivity());
        super.onDetach();
    }

}