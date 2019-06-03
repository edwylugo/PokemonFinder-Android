package edwy.lugo.pokemonfinder.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.os.Handler;

public class Functions {

    private static int SPLASH_TIME_OUT = 3000;

    /* ============== Screen Splash ================ */
    public void screenSplash(final Activity activity, final Class classActivity) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(activity, classActivity);
                activity.startActivity(i);
                activity.finish();
            }
        }, SPLASH_TIME_OUT);
    }

    /* ============== Toast Activity================ */
    public void toast(final Activity activity, int message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    /* ============== Toast Context================ */
    public void toastContext(final Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /* ============== Alter Activity ================ */
    public void alterActivity(Activity activity, Class classActivity) {
        Intent intent = new Intent(activity, classActivity);
        activity.startActivity(intent);
        activity.finish();
    }

}
