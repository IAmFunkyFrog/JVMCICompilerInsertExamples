package jdk.vm.compilerTest;

import jdk.vm.ci.code.CompilationRequest;
import jdk.vm.ci.code.CompilationRequestResult;
import jdk.vm.ci.hotspot.*;
import jdk.vm.ci.runtime.JVMCICompiler;

public class MyEmptyCompiler implements JVMCICompiler {

    @Override
    public CompilationRequestResult compileMethod(CompilationRequest request) {
        return HotSpotCompilationRequestResult.failure("Empty compiler can`t compile any method", false);
    }

    @Override
    public boolean isGCSupported(int gcIdentifier) {
        return false;
    }
}
