package com.test.lambda;

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
 *
 *
 *
 *
 *
 *
 *
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

    public static void main(String[] args) {

        Stream.iterate(1, (x) -> x +1);

        Stream.generate(Math::random);

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

}
