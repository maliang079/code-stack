package com.test.lambda;

/**
 *Java8 lambda学习知识点
 *
 * 为什么使用lambda表达式？
 * lambda是一个匿名函数，我们可以把lambda表达式理解为是一段可以传递的代码（将代码像数据一样传递）。
 * 可以写出更简洁，更灵活的代码。作为一种更紧凑的代码分格，使java的语言表达能力得到了提升
 *
 * lambda表达式其实也就是能用于替换匿名内部类，也即是对接口的实现
 * lambda表达式需要函数式接口的支持（所谓函数式接口就是接口中只能有一个抽象方法，可以使用注解@FunctionalInterface修饰，用于检查接口是否是函数式接口）
 *
 * lambda表达式的基础语法，java8中引入了一个新的操作符“->”，该操作符称为箭头操作符或者lambda操作符
 * 箭头操作符将lambda表达式拆分成两部分：
 *
 * 左侧：lambda表达式的参数列表（即也即是接口中抽象方法的参数列表）
 * 右侧：lambda表达式中所需要执行的功能，即lambda体 （即也即是接口中抽象方法的具体实现）
 *
 * 语法格式一：无参，无返回值
 *            eg. () -> System.out.println("hello lambda");
 *
 * 语法格式二：有一个参数，无返回值
 *            eg. Consumer<String> consumer = (str) -> System.out.println(str);
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 *            eg. Consumer<String> consumer = str -> System.out.println(str);
 *
 * 语法格式四：有两个以上的参数，并且lambda体中有多条语句，必须使用大括号
 *            eg. Comparator<Integer> comparator = (x, y) -> {x = x + y; return Integer.compare(x, y);}
 *
 * 语法格式五：若lambda体中只有一行语句，则“return”和大括号都可以省略
 *            eg. Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：lambda参数列表的数据类型可以省略不写，因为jvm的编译器可以通过上下文推断出数据类型，即“类型推断”  java8中增强了类型推断
 *
 * 总结：
 * 左右遇一括号省
 * 左侧推断类型省
 *
 *
 *
 *
 * 一、Java8 内置的四大核心函数式接口
 *         Consumer<T>: 消费型接口
 *             void accept(T t);
 *
 *         Supplier<T>: 供给型接口
 *             T get();
 *
 *         Function<T, R>: 函数型接口
 *             R apply(T t);
 *
 *         Predicate<T>: 断言型接口
 *             boolean test(T t);
 *
 *
 * 二、方法引用和构造器引用
 *     方法引用：若lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 *              （可以理解为方法引用是lambda表达式的另一种表现形式）
 *     主要有三种语法格式
 *         对象::实例方法名    eg. Consumer<String> consumer = System.out::print;
 *         类::静态方法名      eg. Comparator<Integer> comparator = Integer::compare;
 *         类::实例方法名      eg. BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);等同于
 *                                BiPredicate<String, String> biPredicate = String::equals;
 *     注意：
 *         lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 *         若lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时可以使用   类::实例方法名
 *
 *     构造器引用：
 *         格式：
 *             类::new
 *             eg. 假设User有String name、int age两个属性，有一个无参的构造方法，有一个只有name的构造方法，有一个同时有name，age的构造方法
 *                 Supplier<User> supplier = User::new;  此时调用的是无参构造方法
 *                 Function<String, User> function = User::new; 此时调用的是只有name参数的构造方法
 *                 BiFunction<String, Integer, User> biFunction = User::new; 此时调用的是同时有name，age的构造方法
 *     注意：需要调用的构造器的参数列表，要与函数式接口中抽象方法的参数列表保持一致
 *
 *     数组引用：
 *         格式：
 *            Type[]::new
 *            eg. String[]::new
 *                Function<Integer, String[]> function = (num) -> new String[num];等同于
 *                Function<Integer, String[]> function = String[]::new;    返回指定长度的数组
 *
 *
 *
 */
public class TestLambda {
}
