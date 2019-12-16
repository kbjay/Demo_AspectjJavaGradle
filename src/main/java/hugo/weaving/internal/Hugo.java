package hugo.weaving.internal;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Hugo {

    @Pointcut("within(@hugo.weaving.DebugLog *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {
    }

    @Pointcut("execution(@hugo.weaving.DebugLog * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Pointcut("execution(@hugo.weaving.DebugLog *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    @Around("method() || constructor()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        enterMethod(joinPoint);

        Object result = joinPoint.proceed();

        return result;
    }

    private void enterMethod(ProceedingJoinPoint joinPoint) {
        System.out.println("before!!!");
    }

}
