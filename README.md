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

	![](http://120.77.237.175:9080/photos/sprigmvc/8.png)

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

![](http://120.77.237.175:9080/photos/sprigmvc/9.png)

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
	</filter-mapping>

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

1. Spring MVC 通过分析处理方法的签名，HTTP请求信息绑定到处理方法的相应形参中
2. Spring MVC 对控制器处理方法签名的限制是很宽松的，几乎可以按喜欢的任何方式对方法进行签名。 
3.	必要时可以对方法及方法入参标注相应的注解（ @PathVariable 、@RequestParam、@RequestHeader 等）、
4.	Spring MVC 框架会将 HTTP 请求的信息绑定到相应的方法入参中，并根据方法的返回值类型做出相应的后续处理。

**@RequestParam注解**

1. 在处理方法入参处使用 @RequestParam 可以把请求参数传递给请求方法
2. **value**：参数名
3. **required**：是否必须。默认为 true, 表示请求参数中必须包含对应的参数，若不存在，将抛出异常
4. **defaultValue**: 默认值，当没有传递参数时使用该值

示例：

	/**
     * @RequestParam  映射请求参数到请求处理方法的形参
     * 	 1. 如果请求参数名与形参名一致， 则可以省略@RequestParam的指定，推荐是指定value。
     * 	 2. @RequestParam 注解标注的形参必须要赋值。 必须要能从请求对象中获取到对应的请求参数。
     * 		可以使用required来设置为不是必须的。
     * 	 3. 可以使用defaultValue来指定一个默认值取代null
     * 客户端的请求:testRequestParam?username=test&age=22
     */
	/**age如果设置成Integer类型话，不设置默认值是不会报错的,但int会因为无法转成null会报异常,因此必须设置默认值**/
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam(value = "username") String username ,@RequestParam(value = "age",required = false,defaultValue = "20") int age)
    {

        System.out.println("username is "+username+" , age is "+age);
        return "success";
    }
链接
	
	<a href="testRequestParam?username=test">Test Request Param</a>

**@RequestHeader注解**

1. 使用@RequestHeader绑定请求报头的属性值
2. 请求头包含了若干个属性，服务器可据此获知客户端的信息，通过 @RequestHeader即可将请求头中的属性值绑定到处理方法的入参中

示例:

    /**
     * @RequestHeader  映射请求头信息到请求处理方法的形参中
     */
	/**参数和上面的@RequestParam一样，可以参考上面**/
    @RequestMapping(value = "testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String acceptLanguage)
    {
        System.out.println("Accept-Language is "+acceptLanguage);	//Accept-Language is en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,ja;q=0.6,zh-TW;q=0.5
        return "success";
    }

链接:

	<a href="testRequestHeader">Test Request Header</a>

**@CookieValue 注解**

1. 使用 @CookieValue 绑定请求中的 Cookie 值
2. @CookieValue 可让处理方法入参绑定某个 Cookie 值

示例:

	/**
	 * @CookieValue  映射cookie信息到请求处理方法的形参中
	 */
  	@RequestMapping(value = "testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID")String sessionid)
    {
        System.out.println("JSESSIONID is " +sessionid);	//JSESSIONID is 7B2527085E486EA645238B11FFB626C9
        return "success";
    }

链接:

	<a href="testCookieValue">Test Cookie Value</a>

**使用POJO作为参数**

1. 使用 POJO 对象绑定请求参数值
2. Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性。如：dept.deptId、dept.address.tel 等

示例:

	/**
	 * Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配， 自动为该对象填充属性值。
	 * 支持级联属性
	 *                 如：dept.deptId、dept.address.tel 等
	 */
	@RequestMapping(value = "testPOJO")
    public String testPOJO(User user)
    {
        System.out.println("User is "+user);	//User is User{username='test', password='123456', email='test@test.com', gender=1, address=Address{province='test1', city='test2'}}
        return "success";
    }

表单:

	<form action="testPOJO" method="post">
	    用户名称: <input type="text" name="username"/>
	    <br/>
	    用户密码: <input type="password" name="password"/>
	    <br/>
	    用户邮箱: <input type="text" name="email"/>
	    <br/>
	    用户性别: 男 <input type="radio" name="gender" value="1"/>
	    女<input type="radio" name="gender" value="0"/>
	    <br/>
	    <!-- 支持级联的方式 -->
	    用户省份: <input type="text" name="address.province" />
	    <br/>
	    用户城市: <input type="text" name="address.city"/>
	    <br/>
	    <input type="submit" value="注册"/>
	</form>

增加两个实体类:

	com.springmvc.bean.Address
	com.springmvc.bean.User

**如果中文有乱码，需要配置字符编码过滤器，且配置其他过滤器之前,如（HiddenHttpMethodFilter），否则不起作用。**

**使用Servlet原生API作为参数**

MVC的Handler方法可以接受哪些ServletAPI类型的参数

1. **HttpServletRequest**
2. **HttpServletResponse**
3. **HttpSession**
4. java.security.Principal(关于安全)
5. Locale
6. InputStream
7. OutputStream
8. Reader
9. Writer

重点是HttpServletRequest和HttpServletResponse，只要获取得到，5-7的类型参数都可以获取到

	/**根据HttpServletRequest和HttpServletResponse，SpringMVC会根据底层封装的JavaWeb进行调用**/
 	@RequestMapping(value = "testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 转发
        //request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request,response);

        //重定向
        //response.sendRedirect("http://www.baidu.com");

        response.getWriter().println("Hello SpringMVC!");
    }

# 处理响应数据 #

**SpringMVC 输出模型数据概述，提供了以下几种途径输出模型数据**

1. **ModelAndView**: 处理方法返回值类型为 ModelAndView 时, 方法体即可通过该对象添加模型数据 
2. **Map 及 Model**: 入参为 org.springframework.ui.Model、org.springframework.ui.ModelMap 或 java.uti.Map 时，处理方法返回时，Map 中的数据会自动添加到模型中

**处理模型数据之ModelAndView**

1. 控制器处理方法的返回值如果为 ModelAndView, 则其既包含视图信息，也包含模型
数据信息。
2. 添加模型数据:


		MoelAndView addObject(String attributeName, Object attributeValue)
		ModelAndView addAllObject(Map<String, ?> modelMap)
3. 设置视图:


		void setView(View view)
		void setViewName(String viewName)

示例:

	@Controller
	public class SpringmvcModelAndViewHandler {
	
		/**
		 * 目标方法的返回类型可以是ModelAndView类型其中包含视图信息和模型数据信息
		 */
	    /**
	     * ModelAndView
	     * 结论: Springmvc会把ModelAndView中的模型数据存放到request域对象中.
	     */
	    @RequestMapping(value = "testModelAndView")
	    public ModelAndView testModelAndView()
	    {
	        //模型数据: username=Admin
	        ModelAndView mav = new ModelAndView();
	        //添加模型数据
	        mav.addObject("username","admin");
	        //设置视图信息
	        mav.setViewName("view");	 //实质上存放到request域中
	        return mav;
	    }
	}

**view.jsp**

	username: ${requestScope.get("username")} <!-- 四个域对象: pageScope  requestScope sessionScope  applicationScope -->

**源码分析**:

	1. 在com.springmvc.handler.modelandview.SpringmvcModelAndViewHandler.testModelAndView() return里打上断点
	2.分析看到熟悉的DispatcherServlet.doDispatch()
	3. mv = ha.handle(processedRequest, response, mappedHandler.getHandler());	//根据mv模型进行分析，mv包含模型数据
	4.  this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
	5.  this.render(mv, request, response);
	6.  进入render()方法，这时看到定义了接口View view;跟着 mv进入view.render(mv.getModelInternal(), request, response);
	7.  抽象类AbstractViewc继承View, 过入重写方法render()看到this.renderMergedOutputModel(mergedModel, this.getRequestToExpose(request), response);
	8.  根据继承类InternalResourceView.renderMergedOutputModel(){this.exposeModelAsRequestAttributes(model, request);}
	9.  此方法可以看到是个循环把模型里的key和value取出，set到request里


**处理模型数据之 Map**

1. Spring MVC 在内部使用了一个 org.springframework.ui.Model 接口存储模型数据
2. Spring MVC 在调用方法前会创建一个隐含的模型对象作为模型数据的存储容器。
3. 如果方法的入参为 Map 或 Model 类型，Spring MVC 会将隐含模型的引用传递给这些入参。
4. 在方法体内，开发者可以通过这个入参对象访问到模型中的所有数据，也可以向模型中添加新的属性数据

![](http://120.77.237.175:9080/photos/sprigmvc/10.png)
![](http://120.77.237.175:9080/photos/sprigmvc/11.png)

    /**
     * Map
     * 结论: SpringMVC会把Map中的模型数据存放到request域对象中.
     *      SpringMVC再调用完请求处理方法后，不管方法的返回值是什么类型，都会处理成一个ModelAndView对象（参考DispatcherServlet的doDispatch()方法）
     */
    @RequestMapping(value = "testMap")
    public String testMap(Map<String,Object> map)
    {
        System.out.println(map.getClass().getName());   //org.springframework.validation.support.BindingAwareModelMap

        map.put("password" ,123456);
        return "view";
    }
	/**无论是通过ModelAndView或者Map的输出模型，都是同一个处理流程**/
	/**通赤打印request给的Map类型可以看到用的是BindingAwareModelMap,BindingAwareModelMap的父类ExtendedModelMap继承了ModelMap和Model， 因此即可可以使用Model类型，也可以使用Map类型**/

# 视图解析 #

**SpringMVC如何解析视图概述**

![](http://120.77.237.175:9080/photos/sprigmvc/12.png)

1. 不论控制器返回一个String,ModelAndView,View都会转换为ModelAndView对象，由视图解析器解析视图，然后，进行页面的跳转
2. 视图解析源码分析：重要的两个接口:**View**和**ViewResolver**

**视图和视图解析器**

1. 请求处理方法执行完成后，最终返回一个 ModelAndView 对象。对于那些返回 String，View 或 ModeMap 等类型的处理方法，**Spring MVC也会在内部将它们装配成一个 ModelAndView对象**，它包含了逻辑名和模型对象的视图
2. Spring MVC借助**视图解析器（ViewResolver）**得到最终的视图对象（View），最终的视图可以是 JSP ，也可能是 Excel、JFreeChart等各种表现形式的视图
3. 对于最终究竟采取何种视图对象对模型数据进行渲染，处理器并不关心，处理器工作重点聚焦在生产模型数据的工作上，从而实现 MVC 的充分解耦

**视图**

1. **视图**的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户,主要就是完成转发或者是重定向的操作.
2. 为了实现视图模型和具体实现技术的解耦，Spring 在 org.springframework.web.servlet 包中定义了一个高度抽象的 **View** 接口：

		public interface View {
		    String RESPONSE_STATUS_ATTRIBUTE = View.class.getName() + ".responseStatus";
		    String PATH_VARIABLES = View.class.getName() + ".pathVariables";
		    String SELECTED_CONTENT_TYPE = View.class.getName() + ".selectedContentType";
		
		    String getContentType();
		
		    void render(Map<String, ?> var1, HttpServletRequest var2, HttpServletResponse var3) throws Exception;
		}

3. **视图对象由视图解析器负责实例化**。由于视图是**无状态**的，所以他们**不会有线程安全**的问题

**常用的视图实现类**

		URL资源图	**InternalResourceView	将JSP或其它资源封装成一个视图,是InternalResourceViewResolver默认使用的视图实现类
					**JstlView		如果JSP文件中使用了JSTL国际化标签的功能,则需要使用该视图类
		
		文档视图		**AbstractExcelView		Excel文档视图的抽象类.该视图类基于POI构造Excel文档
					AbstractPdfView		PDF文档视图的抽象类,该视图类基于iText够着PDF文档

		(几个使用JasperReports报表技术的视图)
		报表视图		ConfigurableJsperReportsView
					JasperReportsCsvView
					JasperReportsMultiFormatView
					JasperReportsHtmlView
					JasperReportsPdfView
					JasperReportsXLsView
		JSON视图		MappingJacksonJsonView		将模型数据通过Jackson开源框架的ObjectMapper以JSON方式输出
					

**视图解析器**

1. SpringMVC 为逻辑视图名的解析提供了不同的策略，可以在 Spring WEB 上下文中配置一种或多种解析策略，并指定他们之间的先后顺序。每一种映射策略对应一个具体的视图解析器实现类。
2. 视图解析器的作用比较单一：将逻辑视图解析为一个具体的视图对象。
3. 所有的视图解析器都必须实现 ViewResolver 接口：

		public interface ViewResolver {
		    View resolveViewName(String var1, Locale var2) throws Exception;
		}

**常用的视图解析器实现类**

	解析为Bean的名字	BeanNameViewResolver	将逻辑视图解析为一个Bean，Bean的id等于逻辑视图名
	解析为URL文件		InternalResourceViewResolver	将视图名解析为一个URL文件,一般使用该解析器将视图名映射为一个保存在WEB-INF目录下的程序文件(如JSP)
					JasperReportsViewResolver		JasperReports是一个基于Java的开源报表工具,该解析器将视图名解析为报表文件对应的URL
	模板文件视图		FreeMarkerViewResolver	解析为基于FreeMarker模板技术的模板文件
					VelocityViewResolver	解析为基于Velocity模板技术的模板文件
					VelocityLayoutViewResolver

1. 程序员可以选择一种视图解析器或混用多种视图解析器
2. 每个视图解析器都实现了 Ordered 接口并开放出一个 order 属性，可以通过order属性指定解析器的优先顺序，order越小优先级越高。
3. SpringMVC会按视图解析器顺序的优先顺序对逻辑视图名进行解析，直到解析成功并返回视图对象，否则将抛出 ServletException 异常
4. InternalResourceViewResolver
	1. JSP 是最常见的视图技术，可以使用 InternalResourceViewResolve作为视图解析器： 

		  	 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		        <property name="prefix" value="/WEB-INF/views/"/>
		        <property name="suffix" value=".jsp"/>
		    </bean>
	2. /WEB-INF/views/xxx/xxx.jsp

**mvc:view-controller标签**

1. 若希望直接响应通过 SpringMVC 渲染的页面，可以使用**mvc:view-controller** 标签实现

		<!-- 不经过Handler直接跳转页面 -->
		<mvc:view-controller path="testViewContorller" view-name="success"/>
2. 请求的路径：http://localhost:8080/SpringMVC/testViewContorller
3. 配置<mvc:view-controller>会导致其他请求路径失效

		<!-- 使用了view-controlelr以后，会导致RequestMapping的映射失效，因此需要加上 annotation-driven的配置 -->
    	<mvc:annotation-driven/>

**重定向**

1. 关于重定向
	1. 一般情况下，控制器方法返回字符串类型的值会被当成逻辑视图名处理
	2. 如果返回的字符串中带 **forward**: 或 **redirect**: 前缀时，SpringMVC 会对他们进行特殊处理：将 forward: 和 redirect: 当成指示符，其后的字符串作为 URL 来处理
	3. redirect:success.jsp：会完成一个到 success.jsp 的重定向的操作
	4. forward:success.jsp：会完成一个到 success.jsp 的转发操作


# 综合案例RESTRUL_CRUD #

**Beans**:

- com.springmvc.crud.beans.Department
- com.springmvc.crud.beans.Employee

**DAO**:

- com.springmvc.crud.dao.DepartmentDao
- com.springmvc.crud.dao.EmployeeDao


**RESTRUL_CRUD_显示所有员工信息**

1. 增加页面链接

		<a href="emps">List All Emps</a>

2. 增加处理器

	- URI：emps
	- 请求方式：GET 

			/**
			 * 显示所有的员工信息列表
			 */
			@RequestMapping(value = "/emps",method = RequestMethod.GET)
		    public String listAllEmps(Map<String,Object> map)
		    {
		        Collection<Employee> emps = employeeDao.getAll();
		        map.put("emps",emps);
		        return "list";
		    }

3. SpringMVC中没遍历的标签，需要使用jstl标签进行集合遍历增加jstl标签库jar包

		WEB-INF/views/list.jsp

**注意:因为页面引用了jstl标签,在pom文件引入了相应的jar包,还要把下载好的jar包放到Tomcat下的Lib下才可顺利执行**

	 	<!--导入jstl，解决jsp页面使用jstl标签无效<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
        <dependency>
            <groupId>org.apache.taglibs</groupId>
            <artifactId>taglibs-standard-impl</artifactId>
            <version>1.2.5</version>
        </dependency>

        <dependency>
            <groupId>org.apache.taglibs</groupId>
            <artifactId>taglibs-standard-spec</artifactId>
            <version>1.2.5</version>
        </dependency>

**RESTRUL_CRUD_添加操作**

1. 在list.jsp上增加连接
	
		<h2 align="center"><a href="emp">Add New Emp</a></h2>

2. 增加处理器方法

		 /**
	     * 添加功能: 去往添加页面
	     *
	     * 添加页面需要部门数据
	     */
	    @RequestMapping(value = "emp",method = RequestMethod.GET)
	    public String toAddPage(Map<String,Object>map)
	    {
	
	        Collection<Department> departments = departmentDao.getDepartments();
	        map.put("depts",departments);
	
			//2. 构造页面中生成单选框的数据
	        HashMap<String, String> hashMap = new HashMap<>();
	        hashMap.put("0","女");
	        hashMap.put("1","男");
	
			//3. 设置页面中要回显的数据
	        //解决错误：java.lang.IllegalStateException: Neither BindingResult nor plain target object for bean name 'command' available as request attribute
	        map.put("employee",new Employee());
	
	        map.put("genders",hashMap);
	        return "input";
	    }

3. 显示添加页面(\WEB-INF\views\input.jsp)

		<%--页面使用了SpringMVC的form标签,因为要引入标签--%>
		<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@ page contentType="text/html;charset=UTF-8" language="java" %>
		<html>
		<head>
		    <title>Title</title>
		</head>
		<body>
		<!-- Springmvc的表单标签：
					 1. 可以快速的开发表单
					 2. 可以更加方便的回显数据.
		
				Springmvc表单标签遇到的问题:
					Neither BindingResult nor plain target object
					for bean name 'command' available as request attribute
		
				问题原因: Springmvc的表单标签必须要进行数据的回显。 默认会使用"command"这个key到request中
				                  找回显的数据。如果找不到，则抛出异常。
				问题解决: 让Springmvc可以通过"command"从request中找到要回显的数据.
						还可以通过modelAttribute来指定一个key替换默认的command
		
				表单标签在最终执行时会转化成原始的HTML标签.
			 -->
		<%--注意这里必须指定command回显的key,不然必报异常,employee是在Controller指定了空对象--%>
		<form:form action="emp" method="post" modelAttribute="employee">
		    lastName:<form:input path="lastName" /> <!-- path就相当于HTML中input标签中的name属性 -->
		    <!--  <input type="text"	 name="lastName"/> -->
		    <br/>
		    Email:<form:input path="email"/>
		    <br/>
		    <!-- radiobuttons 可以根据Map数据来生成单选框 -->
		    Gender:<form:radiobuttons path="gender" items="${genders}"/>
		    <br/>
		    deptName:<form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"/>
		        <!-- <select name="department.id">
		        <option value="1">开发部</option>
		        <option value="2">测试部</option>
		        </select> -->
		    <br/>
		    <input type="submit" name="ADD"/>
		</form:form>
		</body>
		</html>
4. 当没指定modelAttribute回显报异常

	![](http://120.77.237.175:9080/photos/sprigmvc/13.png)

**使用Spring的表单标签**

1. 通过 SpringMVC 的**表单标签**可以实现将模型数据中的属性和 HTML 表单元素相绑定，以实现表单数据**更便捷编辑和表单值的回显**
2. form 标签
	- 一般情况下，**通过 GET 请求获取表单页面，而通过 POST 请求提交表单页面，因此获取表单页面和提交表单页面的 URL 是相同的。**
	- **只要满足该最佳条件的契约，<form:form> 标签就无需通过 action 属性指定表单提交的 URL**
	- 可以通过 modelAttribute 属性指定绑定的模型属性，若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean，如果该属性值也不存在，则会发生错误。
3. SpringMVC 提供了多个表单组件标签，如 <form:input/>、<form:select/> 等，用以绑定表单字段的属性值，它们的共有属性如下：
	- **path：表单字段，对应 html 元素的 name 属性，支持级联属性**
	- htmlEscape：是否对表单值的 HTML 特殊字符进行转换，默认值为 true
	- cssClass：表单组件对应的 CSS 样式类名
	- cssErrorClass：表单组件的数据存在错误时，采取的 CSS 样式
4. form:input、form:password、form:hidden、form:textarea：对应 HTML 表单的 text、password、hidden、textarea 标签
5. form:radiobutton：单选框组件标签，当表单 bean 对应的属性值和 value 值相等时，单选框被选中
6. **form:radiobuttons**：单选框组标签，用于构造多个单选框
	- **items**：可以是一个 List、String[] 或 Map
	- **itemValue**：指定 radio 的 value 值。可以是集合中 bean 的一个属性值
	- **itemLabel**：指定 radio 的 label 值
	- **delimiter**：多个单选框可以通过 delimiter 指定分隔符
7. form:checkbox：复选框组件。用于构造单个复选框
8. form:checkboxs：用于构造多个复选框。使用方式同 form:radiobuttons 标签
9. form:select：用于构造下拉框组件。使用方式同 form:radiobuttons 标签
10. form:option：下拉框选项组件标签。使用方式同 form:radiobuttons 标签
11. **form:errors**：显示表单组件或数据校验所对应的错误
	- <form:errors path= “*” /> ：显示表单所有的错误
	- <form:errors path= “user*” /> ：显示所有以 user 为前缀的属性对应的错误
	- <form:errors path= “username” /> ：显示特定表单对象属性的错误

**添加员工实验代码**

控制器方法

	/**
     * 添加功能 : 具体的添加操作
     */
    @RequestMapping(value = "emp" ,method = RequestMethod.POST)
    public String addEmp(Employee employee)
    {
        //添加员工
        employeeDao.save(employee);
        //回到列表页面 :重定向到显示所有员工信息列表的请求.
        return "redirect:/emps";
    }

**RESTRUL_CRUD_删除操作&处理静态资源**

1. 引入静态资源/scripts/jquery-3.4.1.min.js
2. 访问http://localhost:8080/SpringMVC/scripts/jquery-3.4.1.min.js,会报404异常
3. 解决办法，SpringMVC 处理静态资源
	1. 为什么会有这样的问题:

		Springmvc处理静态资源的问题:
		静态资源:  .js  .css  .html  .txt  .png  .jpg  .avi 等.
		
		因为DispatcherServlet的 <url-pattern> 配置的是/ , 会匹配到所有的请求(排除jsp的请求).
		因为请求的.js文件，是一个静态资源请求，交给DispatcherServlet后就会出现no mapping found 问题。
		解决问题:
		1. 修改<url-pattern>为后缀匹配. 但是不建议这么做， 对REST的支持不好. 因为一个优秀的REST 不希望请求
		URL带有任何后缀.
		2. 在springmvc.xml中加上一个配置: <mvc:default-servlet-handler/>
		<mvc:annotation-driven/>

		优雅的REST风格的资源URL不希望带.html或.do等后缀，若将DispatcherServlet请求映射配置为/, 则 Spring MVC将捕获WEB容器的所有请求, 包括静态资源的请求,SpringMVC会将他们当成一个普通请求处理, 因找不到对应处理器将导致错误。

	2. 解决: 在SpringMVC的配置文件中配置 
	
		<mvc:default-servlet-handler/>

4. 配置后，原来页面的请求又不好使了需要加多配置

		<mvc:annotation-driven />

**关于<mvc:default-servlet-handler/>作用**

	<!-- 
	<mvc:default-servlet-handler/> 将在 SpringMVC 上下文中定义一个 DefaultServletHttpRequestHandler，
	它会对进入DispatcherServlet的请求进行筛查，如果发现是没有经过映射的请求，
	就将该请求交由WEB应用服务器(Tomcat)默认的Servlet处理，如果不是静态资源的请求，才由DispatcherServlet继续处理
	一般WEB应用服务器(Tomcat)默认的 Servlet 的名称都是 default。
	若所使用的WEB服务器的默认 Servlet 名称不是 default，则需要通过 default-servlet-name 属性显式指定 
	配置了default-serlvet-handler后，RequestMapping的映射会失效，需要加上annotation-driven的配置。       
	-->
	参考：CATALINA_HOME/config/web.xml
	 <servlet>
        <servlet-name>default</servlet-name>
        <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
        <init-param>
            <param-name>debug</param-name>
            <param-value>0</param-value>
        </init-param>
        <init-param>
            <param-name>listings</param-name>
            <param-value>false</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
	<!--该标签属性default-servlet-name默认值是"default",可以省略。<mvc:default-servlet-handler/> -->

**因此注意:下面这两个在SpringMVC里是必配的**

    <mvc:default-servlet-handler/>
  	<mvc:annotation-driven/>

**删除操作**

在\WEB-INF\views\list.jsp增加DELET的POST请求

	<form action="/emp" method="post">
	    <input type="hidden" name="_method" value="DELETE"/>
	</form>

控制器方法

	 /**
     * 删除功能
     */
    @RequestMapping(value = "emp/{id}",method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id")Integer id)
    {
        //删除员工
        employeeDao.delete(id);
        //重定向到列表
        return "redirect:/emps";
    }

**RESTRUL_CRUD_修改操作**

**根据id查询员工对象，表单回显**

1. 页面链接

		 <a href="emp/${emp.id}">Edit </a>

2. 控制器方法


		   /**
		     * 修改功能: 去往修改页面
		     */
		    @RequestMapping(value = "emp/{id}",method = RequestMethod.GET)
		    public String toUpdatePage(@PathVariable("id")Integer id,Map<String,Object> map)
		    {
		        //查询要修改的员工信息
		        Employee employee = employeeDao.get(id);
		        map.put("employee",employee);
		
		        Collection<Department> departments = departmentDao.getDepartments();
		        map.put("depts",departments);
		
		        HashMap<Object, Object> hashMap = new HashMap<>();
		        hashMap.put("0","女");
		        hashMap.put("1","男");
		        map.put("genders",hashMap);
		
		        return "input";
		    }

3. 修改页面

		<!--头部增加c标签-->
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!--注意:提交地址之前是action="emp",因为修改操作地址必须是绝对路径才不会影响之前的添加操作,因此为了开发时不把路径写死,配成下面的路径参数-->
		<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
		    <!--
		    增加判断是添加操作还是修改操作:
		    根据回显的Employee对象的id值来判断: 如果有id就是修改  如果没有id就是添加操作
		    -->
		    <c:if test="${!emptyee.id}" var="flag">
		        <!-- 修改操作 -->
		        <form:hidden path="id"/>
			<!--因为是修改操作,应用PUT请求隐式显示-->
		        <input type="hidden" name="_method" value="PUT"/>
		    </c:if>
		
		    lastName:<form:input path="lastName" />
		    <br/>
		    Email:<form:input path="email"/>
		    <br/>
		    Gender:<form:radiobuttons path="gender" items="${genders}"/>
		    <br/>
		    deptName:<form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"/>
		    <br/>
			<!--根据上面定义的flag标签判断是添加还是修改-->
		    <c:if test="${flag}">
		        <input type="submit" name="Edit"/>
		    </c:if>
		    <c:if test="${!flag}">
		        <input type="submit" name="ADD"/>
		    </c:if>
		
		</form:form>

**提交表单，修改数据**

控制器方法

  	/**
     * 修改功能: 具体的修改操作
     */
    @RequestMapping(value = "emp",method = RequestMethod.PUT)
    public String editEmp(Employee employee)
    {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

**解决页面中文提交乱码问题**

	<!--在web.xml配置增加过滤器,因为过滤器是按配置顺序执行,因此必须把字符过滤器优先配到最上面-->
	  <!-- 字符编码过滤器 -->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<!--相关要配置的参数可以参考CharacterEncodingFilter类里的doFilterInternal()方法-->
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

# 处理JSON #

1. 加入jar包：

	
	 		 <!--导入jackson相关包,增加对json数据的支持-->
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-core</artifactId>
	            <version>2.5.1</version>
	        </dependency>
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-databind</artifactId>
	            <version>2.5.1</version>
	        </dependency>
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-annotations</artifactId>
	            <version>2.5.1</version>
	        </dependency>

2. 编写目标方法，使其返回 JSON 对应的对象或集合

	
	    /**
	     处理Json
	     **/
	    @ResponseBody   // 负责将方法的返回值 转化成json字符串 响应给浏览器端.
	    @RequestMapping(value = "testJson")
	    public Collection<Employee> testJson()
	    {
	        Collection<Employee> emps = employeeDao.getAll();
	        System.out.println(emps);
	        return emps;
	    }

**特别注意:在调试过程中一直报java.util.HashMap转换类型错误或者**

	nested exception is java.lang.NoClassDefFoundError: com/fasterxml/jackson/core/util/DefaultPrettyPrinter

**这是因为在用IDEA开发过程中,生成的WEB目录下的lib没有上面对应的三个jar包,要把三个jar包引入WEB目录下lib**

**HttpMessageConverter原理**

1. **HttpMessageConverter<T>** 是 Spring3.0 新添加的一个接口，**负责将请求信息转换为一个对象（类型为 T），将对象（类型为 T）输出为响应信息**
2. HttpMessageConverter<T>接口定义的方法：
	1. Boolean canRead(Class<?> clazz,MediaType mediaType): 指定转换器可以读取的对象类型，即转换器是否可将请求信息转换为 clazz 类型的对象，同时指定支持 MIME 类型(text/html,applaiction/json等)
	2. Boolean canWrite(Class<?> clazz,MediaType mediaType):指定转换器是否可将 clazz 类型的对象写到响应流中，响应流支持的媒体类型在MediaType 中定义。
	3. List<MediaType> getSupportMediaTypes()：该转换器支持的媒体类型。
	4. T read(Class<? extends T> clazz,**HttpInputMessage** inputMessage)：将请求信息流转换为 T 类型的对象。
	5. void write(T t,MediaType contnetType,**HttpOutputMessgae** outputMessage):将T类型的对象写到响应流中，同时指定相应的媒体类型为 contentType。

![](http://120.77.237.175:9080/photos/sprigmvc/14.png)

		public interface HttpInputMessage extends HttpMessage {
		    InputStream getBody() throws IOException;
		}

		public interface HttpOutputMessage extends HttpMessage {
		    OutputStream getBody() throws IOException;
		}


- StringHttpMessageConverter		//将请求信息转换为字符串
- FormHttpMessageConverter		//将表单数据读取到MultiValueMap中
- XmlAwareFormHttpMessageConverter		//扩展于FormHttpMessageConverter,如果部分表单数据属性是XML数据,可用该转换器进行读取
- ResourceHttpMessageConverter		//读写org.springframework.core.io.Resource对象
- BufferedImageHttpMessageConverter		//读写BufferedImage对象
- ByteArrayHttpMessageConverter		//读写二进制数据
- SourceHttpMessageConverter		//读写javax.xml.transform.Source数据
- MarshallingHttpMessageConverter		//通过Spring的org.springframework.xml.Marshaller和Unmarshaller读写XML消息
- Jaxb2RootElementHttpMessageConverter		//通过JAXB2读写XML消息,将请求消息转换到标注XmlRootElement和XXMLTtyp连接的类中
- MappingJackson2HttpMessageConverter		//利用Jackson开源包的ObjectMapper读写JSON数据
- RssChannelHttpMessageConverter		//能够读写RSS种子消息
- AtomFeedHttpMessageConverter		//和RssChannelHttpMessageConverter能够读写RSS种子消息

3. DispatcherServlet 默认装配 RequestMappingHandlerAdapter，而 RequestMappingHandlerAdapter 默认装配如下 HttpMessageConverter：

![](http://120.77.237.175:9080/photos/sprigmvc/15.png)

4. 加入 jackson jar 包后， RequestMappingHandlerAdapter装配的 HttpMessageConverter如下

![](http://120.77.237.175:9080/photos/sprigmvc/16.png)

**默认情况下数组长度是6个；增加了jackson的包，后多个一个MappingJackson2HttpMessageConverter**

**下载功能**

	  /**
     * 使用HttpMessageConveter完成下载功能:
     *
     * 支持  @RequestBody   @ResponseBody   HttpEntity  ResponseEntity
     *
     * 下载的原理:  将服务器端的文件 以流的形式  写到 客户端.
     * ResponseEntity: 将要下载的文件数据， 以及响应信息封装到ResponseEntity对象中，浏览器端通过解析
     * 				       发送回去的响应数据， 就可以进行一个下载操作.
     */
    @RequestMapping(value = "download")
    public ResponseEntity<byte[]> testDownload(HttpSession session) throws IOException
    {
        //一,定义一个空的字符数组
        byte[] bytes;

        //二,获取本地图片的流
        ServletContext context = session.getServletContext();
        InputStream stream = context.getResourceAsStream("image/test.jpg");

        //三,定义长度大小的字符
        //available()在非阻塞情况下(本地读取,非网络流),获取已经流的长度大小
        bytes = new byte[stream.available()];

        //四,把本地流写入字符数组里
        stream.read(bytes);

        //五定义一个HTTP头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=test.jpg");

        //返回ResponseEntity
        ResponseEntity<byte[]> entity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);

        return entity;

    }

**<mvc:annotation-driven>的作用**:

1. 配置了<mvc:view-controller>
2. 配置了<mvc:default-servlet-handler/>
3. 处理Json,对HttpMessageConveter的支持
4. 对数据绑定流程的支持,对异常处理的支持

**<mvc:annotation-driven>原理**:

启动一些新的组件对象替换原先旧的组件对象,从而实现一些新的,更强大的功能

解释:<mvc:default-servlet-handler/>为什么还要配<mvc:annotation-driven>?

<mvc:default-servlet-handler/>和<mvc:annotation-driven>都没有配置的情况,DispatcherServlet中handlerAdapters装配:

- HttpRequestHandlerAdapter
- SimpleControllerHandlerAdapter
- AnnotationMethodHandlerAdapter(spring3.2之后已废弃)

<mvc:default-servlet-handler/>配置,<mvc:annotation-driven>不配置的情况,DispatcherServlet中handlerAdapters装配:

- HttpRequestHandlerAdapter
- SimpleControllerHandlerAdapter

<mvc:default-servlet-handler/>和<mvc:annotation-driven>都配置的情况,DispatcherServlet中handlerAdapters装配:

- HttpRequestHandlerAdapter
- SimpleControllerHandlerAdapter
- RequestMappingHandlerAdapter

**总结:在Spring3.2之后,RequestMappingHandlerAdapter替换掉了AnnotationMethodHandlerAdapter**

# 文件上传 #

1. 拷贝jar包

	    <!--文件上传依赖包-->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.4</version>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.6</version>
        </dependency>

2. 配置文件上传解析器

		 <!-- 配置文件的上传
		    该bean的id值必须是 multipartResolver , 因为springmvc底层会通过该名字到容器中找对应的bean
		  -->
	    <!--具体是因为初始化DispatcherServlet时,传入的ApplicationContext上下文获取加载bean,默认把bean的ID写死了,id值必须是multipartResolver,不然会报异常-->
	    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	        <!-- 保证与上传表单所在的Jsp页面的编码一致. -->
	        <property name="defaultEncoding" value="UTF-8"/>
			<!--限制文件大小为10M-->
	        <property name="maxUploadSize" value="10485760"/>
	    </bean>

3. 上传页面

		<form action="upload" method="post" enctype="multipart/form-data">
		    上传文件:<input type="file" name="uploadFile"/>
		    <br/>
		    文件描述:<input type="text" name="desc"/>
		    <br/>
		    <input type="submit" value="上传"/>
		</form>

4. 控制器方法

	    /**
	     * 文件的上传
	     * 上传的原理:  将本地的文件 上传到 服务器端
	     */
	    @RequestMapping(value = "upload" )
	    public String testUploadFile(@RequestParam("desc") String desc,@RequestParam("uploadFile")MultipartFile multipartFile, HttpSession session) throws IOException
	    {
	
	        //获取到上传文件的名字
	        String filename = multipartFile.getOriginalFilename();
	        //获取输入流
	        InputStream in = multipartFile.getInputStream();
	
	        //获取服务器端的uploads文件夹的真实路径。
	        ServletContext context = session.getServletContext();
	        String realPath = context.getRealPath("uploads");
	
	        File file = new File(realPath + "/" + filename);
	
	        FileOutputStream out = new FileOutputStream(file);
	
	        //写文件
	        int i;
	        while ((i = in.read()) != -1)
	        {
	            out.write(i);
	        }
	        out.close();
	        in.close();
	
	        return "uploadsuccess";
	    }


		 /*上传文件更简单的实现,调用MultipartFile类里封装好的transferTo()方法,原理也是用Stream流进行读写*/
	    @RequestMapping(value = "upload" )
	    public String testUploadFile(@RequestParam("desc") String desc,@RequestParam("uploadFile")MultipartFile multipartFile, HttpSession session) throws IOException
	    {
	        String filename = multipartFile.getOriginalFilename();
	
	        ServletContext context = session.getServletContext();
	        String realPath = context.getRealPath("uploads");
	        File file = new File(realPath + "/" + filename);
	        multipartFile.transferTo(file);
	        return "uploadsuccess";
	    }


# 拦截器 #

Spring MVC也可以使用拦截器对请求进行拦截处理，用户可以自定义拦截器来实现特定的功能，**自定义的拦截器可以实现HandlerInterceptor接口，或者可以继承
HandlerInterceptorAdapter适配器类**

1. **preHandle**()：这个方法在业务处理器处理请求之前被调用，在该方法中对用户请求 request 进行处理。**如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去进行处理，则返回true；如果程序员决定不需要再调用其他的组件去处理请求，则返回false。**
2. **postHandle**()：**这个方法在业务处理器处理完请求后，但是DispatcherServlet 向客户端返回响应前被调用**，在该方法中对用户请求request进行处理。
3. **afterCompletion**()：这个方法**在 DispatcherServlet 完全处理完请求后被调用**，可以在该方法中进行一些资源清理的操作。


**自定义拦截器类**

1. 自定义拦截器类 

		/**
		 * 自定义拦截器
		 */
		/*@Component*/
		public class MyFirstInterceptor implements HandlerInterceptor {
		
		    /**
		     * 1. 是在DispatcherServlet的962行   在请求处理方法之前执行
		     */
		    @Override
		    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		        System.out.println(this.getClass().getName() + " preHandle");
		        return true;
		    }
		
		    /**
		     * 2. 在DispatcherServlet 974行   请求处理方法之后，视图处理之前执行。
		     */
		    @Override
		    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		        System.out.println(this.getClass().getName() + " postHandle");
		    }
		
		    /**
		     * 3.
		     * 	 [1].在DispatcherServlet的 1059行   视图处理之后执行.(转发/重定向后执行)
		     * 	 [2].当某个拦截器的preHandle返回false后，也会执行当前拦截器之前拦截器的afterCompletion
		     *   [3].当DispatcherServlet的doDispatch方法抛出异常,也可能会执行拦截器的afterCompletion
		     */
		    @Override
		    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		        System.out.println(this.getClass().getName() + " afterCompletion");
		    }
		}

2. 配置拦截器

	   	<!-- 配置拦截器 -->
	    <mvc:interceptors>
	        <!--1. 拦截所有的请求 -->
	        <bean class="com.springmvc.interceptor.MyFirstInterceptor"/>
	        <!--如果在拦截器上定义了@Component组件,可以通过ref进行配置-->
	       <!-- <ref bean="myFirstInterceptor"/>-->
	
	        <!-- 2. 指定拦截 或者指定不拦截 -->
	       <!-- <mvc:interceptor>
	            <mvc:mapping path="emps"/>      &lt;!&ndash;指定拦截哪些路径&ndash;&gt;
	            <mvc:exclude-mapping path="emps"/>  &lt;!&ndash;指定不拦截哪些路径&ndash;&gt;
	            <bean class="com.springmvc.interceptor.MyFirstInterceptor"/>
	            <ref bean="myFirstInterceptor"/>
	        </mvc:interceptor>-->
	    </mvc:interceptors>

3. 拦截器方法执行顺序

![](http://120.77.237.175:9080/photos/sprigmvc/17.png)

**多个拦截器**

1. 自定义拦截器类(两个)

		public class MySecondInterceptor implements HandlerInterceptor {
		    @Override
		    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		        System.out.println(this.getClass().getName() + " preHandle");
		        return true;
		    }
		
		    @Override
		    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		        System.out.println(this.getClass().getName() + " postHandle");
		    }
		
		    @Override
		    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		        System.out.println(this.getClass().getName() + " afterCompletion");
		    }
		}

2. 配置自定义拦截器

	   	<!-- 配置拦截器 -->
	    <mvc:interceptors>
	        <!--1. 拦截所有的请求 -->
	        <bean class="com.springmvc.interceptor.MyFirstInterceptor"/>
			<!--定义第二个拦截器-->
	        <bean class="com.springmvc.interceptor.MySecondInterceptor"/>
	        <!--如果在拦截器上定义了@Component组件,可以通过ref进行配置-->
	       <!-- <ref bean="myFirstInterceptor"/>-->
	
	        <!-- 2. 指定拦截 或者指定不拦截 -->
	       <!-- <mvc:interceptor>
	            <mvc:mapping path="emps"/>      &lt;!&ndash;指定拦截哪些路径&ndash;&gt;
	            <mvc:exclude-mapping path="emps"/>  &lt;!&ndash;指定不拦截哪些路径&ndash;&gt;
	            <bean class="com.springmvc.interceptor.MyFirstInterceptor"/>
	            <ref bean="myFirstInterceptor"/>
	        </mvc:interceptor>-->
	    </mvc:interceptors>

		<!--拦截器的执行顺序根据配置的前后顺序有关-->
		<!--
		com.springmvc.interceptor.MyFirstInterceptor preHandle
		com.springmvc.interceptor.MySecondInterceptor preHandle
		com.springmvc.interceptor.MySecondInterceptor postHandle
		com.springmvc.interceptor.MyFirstInterceptor postHandle
		com.springmvc.interceptor.MySecondInterceptor afterCompletion
		com.springmvc.interceptor.MyFirstInterceptor afterCompletion
		-->

**多个拦截方法的执行顺序**

1. 关于执行顺序

		<!--
		com.springmvc.interceptor.MyFirstInterceptor preHandle
		com.springmvc.interceptor.MySecondInterceptor preHandle
		com.springmvc.interceptor.MySecondInterceptor postHandle
		com.springmvc.interceptor.MyFirstInterceptor postHandle
		com.springmvc.interceptor.MySecondInterceptor afterCompletion
		com.springmvc.interceptor.MyFirstInterceptor afterCompletion
		-->

2. 执行顺序图解

![](http://120.77.237.175:9080/photos/sprigmvc/18.png)

3. 从源代码的执行角度分析流程

![](http://120.77.237.175:9080/photos/sprigmvc/19.png)

可以看到现在加载到有3个拦截器，第一个是SpringMVC的，这里不作讨论

    boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerInterceptor[] interceptors = this.getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
			/**this.interceptorIndex默认是从-1开始，递增遍历加载的三个拦截器同时赋值给this.interceptorIndex，最终this.interceptorIndex为2**/
            for(int i = 0; i < interceptors.length; this.interceptorIndex = i++) {
                HandlerInterceptor interceptor = interceptors[i];
				/**注意:在拦截器里现在首个拦截全部都是返回true的，这里判断是非真，判断是false，没有进入**/
                if (!interceptor.preHandle(request, response, this.handler)) {
                    this.triggerAfterCompletion(request, response, (Exception)null);
                    return false;
                }
            }
        }

        return true;
    }
	/**因此执行顺序是以下**/
	/**
		com.springmvc.interceptor.MyFirstInterceptor preHandle
		com.springmvc.interceptor.MySecondInterceptor preHandle
	**/

	void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
        HandlerInterceptor[] interceptors = this.getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
			/**这里是按拦截器的长度进行递减遍历**/
            for(int i = interceptors.length - 1; i >= 0; --i) {
                HandlerInterceptor interceptor = interceptors[i];
                interceptor.postHandle(request, response, this.handler, mv);
            }
        }

    }
	/**因此执行顺序是以下**/
	/**
		com.springmvc.interceptor.MySecondInterceptor postHandle
		com.springmvc.interceptor.MyFirstInterceptor postHandle
	**/

    void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        HandlerInterceptor[] interceptors = this.getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
			/**根据上面的索引赋值，值为2进行递减遍历**/
            for(int i = this.interceptorIndex; i >= 0; --i) {
                HandlerInterceptor interceptor = interceptors[i];
                try {
                    interceptor.afterCompletion(request, response, this.handler, ex);
                } catch (Throwable var8) {
                    logger.error("HandlerInterceptor.afterCompletion threw exception", var8);
                }
            }
        }

    }

	/**因此执行顺序是以下**/
	/**
		om.springmvc.interceptor.MySecondInterceptor afterCompletion
		com.springmvc.interceptor.MyFirstInterceptor afterCompletion
	**/

