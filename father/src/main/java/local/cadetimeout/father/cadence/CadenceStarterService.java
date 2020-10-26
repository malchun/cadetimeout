package local.cadetimeout.father.cadence;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import com.uber.cadence.workflow.ChildWorkflowOptions;
import com.uber.cadence.workflow.Workflow;
import local.cadetimeout.child.workflow.ChildWorkflow;
import local.cadetimeout.father.workflow.ParentWorkflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Service
@Slf4j
public class CadenceStarterService {

    @Autowired
    private CadenceProperties cadenceProperties;
    private WorkflowClient workflowClient;

    @PostConstruct
    private void init() {
        workflowClient = WorkflowClient.newInstance(cadenceProperties.getHost(),
                                                    cadenceProperties.getPort(),
                                                    cadenceProperties.getDomain());
    }

    public String startWorkflow(String id) {
        WorkflowOptions wo = new WorkflowOptions.Builder()
                                        .setTaskList(cadenceProperties.getTaskList())
                                        .setWorkflowId(String.format("parent-%s", id))
                                        .setExecutionStartToCloseTimeout(Duration.ofSeconds(5))
                                        .build();
        ParentWorkflow w = workflowClient.newWorkflowStub(ParentWorkflow.class, wo);
        WorkflowExecution we = WorkflowClient.start(w::process, id);
        log.info(String.format("Started parent workflow %s", we.getRunId()));
        return "Yes!";
    }


    public String startChildWorkflow(String id) {
        ChildWorkflowOptions cwo = new ChildWorkflowOptions.Builder()
                .setTaskList("CHILD_TASK_LIST")
                .setWorkflowId(String.format("child-%s", id))
                .setExecutionStartToCloseTimeout(Duration.ofSeconds(20))
                .build();
        ChildWorkflow w = Workflow.newChildWorkflowStub(ChildWorkflow.class, cwo);
        log.info(String.format("Starting child workflow %s", id));
        String result = w.process(id);
        return String.format("Yes, my boy! It's %s", result);
    }
}
