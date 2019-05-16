<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'show.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/pagination.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/bootstrap.min.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		</br>
  		<button type="button"  data-toggle="modal" data-target="#insertUser">
		  	添加
		</button>
    	<table id="usertable" border="1" style="width: 100%">
    	<thead>
	  		<tr>
	  			<th>用户编号</th>
	  			<th>用户名称</th>
				<th>用户手机号</th>
				<th>用户邮箱</th>
				<th>用户性别</th>
				<th>用户密码</th>
	  			<th>操作</th>
	  		</tr>
  		</thead>
	  		<tbody id="usertable1">
			</tbody>
	   </table>

	   	<div id="Pagination"></div>
 	
	
	<div class="modal fade" id="insertUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增</h4>
      </div>
      <div class="modal-body">
      <div align="center">
        <br>
        	用户名称<input type="text" id="insertUser_name"></br></br>
			用户密码<input type="text" id="insertUser_pw"></br></br>
		  	手 &nbsp;机&nbsp; 号<input type="text" id="insertUser_ph"></br></br>
		    邮 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;&nbsp;<input type="text" id="insertUser_mail">
        	</br></br>
        	<input type="radio" id="user_sex" name="user_sex" checked="checked" value="男">男</input>
        	<input type="radio" id="user_sex" name="user_sex" value="女">女</input>
        	</br></br>

				  </select>
				  </br>
				  <div align="right">
				  	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				  	<input type="button" class="btn btn-default" id="insertButton" value="添加"></input>
	        	  </div>
        </from>
       </div>
      </div>
      </div>
    </div>
  </div>

		<div class="modal fade" id="updataUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="UpdataModalLabel">修改</h4>
					</div>
					<div class="modal-body">
						<div align="center">
							<from>
								用户名称<input type="text" id="updateUser_name"></br></br>
								用户密码<input type="text" id="updateUser_pw"></br></br>
								手 &nbsp;机&nbsp; 号<input type="text" id="updateUser_ph"></br></br>
								邮 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;&nbsp;<input type="text" id="updateUser_mail">
								</br>
								<input type="radio" id="User_upstate" name="User_sex" value="男">男</input>
								<input type="radio" id="User_upstate" name="User_sex" value="女">女</input>
								</br></br>
								<div align="right">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<input type="button" class="btn btn-default" id="updataButton" value="修改"></input>
								</div>
							</from>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="showUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="UpdataroleModalLabel">用户角色修改</h4>
					</div>
					<div class="modal-body">
						<div>
							<from>用户名称: <span id="updatauserName"></span></br>
								<hr>
								<table id="UpdatauserTable" border="0" style="width: 100%">

								</table>
								<div align="right">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<input type="button" class="btn btn-default" id="updatatitleButton" value="修改"></input>
								</div>
							</from>
						</div>
					</div>
				</div>
			</div>
		</div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.pagination.js"></script>
    <script type="text/javascript">
    	//定义一页显示多少行数据
    	var page_size = 5;
    	//总行数
    	var total_size = 0;
    	//第几页
    	var pageIndex = 0;

    	var userId = 0;
    	var updataUserRoleid=0;
    	 $(document).ready(function() {
    	        //初始化分页，展示分页信息并动态获取总数据条数、每页展示条数
             	initPaginationUI(pageIndex);
    	        //初始化分页插件
    	        $("#Pagination").pagination(total_size, {
    	            callback : pageselectCallback,
    	            prev_text : '上一页',
    	            next_text : '下一页',
    	            link_to : 'javascript:void(0);',//分页的链接,默认“#”
    	            items_per_page : page_size,//每页显示的条目数           
    	            num_display_entries : 5,//连续分页主体部分显示的分页条目数   
    	            current_page : pageIndex,//当前选中的页面
    	            num_edge_entries : 1//两侧显示的首尾分页的条目数
    	        });
    	    });
    		
    		$("#insertButton").click(function(){
    			if($("#insertUser_name").val()!=""&&$("#insertUser_pw").val()!=""&&$("#insertUser_ph").val()!=""&&$("#insertUser_mail").val()!=""&&$("#user_sex").val()!=""){
    				Insert();
    			}else{
    				alert("必须全部填写完");
    			}
    		});

			$("#updataButton").click(function(){
			    if($("#updateUser_name").val()!=""&&$("#updateUser_pw").val()!=""&&$("#updateUser_ph").val()!=""&&$("#updateUser_mail").val()!=""&&$("#User_upstate").val()!=""){
                    Updata();
				}else{
                    alert("必须全部填写完");
				}
            })

			/*$("#showUser").on("show.bs.modal",function(e){
				var name = $(e.relatedTarget).data("userName");
				alert(name);
				$("#updatauserName").empty();
				$("#updatauserName").append(name);
            })*/

			$("#updatatitleButton").click(function(){
                var userid = updataUserRoleid
                var Arr = new Array();
                $.each($('input:checkbox:checked'),function(i){
                    Arr[i] = $(this).val();
                });

                $.ajax({
                        url:"/user/insertUserRole.do",
                        data:{Arr:Arr,
                            userId :userid
                        },
                        type:"post",
                        dataType:"json",
                        traditional: true,
                        success:function(data){
                            if(data["data"]==1){
                                alert("提交成功");
                                window.location.reload(true);
                            }else {
                                alert("提交失败");
                            }
                        }
                    }
                )
            });

	        function pageselectCallback(page_index, jq) {
                	initPaginationUI(page_index);
    		}


	        
    	function initPaginationUI(page_index) {
        $.ajax({
            url : "/user/userSelect.do",
            data : {
                currentPage : page_index+1,
                pageSize : page_size,
                totalSize : total_size
            },
            type : "post",
            dataType : "json",
            async : false,
            success : function(data){
            	$("#usertable1").empty();
                if (!$.isEmptyObject(data)) {
                  for(var key in data){
                      if(key =="data"){
                          var Data=data[key];
                          for(var key in Data) {
                              if(key=="totalRecord"){
                                  total_size = Data[key];
                              }  else if(key=="listData"){
                                  $(Data[key]).each(function(){
                                      var html = "<tr>";
                                      var user = this;
                                      html+= "<td>"+this.user_id+"</td>";
                                      html+= "<td>"+this.user_name+"</td>";
                                      html+= "<td>"+this.user_phone+"</td>";
                                      html+= "<td>"+this.user_mail+"</td>";
                                      html+= "<td>"+this.user_sex+"</td>";
                                      html+= "<td>"+this.user_password+"</td>";
                                      html+= "<td><a href=\"#\" onclick=\"Delete("+this.user_id+")\">删除</a> / <button type=\"button\"  data-toggle=\"modal\" data-target=\"#updataUser\" onclick=\"saveid("+this.user_id+")\">修改</button>" +
										  "/ <button type=\"button\"  data-toggle=\"modal\" data-target=\"#showUser\" data-userName=\"12345\" onclick='updataTitle("+JSON.stringify(user)+")'>查看角色权限</button></td>"
                                      $("#usertable1").append(html);
                                  });
                              }
                          }
					  }

				  }


                } else {
                    alert("没有获取到相关信息！");
                }
            }
        });
    }
    	
    	function Insert(){
    		var name = $("#insertUser_name").val();
    		var sex = $("input[name='user_sex']:checked").val();
    		var pw = $("#insertUser_pw").val();
    		var ph = $("#insertUser_ph").val();
    		var mail = $("#insertUser_mail").val();
    		$.ajax({
    			 url : "/user/userInsert.do",
    	            data : {
    	                userName:name,
                		userSex:sex,
						userPassword:pw,
						userPhone:ph,
						userMail:mail
    	            },
    	            type : "post",
    	            dataType : "json",
                	error : function (jqXHR,errorThrown) {
						alert(jqXHR.responseText)
						alert(errorThrown);
						alert("错误");
                    },
    	            success : function(data){
                             if(data["data"]==1){
                                 alert("添加成功")
                                 $("#insertUser").modal("hide");
                                 location.reload(true);
                             }else{
                                 alert("失败，功能名重复了");
                             }
						 }
    		})
    	}

    	function Delete(id){
    	    var userId = id;

    	    $.ajax({
                url : "/user/userDelete.do",
				data :{
                    userId : userId
				},
				type:"post",
				dataType:"json",
                success :function(data){
                    alert("删除成功");
                    location.reload(true);
                }
			})
		}
		
		function saveid(id) {
            userId = id;
            $.ajax({
                url : "/user/userSelect.do",
                data : {
                    userId:userId
                },
                type:"post",
                dataType:"json",
                success :function(data){
                    for(var key in data){
                        if(key=="data"){
							var Data=data[key];
							for(var key2 in Data){
							    if(key2=="listData"){
							        $(Data[key2]).each(function(){
										$("#updateUser_name").val(this.user_name);
                                        $("#updateUser_pw").val(this.user_password);
                                        $("#updateUser_ph").val(this.user_phone);
                                        $("#updateUser_mail").val(this.user_mail);
                                        $("input[name='User_sex'][value="+this.user_sex+"]").attr("checked",true);
                                    })
								}
							}
						}
                    }
                }
            })
        }
		
        
        function Updata() {
            var name = $("#updateUser_name").val();
            var pw = $("#updateUser_pw").val();
            var ph = $("#updateUser_ph").val();
            var mail = $("#updateUser_mail").val();
            var sex = $("input[name='User_sex']:checked").val();
            $.ajax({
                url : "/user/userUpdate.do",
                data :{
                    userName:name,
					userPassword:pw,
					userPhone:ph,
					userMail:mail,
                    userSex:sex,
                    userId:userId
                },
                type:"post",
                dataType:"json",
                success :function(data){
                    if(data["data"]==1){
						alert("修改成功");
                        $("#updata").modal("hide");
                        location.reload(true);
					}else{
                        alert("修改失败");
					}
                }
            })
        }

		function updataTitle (data) {
    	    var user2 = data;
    	    $("#updatauserName").empty();
    	    $("#updatauserName").append(user2.user_name);
            updataUserRoleid = user2.user_id;
            $.ajax({
                url : "/user/selectUserRole.do",
                data:{
                    "userId": user2.user_id
                },
                type:"post",
                dataType:"json",
                success :function(data){
                    $("#UpdatauserTable").empty();
                    if (!$.isEmptyObject(data)){
                        var Data = data["data"];
                        var html="<tr><td>"
                        $(Data).each(
                            function(){
								if(this.checked==1&&this.checked!=null){
									html+="<input id=\"menuchoose\" type=\"checkbox\" checked=\"true\" value=\""+this.role_id+"\">"+this.role_name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
								}else {
								    html+="<input id=\"menuchoose\" type=\"checkbox\" value=\""+this.role_id+"\">"+this.role_name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
								}

                            }
						)
						html+="</td></tr>"
						$("#UpdatauserTable").append(html);
                    }
                }
            })
        }
    	</script>
  </body>
</html>
