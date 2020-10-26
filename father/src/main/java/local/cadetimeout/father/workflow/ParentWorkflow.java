package local.cadetimeout.father.workflow;

import com.uber.cadence.workflow.WorkflowMethod;

public interface ParentWorkflow {

    @WorkflowMethod
    void process(String s);
}
