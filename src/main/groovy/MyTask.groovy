import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner
import io.github.lukehutch.fastclasspathscanner.scanner.ClasspathFinder
import io.github.lukehutch.fastclasspathscanner.scanner.classloaderhandler.ClassLoaderHandler
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task

/**
 * Created by vnemec on 5/16/16.
 */
class MyTask extends Task {
    @Override
    public void execute() throws BuildException {
        FastClasspathScanner scanner = new FastClasspathScanner();

        def next = scanner.classpathFinder.classLoaderHandlerLoader.iterator().hasNext()

        log("Classpath finder found at least one class loader handler: $next", Project.MSG_WARN)

        def serviceLoaderFoundAny = ServiceLoader.load(ClassLoaderHandler.class).iterator().hasNext()

        log("ServiceLoader found at least one class loader handler: $serviceLoaderFoundAny", Project.MSG_WARN)

        def serviceLoaderFoundAnyUsingProvidedClassloader = ServiceLoader.load(ClassLoaderHandler.class, ClasspathFinder.class.getClassLoader()).iterator().hasNext()

        log("ServiceLoader found at least one class loader handler using provided classloader: $serviceLoaderFoundAnyUsingProvidedClassloader", Project.MSG_WARN)
    }
}
