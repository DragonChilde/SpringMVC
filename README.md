# SpringMVC

**常用主要组件**

1. DispatcherServlet：前端控制器
2. Controller：处理器/页面控制器，做的是MVC中的C的事情，但控制逻辑转移到前端控制器了，用于对请求进行处理
3. HandlerMapping ：请求映射到处理器，找谁来处理，如果映射成功返回一个HandlerExecutionChain对象（包含一个Handler处理器(页面控制器)对象、多个HandlerInterceptor拦截器对象） 
4. View Resolver : 视图解析器，找谁来处理返回的页面。把逻辑视图解析为具体的View,进行这种策略模式，很容易更换其他视图技术；
	- 如InternalResourceViewResolver将逻辑视图名映射为JSP视图
5. LocalResolver：本地化、国际化
6. MultipartResolver：文件上传解析器
7. HandlerExceptionResolver：异常处理器

**搭配环境**

**pom.xml**

	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0"
	         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	    <modelVersion>4.0.0</modelVersion>
	
	    <groupId>com.springmvc</groupId>
	    <artifactId>SpringMVC</artifactId>
	    <version>1.0-SNAPSHOT</version>
	
	
	    <dependencies>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-context</artifactId>
	            <version>4.3.18.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-core</artifactId>
	            <version>4.3.18.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-beans</artifactId>
	            <version>4.3.18.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-expression</artifactId>
	            <version>4.3.18.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-aop</artifactId>
	            <version>4.3.18.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-web</artifactId>
	            <version>4.3.18.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-webmvc</artifactId>
	            <version>4.3.18.RELEASE</version>
	        </dependency>
	
	        <dependency>
	            <groupId>commons-logging</groupId>
	            <artifactId>commons-logging</artifactId>
	            <version>1.2</version>
	        </dependency>
	    </dependencies>
	</project>

**web.xml**

	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	         version="4.0">
	
	    <!-- Springmvc的前端控制器 / 核心控制器:  DispatcherServlet -->
	    <servlet>
	        <servlet-name>springDispatcherServlet</servlet-name>
	        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	
	        <!-- 给DispatcherServlet配置初始化参数：
					指定Springmvc的核心配置文件
			 -->
	        <init-param>
	            <param-name>contextConfigLocation</param-name>
	            <param-value>classpath:springmvc.xml</param-value>
	        </init-param>
	        <!--
				load-on-startup: 设置DispatcherServlet在服务器启动时加载。
					Servlet的创建时机：
						 1. 请求到达以后创建
						 2. 服务器启动即创建
			 -->
	        <load-on-startup>1</load-on-startup>
	    </servlet>
	
	    <!-- 指定请求的匹配 -->
	    <servlet-mapping>
	        <servlet-name>springDispatcherServlet</servlet-name>
	        <url-pattern>/</url-pattern>
	    </servlet-mapping>
	</web-app>

如果不通过contextConfigLocation来配置SpringMVC的配置文件, 默认的配置文件为: /WEB-INF/<servlet-name>-servlet.xml

**springmvc.xml**

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
	
	    <!--  1. 组件扫描 -->
	    <context:component-scan base-package="com.springmvc"/>
	
	    <!--  2. 视图解析器
	
			 工作机制:  prefix + 请求处理方法的返回值 + suffix  =  物理视图路径.
			 		 /WEB-INF/views/success.jsp
	
			WEB-INF: 是服务器内部路径。 不能直接从浏览器端访问该路径下的资源. 但是可以内部转发进行访问
	
		-->
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	        <property name="prefix" value="/WEB-INF/views/"/>
	        <property name="suffix" value=".jsp"/>
	    </bean>
	</beans>

**控制器**
	
	@Controller
	public class SpringMVCHandler {
	
	    /**
	     * 处理客户端的请求:  http://localhost:8888/springmvc/hello
	     *
	     * @RequestMapping: 完成请求 与 请求处理方法的映射.
	     *
	     */
	    @RequestMapping(value = "hello")
	    public String handleHello()
	    {
	        System.out.println("hello world!");
	        return "success";
	    }
	}

**HelloWorld深度解析**

1. springMVC Helloworld执行流程:
	1. 启动Tomcat服务器,会加载DispatcherServlet，然后就会读取springmvc.xml，进而创建好的Springmvc容器对象.创建Springmvc容器对象:组件扫描会扫描到请求处理器,以及请求处理器中@RequestMapping注解。能得到具体的请求与请求处理器中方法的映射
	2. 客户端发送请求：http://localhost:8080/SpringMVC/hello
	3. 请求到web.xml中与<url-pattern>进行匹配,匹配成功,就将请求交给DispatcherServlet
	4. DispatcherServlet根据请求与请求处理方法的映射,将请求交给具体的请求处理器中的请求处理方法来进行处理
	5. 请求处理方法处理完请求,最终方法会返回一个字符串
	6. 视图解析器根据请求处理方法返回的结果.prefix + returnValue + suffix,解析生成具体的物理视图路径，再通过转发的方式去往视图

	![](https://github.com/DragonChilde/MarkdownPhotos/blob/master/photos/8.png)

2. 请求的映射路径名称和处理请求的方法名称最好一致（实质上方法名称任意）

		 @RequestMapping(value = "hello")
	    public String handleHello()
	    {
	        System.out.println("hello world!");
	        return "success";
	    }

3. 常见配置上错误,把“/WEB-INF/views/”配置成了"/WEB-INF/views

		 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		        <property name="prefix" value="/WEB-INF/views/"/>
		        <property name="suffix" value=".jsp"/>
	   	 </bean>

4. 理请求方式有哪几种

		public enum RequestMethod {
		    GET,
		    HEAD,
		    POST,
		    PUT,
		    PATCH,
		    DELETE,
		    OPTIONS,
		    TRACE;
		
		    private RequestMethod() {
		    }
		}

5. @RequestMapping可以应用在什么地方(方法和类上)

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Documented
		@Mapping
		public @interface RequestMapping {
			....
		}

6. 流程分析

![](https://github.com/DragonChilde/MarkdownPhotos/blob/master/photos/9.png)

基本步骤:

1. 客户端请求提交到DispatcherServlet
2. 由DispatcherServlet控制器查询一个或多个HandlerMapping，找到处理请求的Controller
3. DispatcherServlet将请求提交到Controller（也称为Handler）
4. Controller调用业务逻辑处理后，返回ModelAndView
5. DispatcherServlet查询一个或多个ViewResoler视图解析器，找到ModelAndView指定的视图
6. 视图负责将结果显示到客户端
