# Java 9+ способ интеграции JVMCI компилятора

Как интегрировать пустой компилятор с помощью [Java 9 modules](https://www.oracle.com/cis/corporate/features/understanding-java-9-modules.html):
1. Необходимо собрать package с помощью [maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
<pre>mvn package</pre>
2. После сборки, должна появиться папка `target`, содержащая `MyEmptyCompiler-1.0-SNAPSHOT.jar` - `jar` файл с нашей реализацией компилятора. Запустим `Hello world!` программу, используя наш компилятор:
<pre>java --upgrade-module-path ./target/MyEmptyCompiler-1.0-SNAPSHOT.jar -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Djvmci.Compiler=MyEmptyCompiler ./test_program/Main.java</pre>
3. Также можно убедиться, что JVMCI компилятор в ответ на запросы о компиляции шлет ошибки. Для этого необходимо запустить `Hello world!` с такими опциями:
<pre>java --upgrade-module-path ./target/MyEmptyCompiler-1.0-SNAPSHOT.jar -XX:+UnlockExperimentalVMOptions -XX:+PrintCompilation -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Djvmci.Compiler=MyEmptyCompiler ./test_program/Main.java -Xcomp</pre>
Среди вывода опции `-XX:+PrintCompilation` мы можем видеть сообщения, подобне этому, что сигнализирует о том, что компилятор был интегрирован верно:
<pre>1206 1266       4       jdk.internal.misc.Unsafe::allocateUninitializedArray0 (90 bytes)   COMPILE SKIPPED: Empty compiler can`t compile any method (not retryable)</pre>