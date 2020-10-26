package local.cadetimeout.child.cadence;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class CadenceProperties {
    private String domain = "cadetimeout";
    private String host = "localhost";
    private Integer port = 7933;
    private String description = "test domain";
    private Boolean alwaysSave = true;
    private String taskList = "CHILD_TASK_LIST";
    private Integer retentionPeriodInDays = 5;
    private Boolean emitMetric = true;
}
