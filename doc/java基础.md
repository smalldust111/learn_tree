

# 					一、java基础

## 1.1 数据类型和基本概念

### 1.1.1 基本类型

| 基本类型 | 封装对象 | 字节长度 | 实现常量池技术 |
| -------- | -------- | -------- | -------------- |
| byte     | Byte     | 1        | -128~127       |
| short    | Short    | 2        | -128~127       |
| int    |  Integer     |   4   | -128~127         |
|long|Long|8|-128~127|
|float|Float|4|false|
|double|Double|8|false|
|char|Character|依编码变化||
|boolean|Boolean|2||

### 1.1.2 java基础知识（变量和常量等）

```
标识符：void是关键字，而Void不是关键字。标识符不能以数字开头。可以以字母、数字、下划线、美元符组成。
java数据类型：基本数据类型：（数值型（整数类型）byte、short、int、long（浮点型）float、double）（字符型char）（布尔型boolean）
			  引用数据类型：（类class）（接口interface）（数组）（String）
			  java中，基本数据类型变量存的是数据本身，而引用类型变量存的是保存数据的空间地址。
变量的使用规则：1.java中的变量需要先声明在使用。
				2.变量使用时，可以声明变量的同时进行初始化，也可以先声明在赋值。
				3.变量中每次只能赋一个值，但可以修改多次。
				4.main方法中定义的变量必须先赋值，然后才能输出。
				5.变量名不建议使用中文。
java的自动类型转换：1.目标类型能与源类型兼容，如double型兼容int型，但char型不能兼容int型。
					2.目标类型大于源类型，如double类型字节长度为8，int为4字节，故double类型变量中可以直接存放int类型的数据，但反过来就不行了。
java中的强制类型转换：可能会造成数据的丢失。
java中的常量：final 常量名=值;
			  常量名一般使用大写字符。
java中的注释：单行注释//...
			  多行注释/* ...*/
			  文档注释/**  javadoc -d doc Demo03.java
			           */
运算符：1.算术运算符2.赋值运算符3.比较运算符4.逻辑运算符5.条件运算符	
        算术运算符++：出现左边++a，int a=5;int b=++a;	//让a先执行自增，然后将值赋给变量b	结果为a:6，b:6
                      出现在右边a++，int a=5;int b=a++;  //让a的值先赋给变量b，然后在执行自增。 结果为a:6,b:5
        运算符优先级：（）最高，=最低，^的优先级大于&&的优先级大于||的优先级。	
switch语句：1.switch后面小括号中表达式的值必须是整型 或者字符型
			2.case后面的值可以是常量数值，如1、2；也可以是一个常量表达式，如2+2；但不能是变量或带有变量的表达式，如a*2
			3.case匹配后，执行匹配快中的程序代码，如果没有遇到break会继续执行下一个的case块的内容，知道遇到break语句或者switch语句块结束。
			4.可以把功能相同的case语句合并起来
			5.default块可以出现在任意位置，也可以省略
循环语句：while(){}  do{}while();for(){}
break和continue:break直接结束当前循环
				continue结束当前循环的某一次，进行当前循环的下一次。
成员变量和局部变量：
				成员变量：在类中定义，用来描述对象将要有什么。
				局部变量：在类的方法中定义，在方法中临时保存数据。
				区别：1.作用域不同：
							局部变量的作用域仅限于定义它的方法，即方法的开始到方法的结束。
							成员变量的作用域在整个类内部都是可见的。
					  2.初始值不同：
							java会给成员变量一个初始值；
							java不会给局部变量赋予初始值。
					  3.在同一个方法中，不允许有同名局部变量；在不同的方法中，可以有同名的局部变量。
					  4.两类变量同名时，局部变量具有更高的优先级。
静态变量static：1.java中被static修饰的成员称为静态成员或类成员。
				2.它属于整个类所有，而不是某个对象所有，即被类的所有对象所共享。
				3.静态成员可以使用类名直接访问，也可以使用对象名进行访问，推荐用类名访问。
				4.静态成员属于整个类，当系统第一次使用该类时，就会为其分配内存空间直到该类被卸载才会进行资源回收。
				5.静态变量不能使用private修饰。

```



### 1.1.3 String

