package local.cadetimeout.child.workflow;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildWorkflowImpl implements ChildWorkflow{

    @SneakyThrows
    @Override
    public String process(String s) {
        log.info(String.format("I'm child workflow processing %s", s));
        log.info("Sleeping 10 sec");
        Thread.sleep(10000);
        log.info("Awake!");
        return String.format("%s done!", s);
    }
}
