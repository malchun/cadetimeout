package local.cadetimeout.child.cadence;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import local.cadetimeout.child.cadence.workflow.ChildWorkflow;
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
                                        .setWorkflowId(String.format("child-%s", id))
                                        .setExecutionStartToCloseTimeout(Duration.ofSeconds(20))
                                        .build();
        ChildWorkflow w = workflowClient.newWorkflowStub(ChildWorkflow.class, wo);
        WorkflowExecution we = WorkflowClient.start(w::process, id);
        log.info(String.format("Started workflow %s", we.getRunId()));
        return "Yes!";
    }
}
