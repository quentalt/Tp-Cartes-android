package com.example.cartes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Toast;


import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.List;


public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback, PermissionsListener {
    private MapView maCarte;
    private MapboxMap carte;
    private PermissionsManager permissionsManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_main);

        maCarte = findViewById(R.id.maCarte);
        maCarte.onCreate(savedInstanceState);
        maCarte.getMapAsync(this);

        /*maCarte.getMapAsync(mapboxMap -> mapboxMap.setStyle(
                new Style.Builder().fromUri(Style.MAPBOX_STREETS)

                ));*/

    }


    @Override
    public void onPermissionResult(boolean granted) {

    }


    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.carte = mapboxMap;

        carte.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/cjerxnqt3cgvp2rmyuxbeqme7"),
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mettreenPlaceComposantLocalisation(style);
                    }


                });
    }

    private void mettreenPlaceComposantLocalisation(Style style) {
    }


    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this,  R.string.user_location_permission_explication,
                Toast.LENGTH_LONG).show();
    }

    @SuppressWarnings({"MissingPermission"})
    private void miseEnPlaceComposantLocalisation(@NonNull Style loadedMapStyle) {

        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            LocationComponent locationComponent = carte.getLocationComponent();

            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

            locationComponent.setLocationComponentEnabled(true);

            locationComponent.setCameraMode(CameraMode.TRACKING);

            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);


        }
    }



    @Override

    protected void onStart() {

        super.onStart();
        maCarte.onStart();
    }

    @Override

    protected void onResume() {

        super.onResume();
        maCarte.onResume();
    }

    @Override

    protected void onPause() {

        super.onPause();
        maCarte.onPause();
    }



    @Override

    public void onLowMemory() {

        super.onLowMemory();
        maCarte.onLowMemory();
    }

    @Override

    protected void onDestroy() {

        super.onDestroy();
        maCarte.onDestroy();
    }

    @Override

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        maCarte.onSaveInstanceState(outState);
    }


}
