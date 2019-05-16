<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户个人中心</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/pagination.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jstree/dist/jstree.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/jstree/dist/themes/default/style.css">
</head>
<body>
    <div>
        <span id="userName"><%= request.getSession().getAttribute("userName")%></span>
        <div id="jstree">

        </div>
    </div>
    <script type="text/javascript">
        var id = <%= request.getSession().getAttribute("userId")%>
        $("#jstree").jstree({
            'core' : {
                'data' : function(obj,callback){
                    var jsonstr="[]";
                    var jsonarray = eval('('+jsonstr+')');
                    $.ajax({
                        type: "post",
                        url:"/userinfo/userSelectTree.do",
                        data:{
                            userId:id
                        },
                        dataType:"json",
                        async: false,
                        success:function(result) {
                            var arrays= result["data"];
                            for(var i=0 ; i<arrays.length; i++){
                                var arr = {
                                    "id":arrays[i].role_id+"role",
                                    "parent" : "#",
                                    "text":arrays[i].role_name
                                }
                                jsonarray.push(arr);
                                var titleList=arrays[i].titleList;
                                for(var j=0;j<titleList.length;j++){
                                    var arr2 = {
                                        "id":titleList[j].title_id+"title"+i+j,
                                        "parent" : arrays[i].role_id+"role",
                                        "text":titleList[j].title_name
                                    }
                                    jsonarray.push(arr2);
                                    var menuDadList=titleList[j].menuDadList;
                                    for(var z=0;z<menuDadList.length;z++){
                                        var arr3 = {
                                            "id":menuDadList[z].menu_lastid+"menuDad"+i+j+z,
                                            "parent" : titleList[j].title_id+"title"+i+j,
                                            "text":menuDadList[z].menu_lastname
                                        }
                                        jsonarray.push(arr3);
                                        var menuList=menuDadList[z].menuList;
                                        for(var y=0;y<menuList.length;y++){
                                            var arr4 = {
                                                "id":menuList[y].menu_id+"menu"+i+j+z+y,
                                                "parent" : menuDadList[z].menu_lastid+"menuDad"+i+j+z,
                                                "text":menuList[y].menu_name
                                            }
                                            jsonarray.push(arr4);
                                        }
                                    }
                                }
                                callback.call(this, jsonarray);
                            }
                        }

                    });
                }
            }
        })
    </script>
</body>
</html>
