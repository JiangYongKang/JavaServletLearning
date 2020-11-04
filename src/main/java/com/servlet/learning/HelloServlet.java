package com.servlet.learning;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: vincent
 * Date: 2020-11-03 16:20
 * Comment: 自定义 Servlet 实现类
 */

public class HelloServlet extends HttpServlet {


    /**
     * Servlet 的初始化方法，在这里可以做一些初始化的动作，需要注意的是这个方法只会被调用一次
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("Servlet 初始化...");
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * 接受并处理 GET 请求，这个方法可以编写自己的业务逻辑
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>Hello " + req.getParameter("name") + "</h1>");
        writer.flush();
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    /**
     * Servlet 容器会调用这个方法进行处理，在这个方法中，会根据请求的类型去调用如 doGet doPost 方法，具体调用根据请求类型决定。
     * 父类中已经很好的根据请求类型做了方法调用，不建议重写这个方法。
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("接收到请求...");
        super.service(req, res);
    }

    /**
     * Servlet 对象在被销毁时做的动作，在这里可以做一些销毁之前需要处理的任务，比如关闭数据库链接等，这个方法也只会被执行一次
     */
    @Override
    public void destroy() {
        System.out.println("Servlet 被销毁...");
        super.destroy();
    }
}
