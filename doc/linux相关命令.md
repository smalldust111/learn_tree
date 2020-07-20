# linux相关命令

## 1 weblogic相关命令
```
linux
1.    执行 ：   ps  -ef|grep  weblogic                   //查看weblogic的进程信息
 ps  -ef|grep  java 查看 java信息 
 
通过“kill -3 pid”主动产生javacore和heapdump转储文件，
其中pid通过 命令：ps  -ef|grep  java 获取

2.    执行 ：    kill  -9    pid号                               //杀掉weblogic的进程
cd /weblogic/user_projects/domains/zjgits_domain/bin


cd /weblogic/bea/user_projects/domains/stits_domain/bin
cd /weblogic/bea/user_projects/domains/stitsForTest_domain/bin
3     执行：      ./startWebLogic.sh  &                  //启动weblogic
4 ./stopWebLogic.sh  &  //停止weblogic

cd /weblogic/user_projects/domains/
删除domain  rm -rf test_domain

创建Weblgoic 应用用户：
groupadd weblogic (创建用户组)
useradd -d /usr/WebLogic -g weblogic  wasadm  (创建wasadm用户)
passwd wasadm（修改wasadm密码）

#增加用户访问权限 可以使用该用户权限
[root@stits ~]# chown -R weblogic:weblogic /weblogic

chown -R wasadm:weblogic /usr/WebLogic


新建用户到weblogic组下
useradd -d /home/weblogic -g weblogic  dkysp
增加访问权限
chown -R dkysp:weblogic /home/weblogic

删除用户
userdel -r dkysp
可以在/bea/user_projects/domains/{domain_name}/bin/setDomainEnv.sh中设置GC日志的打印参数分析：

MEM_ARGS=”-Xms2048m –Xmx2048m –verbosegclog:gc.log”
-verbosegclog:gc.log
Export MEM_ARGS
该参数会在/bea/user_projects/domains/{domain_name}/下输出gc.log
weblogic新建domain:
	cd /weblogic/wlserver_12.1/common/bin/
	./config.sh
	
解决weblogic创建域慢和启动慢问题

/usr/local/jdk1.6.0_45/jre/lib/security

将 
securerandom.source=file:/dev/urandom
改为
securerandom.source=file:/dev/./urandom
```




## 2 查找端口占用进程
```
netstat -anp|grep 8999
kill占用进程
kill -s 9 19269
```
## 3 防火墙
```
 service iptables status
 chkconfig iptables off
 service iptables stop
 chkconfig iptables on
```

## 4 查看系统支持的字符集
```
locale -a  查看本地字符集
locale -m 查看所有支持的字符集
```

## 5 oracle相关
```
oracle启动实例:
1.使用oracle用户登录  户名：oracle 密码：oracle

	export ORACLE_SID=zjgitsdb
	export ORACLE_SID=stitsdb
	
	lsnrctl start 启动oracle监听
	lsnrctl status 查看监听状态
	
	sqlplus /nolog

	connect /as sysdba
	
	shutdown immediate
	
	startup
	startup mount
	
	--startup nomount
	SELECT USERENV('language') FROM dual;--查看数据库字符集
	
oracle 11g空表不会分配空间，因此导不出
	select 'alter table ' || table_name || ' allocate extent; ' from user_tables  where num_rows = '0';

--oracle建表空间
	create temporary tablespace ZJGITSDB_TEMP tempfile '\oracle\product\11.2.0\dbhome_1\ordata\zjgitsdb\ZJGITSDB_TEMP.DBF'
	size 100 m autoextend on next 32 m maxsize 1024 m extent management local;

--创建表空间
	CREATE TABLESPACE ZJGITSDB DATAFILE '\oracle\product\11.2.0\dbhome_1\ordata\zjgitsdb\ZJGITSDB.DBF'
	SIZE 512M AUTOEXTEND ON NEXT 256M
	EXTENT MANAGEMENT LOCAL AUTOALLOCATE
	SEGMENT SPACE MANAGEMENT AUTO;

--创建移植表空间
	CREATE TABLESPACE MOVE_DATA DATAFILE  '\oracle\product\11.2.0\dbhome_1\ordata\zjgitsdb\rmove_datalv.DBF'
	SIZE 512M AUTOEXTEND ON NEXT 256M
	EXTENT MANAGEMENT LOCAL AUTOALLOCATE
	SEGMENT SPACE MANAGEMENT AUTO;


	create user zjgits identified by zjgits default tablespace ZJGITSDB temporary tablespace ZJGITSDB_TEMP;

	grant dba to zjgits;
	grant connect to zjgits;
	grant resource to zjgits;

导入数据库:
	imp zjgits/zjgits@zjgitsdb fromuser=zjgits touser=zjgits  ignore=y file=$HOME/xl/its_db_run_20130803.dmp log=$HOME/xl/20130803.log

如果dmp与数据库字符集不一致
	cat its_db_run_20130424.dmp |od -x|head -1|awk '{print$2$3}'|cut -c 3-6         查看dmp文件字符集
	0345 = ZHS16GBK
```
## 6 编辑命令
```
vi 命令
vi 文件名
a 或 i 更改
修改好后 esc 输入 ：wq! 保存
:w 保存修改
:wq 保存修改并退出
:wq! 强制保存修改并退出
:q 推出 有提示
:q! 放弃修改
```
##  7 设置权限
```
log写权限问题  chmod 777 filename

修改文件夹权限
chmod -R 777 /weblogic 
```
## 8 压缩和解压缩
```
tar -zcvf DKYS_20170523.tar.gz ./

tar -xvf DKYS_20170523.tar.gz 
```

