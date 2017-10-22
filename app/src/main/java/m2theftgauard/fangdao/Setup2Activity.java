package m2theftgauard.fangdao;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import static m2theftgauard.fangdao.R.*;
public class Setup2Activity extends BaseSetupActivity {
    private TelephonyManager mTelephonyManager;
    private Button mBindSIMBtn;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(layout.activity_setup_2);
        mTelephonyManager = (TelephonyManager) getSystemService(TELECOM_SERVICE);
        initView();
    }

    private void initView() {
        ((RadioButton) findViewById(id.rb_first)).setChecked(true);
        mBindSIMBtn = (Button) findViewById(id.up);
        mBindSIMBtn.setOnClickListener((View.OnClickListener) this);
        if (isBind()) {
            mBindSIMBtn.setEnabled(false);
        } else {
            mBindSIMBtn.setEnabled(true);
        }
    }

    private boolean isBind() {
        String simString = sp.getString("sim", null);
        if (TextUtils.isEmpty(simString)) {
            return false;
        }

        return true;
    }

       public void showNext(){
        if(!isBind()) {
           System.out.println("您还没有绑定过SIM卡");
            return;
        }
      startActivityAndFinishSelf(Setup3Activity.class);
    }
    public void onClick(View v){
        switch (v.getId()){
            case id.up:
                bindSIM();
                break;
        }
}
    private void bindSIM() {
        if(!isBind()){
            String simSerialNumber=mTelephonyManager.getSimSerialNumber();
            SharedPreferences.Editor edit=sp.edit();
            edit.putString("sim",simSerialNumber);
            edit.commit();
            System.out.println("SIM卡绑定成功");
            mBindSIMBtn.setEnabled(false);
        }
        else
        {
            System.out.println("SIM卡绑定成功");
            mBindSIMBtn.setEnabled(false);
        }
    }
}