- 如果在在MyFirstInterceptor.preHandle()返回false时，只会打印com.springmvc.interceptor.MyFirstInterceptor preHandle就不会再执行,可以看源码

	    boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        HandlerInterceptor[] interceptors = this.getInterceptors();
	        if (!ObjectUtils.isEmpty(interceptors)) {
				/**this.interceptorIndex默认是从-1开始，递增遍历加载的三个拦截器同时赋值给this.interceptorIndex，最终this.interceptorIndex为2**/
	            for(int i = 0; i < interceptors.length; this.interceptorIndex = i++) {
	                HandlerInterceptor interceptor = interceptors[i];
					/**现在是false,非假，判断为真,调用triggerAfterCompletion()方法，返回false，**/
	                if (!interceptor.preHandle(request, response, this.handler)) {
	                    this.triggerAfterCompletion(request, response, (Exception)null);
	                    return false;
	                }
	            }
	        }
	
	        return true;
	    }
	
		/**在DispatchServlet.doDispatch()里,调用拦截器的applyPreHandle判断执行return,直接终止操作**/
	    if (!mappedHandler.applyPreHandle(processedRequest, response)) {
	        return;
	    }

- 当两个拦截器都返回false时,这时也只会打印 com.springmvc.interceptor.MyFirstInterceptor preHandle

	因为在遍历过程中当第一个为flase后,this.triggerAfterCompletion()方法遍历无法找到自身的拦截器执行,因此只能直接return;

