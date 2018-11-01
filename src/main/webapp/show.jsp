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
  		功能名字<input type="text" id="menu_name"/>
  		上级功能编号<input type="text" id="menu_lastid"/>
  		<input type="button" id="where" value="查询"/>
  		</br>
  		<button type="button"  data-toggle="modal" data-target="#insert">
		  	添加
		</button>
    	<table id="tbl" border="1" style="width: 100%">
    	<thead>
	  		<tr>
	  			<th>功能编号</th>
	  			<th>功能名称</th>
	  			<th>上级功能编号</th>
	  			<th>上级功能名称</th>
	  			<th>状态</th>
	  			<th>操作</th>
	  		</tr>
  		</thead>
	  		<tbody id="tb">
		   </tbody>
	   </table>

	   	<div id="PaginationTb"></div>
 	
	
	<div class="modal fade" id="insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增</h4>
      </div>
      <div class="modal-body">
      <div align="center">
        <from>
        	菜单名称<input type="text" id="insertmenu_name"></br></br>
        	菜单链接<input type="text" id="insertmenu_location">
        	</br></br>
        	<input type="radio" id="menu_state" name="menu_state" checked="checked" value="正常">正常</input>
        	<input type="radio" id="menu_state" name="menu_state" value="失效">失效</input>
        	</br></br>
        	上级菜单<select id="menu_lastname" name="lastname">
					<option value="">最上级功能</option>
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

		<div class="modal fade" id="updata" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="UpdataModalLabel">修改</h4>
					</div>
					<div class="modal-body">
						<div align="center">
							<from>
								菜单名称<input type="text" id="updatamenu_name"></br></br>
								菜单链接<input type="text" id="updatamenu_location">
								</br></br>
								<input type="radio" id="updata_state" name="menu_state" checked="checked" value="正常">正常</input>
								<input type="radio" id="updata_state" name="menu_state" value="失效">失效</input>
								</br></br>
								上级菜单<select id="updata_lastname" name="lastname">

							</select>
								</br>
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
	
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.pagination.js"></script>
    <script type="text/javascript">
    	//定义一页显示多少行数据
    	var page_size = 5;
    	//总行数
    	var total_size = 0;
    	//第几页
    	var pageIndex = 0;

    	var menu_name = '';
    	var menu_lastid = '';
    	var menuid = 0;
    	var menuname=0;
    	
    	 $(document).ready(function() {
    	        //初始化分页，展示分页信息并动态获取总数据条数、每页展示条数
	        		initPaginationTb(pageIndex,menu_name,menu_lastid);
    	        //初始化分页插件
    	        $("#PaginationTb").pagination(total_size, {
    	            callback : pageselectCallbackTb,
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
    			if($("#insertmenu_name").val()!=""&&$("#insertmenu_location").val()!=""&&$("#menu_state").val()!=""){
    				Insert();
    			}else{
    				alert("必须全部填写完");
    			}
    		});
			menuName();


			$("#updataButton").click(function(){
			    if($("#updatamenu_name").val()!=""&&$("#updatamenu_lastname").val()!=""&&$("#updatamenu_location").val()!=""&&$("#updatamenu_state").val()!=""){
                    Updata();
				}else{
                    alert("必须全部填写完");
				}
            })
    	 
    		$("#where").click(function(){
				initPaginationTb(pageIndex,$("#menu_name").val(),$("#menu_lastid").val());
                $("#PaginationTb").pagination(total_size, {
                    callback : pageselectCallbackTb,
                    prev_text : '上一页',
                    next_text : '下一页',
                    link_to : 'javascript:void(0);',//分页的链接,默认“#”
                    items_per_page : page_size,//每页显示的条目数
                    num_display_entries : 5,//连续分页主体部分显示的分页条目数
                    current_page : pageIndex,//当前选中的页面
                    num_edge_entries : 1//两侧显示的首尾分页的条目数
                });
    		});
    	
	        function pageselectCallbackTb(page_index, jq) {
                    initPaginationTb(page_index,$("#menu_name").val(),$("#menu_lastid").val());
    		}
	        
    	function initPaginationTb(page_index,menu_name,menu_lastid) {
		var name = menu_name;
		var lastid = menu_lastid;
        $.ajax({
            url : "/menu/menuSelectAll.do",
            data : {
                currentPage : page_index+1,
                pageSize : page_size,
                totalSize : total_size,
				menuName :	name,
				menuLastid : lastid
            },
            type : "post",
            dataType : "json",
            async : false,
            success : function(data){
            	$("#tb").empty();
                if (!$.isEmptyObject(data)) {
                  var Data=data["data"];
                  for(var key in Data) {
                       if(key=="totalRecord"){
                       		total_size = Data[key];
                       }  else if(key=="listData"){
	                       $(Data[key]).each(function(){
		                       var html = "<tr>";
				    			html+= "<td>"+this.menu_id+"</td>";
				    			html+= "<td>"+this.menu_name+"</td>";
				    			html+= "<td>"+this.menu_lastid+"</td>";
				    			html+= "<td>"+this.menu_lastname+"</td>";
				    			if(this.menu_state==1){
									html+= "<td>正常</td>";
								}else if(this.menu_state==0){
                                    html+= "<td>失效</td>";
								}else {
                                    html+= "<td>"+this.menu_state+"</td>";
								}
								html+= "<td><a href=\"#\" onclick=\"Delete("+this.menu_id+")\">删除</a> / <button type=\"button\"  data-toggle=\"modal\" data-target=\"#updata\" onclick=\"saveid("+this.menu_id+")\">修改</button></td>"
			    			   $("#tb").append(html);
		    			  });
	    			  }
                    }
                } else {
                    alert("没有获取到相关信息！");
                }
            }
        });
    }
    	
    	function Insert(){
    		var name = $("#insertmenu_name").val();
    		var lastname = $("#menu_lastname").val();
    		var location = $("#insertmenu_location").val();
    		var state = $("#menu_state").val();

    		$.ajax({
    			 url : "/menu/insertMenu.do",
    	            data : {
    	                menuName:name,
    	                menuLastname:lastname,
    	                menuLocation:location,
    	                menuState:state
    	            },
    	            type : "post",
    	            dataType : "json",
                	error : function (jqXHR,errorThrown) {
						alert(jqXHR.responseText)
						alert(errorThrown);
						alert("错误");
                    },
    	            success : function(data){
    	            	if(data["data"]=1){
    	            	    alert("新增成功")
                            window.location.reload(true);
    	            	}else{
    	            		alert("失败，功能名重复了");
    	            	}
    	            }
    		})
    	}

    	function Delete(id){
    	    var menuid = id;

    	    $.ajax({
                url : "/menu/deleteMenu.do",
				data :{
                    "menuId" : menuid
				},
				type:"post",
				dataType:"json",
                success :function(data){
                    if(data["data"]==1){
                        alert("删除成功")
                        location.reload(true);
					}else {
                        alert("删除失败")
					}
                }
			})
		}
		
		function saveid() {
			menuid = arguments[0];
			$.ajax({
				url : "/menu/menuSelectAll.do",
				data : {
				    menuId:menuid
				},
				type:"post",
				dataType:"json",
                success :function(data){
					var Data = data["data"];
					for(var key in Data){
						if(key == "listData"){
                            $(Data[key]).each(function() {
								$("#updatamenu_name").val(this.menu_name);
                                $("#updatamenu_location").val(this.menu_location);
                            });
						}
					}
                }
			})
        }
		
        
        function Updata() {
            var name = $("#updatamenu_name").val();
            var lastname = $("#updata_lastname").val();
            var location = $("#updatamenu_location").val();
            var state = $("#updata_state").val();

            $.ajax({
                url : "/menu/updataMenu.do",
                data :{
                    menuName:name,
                    menuLastname:lastname,
                    menuLocation:location,
                    menuState:state,
					menuId:menuid
                },
                type:"post",
                dataType:"json",
                success :function(data){
                    if(data["data"]==1){
						alert("修改成功");
                        $("#updata").modal("hide");
                        window.location.reload();
                    }else{
                        alert("修改失败");
					}
                }
            })
        }

		function menuName(){
    	    $.ajax({
                url : "/menu/menuName.do",
                type:"post",
                dataType:"json",
                success :function(data){
                    $(data["data"]).each(
                        function(){
                            var html = "";
                            html+= "<option value=\""+this.menu_name+"\">"+this.menu_name+"</option>";
                            $("#menu_lastname").append(html);
                            $("#updata_lastname").append(html);
						}
					)
				}
			})
		}
    	</script>
  </body>
</html>
