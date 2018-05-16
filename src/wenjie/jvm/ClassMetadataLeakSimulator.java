package wenjie.jvm;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gongwenjie on 2018-05-16
 */
public class ClassMetadataLeakSimulator {
    private static Map<String, MetaspaceFacade> classLeakingMap = new HashMap<String, MetaspaceFacade>();
    private final static int NB_ITERATIONS_DEFAULT = 50000;

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Class metadata leak simulator");

        int nbIterations = NB_ITERATIONS_DEFAULT;

        try {

            for (int i = 0; i < nbIterations; i++) {

                String fictiousClassloaderJAR = "file:" + i + ".jar";

                URL[] fictiousClassloaderURL = new URL[] { new URL(fictiousClassloaderJAR) };

                URLClassLoader newClassLoader = new URLClassLoader(fictiousClassloaderURL);

                MetaspaceFacade t = (MetaspaceFacade) Proxy.newProxyInstance(newClassLoader,
                        new Class<?>[] { MetaspaceFacade.class },
                        new MetespaceFacadeInvocationHandler(new MetaspaceFacadeImpl()));

                classLeakingMap.put(fictiousClassloaderJAR, t);
            }
        }
        catch (Throwable any) {
            System.out.println("ERROR: " + any);
        }

        System.out.println("Done!");
    }
}
