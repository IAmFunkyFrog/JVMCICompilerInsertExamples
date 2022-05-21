package jdk.vm.compilerTest;

import jdk.vm.ci.runtime.JVMCICompilerFactory;
import jdk.vm.ci.services.JVMCIServiceLocator;

public class MyEmptyCompilerJVMCIServiceLocator extends JVMCIServiceLocator {
    @Override
    protected <S> S getProvider(Class<S> service) {
        if(service == JVMCICompilerFactory.class) {
            return service.cast(new MyEmptyCompilerFactory());
        }
        return null;
    }
}
