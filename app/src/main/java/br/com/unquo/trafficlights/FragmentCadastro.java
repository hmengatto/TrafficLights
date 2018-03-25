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

/**
 * Created by HenriqueM on 25/03/2018.
 */

public class FragmentCadastro extends Fragment {

    private EditText inputEmail, inputPassword, repeatPassword;

    public static FragmentCadastro newInstance() {
        return new FragmentCadastro();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        inputEmail = (EditText) view.findViewById(R.id.edt_login_email);
        inputPassword = (EditText) view.findViewById(R.id.edt_login_password);
        repeatPassword = (EditText) view.findViewById(R.id.edt_repeat_password);

//        TextView textView1 = (TextView) view.findViewById(R.id.txt_login_1);
//        SpannableString content1 = new SpannableString("Ainda não possui conta?");
//        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
//        textView1.setText(content1);

        TextView textView2 = (TextView) view.findViewById(R.id.txt_login_2);
        SpannableString content2 = new SpannableString("Fazer login");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        textView2.setText(content2);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).goBack();
            }
        });

        view.findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString();//.trim();
                String password2 = repeatPassword.getText().toString();//.trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Digite um endereço de e-mail!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Digite uma senha!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(password2)) {
                    Toast.makeText(getContext(), "As duas senhas não conferem!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getContext(), "Senha muito curta! Digite pelo menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                }

                MySingleton.getInstance().auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    Toast.makeText(getActivity(), "Falha no cadastro!", Toast.LENGTH_LONG).show();
//                                    listener.onError("Erro");
                                } else {
//                                    sendEmailVerification(listener);
                                    Toast.makeText(getActivity(), "Feito!", Toast.LENGTH_LONG).show();
                                    ((MainActivity)getActivity()).startSplashScreen();
                                }
                            }
                        });

            }
        });

        return view;
    }

}
