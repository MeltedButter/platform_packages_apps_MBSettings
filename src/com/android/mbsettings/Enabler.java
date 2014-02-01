
package com.android.mbsettings;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.widget.CompoundButton;
import android.widget.Switch;

public abstract class Enabler extends ContentObserver implements
        CompoundButton.OnCheckedChangeListener {
    protected final Context mContext;
    protected Switch mSwitch;

    public Enabler(Context context, Switch switch_) {
        super(new Handler());
        mContext = context;
        mSwitch = switch_;
    }

    public void setSwitch(Switch switch_) {
        if (mSwitch == switch_)
            return;
        mSwitch.setOnCheckedChangeListener(null);
        mSwitch = switch_;
        mSwitch.setOnCheckedChangeListener(this);
        updateSwitch();
    }
    
    @Override
    public void onChange(boolean selfChange) {
        onChange(selfChange, null);
    }

    public abstract void pause();

    public abstract void resume();

    protected abstract void updateSwitch();
}
