# Java 8 способ интеграции JVMCI компилятора

Как интегрировать пустой компилятор:
1. Необходимо собрать package с помощью [maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
<pre>mvn package</pre>
2. После сборки, должна появиться папка `target`, содержащая `MyEmptyCompiler-1.0-SNAPSHOT.jar` - `jar` файл с нашей реализацией компилятора. Запустим `Hello world!` программу, используя наш компилятор:
<pre>java -classpath ./target/MyEmptyCompiler-1.0-SNAPSHOT.jar --add-modules jdk.internal.vm.ci --add-exports jdk.internal.vm.ci/jdk.vm.ci.services=ALL-UNNAMED -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Djvmci.Compiler=MyEmptyCompiler ./test_program/Main.java</pre>
3. Также можно убедиться, что JVMCI компилятор в ответ на запросы о компиляции шлет ошибки. Для этого необходимо запустить `Hello world!` с такими опциями:
<pre>java -classpath ./target/MyEmptyCompiler-1.0-SNAPSHOT.jar --add-modules jdk.internal.vm.ci --add-exports jdk.internal.vm.ci/jdk.vm.ci.services=ALL-UNNAMED -XX:+UnlockExperimentalVMOptions -XX:+PrintCompilation -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Djvmci.Compiler=MyEmptyCompiler ./test_program/Main.java -Xcomp
</pre>
Среди вывода опции `-XX:+PrintCompilation` мы можем видеть сообщения, подобне этому, что сигнализирует о том, что компилятор был интегрирован верно:
<pre>945 1216       4       com.sun.tools.javac.util.ByteBuffer::getInt (59 bytes)   COMPILE SKIPPED: Empty compiler can`t compile any method (not retryable)</pre>