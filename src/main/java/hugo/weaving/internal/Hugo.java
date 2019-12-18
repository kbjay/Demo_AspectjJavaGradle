package hugo.weaving.internal;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Hugo {
    //标有DebugLog 的类
    @Pointcut("within(@hugo.weaving.DebugLog *)")
    public void withinAnnotatedClass() {
    }

    //非编译器生成的方法  && 在用debugLog标记的类中
    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    //非编译器生成的构造方法 && 在用debuglog标记的类中
    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {
    }

    //用debug标记的所有方法 || （非编译器生成的方法  && 在用debugLog标记的类中）
    @Pointcut("execution(@hugo.weaving.DebugLog * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    //用debug标记的构造方法 || （非编译器生成的构造方法  && 在用debugLog标记的类中）
    @Pointcut("execution(@hugo.weaving.DebugLog *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    @Around("method() || constructor()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        enterMethod(joinPoint);

        //执行方法
        Object result = joinPoint.proceed();

        return result;
    }

    private void enterMethod(ProceedingJoinPoint joinPoint) {
        System.out.println("before!!!");
    }

}
