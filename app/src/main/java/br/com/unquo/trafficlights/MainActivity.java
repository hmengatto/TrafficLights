package br.com.unquo.trafficlights;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by HenriqueM on 24/03/2018.
 */

public class MainActivity extends AppCompatActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        MySingleton.getInstance().auth = FirebaseAuth.getInstance();

        //Check if the application has draw over other apps permission or not?
        //This permission is by default available for API<23. But for API > 23
        //you have to ask for the permission in runtime.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            initializeView();
        }
    }

    public void goBack(){
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Set and initialize the view elements.
     */
    private void initializeView() {
        /*findViewById(R.id.notify_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, FloatingAlertService.class));
                finish();
            }
        });*/

        if (MySingleton.getInstance().auth.getCurrentUser() != null) {
            startSplashScreen();
        }else {
            startLogin();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {

            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                initializeView();
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startLogin(){
        FragmentLogin fragment = FragmentLogin.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up_in,R.anim.slide_up_out,R.anim.slide_down_in,R.anim.slide_down_out);
        fragmentTransaction.replace(R.id.content_main,fragment,"login");
        fragmentTransaction.addToBackStack("login");
        fragmentTransaction.commit();
    }

    public void startCadastro(){
        FragmentCadastro fragment = FragmentCadastro.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_left_in,R.anim.slide_left_out,R.anim.slide_right_in,R.anim.slide_right_out);
        fragmentTransaction.replace(R.id.content_main,fragment,"cadastro");
        fragmentTransaction.addToBackStack("cadastro");
        fragmentTransaction.commit();
    }

    public void startSplashScreen(){

        FragmentSplashScreen fragment = FragmentSplashScreen.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.slide_left_in,R.anim.slide_left_out,R.anim.slide_right_in,R.anim.slide_right_out);
        fragmentTransaction.replace(R.id.content_main, fragment, "splashScreen");
        fragmentTransaction.commit();
    }
}