```
整型和字符串类型有常量池技术：
	String s1 = "Hello";
	String s2 = "Hello";
	String s3 = "Hel" + "lo";
	String s4 = "Hel" + new String("lo");
	String s5 = new String("Hello");
	String s6 = s5.intern();
	String s7 = "H";
	String s8 = "ello";
	String s9 = s7 + s8;

	System.out.println(s1 == s2); // true
	System.out.println(s1 == s3); // true
	System.out.println(s1 == s4); // false
	System.out.println(s1 == s9); // false
	System.out.println(s4 == s5); // false
	System.out.println(s1 == s6); // true
```



### 1.1.4 值传递

D:\learn_tree\workspace\practice\src\main\java\com\me\practice\t0009_javabase\值引用.java

```\
package com.me.practice.t0009_javabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 原始值：a=1, b=b, c=值引用.Vo(c=c)
 * 方法内：a=2, b=update, c=值引用.Vo(c=update)
 * 修改结果：a=1, b=b, c=值引用.Vo(c=update)
 *
 * @author jia
 * @since 2020/7/2 23:50
 */
@Slf4j
public class 值引用 {
    public static void main(String[] args) {
        int a = 1;
        String b = "b";
        Vo c = new Vo("c");
        System.out.println("原始值：a=" + a + ", b=" + b + ", c=" + c);
        update(1, "1", c);
        System.out.println("修改结果：a=" + a + ", b=" + b + ", c=" + c);
    }

    public static void update(int a, String b, Vo c) {
        a++;
        b = "update";
        c.setC("update");
        System.out.println("方法内：a=" + a + ", b=" + b + ", c=" + c);
    }

    @Data
    @AllArgsConstructor
    public static class Vo {
        String c;
    }
}

```

### 1.1.5 数组

```
数组的创建方式：1.int [] arr=new int[5];
 		2.int [] arr={1,2,3};
 		不能是int [] arr=new int[3]{1,2,3};
数组越界的异常类：ArrayIndexOutOfBoundsException
数组工具类Arrays：1.排序 Arrays.sort(数组名）;自动按升序排列
				  2.将数组转换为字符串Arrays.toString(数组名);输出结果为[1,2,3,4]
二维数组定义时必须指定行索引，访问时必须指定列索引和列索引。
一维数组
		1. 数组可以看成是多个相同类型数据组合，对这些数据的统一管理
		2. 数组变量属于引用类型，数组可以看成是对象，数组中的每个元素相当于该对象的成员变量
		3. 数组中的元素可以是任何数据类型，包括基本型和引用类型
二维数组
		定义:二维数组相当于数组元素为元素的数组,例如   Int a[ ] [ ] = {{1,2},{3,4,5,6},{7,8,9} };
		Java中多维数组的声明和初始化应按从高维到低维的顺序进行，即从左到右.
		int a[ ][ ] = new int[ 3 ][ ];
数组拷贝
		使用java.lang.System类的静态方法:
		Public static void arraycopy( Object src,int srcPos,Object dest, int destPos,int length)
		可以用于数组src从第srcPos项元素开始的length个元素拷贝到目标数组从destPos项开始的length个位置。
		如果源数据数目超过目标数组边界会抛出IndexOutOfBoundsException异常。				 

```

### 1.1.6  Java中的方法（方法修饰符与方法的重写和重载、构造方法）

