package local.cadetimeout.father.workflow;

import local.cadetimeout.father.cadence.CadenceStarterService;
import local.cadetimeout.father.util.BeanProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParentWorkflowImpl implements ParentWorkflow {

    private final CadenceStarterService cadenceStarterService = BeanProvider.getCadenceStarterService();

    @Override
    public void process(String s) {
        log.info(String.format("I'm parent workflow processing %s", s));
        cadenceStarterService.startChildWorkflow(s);
    }
}
