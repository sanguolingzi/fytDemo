<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
</head>
<body>
    管理权限
    <hr>
    <div id="titleName">
        权限名称 : <%=request.getParameter("title_name")%>
    </div>
    <from>
        <table id="titleTable" border="0" style="width: 100%">

        </table>
    </from>

    <script type="text/javascript">
        var titleid = <%=request.getParameter("title_id")%>
        titleRelation();
        function titleRelation(){
            $.ajax({
                url : "/title/selectMenu.do",
                data:{
                    "title_id": titleid
                },
                type:"post",
                dataType:"json",
                success :function(data){
                    $("#titleTable").empty();
                    if (!$.isEmptyObject(data)){
                        for(var key in data){
                            if(key =="data"){
                                var Data = data[key];
                                for(var i=0;i<Data.length;i++){
                                    var Data2 = Data[i];
                                    var html="";
                                    var html2="";
                                    for(var key in Data2){
                                        if(key=="menu_lastname"){
                                            html+="<tr>";
                                            html+="<td>"+Data2[key]+"</td>";
                                            html+="</tr>"
                                        }else if(key=="SubmenuList"){
                                            html2+="<tr>"
                                            $(Data2[key]).each(function(){
                                                html2+="<td><input id=\"menuchoose\" type=\"checkbox\" checked=\"true\" value=\""+this.menu_id+"\">"+this.menu_name+"</td>"
                                            })
                                            html2+="</tr>"
                                        }
                                    }
                                    $("#titleTable").append(html);
                                    $("#titleTable").append(html2);
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
