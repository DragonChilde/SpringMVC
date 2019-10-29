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

**index**

	<!-- 
		相对路径: 不以/开头的路径 . 相对于当前路径来发送请求. 
		绝对路径: 以/开头的路径 . 直接在 http://localhost:8888 后面拼接请求. 
	 -->
	<a href="hello">Hello SpringMVC</a>

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


# @RequestMapping注解 #

**@RequestMapping概念**

1. springMVC使用@RequestMapping注解为控制器指定可以处理哪些URL请求
2. 在控制器的**类定义及方法定义处**都可标注 @RequestMapping
	1. 标记在类上：提供初步的请求映射信息。相对于WEB应用的根目录
	2. 标记在方法上：提供进一步的细分映射信息。相对于标记在类上的 URL。
3. 若类上未标注 @RequestMapping，则方法处标记的 URL 相对于WEB应用的根目录
4. 作用：DispatcherServlet 截获请求后，就通过控制器上 @RequestMapping 提供的映射信息确定请求所对应的处理方法

**@ RequestMapping源码参考**

		/**consumes,produces这两个参数一般少用**/
		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Documented
		@Mapping
		public @interface RequestMapping {
		    String name() default "";
		
		    @AliasFor("path")
		    String[] value() default {};
		
		    @AliasFor("value")
		    String[] path() default {};
		
		    RequestMethod[] method() default {};
		
		    String[] params() default {};
		
		    String[] headers() default {};
		
		    String[] consumes() default {};
		
		    String[] produces() default {};
		}

**RequestMapping 可标注的位置**

		<a href="springmvc/testRequestMapping">Test Request Mapping</a>
		/**没在类上标注@RequestMapping时，访问URL是testRequestMapping**/
		/**在类上标注了@RequestMapping后，链接的访问URL地址变成springmvc/testRequestMapping**/
		@Controller
		@RequestMapping("springmvc")
		public class SpringmvcHandler {
		
		    @RequestMapping("testRequestMapping")
		    public String testRequestMapping()
		    {
		        return "success";
		    }
		
		}

**RequestMapping映射请求方式**

**映射请求参数、请求方法或请求头**

1. @RequestMapping 除了可以使用请求 URL 映射请求外，还可以使用请求方法、请求参数及请求头映射请求
2. @RequestMapping 的 value【重点】、method【重点】、params【了解】 及 heads【了解】 分别表示请求 URL、请求方法、请求参数及请求头的映射条件，他们之间是与的关系，联合使用多个条件可让请求映射更加精确化。
3. params 和 headers支持简单的表达式：

		param1: 表示请求必须包含名为 param1 的请求参数
		!param1: 表示请求不能包含名为 param1 的请求参数
		param1 != value1: 表示请求包含名为 param1 的请求参数，但其值不能为 value1
		{"param1=value1", "param2"}: 请求必须包含名为 param1 和param2 的两个请求参数，且 param1 参数的值必须为 value1

示例:

	/**这里指定了GET和POST两种方式的请求，如果只指定了一种方式，只能用对应的请求方式访问**/
  	@RequestMapping(value = "testRequestMappingMethod",method = {RequestMethod.GET,RequestMethod.POST})
    public String testRequestMappingMethod(){
        return "success";
    }

**RequestMapping映射请求参数&请求头(了解)**

	/**具体的表达式可以参考上面**/
	/**指定参数username不等于test,并且带有age参数,头信息带有Accept-Language才可访问**/
	 @RequestMapping(value = "testRequestMappingParamsAndHeaders",params = {"username!=test","age"},headers = {"Accept-Language"})
    public String testRequestMappingParamsAndHeaders(){
        return "success";
    }

**RequestMapping映射请求占位符PathVariable注解**

**@PathVariable**