```
方法修饰符：private default protected public 同类 同包 有继承关系 全部
			private 私有类 子类拥有私有变量的所有权，无使用权(只能看 不能动)
方法的重载OverLoad：1.必须是在同一个类中。
			2.方法名相同。
			3.方法参数的个数、顺序或类型不同。
			4.与方法的修饰符和返回值没有关系。
方法的重写：方法覆盖Override(方法重写OverWrite): 在子类中根据需要对基类中继承来的方法进行重写.
			注意:1. 重写方法必须和被重写方法具有相同方法名称、参数列表和返回值.
				 2. 重写方法不能使用比被重写方法更严格的访问权限.
				 3. 子类覆盖父类的方法时，只能比父类抛出更少的异常，或者是抛出父类抛出的异常的子异常，因为子类可以解决父类的一些问题，不能比父类有更多的问题.
				 4. 如果父类的方法是private类型，那么，子类则不存在覆盖的限制，相当于子类中增加了一个全新的方法.			
构造方法：1.使用new+构造方法，创建一个新的对象。
		  2.构造方法是定义在java类中的一个用来初始化对象的方法。
		  3.构造方法与类同名且没有返回值。
		  4.构造方法可以带参数。
		  5.当没有指定构造方法时，系统会自动添加无参的构造方法。
		  6.当有指定构造方法，无论是有参、无参的构造方法，都不会自动添加无参的构造方法。
		  7.构造方法的重载：方法名相同，但是参数不同的多个方法，调用时会自动根据不同的参数选择相应的方法。
		  8.构造方法不但可以给对象的属性赋值，还可以保证给对象的属性赋一个合理的值（在赋值时加一个判断）。
继承中的构造方法：1. 子类的构造过程中必须调用基类的构造方法。
				  2. 子类可在自己的构造方法中使用super(argument_list参数列表)调用基类的构造方法。
				  ------如果调用super.必须写在子类构造方法第一行
				  ------使用this(argument_list)调用本类另外的构造方法
				  补充：可以先用super()调基类，再用this()调子类,super()必须写在子类的构造过程前面！super()和this()不能同时出现在同一构造函数中。
				  3. 如果子类构造方法中没有显式的调用基类构造方法则系统默认调用基类无参数的构造方法。
				  4. 如果子类构造方法既没有显式地调用基类构造方法，而基类中有没有无参数的构造方法,则编译出错。
				  5. 文件编译的时候，子类的所有方法都要执行一遍，即有参SubClass(int n){ }和无参的SubClass( ){}都要执行。		 
静态方法：1.静态方法中可以直接调用同类中的静态成员，但不能直接调用非静态成员。
			如果希望在静态方法中调用非静态变量，可以通过创建类的对象，然后通过对象来访问非静态变量。
		  2.在普通成员方法中，则可以直接访问同类的非静态变量和静态变量。
		  3.静态方法中不能直接调用非静态方法，需要通过对象来访问非静态方法。
静态初始化块：java可以通过初始化块进行赋值。
			  1.在类的声明中，可以包含多个初始化块，当创建类的实例时，就会依次执行这些代码块。
			  2.使用static修饰初始化块就称为静态初始化块。
			  3.静态初始化块只在类加载时执行，且只会执行一次，同时初始化块只能给静态变量赋值，不能初始化普通的成员变量。
			  先执行静态初始化块，再执行普通初始化块，最后才执行构造方法。
Object类之toString方法：
		1. Object类中定义有public String toString( )方法，起返回值是String类，描述当前对象的有关信息
		2. 在进行String与其他类型数据的连接操作时(如：System.out.println(“info”+person)),将自动调用该对象类的toString()方法
		3. 可以根据需要在用户自定义类型中重写toString()方法
Object类之equals方法： 
		1. Object的equals方法：比较两个引用指向的对象是否相等
		2. String重写了equals方法：比较的是字符串的内容是否相等。		
		
```

### 1.1.7 内部类

```
内部类的主要作用如下：
	1.内部类提供了更好的封装，可以把内部类隐藏在外部类之内，不允许同一个包中的其他类访问该类
	2.内部类的方法可以直接访问外部类的所有数据，包括私有的数据
	3.内部类所实现的功能使用外部类同样可以实现，只是有时使用内部类更方便
内部类可分为以下几种：	实例化方式：先实例化外部类，在实例化内部类
	成员内部类(最常用的，也叫普通内部类) 实例化内部类：Inner i=hello.new Inner();
			   成员内部类的使用方法：
					1、Inner 类定义在 Outer 类的内部，相当于 Outer 类的一个成员变量的位置，Inner 类可以使用任意访问控制符，如 public 、 protected 、 private 等
					2、Inner 类中定义的 test() 方法可以直接访问 Outer 类中的数据，而不受访问控制符的影响，如直接访问 Outer 类中的私有属性a
					3、定义了成员内部类后，必须使用外部类对象来创建内部类对象，而不能直接去 new 一个内部类对象，即：内部类 对象名 = 外部类对象.new 内部类( );
					4、编译上面的程序后，会发现产生了两个 .class 文件
				1、外部类是不能直接使用内部类的成员和方法,可先创建内部类的对象，然后通过内部类的对象来访问其成员变量和方法。
				2、如果外部类和内部类具有相同的成员变量或方法，内部类默认访问自己的成员变量或方法，如果要访问外部类的成员变量，可以使用 this 关键字。
	静态内部类	静态内部类是 static 修饰的内部类，这种内部类的特点是：
					1、静态内部类不能直接访问外部类的非静态成员，但可以通过 new 外部类().成员 的方式访问 
					2、如果外部类的静态成员与内部类的成员名称相同，可通过“类名.静态成员”访问外部类的静态成员；
						如果外部类的静态成员与内部类的成员名称不相同，则可通过“成员名”直接调用外部类的静态成员
					3、创建静态内部类的对象时，不需要外部类的对象，可以直接创建 内部类 对象名= new 内部类();
	方法内部类	方法内部类就是内部类定义在外部类的方法中，方法内部类只在该方法的内部可见，即只在该方法内可以使用。
				注意：由于方法内部类不能在外部类的方法以外的地方使用，因此方法内部类不能使用访问控制符和 static 修饰符。
	匿名内部类
```



