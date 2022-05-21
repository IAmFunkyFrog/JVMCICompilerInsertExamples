package jdk.vm.compilerTest;

import jdk.vm.ci.code.*;
import jdk.vm.ci.code.site.DataPatch;
import jdk.vm.ci.hotspot.*;
import jdk.vm.ci.meta.Assumptions;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.ValueKind;
import jdk.vm.ci.runtime.JVMCICompiler;
import jdk.vm.ci.code.site.Site;

import java.util.Objects;

public class MyEmptyCompiler implements JVMCICompiler {

    private static final byte[] asmCodeForAddMethod = {
            (byte) 0x8d, (byte) 0x04, (byte) 0x16, (byte) 0xc3
    };

    @Override
    public CompilationRequestResult compileMethod(CompilationRequest request) {
        HotSpotResolvedJavaMethod method = (HotSpotResolvedJavaMethod) request.getMethod();
        if (!Objects.equals(method.getName(), "add"))
            return HotSpotCompilationRequestResult.failure("Test compiler compiles only add method!", false);

        CompiledCode compiledCode =
                new HotSpotCompiledNmethod(method.getName(), asmCodeForAddMethod, asmCodeForAddMethod.length,
                        new Site[]{}, new Assumptions.Assumption[]{},
                        new ResolvedJavaMethod[]{method}, new HotSpotCompiledCode.Comment[0],
                        new byte[0], 16, new DataPatch[0], false, 32, StackSlot.get(ValueKind.Illegal, 0, false),
                        method, request.getEntryBCI(),
                        method.allocateCompileId(request.getEntryBCI()),
                        ((HotSpotCompilationRequest) request).getJvmciEnv(), false);

        CodeCacheProvider codeCacheProvider = HotSpotJVMCIRuntime.runtime().getHostJVMCIBackend().getCodeCache();
        codeCacheProvider.setDefaultCode(method, compiledCode);

        System.out.println(">> Code for add method installed");
        return HotSpotCompilationRequestResult.success(0);
    }

    @Override
    public boolean isGCSupported(int gcIdentifier) {
        return false;
    }
}
