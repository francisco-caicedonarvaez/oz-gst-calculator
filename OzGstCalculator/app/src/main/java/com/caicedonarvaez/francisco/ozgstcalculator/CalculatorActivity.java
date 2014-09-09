package com.caicedonarvaez.francisco.ozgstcalculator;

import com.caicedonarvaez.francisco.ozgstcalculator.classes.AppData;
import com.caicedonarvaez.francisco.ozgstcalculator.classes.GSTCalculator;
import com.caicedonarvaez.francisco.ozgstcalculator.util.SystemUiHider;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class CalculatorActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    /**
     * Get Conntrols form layout
     */
    private TextView captionAmountTextView;
    private TextView captionSubtotalTextView;
    private TextView captionGstComponentTextView;
    private TextView captionTotalTextView;

    private TextView amountTextView;
    private TextView subtotalTextView;
    private TextView gstComponentTextView;
    private TextView totalTextView;
    private TextView gstRateTextView;

    private ImageButton componentGSTButton;
    private ImageButton addGSTButton;
    private Button resetButton;

    private Typeface numberTypeface;
    private Typeface textTypeface;

    private GSTCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator);

        captionAmountTextView = (TextView) findViewById(R.id.captionAmountTextView);
        captionSubtotalTextView = (TextView) findViewById(R.id.captionSubtotalTextView);
        captionGstComponentTextView = (TextView) findViewById(R.id.captionGstTextView);
        captionTotalTextView = (TextView) findViewById(R.id.captionTotalTextView);

        amountTextView = (TextView) findViewById(R.id.amountEditText);
        subtotalTextView = (TextView) findViewById(R.id.subtotalTextView);
        gstComponentTextView = (TextView) findViewById(R.id.gstTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        gstRateTextView = (TextView) findViewById(R.id.gstRateTextView);

        componentGSTButton = (ImageButton) findViewById(R.id.componentGstImageButton);
        addGSTButton = (ImageButton) findViewById(R.id.addGSTImageButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        componentGSTButton.setOnTouchListener(onClickListenerForAppButtons);
        addGSTButton.setOnTouchListener(onClickListenerForAppButtons);
        resetButton.setOnTouchListener(onClickListenerForAppButtons);

        try {
            //-- app font styles
            textTypeface = Typeface.createFromAsset(getAssets(), AppData.TEXT_FONT);
            numberTypeface = Typeface.createFromAsset(getAssets(), AppData.NUMBER_FONT);


            captionAmountTextView.setTypeface(textTypeface);
            captionSubtotalTextView.setTypeface(textTypeface);
            captionGstComponentTextView.setTypeface(textTypeface);
            captionTotalTextView.setTypeface(textTypeface);
            resetButton.setTypeface(textTypeface);
            gstRateTextView.setTypeface(textTypeface);

            amountTextView.setTypeface(numberTypeface);
            subtotalTextView.setTypeface(numberTypeface);
            gstComponentTextView.setTypeface(numberTypeface);
            totalTextView.setTypeface(numberTypeface);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private View.OnTouchListener onClickListenerForAppButtons = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {

            if (amountTextView.getText().length() > 0)
                calculator = new GSTCalculator(Double.parseDouble(amountTextView.getText().toString()));
            else
                calculator = new GSTCalculator(0.0);

            switch (v.getId()) {
                case R.id.componentGstImageButton:

                    calculator.calculateGST();

                    subtotalTextView.setText(AppData.moneyFormat.format(calculator.getSubTotal()));
                    gstComponentTextView.setText(AppData.moneyFormat.format(calculator.getGst()));
                    totalTextView.setText(AppData.moneyFormat.format(calculator.getTotal()));

                    break;

                case R.id.addGSTImageButton:


                    calculator.addGSTtoTotal();

                    subtotalTextView.setText(AppData.moneyFormat.format(calculator.getSubTotal()));
                    gstComponentTextView.setText(AppData.moneyFormat.format(calculator.getGst()));
                    totalTextView.setText(AppData.moneyFormat.format(calculator.getTotal()));

                    break;

                case R.id.resetButton:

                    //-- clear TextViews
                    amountTextView.setText("");
                    subtotalTextView.setText("");
                    gstComponentTextView.setText("");
                    totalTextView.setText("");

                    break;
            }
            return false;
        }
    };
}