## 1.2 文件系统

### 1.2.3 文件操作常用Api

D:\learn_tree\workspace\practice\src\main\java\com\me\practice\t0009_javabase\文件操作Api.java

```
package com.me.practice.t0009_javabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author jia
 * @since 2020/7/5 17:35
 */
public class 文件操作Api {
    public static void main(String[] args) {
        // 1.创建文件夹
        createDir();
        // 2.创建文件
        createFile();
        // 3.删除文件夹
        deleteDir();
        // 4.删除文件
        deleteFile();
        // 5.删除一个文件夹下所有的文件夹
        deleteUnderDir();
        // 6.清空文件夹
        clearDir();
        // 7.读取文件
        readFile();
        // 8.写入文件
        writeFile();
        // 9.写入随机文件
        writeRandomFile();
        // 10.读取文件属性
        getFileInfo();
        // 11.写入属性
        setReadOnly();
        // 12.枚举一个文件夹中的所有文件
        String filename = getDirAllFile();
        // 13.提取扩展名
        String str20 = filename.substring(filename.lastIndexOf(".") + 1);
    }

    private static String getDirAllFile() {
        String filename = "test.txt";
        LinkedList<String> folderList = new LinkedList<>();
        while (folderList.size() > 0) {
            File file = new File(folderList.peek());
            folderList.removeLast();
            File[] files = file.listFiles();
            ArrayList<File> fileList = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    folderList.add(files[i].getPath());
                } else {
                    fileList.add(files[i]);
                }
            }
            for (File f : fileList) {
                folderList.add(f.getAbsolutePath());
            }
        }
        return filename;
    }

    private static void setReadOnly() {
        String filename = "test.txt";
        File filereadonly = new File(filename);
        try {
            boolean b = filereadonly.setReadOnly();
        } catch (Exception e) {
            System.out.println("拒绝写访问：" + e.getMessage());
        }
    }

    private static void getFileInfo() {
        // 文件属性的取得
        String filename = "test.txt";
        File f = new File(filename);
        if (f.exists()) {
            System.out.println(f.getName() + "的属性如下： 文件长度为：" + f.length());
            System.out.println(f.isFile() ? "是文件" : "不是文件");
            System.out.println(f.isDirectory() ? "是目录" : "不是目录");
            System.out.println(f.canRead() ? "可读取" : "不");
            System.out.println(f.canWrite() ? "是隐藏文件" : "");
            System.out.println("文件夹的最后修改日期为：" + new Date(f.lastModified()));
        }
    }

    private static void writeRandomFile() {
        String filename = "test.txt";
        long pos = 0;
        int size = 2048;
        try {
            RandomAccessFile logFile = new RandomAccessFile(filename, "rw");
            long lg = logFile.length();
            logFile.seek(pos);
            logFile.writeByte(size);
        } catch (IOException ioe) {
            System.out.println("无法写入文件：" + ioe.getMessage());
        }
    }

    private static void writeFile() {
        // 将数据写入文件
        String filenameOrigin = "test2";
        String filename = "test";
        try {
            FileWriter fw = new FileWriter(filenameOrigin);
            fw.write(filename);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearDir() {
        String dirname = "test";
        File delfilefolder = new File(dirname);
        try {
            if (delfilefolder.exists()) {
                delfilefolder.delete();
            }
            delfilefolder.mkdir();
        } catch (Exception e) {
            System.out.println("清空目录操作出错");
            e.printStackTrace();
        }
    }

    private static void deleteUnderDir() {
        String dirname = "test";
        File delfile = new File(dirname);
        File[] files = delfile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                files[i].delete();
            }
        }
    }

    private static void deleteFile() {
        String filename = "test.txt";
        File myDelFile = new File(filename);
        try {
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }

    private static void deleteDir() {
        String dirname = "test";
        File delFolderPath = new File(dirname);
        try {
            delFolderPath.delete(); //删除空文件夹
        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();
        }
    }

    private static void createFile() {
        String filename = "test.txt";
        String str2_1 = "";
        File myFilePath = new File(filename);
        try {
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(str2_1);
            resultFile.close();
        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
    }

    private static void createDir() {
        String dirname = "test";
        File myFolderPath = new File(dirname);
        try {
            if (!myFolderPath.exists()) {
                myFolderPath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    private static void readFile() {
        // 逐行读取数据
        String filename = "test.txt";
        FileReader fr = null;
        BufferedReader br = null;
        String str2 = null;
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            str2 = br.readLine();
            while (str2 != null) {
                str2 = br.readLine();
                System.out.println(str2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

```