- 当第一个拦截器为true,第二个拦截器为false时 打印的是

	- com.springmvc.interceptor.MyFirstInterceptor preHandle
	- com.springmvc.interceptor.MySecondInterceptor preHandle
	- com.springmvc.interceptor.MyFirstInterceptor afterCompletion

	因为执行完第一个拦截器后是不会满足条件进入判断,执行第二个拦截器会进入if (!interceptor.preHandle(request, response, this.handler))的判断调用 this.triggerAfterCompletion()方法,因此多个拦截器只要preHandle返回true,都会成功执行到afterCompletion()方法,看下图

![](http://120.77.237.175:9080/photos/sprigmvc/20.png)

# 运行流程图解 #

**流程图**

![](http://120.77.237.175:9080/photos/sprigmvc/21.png)

**Spring工作流程描述**

1. 用户向服务器发送请求，请求被SpringMVC 前端控制器 DispatcherServlet捕获；
2. DispatcherServlet对请求URL进行解析，得到请求资源标识符（URI）:

	判断请求URI对应的映射
	1. 不存在：
		- 再判断是否配置了mvc:default-servlet-handler：
		- 如果没配置，则控制台报映射查找不到，客户端展示404错误
		- 如果有配置，则执行目标资源（一般为静态资源，如：JS,CSS,HTML）
	2. 存在：
		- 执行下面流程
3. 根据该URI，调用HandlerMapping获得该Handler配置的所有相关的对象（包括Handler对象以及Handler对象对应的拦截器），最后以HandlerExecutionChain对象的形式返回；
4. DispatcherServlet 根据获得的Handler，选择一个合适的HandlerAdapter。
5. 如果成功获得HandlerAdapter后，此时将开始执行拦截器的preHandler(...)方法【正向】
6. 提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)方法，处理请求。在填充Handler的入参过程中，根据你的配置，Spring将帮你做一些额外的工作：
	1. HttpMessageConveter： 将请求消息（如Json、xml等数据）转换成一个对象，将对象转换为指定的响应信息
	2. 数据转换：对请求消息进行数据转换。如String转换成Integer、Double等
	3. 数据根式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等
	4. 数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中
7. Handler执行完成后，向DispatcherServlet 返回一个ModelAndView对象；
8. 此时将开始执行拦截器的postHandle(...)方法【逆向】
9. 根据返回的ModelAndView（此时会判断是否存在异常：如果存在异常，则执行HandlerExceptionResolver进行异常处理）选择一个适合的ViewResolver（必须是已经注册到Spring容器中的ViewResolver)返回给DispatcherServlet，根据Model和View，来渲染视图
10. 在返回给客户端时需要执行拦截器的AfterCompletion方法【逆向】
11. 将渲染结果返回给客户端

