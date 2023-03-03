package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.SeekBar;

public class TvSeekBar extends SeekBar implements SeekBar.OnSeekBarChangeListener {

    public interface TvSeekBarListener {

        void onProgressChanged(TvSeekBar tvSeekBar, int progress, boolean fromUser);

        void onStartPreview(TvSeekBar tvSeekBar, int progress);

        void onStopPreview(TvSeekBar tvSeekBar, int progress);

    }

    private TvSeekBarListener listener;

    public TvSeekBar(Context context) {
        super(context);
        super.setOnSeekBarChangeListener(this);
    }

    public TvSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOnSeekBarChangeListener(this);
    }

    public TvSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setOnSeekBarChangeListener(this);
    }

    public void setListener(TvSeekBarListener listener) {
        this.listener = listener;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (listener != null) {
            listener.onProgressChanged(this, progress, fromUser);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (listener != null) {
            listener.onStartPreview(this, getProgress());
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (listener != null) {
            listener.onStopPreview(this, getProgress());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            onStartTrackingTouch(this);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            onStopTrackingTouch(this);
        }
        return super.onKeyUp(keyCode, event);
    }

}
