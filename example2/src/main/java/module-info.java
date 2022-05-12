module jdk.internal.vm.compiler {
    requires jdk.internal.vm.ci;

    provides jdk.vm.ci.services.JVMCIServiceLocator with
            jdk.vm.compilerTest.MyEmptyCompilerJVMCIServiceLocator;
}