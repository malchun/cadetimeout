package local.cadetimeout.child.cadence.activity;

import com.uber.cadence.activity.ActivityMethod;
import local.cadetimeout.child.domain.Event;

public interface SaveEventActivity {

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 100)
    void saveEvent(Event event);
}
