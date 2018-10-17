package com.friendsproducts.kidzzone;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firebase.client.Firebase;
import com.friendsproducts.kidzzone.Helper_Classes.Sharepref;
import com.friendsproducts.kidzzone.Helper_Classes.URLs;
import com.friendsproducts.kidzzone.Helper_Classes.Upload;
import com.friendsproducts.kidzzone.Helper_Classes.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegisterTeacher extends AppCompatActivity {

    //image
    private static final int SELECT_VIDEO = 3;
    String rec, subject = "Kids Zone Confirmation Code", textMessage;
    Session session = null;
    ProgressDialog pdialog = null;
    AlertDialog alertDialog;
    Sharepref sp;
    ImageView userimg;
    EditText editTextUsername, editTextUsernameUnique, editTextEmail, editTextPassword, editTextQual, editTextNmbr;
    RadioGroup radioGroupGender;
    ProgressDialog progressDialog;
    private String selectedPath;

    String after(String value, String a) {
        // Returns a substring containing all characters after a string.
        int posA = value.lastIndexOf(a);
        if (posA == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= value.length()) {
            return "";
        }
        return value.substring(adjustedPosA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        Firebase.setAndroidContext(this);

        progressDialog = new ProgressDialog(this);
        editTextUsername = (EditText) findViewById(R.id.tecName);
        editTextUsernameUnique = (EditText) findViewById(R.id.tecUniqueName);
        editTextEmail = (EditText) findViewById(R.id.tecEmail);
        editTextPassword = (EditText) findViewById(R.id.tecPass);
        editTextQual = (EditText) findViewById(R.id.tecQual);
        editTextNmbr = (EditText) findViewById(R.id.tecPhNmbr);
        radioGroupGender = (RadioGroup) findViewById(R.id.tecRadioGrp);

        userimg = (ImageView) findViewById(R.id.tecImage);
        userimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        findViewById(R.id.tecRegBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });

    }

    private void registerUser() {
        final String username = editTextUsername.getText().toString().trim();
        final String usernameunique = editTextUsernameUnique.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String qual = editTextQual.getText().toString().trim();
        final String nmbr = editTextNmbr.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        rec = email;
        textMessage = getCode();
        sp.setStr(sp.RANDOM_CODE, textMessage);
        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter name");
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(usernameunique)) {
            editTextUsernameUnique.setError("Please enter username");
            editTextUsernameUnique.requestFocus();
            return;
        } else if (!usernameunique.matches("[A-Za-z0-9]+")) {
            editTextUsernameUnique.setError("only alphabet or number allowed");
            editTextUsernameUnique.requestFocus();
        } else if (usernameunique.length() < 5) {
            editTextUsernameUnique.setError("at least 5 characters long");
            editTextUsernameUnique.requestFocus();
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        } else if (password.length() < 5) {
            editTextPassword.setError("at least 5 characters long");
            editTextPassword.requestFocus();
        }
        if (TextUtils.isEmpty(qual)) {
            editTextQual.setError("Enter Qualification");
            editTextQual.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nmbr)) {
            editTextNmbr.setError("Enter Phone Number");
            editTextNmbr.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(selectedPath)) {
            Toast.makeText(RegisterTeacher.this, "Please select image", Toast.LENGTH_LONG).show();
            return;
        }

        sendEmail();
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText codeET = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String code = codeET.getText().toString().trim();
                                if (TextUtils.isEmpty(code)) {
                                    Toast.makeText(RegisterTeacher.this, "Please enter confirmation code", Toast.LENGTH_LONG).show();

                                    alertDialog.show();
                                    return;
                                }

                                if (code.equals(sp.getStr(sp.RANDOM_CODE))) {
                                    progressDialog.setMessage("Registering user...");
                                    progressDialog.show();
                                    StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, URLs.URL_REGISTER_TCHR,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        if (response.equals("Successfully Registered")) {
                                                            // To Dismiss progress dialog
                                                            progressDialog.dismiss();
                                                            uploadimage();
                                                            finish();
                                                            regInFireBase(usernameunique, password);
                                                            Intent i1 = new Intent(RegisterTeacher.this, FirstScreen.class);
                                                            startActivity(i1);

                                                            Toast.makeText(RegisterTeacher.this, response, Toast.LENGTH_LONG).show();
                                                        } else {
                                                            // To Dismiss progress dialog
                                                            progressDialog.dismiss();
                                                            Toast.makeText(RegisterTeacher.this, response, Toast.LENGTH_LONG).show();
                                                        }
                                                    } catch (Exception e) {
                                                        progressDialog.dismiss();
                                                        //Toast.makeText(getApplicationContext(), "Incorrect User Name or Password", Toast.LENGTH_LONG).show();
                                                        Toast.makeText(RegisterTeacher.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                                        e.printStackTrace();
                                                    }
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    // To Dismiss progress dialog
                                                    progressDialog.dismiss();
                                                    Toast.makeText(RegisterTeacher.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            }) {
                                        @Override
                                        protected Map<String, String> getParams() {
                                            String imagepath = "https://pakmovespoint.000webhostapp.com/Kidzone/v1/uploads/" + after(selectedPath, "s/");
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put("username", username);
                                            params.put("usernameunique", usernameunique);
                                            params.put("useremail", email);
                                            params.put("userpass", password);
                                            params.put("userqual", qual);
                                            params.put("usernmbr", nmbr);
                                            params.put("usergender", gender);
                                            params.put("userimg", imagepath);
                                            return params;
                                        }
                                    };

                                    VolleySingleton.getInstance(RegisterTeacher.this).addToRequestQueue(stringRequest);

                                } else {
                                    Toast.makeText(RegisterTeacher.this, "You entered a wrong code", Toast.LENGTH_LONG).show();
                                }


                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Resend",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        alertDialog = alertDialogBuilder.create();

        // show it
        // alertDialog.show();
    }

    private void regInFireBase(final String user, final String pass) {

        String url = "https://kids-zone-caht.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase("https://kids-zone-caht.firebaseio.com/users");

                if (s.equals("null")) {
                    reference.child(user).child("password").setValue(pass);
                    Toast.makeText(RegisterTeacher.this, "registration successful", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(user)) {
                            reference.child(user).child("password").setValue(pass);
                            // Toast.makeText(RegisterTeacher.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            //  Toast.makeText(RegisterTeacher.this, "username already exists", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);

            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }


    //image
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a Image "), SELECT_VIDEO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO) {
                Uri selectedImageUri = data.getData();
                selectedPath = getPath(selectedImageUri);
                userimg.setImageURI(selectedImageUri);
                Toast.makeText(this, after(selectedPath, "s/"), Toast.LENGTH_SHORT).show();
                // textView.setText(selectedPath);\
            }
        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void uploadimage() {
        class UploadVideo extends AsyncTask<Void, Void, String> {

            ProgressDialog uploading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                uploading = ProgressDialog.show(RegisterTeacher.this, "Registering", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                uploading.dismiss();
                // Toast.makeText(RegisterTeacher.this, s, Toast.LENGTH_SHORT).show();
                // textViewResponse.setText(Html.fromHtml("<b>Uploaded at <a href='" + s + "'>" + s + "</a></b>"));
                // textViewResponse.setMovementMethod(LinkMovementMethod.getInstance());
            }

            @Override
            protected String doInBackground(Void... params) {
                Upload u = new Upload();
                String msg = u.uploadVideo(selectedPath);
                return msg;
            }
        }
        UploadVideo uv = new UploadVideo();
        uv.execute();
    }

    //image end
    //send email
    private void sendEmail() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pakmoviespoint@gmail.com", "Always4You");
            }
        });

        pdialog = ProgressDialog.show(this, "", "Sending Mail...", true);

        RegisterTeacher.RetreiveFeedTask task = new RegisterTeacher.RetreiveFeedTask();
        task.execute();
    }

    protected String getCode() {
        String TableChar = "123456789ABCDEF";
        StringBuilder table = new StringBuilder();
        Random rnd = new Random();
        while (table.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * TableChar.length());
            table.append(TableChar.charAt(index));
        }
        String tableName = table.toString();
        return tableName;

    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            alertDialog.show();
            Toast.makeText(getApplicationContext(), "Code sent", Toast.LENGTH_LONG).show();
        }
    }
}
