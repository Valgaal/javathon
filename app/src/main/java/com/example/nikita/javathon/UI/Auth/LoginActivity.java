package com.example.nikita.javathon.UI.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.nikita.javathon.Data.Repository;
import com.example.nikita.javathon.R;
import com.example.nikita.javathon.UI.PartyList.PartyListActivity;
import com.example.nikita.javathon.utils.Constants;
import com.example.nikita.javathon.utils.ValidateUserInfo;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {


    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private Button mSignInButton;

    private TextView mTxtCreate;
    private TextView mTxtForgot;
    private boolean mAnswer;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initInstances();
    }

    private void initInstances() {
        mAnswer = false;
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.txt_email);

        mPasswordView = (EditText) findViewById(R.id.txt_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL ||id == EditorInfo.IME_ACTION_SEND ||
                        (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mTxtCreate = (TextView) findViewById(R.id.txt_create);
        mTxtCreate.setOnClickListener(this);

        mTxtForgot = (TextView) findViewById(R.id.txt_forgot);
        mTxtForgot.setOnClickListener(this);

        mSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mSignInButton.setOnClickListener(this);


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



    private void attemptLogin() {
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !ValidateUserInfo.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!ValidateUserInfo.isEmailValid(email)) {
            mUsernameView.setError(getString(R.string.error_invalid_email));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            SignInRequest user = new SignInRequest(email, password);
            getResponse(user);

        }
    }

    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }



    @Override
    public void onClick(View v) {
        String login = mUsernameView.getText().toString();

        switch (v.getId()) {
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
            case R.id.txt_create:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra(Constants.TAG_LOGIN, login);
                startActivity(intent);
                finish();
                break;
            case R.id.txt_forgot:
                Intent intentForgot = new Intent(LoginActivity.this, ForgotPassActivity.class);
                intentForgot.putExtra(Constants.TAG_LOGIN, login);
                startActivity(intentForgot);
                finish();
                break;
        }
    }

    private void onSignInClicked() {
        mProgressDialog = ProgressDialog.show(LoginActivity.this, "Connecting...", "Atempting to connect", true);
        mProgressDialog.setCancelable(false);
    }



    private void getResponse(final SignInRequest user) {
        Repository.getInstance().postSignInUser(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                String answer = response.body();
                if (answer != null && answer.equals("OK")) {
                    putOnSharedPreferences(user);
                    mAnswer = true;
                    Intent intent = new Intent(LoginActivity.this, PartyListActivity.class);
                    startActivity(intent);
                }
                showProgress(false);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showProgress(false);
            }
        });
    }
    private void putOnSharedPreferences(final SignInRequest user) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.TAG_LOGIN, user.getLogin());
        editor.putString(Constants.TAG_PASSWORD, user.getPassword());
        editor.commit();
    }


}
