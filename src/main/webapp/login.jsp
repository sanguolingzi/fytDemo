<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>小腾系统</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui/jquery.easyui.min.js"></script>



<body>
<div id="win" class="easyui-window" title="Login" style="width:300px;height:200px;">
    <form style="padding:10px 20px 10px 40px;">
        <p>Name: <input type="text"></p>
        <p>Pass: <input type="password"></p>
        <div style="padding:5px;text-align:center;">
            <a href="main.jsp" class="easyui-linkbutton" icon="icon-ok">Ok</a>
            <a href="#" class="easyui-linkbutton" icon="icon-cancel">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
