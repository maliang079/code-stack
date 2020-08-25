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
 *
 *
 */
public class MapReduce {
}