## 1.3 面向对象

```
对象：万物皆对象，客观存在的事物皆为对象
面向对象：以对生活中的物体处理方式来处理程序问题。
类：类是模子，确定对象将会拥有的特性（属性）和行为（方法）
    特点：1.类是对象的属性
	      2.具有相同属性和方法的一组对象的集合。
对象的属性：对象具有的各种特征，每个对象的每个属性都拥有特定值。
对象的方法：对象执行的操作，对象能干什么。
类与对象的关系或区别：类是抽象的概念，仅仅是模板，是看不见的，现实中不存在的；而对象是现实中存在的，可以看得见、摸得着的具体实体。

封装：将类的某些信息隐藏在类内部，不允许外部程序直接访问，而是通过该类提供的方法来实现对隐藏信息的操作和访问。
      好处：1.只能通过规定的方法访问数据
	        2.隐藏类的实例细节，方便修改和实现。
继承：
this关键字：在类的方法定义中使用this关键字代表使用该方法的对象引用
			使用 this (argument_list) 调用本类的另外的构造方法
			使用this.xxx  = xxx     获取本类另外一个构造方法传来的值
			this可以看做一变量，它的值是当前对象引用
动态绑定和多态：
		1. 在执行期间(非编译期)判断所引用对象实际类型，根据其实的类型调用其相应的方法
		2. 多态存在的条件:有继承，有重写，父类引用指向子类对象。一旦满足，当父类里被重写的方法时，实际new的哪个子类对象，就调用子类对象方法。
			总结：多态＝继承＋重写＋父类引用指向子类的对象.
		3. 父类引用指向子类对象
		要理解多态性，首先要知道什么是“向上转型”。
			我定义了一个子类Cat，它继承了Animal类，那么后者就是前者是父类。我可以通过 Cat c = new Cat(); 实例化一个Cat的对象，这个不难理解。
			但当我这样定义时： Animal a = new Cat(); 这代表什么意思呢？      
			很简单，它表示我定义了一个Animal类型的引用，指向新建的Cat类型的对象。由于Cat是继承自它的父类Animal，所以Animal类型的引用是可以指向Cat类型的对象的。
			那么这样做有什么意义呢？因为子类是对父类的一个改进和扩充，所以一般子类在功能上较父类更强大，属性较父类更独特，
			定义一个父类类型的引用指向一个子类的对象既可以使用子类强大的功能，又可以抽取父类的共性。  
			所以，父类类型的引用可以调用父类中定义的所有属性和方法，而对于子类中定义而父类中没有的方法，它是无可奈何的；
			同时，父类中的一个方法只有在在父类中定义而在子类中没有重写的情况下，才可以被父类类型的引用调用；
			对于父类中定义的方法，如果子类中重写了该方法，那么父类类型的引用将会调用子类中的这个方法，这就是动态连接。
抽象类、抽象方法：
	1. 用abstract来修饰的类/方法
	2. 规则:
		1) 含有抽象方法的类必须被声明为抽象类，抽象类必须被继承，抽象方法必须重写
		2) 抽象类不能被实例化
		3) 抽象方法只需声明而不需实现
	抽象类声明的语法形式为abstract class Number { . . .}
	抽象方法声明的语法形式为public abstract <returnType> <methodName>(...); 
Final关键字：
	1. final 的变量的值不能够被改变
		a) final 的成员变量
		b) final 的局部变量（形参）
	2. final 的方法不能够被重写
	3. final 的类不能够被继承
		如：public final class String     类
		public final void m( )       方法
		final int I = 9             成员/局部变量	
接口/interface：
	1. 接口是抽象方法和常量值的定义的集合
	2. 从本质上讲，接口是一种特殊的抽象类，这种抽象类中只包含常量和方法的定义，而没有变量和方法的实现。
	3. 接口定义举例：
		public interface Runner {
			(public static final)  int   id = 1;
			public (abstract)  void start( );//抽象类，不需要实现
			public (abstract)  void run( );
			public (abstract)  void stop( );
		}
	4. 接口特征
		1) 接口可以多重实现;
		2) 接口中声明的属性必须为public static final的，可以不写;
		3) 接口中只能定义抽象方法,而且这些方法必须为public abstract的;
		4) 接口可以继承其他的接口，并添加新的属性和抽象方法;
		5) 与继承关系类似，接口与实现类之间存在多态性*接口之间可以相互继承，类之间也可以相互继承而类只能实现接口对象实现接口的时候一定要把接口中的方法定义
		接口实现:就是实现接口，实现接口里面的所定义的抽象方法
	5. 接口规则
		1) 多个无关类可以实现同一接口
		2) 一个类可以实现多个无关接口
		3) 与继承关系类似接口与实现类之间存在多态性	
		4）接口可以多继承
		
```

