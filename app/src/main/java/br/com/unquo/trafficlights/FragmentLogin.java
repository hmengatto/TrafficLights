package br.com.unquo.trafficlights;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by HenriqueM on 25/03/2018.
 */

public class FragmentLogin extends Fragment {

    private EditText inputEmail, inputPassword;

//    CustomDialog customDialog = new CustomDialog();

    public static FragmentLogin newInstance() {
        return new FragmentLogin();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        inputEmail = (EditText) view.findViewById(R.id.edt_login_email);
        inputPassword = (EditText) view.findViewById(R.id.edt_login_password);

        TextView textView1 = (TextView) view.findViewById(R.id.txt_login_1);
        SpannableString content1 = new SpannableString("Ainda não possui conta?");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        textView1.setText(content1);

        TextView textView2 = (TextView) view.findViewById(R.id.txt_login_2);
        SpannableString content2 = new SpannableString("Cadastre-se");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        textView2.setText(content2);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startCadastro();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startCadastro();
            }
        });

        TextView textView3 = (TextView) view.findViewById(R.id.txt_login_3);
        SpannableString content3 = new SpannableString("Esqueci minha senha");
        content3.setSpan(new UnderlineSpan(), 0, content3.length(), 0);
        textView3.setText(content3);

        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Digite um endereço de e-mail!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Digite uma senha!", Toast.LENGTH_SHORT).show();
                    return;
                }


                MySingleton.getInstance().auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
//                                    listener.onError("Erro");
                                    Toast.makeText(getActivity(), "Falha no login!", Toast.LENGTH_LONG).show();
                                } else {
//                                    listener.onSuccess();
                                    Toast.makeText(getActivity(), "Logado!", Toast.LENGTH_LONG).show();
                                    ((MainActivity)getActivity()).startSplashScreen();
                                }
                            }
                        });

            }
        });

        return view;
    }

}