package com.test.bigdata.hadoop;

/**
 * MapReduce是一个分布式运算程序编程框架，使用户开发“基于hadoop的数据分析应用”的核心框架
 *
 * 一个完整的MapReduce程序在分布式运行时有三类实例进程
 * MrAppMaster：负责整个程序的过程调度及状态协调
 * MapTask：负责map阶段的整个数据处理流程
 * ReduceTask：负责reduce阶段的整个数据处理流程
 *
 * MapReduce 序列化自定义
 * MapReduce切片和MapTask并行度决定机制
 * MapReduce Job提交流程
 * FileInputFormat 切片机制 （每一个文件都单独切片）
 * CombineTextInputFormat切片机制 用于小文件过多的场景，它可以将多个小文件从逻辑上规划到一个切片中，这样多个小文件就可以交给一个MapTask处理
 *
 * MapReduce InputFormat实现类关系
 *    InputFormat
 *        FileInputFormat
 *            TextInputFormat是默认的FileInputFormat实现类，按行读取每条记录。键是该行在整个文件中的起始字节偏移量LongWritable类型。值是这行的内容不包括任何行终止符（换行符和回车符）Text类型
 *            KeyValueTextInputFormat每一行均为一条记录，被分隔符分割为key和value可以通过在驱动类中设置conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, "\t");来设定分隔符。默认分隔符是tab(\t)
 *                                   eg.   line1 \t rich learning from     最后程序收到的数据  key:line1 value rich learning from
 *            NlineInputFormat 代表每个map进程处理的InputSplit不在按照block块儿去划分，而是按照NlineInputFormat指定的行数来划分。即输入文件的总行数/n=切片数，如果不能整除，切片数=商+1
 *                             key和value与TextInputFormat相同
 *
 * shuffle机制
 * map方法之后，reduce方法之前的数据处理过程称为shuffle（主要涉及到：分区、排序、combiner、归并排序、压缩）
 *
 * partition分区总结：
 * 如果reduce task的数量大于partition的数量，则会多产生几个空的输出文件
 * 如果1< reduce task < partition数量，则有一部分分区的数据无处安放，抛出Exception
 * 如果reduce task = 1，则不管多个个partition，最终只会交给一个reduce task，最终只会产生一个结果文件
 * 分区号必须从零开始逐一累加。
 *
 * 排序
 * 排序是map reduce框架中最重要的操作之一
 * MapTask和ReduceTask均会对数据按照key进行排序。该操作属于hadoop的默认行为。任何应用程序中的数据均会被排序，而不管逻辑上是否需要
 * 默认排序是按照字典顺序排序，且实现该排序的方法是快速排序
 *
 * 排序分类
 * 部分排序
 *    MapReduce根据输入记录的键对数据集排序，保证输出的每个文件内部有序
 * 全排序
 *   最终输出结果只有一个文件，且文件内部有序。实现方式是只设置一个reduce task。但该方法在处理大型文件时效率极低，因为一台机器处理所有文件
 *   ，完全丧失了MapReduce所提供的并行框架
 * 辅助排序
 *   在Reduce端对key进行分组。
 * 二次排序
 *  在自定义排序过程中，如果compareTo中的判断条件为两个即为二次排序
 *
 */
public class MapReduce {
}
