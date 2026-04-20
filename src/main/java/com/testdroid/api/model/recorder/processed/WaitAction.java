package com.testdroid.api.model.recorder.processed;

import com.testdroid.api.model.recorder.input.ActionType;

/**
 * @author Artur Ćwikliński <artur.cwiklinski@smartbear.com>
 */
public class WaitAction implements RecordedAction {

    private long durationSeconds;

    private long createTime;

    @Override
    public ActionType getType() {
        return ActionType.WAIT;
    }

    @Override
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public long getCreateTime() {
        return createTime;
    }

    public WaitAction withDurationSeconds(long durationSeconds) {
        this.durationSeconds = durationSeconds;
        return this;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }
}
