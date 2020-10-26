package local.cadetimeout.father.util;

import local.cadetimeout.father.cadence.CadenceStarterService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static CadenceStarterService getCadenceStarterService() {
        return applicationContext.getBean(CadenceStarterService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
