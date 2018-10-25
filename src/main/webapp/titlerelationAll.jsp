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
        <input type="button" id="titlerelationAll" value="提交">
    </from>

    <script type="text/javascript">
        titleRelation();
        function titleRelation(){
            $.ajax({
                url : "/title/selectMenuAll.do",
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
                                        if(key=="menu_name"){
                                            html+="<tr>";
                                            html+="<td>"+Data2[key]+"</td>";
                                            html+="</tr>"
                                        }else if(key=="SubmenuList"){
                                            html2+="<tr>"
                                            $(Data2[key]).each(function(){
                                                html2+="<td><input id=\"menuchoose\" name=\"menuChoose\" type=\"checkbox\" value=\""+this.menu_id+"\">"+this.menu_name+"</td>"
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

        $("#titlerelationAll").click(function(){
            var Arr = new Array()
            //$('input:checkbox:checked') 等同于 $('input[type=checkbox]:checked')
            //意思是选择被选中的checkbox

            $.each($('input:checkbox:checked'),function(i){
                window.alert("你选了："+ $('input[type=checkbox]:checked').length+"个，其中有："+$(this).val());
                    Arr[i] = $(this).val();
            });
            $.ajax({
                url:"/title/insertTitleMenu.do",
                data:{Arr:Arr},
                type:"post",
                dataType:"json",
                traditional: true,
                success:function(data){
                    if(data["data"]==1){
                        alert("提交成功");
                    }else {
                        alert("提交失败");
                    }
                }
            }
            )
        });
    </script>
</body>
</html>
