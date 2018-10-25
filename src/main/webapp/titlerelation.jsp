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
        <table id="titleTable" border="0" style="width: 100%">

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
                                    var html="";
                                    var html2="";
                                    for(var key in Data2){
                                        if(key=="menu_lastname"){
                                            html+="<tr>";
                                            html+="<td>"+Data2[key]+"</td>";
                                            html+="</tr>"
                                            alert(Data2[key]);
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
