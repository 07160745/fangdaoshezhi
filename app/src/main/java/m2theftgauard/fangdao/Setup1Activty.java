package m2theftgauard.fangdao;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;
public class Setup1Activty extends BaseSetupActivity {

    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_setup1);
        initView();
    }
    protected void initView(){
        ((RadioButton)findViewById(R.id.rb_first)).setChecked(true);
    }
    public void showNext(){
        startActivityAndFinishSelf(Setup2Activity.class);
    }

}
