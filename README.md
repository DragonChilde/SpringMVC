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
