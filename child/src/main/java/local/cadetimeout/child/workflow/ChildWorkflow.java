package local.cadetimeout.child.workflow;

import com.uber.cadence.workflow.WorkflowMethod;

public interface ChildWorkflow {

    @WorkflowMethod
    String process(String s);
}
