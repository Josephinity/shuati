package Apple;

import java.util.BitSet;

/**
 *
 * 题目一开始比较简单：一串log 假设有个api可以把里面的ip地址 返回里面只出现过一次的地址
 Follow-up：如果读的是一个data stream 我们要做哪些修改
 Follow-up：假设我们的机器只有4GB内存 会出现什么问题？（我用了一个set去存看到过的地址 这里机器里可能存不下）
 问：你算算到底有没有情况可能存不下？答：256 ^ 4 ～= 4billion个可能ip 一个ipv4地址15characters -》如果存成string那要15B 4GB ／ 15B 肯定小于4billion. From 1point 3acres bbs
 问：那存不下咋办？答：不要存string存integer 8B
 问：那你再算算现在存的下了吗？答：好吧还是存不下
 问：那现在咋办？ 答：要不我们用bloom filter （这边追问bloom filter怎么实现的 可以从中删除数据吗 有什么劣势？（false positives））
 问：不 我要100%的正确率 不喜欢false positive 答：4GB 4billion组合 每个ip只能占1b 如果把它再压缩一下 或者做bitwise操作。。。？
 问：这样我觉得是不可能的 既然我们知道只有4billion ip 我们可以用bitmap 不用把ip存起来 只要知道它有没有出现过就可以了

 Apple phone interview

 Given an API to find all IPv4 addresses in a log file, find all IPs that occurred only once.

 Follow-up: What if the log comes from a data stream.

 Follow-up: If the machine has 4GB RAM, is there going to be a problem?
 (HashSet would not work then. IPv4 has 15 chars. There are 4billion IPs. 4GB is not enough to store all of them.)
4,000,000,000

2^32
64
 */


import java.util.List;
public class IPFilter {

    long[] map;          //mark all ip that showed up
    long[] repeatedIP;    //mark all ips that repeatedly showed up

    public IPFilter() {
        //there's 2^32 IP in total.
        // each long integer is identifies 64 IPs.
        // Need 2^32 / 2^6 long integers in the bit map
        int size = 1 << (32 - 6);
        map = new long[size];
        repeatedIP = new long[size];
    }

    public void addToMap(List<String> IPs) {
        for(String ip: IPs) {
            long decimal = IPToLong(ip);
            int idx = (int)(decimal / 64);
            int res = (int)(decimal % 64);

            if((map[idx] >> res) == 1) {    //repeated ip
                repeatedIP[idx] |= (1 << res);
            } else {                        //first occurred ip
                map[idx] |= (1 << res);
            }
        }
    }

    private long IPToLong(String ipAddress) { //convert ip (base 256) to decimal
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }
 }