## 9 日期等等。。
```
查看linux 系统日期 
#date
修改日期：20140101
# date -s 20140101
修改时间 
# date -s 22:59:00

查看系统最新重启时间
#last reboot  方法一
#who -b        方法二
#uptime  系统最新时间

查看系统重启信息
#dmesg

查看系统重启的log日志
#cat /var/log/messages | grep 'reboot'
```

## 10 Linux系统信息查看命令大全(查看内存使用情况)
```
系统
# uname -a # 查看内核/操作系统/CPU信息
# head -n 1 /etc/issue # 查看操作系统版本
# cat /proc/cpuinfo # 查看CPU信息
# hostname # 查看计算机名
# lspci -tv # 列出所有PCI设备
# lsusb -tv # 列出所有USB设备
# lsmod # 列出加载的内核模块
# env # 查看环境变量

# df -lh  查看系统磁盘使用情况
资源
# free -m # 查看内存使用量和交换区使用量
# df -h # 查看各分区使用情况
# du -sh <目录名> # 查看指定目录的大小
# grep MemTotal /proc/meminfo # 查看内存总量
# grep MemFree /proc/meminfo # 查看空闲内存量
# uptime # 查看系统运行时间、用户数、负载
# cat /proc/loadavg # 查看系统负载
磁盘和分区
# mount | column -t # 查看挂接的分区状态
# fdisk -l # 查看所有分区
# swapon -s # 查看所有交换分区
# hdparm -i /dev/hda # 查看磁盘参数(仅适用于IDE设备)
# dmesg | grep IDE # 查看启动时IDE设备检测状况
网络
# ifconfig # 查看所有网络接口的属性
# iptables -L # 查看防火墙设置
# route -n # 查看路由表
# netstat -lntp # 查看所有监听端口
# netstat -antp # 查看所有已经建立的连接
# netstat -s # 查看网络统计信息
进程
# ps -ef # 查看所有进程
# top # 实时显示进程状态
用户
# w # 查看活动用户
# id <用户名> # 查看指定用户信息
# last # 查看用户登录日志
# cut -d: -f1 /etc/passwd # 查看系统所有用户
# cut -d: -f1 /etc/group # 查看系统所有组
# crontab -l # 查看当前用户的计划任务
服务
# chkconfig –list # 列出所有系统服务
# chkconfig –list | grep on # 列出所有启动的系统服务
程序
# rpm -qa # 查看所有安装的软件包
 
在Linux下查看内存我们一般用free命令：
total used free shared buffers cached
Mem: 386024 377116 8908 0 21280 155468
-/+ buffers/cache: 200368 185656
Swap: 393552 0 393552

jvm 生成javacore和heapdump文件 
（1）如果使用Oracle JVM也就是标准的SUN JVM（SUN已被oracle收购）
当内存溢出时生成heapdump文件配置如下
-Xloggc:${目录}/temp_gc.log           （GC日志文件）
-XX:+HeapDumpOnOutOfMemoryError       （内存溢出时生成heapdump文件）
-XX:HeapDumpPath=${目录}              （heapdump文件存放位置）

如果要即时动态生成heapdump文件可以使用jmap命令，jdk6.0已取消了-XX:+HeapDumpOnCtrlBreak配置参数通过ctrl+break的方式。
jmap -dump:format=b,file=temp_heapdump.hprof <pid>

（2）HP JVM
-Xverbosegc:file=${目录}/temp_gc.log  （GC日志文件）
-XX:+HeapDumpOnOutOfMemoryError       （内存溢出时生成heapdump文件）
-XX:+HeapDumpOnCtrlBreak              （可以通过ctrl+break组合键动态生成heapdump文件）
-XX:HeapDumpPath=${目录}              （heapdump文件存放位置）

（3）IBM JVM
非windows操作系统环境中
-XverboseGClog: ${目录}/temp_gc.log   （GC日志文件）
-Xdump:heap:events=user,file=${目录}/pid%uid%pid.phd
表示可以根据需要通过kill -3 <pid>产生DUMP文件，%uid和%pid为变量

windows操作系统环境中
启动wsadmin,进入wsadmin环境
wsadmin> set jvm [$AdminControl completeObjectName type=JVM,process=server1,*]
wsadmin> $AdminControl invoke $jvm generateHeapDump
wsadmin> $AdminControl invoke $jvm dumpThreads
```
## 11 常用命令
```
一、&amp;
加在一个命令的最后，可以把这个命令放到后台执行，如
watch-n 10shtest.sh&amp;#每10s在后台执行一次test.sh脚本
二、ctrl + z
可以将一个正在前台执行的命令放到后台，并且处于暂停状态。
三、jobs
查看当前有多少在后台运行的命令
jobs -l选项可显示所有任务的PID，jobs的状态可以是running,
stopped, Terminated。但是如果任务被终止了（kill），shell 从当前的shell环境已知的列表中删除任务的进程标识。
四、fg
将后台中的命令调至前台继续运行。如果后台中有多个命令，可以用fg %jobnumber（是命令编号，不是进程号）将选中的命令调出。
<IMG src="file:///C:\Users\Thinkpad\AppData\Roaming\feiq\RichOle\2744422577.bmp">
五、bg
将一个在后台暂停的命令，变成在后台继续执行。如果后台中有多个命令，可以用bg %jobnumber将选中的命令调出。
六、kill
·        
法子1：通过jobs命令查看job号（假设为num），然后执行kill %num
·        
法子2：通过ps命令查看job的进程号（PID，假设为pid），然后执行kill pid
前台进程的终止：Ctrl+c
七、nohup
如果让程序始终在后台执行，即使关闭当前的终端也执行（之前的&amp;做不到），这时候需要nohup。该命令可以在你退出帐户/关闭终端之后继续运行相应的进程。关闭中断后，在另一个终端jobs已经无法看到后台跑得程序了，此时利用ps（进程查看命令）
ps-aux | grep'test.sh'#a:显示所有程序u:以用户为主的格式来显示x:显示所有程序，不以终端机来区分


如果要临时禁止SELinux
# setenforce 0

HA切换：
clusvcadm -r oracledb -m HDSR-GJ-01


#应用目录
APP_HOME=/app/sunfcbp/sunflow
echo "应用目录:"$APP_HOME

#日志目录
LOG_DIR=$APP_HOME/log/subsys
echo "日志目录:"$LOG_DIR

#备份目录
BK_DIR=/backup/log
echo "备份目录:"$BK_DIR


#删除历史备份（保留60天）
find $BK_DIR -mtime +60 -name "sunfcbp_sun02_log*.tar*" -exec rm -f {} \;
echo "删除sun02历史备份:find "$BK_DIR' -mtime +60 -name "sunfcbp_sun02_log*.tar*" -exec rm -f {} \;'


#删除历史日志（保留3天）
echo "删除历史日志:find "$LOG_DIR' -mtime +1 -name "*" -exec rm -rf {} \;'
find $LOG_DIR/ -mtime +1 -name "*" -exec rm -rf {} \;


pwd 查找当前所在路径

ALT+enter全屏
lsof -i:7001查看7001端口的占用情况
kill -9 pid 杀死pid处进程占用的端口

netstat -anp|grep 7001
```
## 12 windows命令
```
tasklist|findstr 进程号 显示进程占用情况
taskkill杀掉进程或pid占用
netstat -ano|findstr 端口号
```

