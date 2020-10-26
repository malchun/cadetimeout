package local.cadetimeout.child.rest;

import local.cadetimeout.child.cadence.CadenceStarterService;
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
}
