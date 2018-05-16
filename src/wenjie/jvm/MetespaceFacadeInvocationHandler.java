package wenjie.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Gongwenjie on 2018-05-16
 */
public class MetespaceFacadeInvocationHandler implements InvocationHandler{
    private Object classAImpl;

    public MetespaceFacadeInvocationHandler(Object impl) {
        this.classAImpl = impl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(classAImpl, args);
    }
}
