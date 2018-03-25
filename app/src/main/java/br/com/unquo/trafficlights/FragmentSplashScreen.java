package br.com.unquo.trafficlights;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by HenriqueM on 25/03/2018.
 */

public class FragmentSplashScreen extends Fragment {

    public static FragmentSplashScreen newInstance() {
        return new FragmentSplashScreen();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

//        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        testLoadFromFirebase();

        return view;
    }

    private void testLoadFromFirebase(){
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Mobility", "onDataChange: " + dataSnapshot.toString());

                MySingleton.getInstance().semaforos = dataSnapshot.getValue(LoadSemaforos.class);

                Log.d("Mobility", "Size: " + MySingleton.getInstance().semaforos.Semaforos.size());

                Log.d("Mobility", "Localizacao: " + MySingleton.getInstance().semaforos.Semaforos.get(0).Localizacao.Coordenadas.get(0));

                Intent intent = new Intent(getActivity(), GPSActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Mobility", "onCancelled: " + databaseError.getMessage().toString());
            }
        });
    }

}

