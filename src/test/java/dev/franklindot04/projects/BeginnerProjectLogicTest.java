package dev.franklindot04.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BeginnerProjectLogicTest {
    @TempDir
    Path tempDir;

    @Test
    void calculatorPerformsBasicOperations() throws Exception {
        ClassLoader classLoader = compileProject(
                "calculator-cli",
                "Calculator.java",
                "CalculatorApp.java");
        Class<?> calculatorClass = classLoader.loadClass("Calculator");
        Object calculator = calculatorClass.getConstructor().newInstance();

        assertEquals(8.0, invokeDouble(calculator, "add", 5.0, 3.0));
        assertEquals(2.0, invokeDouble(calculator, "subtract", 5.0, 3.0));
        assertEquals(15.0, invokeDouble(calculator, "multiply", 5.0, 3.0));
        assertEquals(2.5, invokeDouble(calculator, "divide", 5.0, 2.0));
    }

    @Test
    void calculatorRejectsDivisionByZero() throws Exception {
        ClassLoader classLoader = compileProject(
                "calculator-cli",
                "Calculator.java",
                "CalculatorApp.java");
        Class<?> calculatorClass = classLoader.loadClass("Calculator");
        Object calculator = calculatorClass.getConstructor().newInstance();
        Method divide = calculatorClass.getMethod("divide", double.class, double.class);

        InvocationTargetException error = assertThrows(
                InvocationTargetException.class,
                () -> divide.invoke(calculator, 5.0, 0.0));

        assertInstanceOf(IllegalArgumentException.class, error.getCause());
        assertEquals("Cannot divide by zero.", error.getCause().getMessage());
    }

    @Test
    void numberGuessingRoundReportsLowHighAndCorrectGuesses() throws Exception {
        ClassLoader classLoader = compileProject(
                "number-guessing-game",
                "GameRound.java",
                "NumberGuessingGame.java");
        Class<?> gameRoundClass = classLoader.loadClass("GameRound");
        Object round = gameRoundClass.getConstructor(int.class).newInstance(42);
        Method guess = gameRoundClass.getMethod("guess", int.class);
        Method attempts = gameRoundClass.getMethod("attempts");

        assertEquals("TOO_LOW", guess.invoke(round, 30).toString());
        assertEquals("TOO_HIGH", guess.invoke(round, 50).toString());
        assertEquals("CORRECT", guess.invoke(round, 42).toString());
        assertEquals(3, attempts.invoke(round));
    }

    @Test
    void todoListAddsCompletesAndDeletesTasks() throws Exception {
        ClassLoader classLoader = compileProject(
                "todo-list-cli",
                "Task.java",
                "TaskList.java",
                "TodoApp.java");
        Class<?> taskListClass = classLoader.loadClass("TaskList");
        Object taskList = taskListClass.getConstructor().newInstance();

        Method add = taskListClass.getMethod("add", String.class);
        Method all = taskListClass.getMethod("all");
        Method markComplete = taskListClass.getMethod("markComplete", int.class);
        Method delete = taskListClass.getMethod("delete", int.class);

        add.invoke(taskList, " Study arrays ");
        add.invoke(taskList, "Build a small project");
        markComplete.invoke(taskList, 1);

        List<?> tasks = (List<?>) all.invoke(taskList);
        assertEquals(2, tasks.size());
        assertEquals("Study arrays", invokeString(tasks.get(0), "description"));
        assertEquals(true, tasks.get(0).getClass().getMethod("isComplete").invoke(tasks.get(0)));

        delete.invoke(taskList, 1);
        tasks = (List<?>) all.invoke(taskList);
        assertEquals(1, tasks.size());
        assertEquals("Build a small project", invokeString(tasks.get(0), "description"));
    }

    @Test
    void todoListRejectsBlankTasksAndInvalidTaskNumbers() throws Exception {
        ClassLoader classLoader = compileProject(
                "todo-list-cli",
                "Task.java",
                "TaskList.java",
                "TodoApp.java");
        Class<?> taskListClass = classLoader.loadClass("TaskList");
        Object taskList = taskListClass.getConstructor().newInstance();
        Method add = taskListClass.getMethod("add", String.class);
        Method markComplete = taskListClass.getMethod("markComplete", int.class);

        InvocationTargetException blankTask = assertThrows(
                InvocationTargetException.class,
                () -> add.invoke(taskList, "   "));
        assertInstanceOf(IllegalArgumentException.class, blankTask.getCause());

        InvocationTargetException missingTask = assertThrows(
                InvocationTargetException.class,
                () -> markComplete.invoke(taskList, 1));
        assertInstanceOf(IllegalArgumentException.class, missingTask.getCause());
        assertEquals("Task number does not exist.", missingTask.getCause().getMessage());
    }

    private ClassLoader compileProject(String projectFolder, String... files) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Path outputDirectory = tempDir.resolve(projectFolder);
        outputDirectory.toFile().mkdirs();

        String[] arguments = new String[files.length + 2];
        arguments[0] = "-d";
        arguments[1] = outputDirectory.toString();

        for (int index = 0; index < files.length; index++) {
            arguments[index + 2] = Path.of("projects", "beginner", projectFolder, files[index]).toString();
        }

        int result = compiler.run(null, null, null, arguments);
        assertEquals(0, result, "Project should compile before its logic is tested.");

        return new URLClassLoader(new URL[] {outputDirectory.toUri().toURL()});
    }

    private double invokeDouble(Object target, String methodName, double first, double second) throws Exception {
        Method method = target.getClass().getMethod(methodName, double.class, double.class);
        return (double) method.invoke(target, first, second);
    }

    private String invokeString(Object target, String methodName) throws Exception {
        Method method = target.getClass().getMethod(methodName);
        return (String) method.invoke(target);
    }
}
