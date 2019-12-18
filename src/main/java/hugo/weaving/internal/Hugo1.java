package hugo.weaving.internal;

import hugo.weaving.DebugLog1;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Hugo1 {

    @Pointcut("execution(@hugo.weaving.DebugLog1 * *(..))")
    public void pointcut() {
    }


    @Before("pointcut() && @annotation(as)")
    public void urlOpenMethod(DebugLog1 as) {
        System.out.println(as.value());
    }
}
