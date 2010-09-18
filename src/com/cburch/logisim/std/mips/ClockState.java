/* Copyright (c) 2006, 2009, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.std.mips;

import com.cburch.logisim.std.memory.*;

import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.StdAttr;

class ClockState implements Cloneable {
    private Value lastClock;
    
    public ClockState() {
        lastClock = Value.FALSE;
    }
    
    public Object clone() {
        try {
            return super.clone();
        } catch(CloneNotSupportedException e) { return null; }
    }
        
    public boolean updateClock(Value newClock, Object trigger) {
        Value oldClock = lastClock;
        lastClock = newClock;
        if(trigger == null || trigger == StdAttr.TRIG_RISING) {
            return oldClock == Value.FALSE && newClock == Value.TRUE;
        } else if(trigger == StdAttr.TRIG_FALLING) {
            return oldClock == Value.TRUE && newClock == Value.FALSE;
        } else if(trigger == StdAttr.TRIG_HIGH) {
            return newClock == Value.TRUE;
        } else if(trigger == StdAttr.TRIG_LOW) {
            return newClock == Value.FALSE;
        } else {
            return oldClock == Value.FALSE && newClock == Value.TRUE;
        }
    }
}