## 1.4 集合

```
Collection接口: (一个一个的装)：定义了存取一组对象的方法，其子接口Set和List分别定义了存储方式。
		注意：重写equals方法必须重写hashCode方法(当一个类的某个对象当做索引(键)，会使用hashcode方法)
		两个对象互相equals则hashcode必须相等.
Set接口:其中的数据对象没有顺序并且不可以重复(两对象互相equals相等则重复), Set接口没有提供额外的方法. 与数学中”集合”的概念想对应.
		HashSet(哈希集) Set接口的典型实现,按hash算法来存储集合中的元素。因此具有很好的存储和查找性能。HashSet判断两个元素的标准是两个元素的equals方法比较相等，同时两个对象的hasCode()方法返回值也相等。HashSet可以保存null元素。
		LinkedHashSet: 是HashSet的子类，用法和HashSet一致。它们的不同之处在于LinkedHashSet是有序的，可以记住元素的插入顺序, 采用双重链接式列表.
		SortedSet:是一个按照升序排列元素的Set接口的实现。
		TreeSet(树集): 
List接口:中的数据对象有顺序并且可以重复,类似数组(大小自动增加). List容器的元素都对应一个整数型的序号记载其在容器中的位置，可以根据序号存取容器总的元素。
		ArrayList: 封装了一个自动增长容量的Object[]数组。所以ArrayList适合随机访问内部元素，而在列表中间添加删除元素的效率较低。ArrayList的常见方法都在List接口中。
			1) 利用ArrayList的toArray()返回一个数组。
			2) Arrays.asList()返回一个列表。
			3) 迭代器(Iterator) 给我们提供了一种通用的方式来访问集合中的元素。
		LinkedList:内部结构是线性链表，所以它适合从列表中间添加删除元素，而随机访问元素的效率较低。LinkedList类添加了一些处理列表两端元素的方法。
		注意LinkedList没有同步方法。如果多个线程同时访问一个List，则必须自己实现访问同步。一种解决方法是在创建List时构造一个同步的List：
　　　　	List list = Collections.synchronizedList(new LinkedList(...));
Map接口:(一对一对的装)：定义了存储“键(key) — 值(value)映射对”的方法. 键值不能重复。
		HashTable: 继承Map接口，实现一个key-value映射的哈希表。任何非空（non-null）的对象都可作为key或者value。
		HashMap:和Hashtable类似，不同的是HashMap是非同步的，并且允许null(null value和null key)
		LinkedHashMap: LinkedHashMap扩展HashMap，以插入顺序将关键字/值对添加进链接哈希映像中。象LinkedHashSet一样，LinkedHashMap内部也采用双重链接式列表。
		SortedMap:按照升序排列key的Map。
		TreeMap:
Iterator接口 ：用于遍历Collectin中的元素，相当于指针、游标，每一种容器有自己的Iterator接口。
Comparable接口：实现了这个接口的类，他们之间的对象可以互相比较大小.
Hash表
		Hash表是一种数据结构，用来查找对象。Hash表为每个对象计算出一个整数，称为Hash Code(哈希码)。Hash表是个链接式列表的阵列。每个列表称为一个buckets(哈希表元)

如何选择数据结构
衡量标准：读的效率和改的效率
Array读快改慢
Linked改快读慢
Hash两者之间

一、 重点比较
		1. ArrayList与LinkedList区别(从数据结构考虑)与性能对比(读写数据)+Vector
			1) ArrayList和Vector都是实现了基于动态数组的数据结构, 其中Vector使用了synchronized方法(线程安全) 通常性能上较ArrayList差; 
			LinkedList基于链表的数据结构。
			2) 对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
			3) 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
			4) 查找操作indexOf,lastIndexOf,contains等，两者差不多。
			5) 随机查找指定节点的操作get，ArrayList速度要快于LinkedList.
		另外：
		ArrayList和Vector都是使用数组方式存储数据，此数组元素数大于实际存储的数据以便增加和插入元素，它们都允许直接按序号索引元素，
		但是插入元素要涉及数组元素移动等内存操作，所以索引数据快而插入数据慢，Vector由于使用了synchronized方法（线程安全），通常性能上较ArrayList差，
		而LinkedList使用双向链表实现存储，按序号索引数据需要进行前向或后向遍历，但是插入数据时只需要记录本项的前后项即可，所以插入速度较快。
		2. HashMap与HashTable是区别与性能对比
			1) HashMap是Hashtable的轻量级实现（非线程安全的实现），他们都完成了Map接口，
			主要区别在于HashMap允许空（null）键值（key）,由于非线程安全，效率上可能高于Hashtable。
			2) HashMap允许将null作为一个entry的key或者value，而Hashtable不允许。
			3) HashMap把Hashtable的contains方法去掉了，改成containsvalue和containsKey。因为contains方法容易让人引起误解。 
			4) Hashtable继承自Dictionary类，而HashMap是Java1.2引进的Map interface的一个实现。
			5) 最大的不同是，Hashtable的方法是Synchronize的，而HashMap不是，在多个线程访问Hashtable时，不需要自己为它的方法实现同步，
			而HashMap 就必须为之提供外同步。 
			6) Hashtable和HashMap采用的hash/rehash算法都大概一样，所以性能不会有很大的差异。
		两句话总结：
			HashMap是Hashtable的轻量级实现（非线程安全的实现），他们都完成了Map接口
			HashMap => 不同步、允许空键值、效率高；
			Hashtable => 同步、非空键值、效率略低
二、 Vector、ArrayList和LinkedList 
		大多数情况下，从性能上来说ArrayList最好，但是当集合内的元素需要频繁插入、删除时LinkedList会有比较好的表现，
		但是它们三个性能都比不上数组，另外Vector是线程同步的。所以：
		如果能用数组的时候(元素类型固定，数组长度固定)，请尽量使用数组来代替List；
		如果没有频繁的删除插入操作，又不用考虑多线程问题，优先选择ArrayList；
		如果在多线程条件下使用，可以考虑Vector；
		如果需要频繁地删除插入，LinkedList就有了用武之地；
		如果你什么都不知道，用ArrayList没错。
三、 Vector、ArrayList和LinkedList总结
		1) 如果涉及到堆栈，队列等操作，应该考虑用List，对于需要快速插入，删除元素，应该使用LinkedList，如果需要快速随机访问元素，应该使用ArrayList。
		2) 如果程序在单线程环境中，或者访问仅仅在一个线程中进行，考虑非同步的类，其效率较高，如果多个线程可能同时操作一个类，应该使用同步的类。
		3) 要特别注意对哈希表的操作，作为key的对象要正确复写equals和hashCode方法。
		4) 尽量返回接口而非实际的类型，如返回List而非ArrayList，这样如果以后需要将ArrayList换成LinkedList时，客户端代码不用改变。这就是针对抽象编程.		

```

## 1.5 IO流

```

```