**带占位符的 URL 是 Spring3.0 新增的功能**，该功能在 SpringMVC向**REST**目标挺进发展过程中具有里程碑的意义
**通过@PathVariable可以将URL中占位符参数绑定到控制器处理方法的入参中**：
URL 中的 {xxx} 占位符可以通过 @PathVariable("xxx") 绑定到操作方法的入参中。

	/**
     * 带占位符的URL
     * http://localhost:8080/SpringMVC/testPathVariable/admin/1001
     */
	/**占位符{xxx}里的值不并与参数名一致，只需与注解@PathVariable(xxx)一致**/
    @RequestMapping(value = "testPathVariable/{name}/{id}")
    public String testPathVariable(@PathVariable("name") String name,@PathVariable("id")Integer id)
    {
        System.out.println("name is "+name);		//name is admin
        System.out.println("id is "+id);			//id is 1001
        return "success";
    }

# REST #

1. REST：即 Representational State Transfer。（资源）表现层状态转化。是目前最流行
的一种互联网软件架构。它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多网站的采用
	1. 资源（Resources）：网络上的一个实体，或者说是网络上的一个具体信息。它可以是一段文本、一张图片、一首歌曲、一种服务，总之就是一个具体的存在。可以用一个URI（统一资源定位符）指向它，每种资源对应一个特定的 URI 。获取这个资源，访问它的URI就可以，因此 URI 即为每一个资源的独一无二的识别符。
	2. 表现层（Representation）：把资源具体呈现出来的形式，叫做它的表现层（Representation）。比如，文本可以用 txt 格式表现，也可以用 HTML 格式、XML 格式、JSON 格式表现，甚至可以采用二进制格式。
	3. 状态转化（State Transfer）：每发出一个请求，就代表了客户端和服务器的一次交互过程。HTTP协议，是一个无状态协议，即所有的状态都保存在服务器端。因此，如果客户端想要操作服务器，必须通过某种手段，让服务器端发生“状态转化”（State Transfer）
	而这种转化是建立在表现层之上的，所以就是 “表现层状态转化”。
	4. 具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。
它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。
2. URL风格

		order/1  HTTP GET ：得到 id = 1 的 order   
		order/1  HTTP DELETE：删除 id = 1的 order   
		order    HTTP PUT：更新order   
		order    HTTP POST：新增 order 

3. HiddenHttpMethodFilter

浏览器 form 表单只支持 GET 与 POST 请求，而DELETE、PUT 等 method 并不支持，Spring3.0 添加了一个过滤器，可以将这些请求转换为标准的 http 方法，使得支持 GET、POST、PUT 与 DELETE 请求。


	public class HiddenHttpMethodFilter extends OncePerRequestFilter {
	    private static final List<String> ALLOWED_METHODS;
	    public static final String DEFAULT_METHOD_PARAM = "_method";
	    private String methodParam = "_method";
	
	    public HiddenHttpMethodFilter() {
	    }
	
	    public void setMethodParam(String methodParam) {
	        Assert.hasText(methodParam, "'methodParam' must not be empty");
	        this.methodParam = methodParam;
	    }
	
		/**hiddenHttpMethodFilter 的处理过程**/
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			/**request对象赋值到requestToUse**/
	        HttpServletRequest requestToUse = request;
			/**获取请求request对象是否POST方式并且exception==null**/
	        if ("POST".equals(request.getMethod()) && request.getAttribute("javax.servlet.error.exception") == null) {
				/**request对象获取带有_method的值**/
	            String paramValue = request.getParameter(this.methodParam);
				/**判断是否值**/
	            if (StringUtils.hasLength(paramValue)) {
					/**转成大写**/
	                String method = paramValue.toUpperCase(Locale.ENGLISH);
					/**判断集合是否有当前请求方式**/
	                if (ALLOWED_METHODS.contains(method)) {
						/**调用内部类，把mehtod转成Wrapper类型**/
	                    requestToUse = new HiddenHttpMethodFilter.HttpMethodRequestWrapper(request, method);
	                }
	            }
	        }
			
	        filterChain.doFilter((ServletRequest)requestToUse, response);
	    }
	
	    static {
	        ALLOWED_METHODS = Collections.unmodifiableList(Arrays.asList(HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));
	    }
	
	    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
	        private final String method;
	
	        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
	            super(request);
	            this.method = method;
	        }
	
	        public String getMethod() {
	            return this.method;
	        }
	    }
	}

**配置HiddenHttpMethodFilter过滤器**

