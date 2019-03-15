package id.metamorph.fabis.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import id.metamorph.fabis.MainActivity;
import id.metamorph.fabis.MainPelatihActivity;
import id.metamorph.fabis.R;
import id.metamorph.fabis.data.Session;
import id.metamorph.fabis.models.auth.LoginResponse;
import id.metamorph.fabis.utils.ActivityUtils;
import okhttp3.Response;

import static id.metamorph.fabis.data.Contans.LOGIN;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_signin)
    Button btnSignin;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new Session(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.btn_signin)
    public void onViewClicked() {
        login();
    }

    public void login() {
        showProgress();
        AndroidNetworking.post(LOGIN)
                .addBodyParameter("email", etEmail.getText().toString())
                .addBodyParameter("password", etPassword.getText().toString())
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closeProgress();
                        if (response instanceof LoginResponse) {
                            if (((LoginResponse) response).isStatus()) {
                                session.createLoginSession((LoginResponse) response);
                                if(((LoginResponse) response).getData().getStatus().equals("0")){
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }else if(((LoginResponse) response).getData().getStatus().equals("1")){
                                    startActivity(new Intent(LoginActivity.this, MainPelatihActivity.class));
                                }else {

                                }
                                finish();
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, ((LoginResponse) response).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Kesalahan Teknis !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeProgress();
                    }
                });
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . .");
        progressDialog.show();
    }

    public void closeProgress() {
        progressDialog.dismiss();
    }
}
