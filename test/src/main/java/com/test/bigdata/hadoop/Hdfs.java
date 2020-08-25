package com.test.bigdata.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Hdfs {

    /**
     * Hadoop是什么？
     *   1. Hadoop是一个由apache基金会所开发的分布式系统基础架构
     *   2. 主要解决海量数据的存储，海量数据的分析计算
     *   3. 广义上来讲，hadoop通常是指一个更广泛的概念——hadoop生态圈
     *
     * Hadoop三大发行版本
     *
     *
     * Hadoop1.x和Hadoop2.x的区别
     *          1.x                              2.x
     *                                        Yarn（资源调度）
     *       MapReduce（计算+调度）            MapReduce（计算）
     *       HDFS（数据存储）                  HDFS（数据存储）
     *       Common（辅助工具）                Common（辅助工具）
     * 在Hadoop1.x时代Hadoop中的MapReduce同时处理业务逻辑运算和资源的调度，耦合性较大
     * 在Hadoop2.x时代，增加了Yarn只负责资源的调度，MapReduce只负责运算
     *
     * HDFS架构概念
     * NameNode（nn）：存储文件的元数据，如文件名，文件目录结构，文件属性（生成时间、副本数、文件权限），以及每个文件的块列表和块
     * 所在的DataNode等
     * DataNode（dn）：在本地文件系统存储数据，以及块数据的校验和
     * Secondary NameNode（2nn）：用来监控HDFS状态的辅助后台程序，每隔一段时间获取HDFS元数据的快照
     *
     * YARN架构
     *
     *                                                                 Node Manager（App Master）
     *
     * client                  Resource Manager                        Node Manager（App Master）
     *
     *                                                                 Node Manager（App Master）
     * ResourceManager（RM）主要作用
     *   处理客户端请求
     *   监控NodeManager
     *   启动和监控ApplicationMaster
     *   资源的分配和调度
     * NodeManager（NM）主要作用
     *   管理单个节点上的资源
     *   处理来自ResourceManager的命令
     *   处理来自ApplicationMaster的命令
     * ApplicationMaster（AM）主要作用
     *   负责数据切分
     *   为应用程序申请资源并分配给内部任务
     *   任务的监控和容错
     * Container
     *   Container是Yarn中的资源抽象，它封装了各个节点上的多为资源，如内存、cpu、磁盘、网络等
     *
     * MapReduce架构
     *   MapReduce将计算过程分为两个阶段：Map和Reduce：
     *     Map并行处理输入数据
     *     Reduce阶段对Map结果进行汇总
     *
     * 大数据技术生态体系
     *
     *
     *
     *
     *
     *
     *
     */


    /**
     *
     * hadoop hdfs shell 操作
     *
     * Usage: hadoop fs [generic options]
     *         [-appendToFile <localsrc> ... <dst>]
     *         [-cat [-ignoreCrc] <src> ...]
     *         [-checksum <src> ...]
     *         [-chgrp [-R] GROUP PATH...]
     *         [-chmod [-R] <MODE[,MODE]... | OCTALMODE> PATH...]
     *         [-chown [-R] [OWNER][:[GROUP]] PATH...]
     *         [-copyFromLocal [-f] [-p] [-l] [-d] <localsrc> ... <dst>]
     *         [-copyToLocal [-f] [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
     *         [-count [-q] [-h] [-v] [-t [<storage type>]] [-u] [-x] <path> ...]
     *         [-cp [-f] [-p | -p[topax]] [-d] <src> ... <dst>]
     *         [-createSnapshot <snapshotDir> [<snapshotName>]]
     *         [-deleteSnapshot <snapshotDir> <snapshotName>]
     *         [-df [-h] [<path> ...]]
     *         [-du [-s] [-h] [-x] <path> ...]
     *         [-expunge]
     *         [-find <path> ... <expression> ...]
     *         [-get [-f] [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
     *         [-getfacl [-R] <path>]
     *         [-getfattr [-R] {-n name | -d} [-e en] <path>]
     *         [-getmerge [-nl] [-skip-empty-file] <src> <localdst>]
     *         [-help [cmd ...]]
     *         [-ls [-C] [-d] [-h] [-q] [-R] [-t] [-S] [-r] [-u] [<path> ...]]
     *         [-mkdir [-p] <path> ...]
     *         [-moveFromLocal <localsrc> ... <dst>]
     *         [-moveToLocal <src> <localdst>]
     *         [-mv <src> ... <dst>]
     *         [-put [-f] [-p] [-l] [-d] <localsrc> ... <dst>]
     *         [-renameSnapshot <snapshotDir> <oldName> <newName>]
     *         [-rm [-f] [-r|-R] [-skipTrash] [-safely] <src> ...]
     *         [-rmdir [--ignore-fail-on-non-empty] <dir> ...]
     *         [-setfacl [-R] [{-b|-k} {-m|-x <acl_spec>} <path>]|[--set <acl_spec> <path>]]
     *         [-setfattr {-n name [-v value] | -x name} <path>]
     *         [-setrep [-R] [-w] <rep> <path> ...]
     *         [-stat [format] <path> ...]
     *         [-tail [-f] <file>]
     *         [-test -[defsz] <path>]
     *         [-text [-ignoreCrc] <src> ...]
     *         [-touchz <path> ...]
     *         [-truncate [-w] <length> <path> ...]
     *         [-usage [cmd ...]]
     *
     *
     * hdfs的写数据流程
     * namenode的工作原理（编辑日志、镜像文件）、如何通过命令oiv、oev查看编辑日志和镜像文件的数据
     * 最短路径和机架感知
     * namenode设置多目录存储（每个目录存储内容是一致的）
     * namenode的故障回复，一种方法是直接拷贝2nn的数据，第二种方法通过 hdfs namenode --importCheckpoint
     * hdfs安全模式 hdfs dfsadmin <enter | leave | get | wait>
     *
     * datanode工作原理
     * 数据完整性
     * 掉线时限参数设置
     * 服役新数据节点
     * 退役旧数据节点（添加白名单、黑名单退役）
     *  添加白名单在hdfs-site.xml中指定dfs.hosts的key，然后hdfs dfsadmin --refreshNodes
     *  黑名单退役在hdfs-site.xml中指定dfs.hosts.exclude的key，然后hdfs dfsadmin --refreshNodes、  yarn rmadmin --refreshNodes
     * datanode多目录设置（也可以配置成多个目录，每个目录存储的数据不一样）
     *
     *
     * hdfs2.x的新特性
     * 集群间数据拷贝（命令hadoo distcp hdfs://node-one:9000/test-shell/1.txt hdfs://node-two:9000/test-shell/1.txt）
     * 小文件存档 hdfs存档文件或者har文件  hadoop archive -archiveName NAME -p <parent path> <src>* <dest>
     * 回收站 （有两个配置参数：fs.trash.interval（代表删除文件的存活时间）、fs.trash.checkpoint.interval（检查删除文件有没有过期的时间间隔））
     * 快照管理
     *
     *
     *
     */

    public static void main(String[] args) throws URISyntaxException, IOException {

        try (FileSystem fs = FileSystem.get(new URI("hdfs://node-one:9000"), new Configuration())) {
            fs.copyToLocalFile(new Path("/test-shell/a.txt"), new Path("E:\\WorkSpace\\GitWorkSpace\\code-stack\\test\\test-shell"));
        } catch (URISyntaxException | IOException e) {
            throw e;
        }

    }

}
