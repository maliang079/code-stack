package com.test.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 *     1. 创建Stream
 *         创建Stream的方式：
 *             1).通过Collection系列集合提供的stream()、parallelStream()
 *                eg. List<String> list = new ArrayList<>();
 *                    Stream<String> stream = list.stream();
 *
 *             2).通过Arrays中的静态方法stream()获取数组流
 *                eg. String[] strArrays = new String[10];
 *                    String<String> stream = Arrays.stream(strArrays);
 *
 *             3).通过Stream中的静态方法of()
 *                eg. Stream<String> stream = Stream.of("aaa", "bbb", "ccc");
 *
 *             4).创建无限流
 *                迭代生成：
 *                eg. Stream<Integer> stream = Stream.iterate(1, (x) -> x +1);
 *                generate生成：
 *                eg. Stream<Double> stream = Stream.generate(Math::random);
 *
 *     2. 中间操作
 *        多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何处理。
 *        而在终止操作时一次性全部处理，称为“惰性求值”
 *
 *        1). 筛选与切片
 *            filter 接受lambda，从流中排除某些元素
 *            limit 截断流，使其数量不超过给定数量
 *            skip  跳过元素，返回一个扔掉了前n个元素的流。若流中不足n个元素，则返回一个空流
 *            distinct  通过流元素的hashCode()和equals()去除重复元素
 *
 *        2). 映射
 *            map
 *            flatMap  接收一个函数作为参数，将流中的每个值转换成另一个流，然后再把所有的流连接成一个流
 *
 *        3). 排序
 *            sorted 自然排序   Comparable
 *            sorted(Comparator comparator) 定制排序
 *
 *        4). 查找与匹配
 *            allMatch  检查是否匹配所有元素
 *            anyMatch  检查是否至少匹配一个元素
 *            noneMatch  检查是否没有匹配所有元素
 *            findFirst  返回第一个元素
 *            findAny  返回当前流中的任意一个元素
 *            count  返回流中元素的总个数
 *            max  返回流中最大元素
 *            min  返回流中最小元素
 *
 *        5). 归约
 *            reduce  可以将流中元素反复结合起来得到一个值
 *
 *        6). 收集
 *            collect  将流转化为其它形式。接收一个Collector接口的实现，用于给stream中元素做汇总的方法
 *
 *     3. 终止操作(终端操作)
 *
 * 二、并行流与顺序流
 *     并行流就是把一个内容分成多个数据块，别用不同的线程分别处理每个数据块的流。
 *     java8中将并行进行了优化，我们可以很容易的对数据进行并行操作。Stream api
 *     可以声明性的通过parallel()与sequential()在并行流和顺序流之间进行切换
 *
 * 三、Optional
 *     代表一个值存在或者不存在
 *
 * 四、接口中的默认方法和静态方法
 *     接口默认方法的“类优先原则”
 *     若一个接口中定义了一个默认方法，而另一个类中又定义了一个同名的方法时
 *     当一个类同时继承该接口和实现类时，选择父类中的方法，那么接口中具有相同名称和参数列表的默认方法被忽略
 *
 *     若一个接口中定义了一个默认方法，而另一个接口中也定义了同名的默认方法时
 *     当一个类同时继承这两个接口时，引发接口冲突，必须用覆盖方法来解决冲突。(参考下面代码的Hello类)
 *
 * 五、时间和日期
 *
 * 六、重复注解和类型注解
 *
 *
 *
 */
public class TestStream {

    public static class User {
        private int id;
        private String name;
        private int age;
        private String address;
        private String sex;

        public User() {}

        public User(int id, String name, int age, String address, String sex) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
            this.sex = sex;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

    private static boolean isSexMale(User user) {
        return user.getSex().equals("男");
    }

    class Hello implements Hahaha, Heiheihei {

        @Override
        public String say() {
            return Hahaha.super.say();
        }

    }

    interface Hahaha {
        default String say() {
            return "hahaha";
        }
    }

    interface Heiheihei {
        default String say() {
            return "heiheihei";
        }
    }

    public static void main(String[] args) {

        // 通过iterate、generate生成stream
        Stream.iterate(1, (x) -> x +1);
        Stream.generate(Math::random);

        List<User> list = Arrays.asList(new User(1, "张三", 38, "大望路", "男"),
                new User(2, "李四", 27, "国贸", "男"),
                new User(3, "王五", 24, "天宁寺", "女"),
                new User(4, "赵六", 40, "回龙观", "男"),
                new User(6, "田七", 32, "天通苑", "女"),
                new User(7, "孙八", 35, "天安门", "女"));


        System.out.println("是否性别都是男:");
        System.out.println(list.stream().allMatch(TestStream::isSexMale));

        System.out.println("是否有任意一个性别为男:");
        System.out.println(list.stream().anyMatch(TestStream::isSexMale));

        System.out.println("所有用户年龄总和:");
        System.out.println(list.stream().map(User::getAge).reduce(0, Integer::sum));

        System.out.println("获取年龄大于30的用户:");
        list.stream().filter(user -> user.getAge() > 30).collect(Collectors.toList()).stream().forEach(System.out::println);

        System.out.println("所有用户按照年龄进行排序");
        list.stream().sorted(Comparator.comparingInt(User::getAge)).forEach(System.out::println);

        System.out.println("求所有用户年龄的平均值:");
        System.out.println(list.stream().collect(Collectors.averagingInt(User::getAge)));

        System.out.println("求用户个数：");
        System.out.println(list.stream().collect(Collectors.counting()));

        System.out.println("把所有用户信息转换成仪name为key，address为value的map类型：");
        System.out.println(list.stream().collect(Collectors.toMap(User::getName, User::getAddress)));

        System.out.println("把所有用户按照性别分组:");
        System.out.println(list.stream().collect(Collectors.groupingBy(User::getSex)));

        System.out.println("把所有用户先按照性别分组，再按照住址分组:");
        System.out.println(list.stream().collect(Collectors.groupingBy(User::getSex, Collectors.groupingBy(User::getAddress))));

        System.out.println("把所有用户先按照性别分组，再按照住址分组，最后从user中只提取name信息:");
        System.out.println(list.stream().collect(Collectors.groupingBy(User::getSex, Collectors.groupingBy(User::getAddress, Collectors.mapping(User::getName, Collectors.toList())))));

        System.out.println("把所有用户的name提取出来放到list中");
        System.out.println(list.stream().collect(Collectors.mapping(User::getName, Collectors.toList())));

        System.out.println("提取年龄最大的用户：");
        System.out.println(list.stream().collect(Collectors.maxBy(Comparator.comparingInt(User::getAge))));

        System.out.println("提取年龄最小的用户：");
        System.out.println(list.stream().collect(Collectors.minBy(Comparator.comparingInt(User::getAge))));

        System.out.println("获取关于年龄的相关汇总信息：");
        System.out.println(list.stream().collect(Collectors.summarizingDouble(User::getAge)));

        System.out.println("将所有用户名按照逗号连接起来:");
        System.out.println(list.stream().map(User::getName).collect(Collectors.joining(",")));

        System.out.println("最后的返回信息是一个不可变的list");
        list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        System.out.println("把所有用户按照年龄进行分区, 小于35的一个分区，大于等于35的一个分区，并且只提取name");
        System.out.println(list.stream().collect(Collectors.partitioningBy((u) -> u.getAge() >= 35,
                Collectors.mapping(User::getName, Collectors.toList()))));

    }

}
