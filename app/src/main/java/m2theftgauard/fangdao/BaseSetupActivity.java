package m2theftgauard.fangdao;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

import static m2theftgauard.fangdao.R.anim.pre_in;
public class BaseSetupActivity extends AppCompatActivity {
    public SharedPreferences sp;
    protected GestureDetector mGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        setContentView(R.layout.activity_base_setup);

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(velocityX) < 200) {
                    System.out.println("无效动作太慢了");
                    return true;
                }
                if ((e2.getRawX() - e1.getRawX()) > 200) {
                    overridePendingTransition(R.anim.pre_in, R.anim.pre_out);
                    return true;
                }
                if ((e1.getRawX() - e2.getRawX()) > 200) {
                    overridePendingTransition(R.anim.next_in, R.anim.next_out);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
}
public void startActivityAndFinishSelf(Class<?>cls){
    Intent intent=new Intent(this,cls);
    startActivity(intent);
    finish();
}
}
