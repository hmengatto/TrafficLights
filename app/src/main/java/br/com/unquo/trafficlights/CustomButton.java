package br.com.unquo.trafficlights;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by HenriqueM on 25/03/2018.
 */

public class CustomButton extends AppCompatButton
{
    public CustomButton(Context context) {
        this(context, null);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        setBackground(ContextCompat.getDrawable(this.getContext(),R.drawable.custom_button_selector));
        setTextColor(ContextCompat.getColor(getContext(),R.color.black));
        setGravity(Gravity.CENTER);
        setTextSize(18);
        setMinHeight(4);
        setPadding(5,8,5,8);
        setAllCaps(true);
    }

}