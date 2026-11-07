# 一、仓库说明

这是 2020 年 11 月留下的一份 Servlet 练习，用来看一次 HTTP 请求怎样经过过滤器，再由 Servlet 返回一段 HTML。仓库现已归档，源码和配置保持原样，不再继续开发。

本文根据源码、web.xml 和 pom.xml 静态整理。结论描述的是代码在 Servlet 容器中应有的调用关系，没有在本地编译或部署验证。

# 二、工程里有什么

1. pom.xml 的坐标是 com.servlet.learning:JavaServletLearning:1.0-SNAPSHOT，打包类型为 war。编译源和目标都是 1.8。唯一依赖是 javax.servlet-api 4.0.1，作用域为 provided，编译时使用，不打进 WAR。
2. 打包插件是 maven-war-plugin 3.3.0。最终文件名和输出目录都用 Maven 默认值，因此包名为 JavaServletLearning-1.0-SNAPSHOT.war，位于 target。工程没有绑定内嵌容器，也没有启动脚本。
3. src/main/java/com/servlet/learning 下有三个类：HelloServlet 负责响应，BeforeFilter 和 AfterFilter 是两个过滤器。类注释中的作者署名为 vincent。
4. src/main/webapp 里只有 WEB-INF/web.xml。三个类都在这份描述符里注册，源码中没有 @WebServlet 或 @WebFilter。仓库里没有页面、静态文件、JSP 和测试目录。
5. .gitignore 忽略 .idea、*.iml 和 target。根目录的 LICENSE 是 GNU GPL 第 3 版全文，Java 源文件里没有另附版权头。

# 三、一次请求怎么走

1. web.xml 声明的是 Web Application 2.3。过滤器和 Servlet 的地址都是 *.do，任意以 .do 结尾的路径进入同一条链。Servlet 在描述符中的名字是 Controller，类名是 HelloServlet，这个名字只用于描述符内部对应。
2. 过滤器映射的书写顺序是 BeforeFilter，然后 AfterFilter。请求先进入 BeforeFilter，打印“BeforeFilter 执行逻辑...”，再调用 chain.doFilter 交给下一级。
3. AfterFilter 打印“AfterFilter 执行逻辑...”，然后同样调用 chain.doFilter。类注释称之为后置过滤器。doFilter 在这次调用返回之后没有后续语句，响应返回时不再做处理。
4. 容器进入的是 service(ServletRequest, ServletResponse)。这里打印“接收到请求...”后转给父类，父类再调用 service(HttpServletRequest, HttpServletResponse)。本类对后一个方法的重写只是转回父类，由父类按 HTTP 方法分发。
5. GET 由 doGet 处理：响应类型设为 text/html，读取参数 name，写出一对 h1，文字是 Hello 加上参数值，然后 flush。未传 name 时，参数值为 null，标题会变成 Hello null。
6. HEAD、POST、PUT、DELETE、OPTIONS、TRACE 都直接调用 HttpServlet 的对应方法，本类没有再写业务。
7. 容器调用的初始化入口是 init(ServletConfig)。该重写转给父类，父类再进入无参 init，日志“Servlet 初始化...”写在无参方法里。
8. 按 Servlet 的约定，两个过滤器的 init 在应用启动时执行，分别打印各自的初始化文字。描述符没有 load-on-startup，HelloServlet 要等第一次被请求才初始化。应用停止时，三个类的 destroy 各打印一句销毁文字。
9. 上下文路径由部署时的 WAR 文件名或容器配置决定。按默认包名部署时，形如 /JavaServletLearning-1.0-SNAPSHOT/hello.do?name=world 的 GET 会得到标题 Hello world。

# 四、阅读时容易看错的地方

1. 依赖写的是 Servlet 4.0.1，描述符写的是 2.3。现在用到的 filter、servlet 和 url-pattern 在 2.3 中已经存在，描述符里没有使用更高版本才出现的配置项。
2. AfterFilter 的类注释写的是后置过滤器。它和 BeforeFilter 一样，打印语句写在 chain.doFilter 之前，调用返回后没有代码。
3. 响应类型没有带字符集。HelloServlet 没有成员变量，doGet 只使用局部变量和请求参数。
4. 描述符里没有错误页、登录约束和欢迎页。业务输出就是 GET 返回的那一行 HTML。

# 五、归档约定

1. 业务源码、web.xml、pom.xml 和 LICENSE 保持归档时的内容。本文件只说明仓库里实际有什么，方便以后查阅。
2. 使用和再分发以根目录 LICENSE 中的 GNU GPL 第 3 版为准。许可证原文声明软件按现状提供，没有额外担保。
3. 归档之后不再修缺陷，也不再补充功能。要看到真实响应，需要自行准备支持这份 Servlet 接口的容器。
