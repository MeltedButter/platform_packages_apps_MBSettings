
package com.android.mbsettings.ui;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.mbsettings.Enabler;

public class PieEnabler extends Enabler {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e("PieEnabler", "onCheckedChanged");
        Settings.System.putInt(mContext.getContentResolver(),
                Settings.System.PIE_CONTROLS, isChecked ? 1 : 0);
    }

    public PieEnabler(Context context, Switch switch_) {
        super(context, switch_);

        resume();
    }

    @Override
    public void pause() {
        mContext.getContentResolver().unregisterContentObserver(this);
    }

    @Override
    public void resume() {
        mContext.getContentResolver().registerContentObserver(
                Settings.System.getUriFor(Settings.System.PIE_CONTROLS), false, this);
        updateSwitch();
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        updateSwitch();
    }
    
    private void updateSwitch() {
        mSwitch.setChecked(Settings.System.getInt(mContext.getContentResolver(),
                Settings.System.PIE_CONTROLS, 0) != 0);
    }
}