**DEBUG**

1. 正常流程,运行出结果
2. 没有配置<mvc:default-servlet-handler/>，<mvc:annotation-driven/>,访问一个不存在的链接会报404页面和异常

		org.springframework.web.servlet.PageNotFound.noHandlerFound No mapping found for HTTP request with URI [/SpringMVC/add] in DispatcherServlet with name 'springDispatcherServlet'
	- 因为这时访问请求交给了DispatchServlet处理
3. 当配置了<mvc:default-servlet-handler/>,访问一个不存在的链接报404页面,但不会报No mapping异常
	- 因为这时访问请求交给了服务器(Tomcat)处理去匹配目录链接

# Spring整合SpringMVC #

是否需要在web.xml 文件中配置启动 Spring IOC 容器的 ContextLoaderListener ?

- 需要: 通常情况下, 类似于数据源, 事务, 整合其他框架都是放在 Spring 的配置文件中(而不是放在 SpringMVC 的配置文件中). 实际上放入 Spring 配置文件对应的 IOC 容器中的还有 Service 和 Dao. 
- 不需要: 都放在 SpringMVC 的配置文件中. 也可以分多个 Spring 的配置文件, 然后使用 import 节点导入其他的配置文件 


如何启动Spring IOC容器?

- 非WEB环境： 直接在main方法或者是junit测试方法中通过new操作来创建.
- WEB 环境: 我们希望SpringIOC容器在WEB应用服务器启动时就被创建.通过监听器来监听ServletContext对象的创建, 监听到ServletContext对象被创建，就创建SpringIOC容器。 并且将容器对象绑定到ServletContext中， 让所有的web组件能共享到IOC容器对象. 


