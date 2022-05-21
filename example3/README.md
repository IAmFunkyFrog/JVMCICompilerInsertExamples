# Компиляция одного метода add используя JVMCI

Как запустить пример:
1. Необходимо собрать package с помощью [maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
<pre>mvn package</pre>
2. После сборки, должна появиться папка `target`, содержащая `MyEmptyCompiler-1.0-SNAPSHOT.jar` - `jar` файл с нашей реализацией компилятора. Запустим `Main.java` программу, используя наш компилятор:
<pre>java -classpath ./target/MyEmptyCompiler-1.0-SNAPSHOT.jar --add-modules jdk.internal.vm.ci --add-exports jdk.internal.vm.ci/jdk.vm.ci.services=ALL-UNNAMED -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Djvmci.Compiler=MyEmptyCompiler ./test_program/Main.java</pre>
3. В ходе работы программа `Main.java` складывает числа от 0 до 4999, а после печатает сумму на экран. В некоторый момент ее работы метод `add` должен отправиться на компиляцию в JVMCI, и, после компиляции, будет напечатано
<pre>
...
Using add method to sum 5602878 with 3348
>> Code for add method installed
Using add method to sum 5606226 with 3349
...
</pre>
С этого момента, сложение будет производится с помощью скомпилированного кода.