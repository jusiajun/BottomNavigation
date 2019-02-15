package com.lotteresort.bottomnavigation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.lotteresort.bottomnavigation.databinding.ActivityMainBinding;

import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final Object Hex = 0000000020;
    private TextView mTextMessage;
    ActivityMainBinding binding;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_dashboard1:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications1:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_home1:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        mTextMessage = (TextView) findViewById(R.id.message);

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        findViewById(R.id.btn_jp).setOnClickListener(this);
        findViewById(R.id.btn_en).setOnClickListener(this);




        BottomNav bottomNav = findViewById(R.id.bottomNav);
        bottomNav.addItemNav(new ItemNav(this, R.mipmap.ic_launcher, "Explore").addColorAtive(R.color.colorAccent));
        bottomNav.addItemNav(new ItemNav(this, R.mipmap.ic_launcher, "twoFlagsz").addColorAtive(R.color.colorAccent));
        bottomNav.addItemNav(new ItemNav(this, R.mipmap.ic_launcher, "threeblar").addColorAtive(R.color.colorAccent));
        bottomNav.addItemNav(new ItemNav(this, R.mipmap.ic_launcher, "four4").addColorAtive(R.color.colorAccent));
        bottomNav.addItemNav(new ItemNav(this, R.mipmap.ic_launcher, "fivestars").addColorAtive(R.color.colorAccent));
        bottomNav.build();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();


        BottomNav.OnTabSelectedListener listener = new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Fragment selectedFragment = null;
                if(position==0){
                    selectedFragment = ItemOneFragment.newInstance();
                }else if(position==1){
                    selectedFragment = ItemTwoFragment.newInstance();
                }else if(position==2){
                    selectedFragment = ItemThreeFragment.newInstance();
                }else if(position==3){
                    selectedFragment = ItemFourFragment.newInstance();
                }else if(position==4){
                    selectedFragment = ItemFiveFragment.newInstance();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                Toast.makeText(MainActivity.this, "Click position " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabLongSelected(int position) {
                Toast.makeText(MainActivity.this, "Long position " + position, Toast.LENGTH_SHORT).show();
            }
        };

        bottomNav.setTabSelectedListener(listener);

        try {
            String originalText = "이명준";
            String key = "lotteresort1@";
            String en;
            en = Encrypt( originalText, key);
            String de = Decrypt( en, key);
            Log.i("code ", "en: "+en);
            Log.i("code ", "de: "+de);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static String Decrypt(String text, String key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
        byte [] results = cipher.doFinal(Base64.decode(text, 0));
        return new String(results,"UTF-8");
    }



    public static String Encrypt(String text, String key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);
        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.encodeToString(results, 0);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_en) {
            changeConfigulation();
            ActivityCompat.finishAffinity(this);
            restartApp(MainActivity.this);
        } else if (id == R.id.btn_jp) {
            changeConfigulationjp();
            ActivityCompat.finishAffinity(this);
            restartApp(MainActivity.this);

        }
    }

    public void changeConfigulation() {
        Locale mLocale = new Locale("en");
        Configuration config = new Configuration();
        config.locale = mLocale;
        getResources().updateConfiguration(config, null);
    }

    public void changeConfigulationjp() {
        Locale mLocale = new Locale("jp");
        Configuration config = new Configuration();
        config.locale = mLocale;
        getResources().updateConfiguration(config, null);
    }

    public static void restartApp(Context context) {
        Intent intentToBeNewRoot = new Intent(context, MainActivity.class);
        ComponentName cn = intentToBeNewRoot.getComponent();

        Intent mainIntent = Intent.makeRestartActivityTask(cn);

        context.startActivity(mainIntent);
    }

}