**web.xml**

	   <!-- 配置REST过滤器 HiddenHttpMethodFilter将满足转换条件的请求进行转换. 
	       1. 必须是post请求
	       2. 必须要通过_method能获取到一个请求参数值(要转换成的请求方式)
	  -->
    <filter>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>

	/**这里的匹配连接必须是/*,不能是/，因为请求是先进Filter过虑器再到DispatcherServlet，要把进入Filter的都拦截**/
	/**"/*"属于路径匹配，并且可以匹配所有request，由于路径匹配的优先级仅次于精确匹配，所以"/*"会覆盖所有的扩展名匹配，很多404错误均由此引起，所以这是一种特别恶劣的匹配模式，一般只用于filter的url-pattern
	“/”是servlet中特殊的匹配模式，切该模式有且仅有一个实例，优先级最低，不会覆盖其他任何url-pattern，只是会替换servlet容器的内建default servlet ，该模式同样会匹配所有request。**/
    <filter-mapping>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>

**Controller**

	/**
	 * 1.测试REST风格的  GET,POST,PUT,DELETE 操作
	 * 以CRUD为例：
	 * 新增: /order POST
	 * 修改: /order/1 PUT           update?id=1
	 * 获取: /order/1 GET                get?id=1
	 * 删除: /order/1 DELETE        delete?id=1
	 
	 * 2.如何发送PUT请求或DELETE请求?
	 * ①.配置HiddenHttpMethodFilter
	 * ②.需要发送POST请求
	 * ③.需要在发送POST请求时携带一个 name="_method"的隐含域，值为PUT或DELETE
	 
	 * 3.在SpringMVC的目标方法中如何得到id值呢?
	 *   使用@PathVariable注解
	 */
	
	    /**
     * REST GET
     */
    @RequestMapping(value = "order/{id}",method = {RequestMethod.GET})
    public String testRestGet(@PathVariable("id") Integer id){
        System.out.println("REST GET "+ id);
        return "success";
    }
    /**
     * REST DELETE
     */
    @RequestMapping(value = "order/{id}",method = {RequestMethod.DELETE})
    public String testRestDelete(@PathVariable("id") Integer id){
        System.out.println("REST DELETE "+ id);
        return "success";
    }

    /**
     * REST POST
     */
    @RequestMapping(value = "order",method = {RequestMethod.POST})
    public String testRestPost(){
        System.out.println("REST POST ");
        return "success";
    }
    /**
     * REST PUT
     */
    @RequestMapping(value = "order",method = {RequestMethod.PUT})
    public String testRestPut(){
        System.out.println("REST PUT ");
        return "success";
    }

**index.jsp**

	<!-- 修改一个订单 -->
	<form action="order" method="post">
	    <input type="hidden" name="_method" value="PUT"/>
	    <input type="submit" value="REST PUT"/>
	</form>
	<br/>
	
	<!-- 添加一个新的订单 -->
	<form action="order" method="post">
	    <input type="submit" value="REST POST"/>
	</form>
	<br/>
	<!-- 删除id为1001的订单 -->
	<form action="order/1001" method="post">
	    <!-- 隐藏域· -->
	    <input type="hidden" name="_method" value="DELETE"/>
	    <input type="submit" value="REST DELETE"/>
	</form>
	<br/>
	<!-- 查询id为1001的订单 -->
	<a href="order/1001">REST GET</a>
	<br/>


# 处理请求数据 #

**请求处理方法签名**

1. Spring MVC 通过分析处理方法的签名，HTTP请求信息绑定到处理方法的相应人参中
2. Spring MVC 对控制器处理方法签名的限制是很宽松的，几乎可以按喜欢的任何方式对方法进行签名。 
3.	必要时可以对方法及方法入参标注相应的注解（ @PathVariable 、@RequestParam、@RequestHeader 等）、
4.	Spring MVC 框架会将 HTTP 请求的信息绑定到相应的方法入参中，并根据方法的返回值类型做出相应的后续处理。

**@@RequestParam注解**

1. 在处理方法入参处使用 @RequestParam 可以把请求参数传递给请求方法
2. value：参数名
3. required：是否必须。默认为 true, 表示请求参数中必须包含对应的参数，若不存在，将抛出异常
4. defaultValue: 默认值，当没有传递参数时使用该值
