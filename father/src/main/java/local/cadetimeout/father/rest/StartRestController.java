package local.cadetimeout.father.rest;

import local.cadetimeout.father.cadence.CadenceStarterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StartRestController {

    private CadenceStarterService cadenceStarterService;

    @GetMapping("start/{id}")
    public String start(@PathVariable String id) {
        return cadenceStarterService.startWorkflow(id);
    }

    @GetMapping("start_child/{id}")
    public String startChild(@PathVariable String id) {
        return cadenceStarterService.startdWorkflowOfChild(id);
    }
}
