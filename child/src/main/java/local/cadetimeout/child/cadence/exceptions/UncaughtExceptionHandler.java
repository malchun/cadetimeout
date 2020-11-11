package local.cadetimeout.child.cadence.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        log.error("Here what i caught!", throwable);
    }
}
