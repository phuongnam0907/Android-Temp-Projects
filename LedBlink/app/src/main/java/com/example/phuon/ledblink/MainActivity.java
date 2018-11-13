package com.example.phuon.ledblink;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 *
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 *
 */
public class MainActivity extends Activity  {
    private static final int INTERVAL_BETWEEN_BLINKS_MS = 500;
    private static final String TAG = MainActivity.class.getSimpleName();

    private Handler mHandler = new Handler();
    private Gpio mLedGpio, mLedGpio1, mLedGpio2, mLedGpio3, mLedGpio4;
    private static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PeripheralManager service = PeripheralManager.getInstance();
        try {
            String pinName = BoardDefaults.getGPIOForLED();
            mLedGpio = service.openGpio(pinName);
            mLedGpio1 = service.openGpio(BoardDefaults.getGPIOForLED1());
            mLedGpio2 = service.openGpio(BoardDefaults.getGPIOForLED2());
            mLedGpio3 = service.openGpio(BoardDefaults.getGPIOForLED3());
            mLedGpio4 = service.openGpio(BoardDefaults.getGPIOForLED4());
            //mLedGpio = service.openGpio("BCM6");
            mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mLedGpio1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mLedGpio2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mLedGpio3.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mLedGpio4.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            Log.i(TAG, "onCreate: Start Blink Led GPIO Pin");

            mHandler.post(mBlinkRunnable);
        } catch (Throwable t) {
            t.getMessage();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove pending blink Runnable from handler
        mHandler.removeCallbacks(mBlinkRunnable);
        // Close the Gpio pin
        Log.i(TAG, "onDestroy: Closed LED GPIO pin");
        try {
            mLedGpio.close();
            mLedGpio1.close();
            mLedGpio2.close();
            mLedGpio3.close();
            mLedGpio4.close();
        } catch (IOException e) {
            Log.e(TAG, "onDestroy: Error on PeripheralIO API ", e);
        } finally {
            mLedGpio = null;
            mLedGpio1 = null;
            mLedGpio2 = null;
            mLedGpio3 = null;
            mLedGpio4 = null;
        }
    }

    private Runnable mBlinkRunnable = new Runnable() {
        @Override
        public void run() {
            if (mLedGpio == null) return;
            try {
                if(counter == 0) mLedGpio.setValue(!mLedGpio4.getValue());
                else if(counter == 1) mLedGpio1.setValue(!mLedGpio.getValue());
                else if(counter == 2)mLedGpio2.setValue(!mLedGpio1.getValue());
                else if(counter == 3)mLedGpio3.setValue(!mLedGpio2.getValue());
                else if(counter == 4)mLedGpio4.setValue(!mLedGpio3.getValue());
                counter++;
                if (counter >=5) counter = 0;

                Log.d(TAG, "run: State set to " + mLedGpio.getValue());

                mHandler.postDelayed(mBlinkRunnable, INTERVAL_BETWEEN_BLINKS_MS);
            } catch (IOException e) {
                Log.e(TAG, "run: Error on PeripheralIO API", e);
            }
        }
    };
}
