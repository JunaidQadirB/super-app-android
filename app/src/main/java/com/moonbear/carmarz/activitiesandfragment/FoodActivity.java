package com.moonbear.carmarz.activitiesandfragment;

import android.os.Bundle;

import com.moonbear.carmarz.codeclasses.AppCompatLocaleActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.moonbear.carmarz.codeclasses.Functions;
import com.moonbear.carmarz.codeclasses.MyPreferences;
import com.moonbear.carmarz.codeclasses.Variables;
import com.moonbear.carmarz.food.BrowseFragment;
import com.moonbear.carmarz.food.FavouriteFragment;
import com.moonbear.carmarz.food.FoodHomeFragment;
import com.moonbear.carmarz.food.FoodHomeTwo;
import com.moonbear.carmarz.food.FoodMainFragment;
import com.moonbear.carmarz.food.OrdersFragment;
import com.moonbear.carmarz.food.PlaceOrdersFragment;
import com.moonbear.carmarz.food.RestaurantMenuFragment;
import com.moonbear.carmarz.food.ResturantAgainstCatFragment;
import com.moonbear.carmarz.food.SearchFragmentResturant;
import com.moonbear.carmarz.model.CalculationModel;
import com.moonbear.carmarz.model.ResturantModel;
import com.moonbear.carmarz.R;

import java.util.ArrayList;

public class FoodActivity extends AppCompatLocaleActivity implements RestaurantMenuFragment.CallBackListener{

    public FoodMainFragment foodMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Functions.setLocale(MyPreferences.getSharedPreference(this).getString(MyPreferences.setlocale, Variables.DEFAULT_LANGUAGE_CODE)
                , this, getClass(),false);
        setContentView(R.layout.activity_food);


        if (savedInstanceState == null) {
            reload();
        } else {
            foodMainFragment = (FoodMainFragment) getSupportFragmentManager().getFragments().get(0);
        }
    }

    public void reload() {
        foodMainFragment = new FoodMainFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.food_activityContainer, foodMainFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void checkFragment(){
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if(fragment instanceof FoodHomeFragment){
                FoodHomeFragment mainFragment = (FoodHomeFragment) fragment;
                mainFragment.setUpScreenData();
            }

            if(fragment instanceof FoodHomeTwo){
                FoodHomeTwo mainFragment = (FoodHomeTwo) fragment;
                mainFragment.setUpScreenData();
            }

            if(fragment instanceof FavouriteFragment){
                FavouriteFragment mainFragment = (FavouriteFragment) fragment;
                mainFragment.checkCart();
            }


            if(fragment instanceof OrdersFragment){
                OrdersFragment mainFragment = (OrdersFragment) fragment;
                mainFragment.checkCart();
            }

            if(fragment instanceof BrowseFragment){
                BrowseFragment mainFragment = (BrowseFragment) fragment;
                mainFragment.checkCart();
            }

            if(fragment instanceof RestaurantMenuFragment){
                RestaurantMenuFragment mainFragment = (RestaurantMenuFragment) fragment;
                mainFragment.checkCart(true);
            }

            if(fragment instanceof ResturantAgainstCatFragment){
                ResturantAgainstCatFragment mainFragment = (ResturantAgainstCatFragment) fragment;
                mainFragment.checkCart();
            }

        }
    }



    public void updateist(ArrayList<CalculationModel> carList){
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if(fragment instanceof FoodHomeFragment){
                FoodHomeFragment mainFragment = (FoodHomeFragment) fragment;
                mainFragment.setUpScreenData();
            }

            if(fragment instanceof FoodHomeTwo){
                FoodHomeTwo mainFragment = (FoodHomeTwo) fragment;
                mainFragment.setUpScreenData();
            }

            if(fragment instanceof FavouriteFragment){
                FavouriteFragment mainFragment = (FavouriteFragment) fragment;
                mainFragment.checkCart();
            }


            if(fragment instanceof OrdersFragment){
                OrdersFragment mainFragment = (OrdersFragment) fragment;
                mainFragment.checkCart();
            }

            if(fragment instanceof BrowseFragment){
                BrowseFragment mainFragment = (BrowseFragment) fragment;
                mainFragment.checkCart();
            }

            if(fragment instanceof RestaurantMenuFragment){
                RestaurantMenuFragment mainFragment = (RestaurantMenuFragment) fragment;
                mainFragment.checkCart(true);
            }

            if(fragment instanceof ResturantAgainstCatFragment){
                ResturantAgainstCatFragment mainFragment = (ResturantAgainstCatFragment) fragment;
                mainFragment.checkCart();
            }

        }
    }


    @Override
    public void onCallBack() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {

            if(fragment instanceof PlaceOrdersFragment){
                PlaceOrdersFragment mainFragment = (PlaceOrdersFragment) fragment;
                mainFragment.updateList(true);
            }

        }
    }

    public void updateFav(ResturantModel recipeDataModel) {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if(fragment instanceof FoodHomeFragment){
                FoodHomeFragment mainFragment = (FoodHomeFragment) fragment;
                mainFragment.getChangedList(recipeDataModel);
            }

            if(fragment instanceof FoodHomeTwo){
                FoodHomeTwo mainFragment = (FoodHomeTwo) fragment;
                mainFragment.getChangedList(recipeDataModel);
            }

            if(fragment instanceof SearchFragmentResturant){
                SearchFragmentResturant mainFragment = (SearchFragmentResturant) fragment;
                mainFragment.getChangedList(recipeDataModel);
            }
        }
    }
}