**测试模拟SpringMVC ContextLoaderListener监听加载容器**

**新建立web.xml**

	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
	    <!--模拟SpringMVC的ContextLoaderListener监听-->
    <servlet>
        <display-name>HelloServlet</display-name>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.springmvc.servlet.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/HelloServlet</url-pattern>
    </servlet-mapping>
    <listener>
        <listener-class>com.springmvc.listener.MyServletContextListener</listener-class>
    </listener>
	</web-app>

**新建立spring.xml**

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	
	
	<bean id="person" class="com.springmvc.bean.Person">
	    <property name="name" value="test"/>
	</bean>
	</beans>


**com.springmvc.listener.MyServletContextListener**

	public class MyServletContextListener implements ServletContextListener {
	    /**
	     * 当监听到ServletContext被创建，则执行该方法
	     */
	    @Override
	    public void contextInitialized(ServletContextEvent servletContextEvent) {
	        //1. 创建SpringIOC容器对象
	        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	        //2. 将SpringIOC容器对象绑定到ServletContext中
	        ServletContext servletContext = servletContextEvent.getServletContext();
	        servletContext.setAttribute("applicationContext",context);
	    }
	
	    @Override
	    public void contextDestroyed(ServletContextEvent servletContextEvent) {
	
	    }
	}

