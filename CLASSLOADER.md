### 类加载机制

     虚拟机把描述类的数据从Class文件加载到内存中，并且对数据进行校验，转换解析和初始化，最终形成可以被虚拟机直接使用的Java类型，这就是虚拟机的类加载机制
     
     Java可以动态扩展的语言特性就是依赖运行期动态加载和动态连接这个来实现的（可以在运行期间再指定其实际的实现类）
     
     
     类从被加载到虚拟机内存开始，到卸载出内存为止，生命周期包括：
     
     1. 加载 （loading）
     2. 验证 （Verfication） 准备 （Preparation） 解析（Resolution）
     3. 初始化（Initialization）
     4. 使用  （Using）
     5. 卸载  （Unloading）
     
     虚拟机规范严格规定了有且只有5种情况必须立即对类进行“初始化”
     
     （1） 遇到 new/getstatic/putstatic/invokestatic 这4条字节码指令的时候，如果类没有进行过初始化，则需要先触发其初始化
     
        生成这4条指令最常见的Java代码场景是：使用new关键字实例化对象的时候，读取或者设置一个类的静态字段的时候（被final修饰，已在编译期间把结果放入常量池的静态字段除外）已经调用一个类的静态方法的时候
        
     （2） 使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没有进行初始化，则需要先触发其初始化
     
     （3） 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化
     
     （4） 用户指定一个要执行的主类（main方法）, 虚拟机会初始化这个主类
       
       
       
 




### 类加载的过程
   
####加载

    （1） 通过一个类的全限定名来获取定义此类的二进制字节流
    （2） 将这个字节流所代表的静态存储转换为方法区的运行时的数据结构
    （3） 在内存中生成一个代表这个类的java.lang.Class对象,作为方法区这个类的各种数据的访问入口
    
     
      JVM在这边的设计并没有指定要从什么路径下去加载二进制字节流，因此许多Java项目都建立在这一个基础上面：
      （1） 从ZIP包中读取，最终成为日后JAR/WAR/EAR格式的基础
      （2） 从网络中获取，这种场景最经典的就是Applet
      （3） 运行时计算生成，这种场景用的最多的就是动态代理，在java.lang.reflect.Proxy中，就是用了ProxyGenerator.generateProxyClass来为特定接口生成形式为“*$Proxy”的代理类的二进制字节流
      （4） 由其他文件生成，典型场景是JSP运用，即由JSP文件生成对应的Class类
      
  
  
####验证


####准备

    准备阶段是正式为类变量分配内存并且设置类变量初始值的阶段，这些变量所使用的内存都将在方法区中进行分配
    
    （1）这时候进行内存分配的仅包括类变量（被static修饰的变量），而不包括实例变量，实例变量将会在对象实例化的时候谁这对象一起分配到Java堆中
    
    （2）这里所说的初始值，在通常情况下是指的零值
       
        public static int value = 123;
        
        这个变量value在准备阶段过后的初始值为0而不是123

