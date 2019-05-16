<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object code = request.getSession().getAttribute("validation_code");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>小腾系统</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/pagination.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.min.css">



<body>
<div id="win" class="easyui-window" title="Login" style="width:400px;height:200px;">
    <form style="padding:10px 20px 10px 40px;">
        <p>手机号: <input id="phone" type="text"></p>
        <p>密 码: <input id="pwd" type="password"></p>
        <div style="padding:5px;text-align:center;">
            <a class="easyui-linkbutton" id="login" onclick="login()" icon="icon-ok">登陆</a>
            <a data-toggle="modal" data-target="#register" class="easyui-linkbutton" id="register2" icon="icon-ok">注册</a>
            <a href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
        </div>
    </form>
</div>

<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <div align="center">
                    <from>
                        用户名:<input type="text" id="name"><br/>
                        密码:<input type="text" id="pw" onblur="verifyPw()"><span id="verifypw" style="display: none;position:fixed; right:0px; top:100px;">必须以字母开头且不大于10位</span><br/>
                        手机号:<input type="text" id="ph" onblur="verifyPh()"><span id="verifyph" style="display:none;position:fixed; right:50px; top:130px;">请输入正确的手机号</span><br/>
                        邮箱:<input type="text" id="mail" onblur="verifyMail()"><span id="verifymail" style="display:none;position:fixed; right:50px; top:155px;">请输入正确的邮箱</span><br/>
                        性别:<input type="radio" id="sex" name="sex" value="男">男 <input type="radio" id="sex" name="sex" value="女">女<br/>
                        <input type="text"  id="code" name="validation_code"><img title="单击刷新" alt="验证码" src="/verify/verify.do" id="img_code" onclick="refresh()"><br/>
                    </select>
                        </br>
                        <div align="right">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <input type="button" class="btn btn-default" id="registerButton" value="注册"></input>
                        </div>
                    </from>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //重新获得验证码
    function refresh() {
        var img = document.getElementById("img_code");
        img.src = "/verify/verify.do?" + Math.random();//加随机参数，以保证每次的src属性都不同
    }

        function login (){
        var ph = $("#phone").val();
        var pwd = $("#pwd").val();

        $.ajax({
            url : "/login/selectLgoin.do",
            data : {
                userPhone:ph,
                userPassword:pwd
            },
            type : "post",
            dataType : "json",
            success : function(data){
                if (data["data"]==1){
                    window.location.href = 'http://localhost:8080/main.jsp';
                }else {
                    alert("手机号或者密码错误");
                }
            }
        })


    }
    $("#registerButton").click(
        function(){
            register();
        }
    )
    function register(){
            var name = $("#name").val();
            var pw = $("#pw").val();
            var ph = $("#ph").val();
            var mail=$("#mail").val();
            var sex = $("#sex").val();
            var code = $("#code").val();
        $.ajax({
            url : "/register/register.do",
            data : {
                userName:name,
                userPassword:pw,
                userPhone:ph,
                userMail:mail,
                userSex:sex,
                code:code,
            },
            type : "post",
            dataType : "json",
            success : function(data){
                var re = data["data"];
                if(re==0){
                    alert("注册成功");
                    location.reload(true);
                }
                if(re==1){
                    alert("用户名重复");
                }else if(re==2){
                    alert("手机号重复");
                }else if(re==3){
                    alert("邮箱重复");
                }else if(re==4){
                    alert("验证码不正确");
                    refresh();
                }
            }
        })
    }

    function verifyPh(){
        var pattern=/(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^(([0\+]\d{2,3})?(0\d{2,3}))(\d{7,8})((\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
        if(!pattern.test($("#ph").val())){
            $("#verifyph").show();
        }else {
            $("#verifyph").hide();
        }
    }
    function verifyMail() {
        var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!pattern.test($("#mail").val())) {
            $("#verifymail").show();
        } else {
            $("#verifymail").hide();
        }
    }

    function verifyPw() {
         var pattern = /^[a-zA-Z]([a-zA-Z0-9]{5,9})$/;
         if (!pattern.test($("#pw").val())) {
             $("#verifypw").show();
         } else {
             $("#verifypw").hide();
         }
    }
</script>
</body>
</html>
