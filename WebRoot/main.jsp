<%@page language="java" contentType="text/html;charset=UTF-8" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>

    <body>
        这是主页面：${currentUser.chrName}
        <a href="logoutServlet.do">安全退出</a>
    </body>

    </html>