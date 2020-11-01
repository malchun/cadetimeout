package local.cadetimeout.child.cadence.workflow;

import com.uber.cadence.workflow.Workflow;
import local.cadetimeout.child.cadence.activity.SaveEventActivity;
import local.cadetimeout.child.domain.Event;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildWorkflowImpl implements ChildWorkflow {

    private final SaveEventActivity saveEventActivity = Workflow.newActivityStub(SaveEventActivity.class);

    @SneakyThrows
    @Override
    public String process(String s) {
        log.info(String.format("I'm child workflow processing %s", s));
        //log.info("Sleeping 10 sec");
        //Thread.sleep(10000);
        log.info("Awake! Now saving event!");
        saveEventActivity.saveEvent(buildEvent(s));
        return String.format("%s done!", s);
    }

    private Event buildEvent(String data) {
        Event event = new Event();
        event.setData(data);
        return event;
    }
}
