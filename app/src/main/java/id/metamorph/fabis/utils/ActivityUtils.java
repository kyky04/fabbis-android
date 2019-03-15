package id.metamorph.fabis.utils;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Created by knalb on 19/07/17.
 */

public class ActivityUtils {
    public static void addFragment(Context context, int id, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void addFragment(Context context, int id, Fragment fragment, String TAG) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment, TAG)
                .addToBackStack(null)
                .commit();
    }

    public static void replaceFragment(Context context, int id, Fragment fragment ) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    public static void replaceFragment(Context context, int id, Fragment fragment, String TAG) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment, TAG)
                .commit();
    }

    public static void removeFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

    public static void openActivity(Context context, Class destClass) {
        Intent intent = new Intent(context, destClass);
        ((AppCompatActivity) context).startActivity(intent);
    }
}