####解析
      
   解析阶段是虚拟机将常量池内的  <b>符号引用</b> 替换为 <b>直接引用</b> 的过程
        
        符号引用（Symbolic References）：符号引用以一组符号来描述所引用的目标，符号可以是任何形式的字面量，只要使用时能无歧义的定位到目标便可以
        eg : CONSTANT_Class_info   CONSTANT_Fieldref_info  CONSTANT_Methodref_info
        
        
        直接引用（Direct References）： 直接引用可以是直接指向目标的指针，相对偏移量或者是一个能间接定位到目标的句柄
        
  - 类或者接口的解析
    
    假设当前代码所处的**类为D**，如果要把一个从未解析过的**符号引用N**解析为**一个类或者接口C的直接引用**，那虚拟机完成整个解析的过程需要一下3个步骤：  
    
         （1）如果C不是一个数组类型，那虚拟机将会把代表N的全限定名传递给D的类加载器去加载这个类C，在加载过程，由于元数据验证，字节码验证的需要，又可能触发其他相关类的加载动作，例如加载这儿类的父类或实现的接口，一旦这个加载过程出现了任何异常，解析过程就会宣告失败。
         
         （2）如果C是一个数组类型，并且数组的元素类型为对象，也就是N的描述符是类似“[Ljava/lang/Integer”的形式，那么将会按照第一点的规则加载元素类型。
             如果N的描述符如前面假设的形式，需要加载的元素类型就是“Java.lang.Integer”，接着由虚拟机生成一个代表此数组维度和元素的数组对象。
             
         （3）确认D是否具备对C的访问权限，如果发现不具备访问权限，则会抛出java.lang.IllegalAccessError异常
         
   - 字段解析    
     
     要解析一个未被解析过的字段符号引用，首先将会对字段表内class_index项中索引的CONSTANT_Class_info符号进行解析，也就是字段所属的类或者接口的符号引用。如果解析成功，将这个字段所属的类或者接口用C来表示
          
          （1）如果C本身包含了简单名称和字段描述，都与目标匹配的字段，则返回这个字段的直接引用，查找结束
          （2）否则，如果在C中实现了接口，将会按照继承关系从下往上递归每个接口和它的父接口，如果接口中包含了简单名称和字段描述都与目标匹配的字段，则返回这个字段的直接引用，查找结束
          （3）否则，如果C不是Java.lang.Object的话，将会按照继承关系从下往上递归搜索其父类，如果在父类中包含了简单名称和字段描述符都与目标相匹配的字段，则返回这个字段的直接引用，查找结束
           (4) 查找失败，抛出java.lang.NoSuchFieldError异常
   
   - 类方法解析
     
       先解析出类方法表的class_index项中索引的方法所属的类或者接口的符号引用，如果解析成功，我们依然用C来表示这个类
       
        （1） 类方法和接口方法符号引用的常量定义是分开的，如果在类方法表中发现class_index中索引的C是一个接口，则直接排除IncompatibleClassChangeError的异常
        （2） 在类C中查找是否有简单名称和描述符都与目标相匹配的方法，如果有则返回
        （3） 在类C的父类中递归查找
        （4） 在类C实现的接口列表以及它们的父接口之中递归查找
        （5） java.lang.NoSuchMethodError   
           
   - 接口方法解析
   
       接口方法也需要先解析出接口方法表的class_index项中索引的方法所属的类或者接口的符号引用，如果解析成功，用C来表示这个接口，由于接口中的方法默认都是public的，所以不存在访问权限的问题。
           
           （1）与类解析不同，如果在接口方法表中发现class_index中的索引C是个类而不是接口，直接抛出java.lang.IncompatibleClassChangeError异常
           （2）否则，在接口C中查找是否有简单名称和描述符都与目标相互匹配的方法，如果有则返回这个方法的直接引用，查找结束
           （3）否则，在接口C的父类中递归查找，直到java.lang.Object类为止，看是否有简单名称和描述符都与目标相匹配的方法，如果有则返回这个方法的直接引用
           （4）否则，抛出java.lang.NoSuchMethodError异常
                   

####初始化
   
   类初始化阶段是类加载工程中最后一步，这一步骤才真正开始执行类中定义的Java程序代码（或者说字节码）  
   
     在初始化阶段，是执行类构造器 <clinit>()方法的过程
   
     <br> <clinit>()方法的执行过程 </br>  
     
     (1) <client>()方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块（static{}）中语句合并产生的
         编译器收集的顺序是由语句在源文件中出现的顺序决定的，静态语句块只能访问到定义在静态语句块之前的变量，定义在他之后的变量，在前面的静态语句块可以赋值，但是不能访问
         
         public class Demo {
	
	       static {
		      i = 0;                    //给变量赋值可以正常编译通过
		      System.out.println(i);    //会提示“非法向前引用”
	      }
	
           static int i = 1;
		
        } 
        
     （2）<clinit>()方法与类的构造器函数  (或者说实例构造器<init>()方法)不同
          不需要显示的调用父类构造器，虚拟机会保证在子类的<clinit>()方法执行之前，父类的<clinit>()方法已经执行完毕，因此在虚拟机中第一个被执行的<clinit>()方法的类肯定是java.object.Object
          
          public class Demo {
	
	         public static int A = 1;
	
         	static{
		       A = 2;
	        }
	
	        static class Sub extends Demo{
		       public static int B = A;
	        }
	
	        public static void main(String[] args) {
		       System.out.println(Sub.B);   -- > 输出2
	        }
	     }
	     
	  (3) 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确的加锁，同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的<clinit>()方法，其他线程都需要阻塞等待，直到活动线程执行<clinit>()方法完毕
	      如果在一个类的<clinit>()方法有耗时很长的操作，就可能造成多个进程阻塞，在实际应用中，这种阻塞往往是隐蔽的
	      
	      
	      
	      
	      public class Demo {
	
	          static{
		         if(true){
		         
			     System.out.println(Thread.currentThread() + "init DeadLoopClass");
			
			     while(true){
				
			     }
		      }
	      }
	
	     public static void main(String[] args) {
		
		     Runnable script = new Runnable(){
			      public void run() {
				      System.out.println(Thread.currentThread() + "start");
				      Demo demo1 = new Demo();
				      System.out.println(Thread.currentThread() + "run over");
			      }
			 };
		
		
		     Thread thread1 = new Thread(script);
		     Thread thread2 = new Thread(script);
		
		     thread1.start();
		     thread2.start();
	      }
    
		}
		
		运行结果：有一条线程在死循环操作，另外一条线程在阻塞等待
		Thread[Thread-0,5 main] start
		Thread[Thread-1,5 main] start
		Thread[Thread-0,5 main] init DeadLoopClass   
       
        