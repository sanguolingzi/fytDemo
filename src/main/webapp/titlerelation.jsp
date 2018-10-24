<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/24
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
</head>
<body>
    管理权限
    <hr>
    <from>
        <table id="titleTable" border="1" style="width: 100%">

        </table>
    </from>

    <script type="text/javascript">
        titleRelation();
        function titleRelation(){
            $.ajax({
                url : "/title/selectMenu.do",
                type:"post",
                dataType:"json",
                success :function(data){
                    $("#titleTable").empty();
                    alert(data["data"]);
                    if (!$.isEmptyObject(data)){
                        for(var key in data){
                            if(key =="data"){
                                var Data = data[key];
                                alert(Data.length);
                                for(var i=0;i<Data.length;i++){
                                    var Data2 = Data[i];
                                    for(var key in Data2){
                                        if(key=="menu_name"){
                                            var html="<tr>";
                                            html+="<td>"+Data2[key]+"</td>";
                                            html+="</tr>"
                                            $("#titleTable").append(html);
                                            alert(Data2[key]);
                                        }else if(key=="SubmenuList"){
                                            $(Data2[key]).each(function(){
                                                var html2="<tr>"
                                                html2+="<td><input id=\"menuchoose\" type=\"checkbox\" value=\""+this.menu_id+"\">"+this.menu_name+"</td>"
                                                html2+="</tr>"
                                                $("#titleTable").append(html2);
                                            })
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            })
        }
    </script>
</body>
</html>