**com.springmvc.servlet.HelloServlet**

	public class HelloServlet extends HttpServlet {
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	        //访问到SpringIOC容器中的person对象.
	        //从ServletContext对象中获取SpringIOC容器对象
	        ServletContext servletContext = getServletContext();
	        ApplicationContext context = (ApplicationContext)servletContext.getAttribute("applicationContext");
	        Person person = context.getBean("person", Person.class);
	        person.sayHello();
	    }
	
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        super.doPost(req, resp);
	    }
	}

链接
	
	http://localhost:8080/SpringMVC/HelloServlet

最终结果,打印出:My name is test

**Spring整合SpringMVC_解决方案配置监听器**

1. 监听器配置

	  	<!-- 初始化SpringIOC容器的监听器 -->
	    <context-param>
	        <param-name>contextConfigLocation</param-name>
	        <param-value>classpath:spring.xml</param-value>
	    </context-param>
	    <listener>
	        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	    </listener>
		<!--分析ContextLoaderListener源码发现,加载容器的方式与上面模拟的思路都是一样的-->

2. 配置文件：springmvc.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
		       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		       xmlns:context="http://www.springframework.org/schema/context"
		       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
		
		
		<bean id="person" class="com.springmvc.bean.Person">
		    <property name="name" value="test"/>
		</bean>
		
		    <context:component-scan base-package="com.springmvc"/>
		</beans>

3. 增加com.springmvc.handler.UserHandler

		@Controller
		public class UserHandler {
		
		    public UserHandler() {
		        System.out.println(this.getClass().getName());
		    }
		}

