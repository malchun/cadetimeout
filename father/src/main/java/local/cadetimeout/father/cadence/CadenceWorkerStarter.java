package local.cadetimeout.father.cadence;

import com.uber.cadence.*;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import local.cadetimeout.father.cadence.workflow.ParentWorkflowImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CadenceWorkerStarter implements CommandLineRunner {

    private CadenceProperties cadenceProperties;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Starting worker");
        registerDomain();
        Worker.Factory factory = buildWorkerFacory();
        factory.start();
    }

    private void registerDomain() {
        log.debug("Registering domain");
        IWorkflowService cadenceService = new WorkflowServiceTChannel(
                                                cadenceProperties.getHost(), cadenceProperties.getPort());
        RegisterDomainRequest request = new RegisterDomainRequest();
        request.setDescription(cadenceProperties.getDescription());
        request.setEmitMetric(cadenceProperties.getEmitMetric());
        request.setName(cadenceProperties.getDomain());
        request.setWorkflowExecutionRetentionPeriodInDays(cadenceProperties.getRetentionPeriodInDays());
        try {
            cadenceService.RegisterDomain(request);
            log.info("Domain created");
        } catch (DomainAlreadyExistsError error) {
            log.info("Domain already exists");
        } catch (TException error) {
            log.error(error.getStackTrace().toString());
        }
    }

    private Worker.Factory buildWorkerFacory() {
        Worker.Factory factory = new Worker.Factory(cadenceProperties.getDomain());
        Worker worker = factory.newWorker(cadenceProperties.getTaskList());
        worker.registerWorkflowImplementationTypes(ParentWorkflowImpl.class);
        return factory;
    }
}
