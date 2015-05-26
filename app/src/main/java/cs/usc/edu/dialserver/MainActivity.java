package cs.usc.edu.dialserver;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.restlet.Component;
import org.restlet.engine.Engine;
import org.restlet.ext.gson.GsonConverter;
import org.restlet.ext.nio.HttpServerHelper;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private Component mComponent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Engine.getInstance().getRegisteredConverters().add(new GsonConverter());
        Engine.getInstance().getRegisteredServers().clear();
        Engine.getInstance().getRegisteredServers().add(new HttpServerHelper(null));

        try {
            mComponent = new MailServerComponent();
        } catch (Exception exp) {
            Log.e(TAG, "component creation exception " + exp.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (null != mComponent) {
                mComponent.start();
            }
        } catch (Exception exp) {
            Log.e(TAG, "component start exception " + exp.getMessage());
        }
    }

    @Override
    protected void onStop() {
        try {
            if (null != mComponent) {
                mComponent.stop();
            }
        } catch (Exception exp) {
            Log.e(TAG, "component stop exception " + exp.getMessage());
        }
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
