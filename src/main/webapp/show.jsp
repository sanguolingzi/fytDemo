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
    	<table id="tbl">
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

	   	<div id="Pagination"></div>
 	
	
	<div class="modal fade" id="insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改</h4>
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
					<option value="第1级功能">第1级功能</option>
					<option value="第2级功能">第2级功能</option>
					<option value="第3级功能">第3级功能</option>
					<option value="第4级功能">第4级功能</option>
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
	
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.pagination.js"></script>
    <script type="text/javascript">
    	//定义一页显示多少行数据
    	var page_size = 5;
    	//总行数
    	var total_size = 0;
    	//第几页
    	var pageIndex = 0;
    	
    	 $(document).ready(function() {
    	        //初始化分页，展示分页信息并动态获取总数据条数、每页展示条数
	        		initPagination(pageIndex);
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
    			if($("#insertmenu_name").val()!=""&&$("#menu_lastname").val()!=""&&$("#insertmenu_location").val()!=""&&$("#menu_state").val()!=""){
    			    alert("进入添加函数")
    				Insert();
    			}else{
    				alert("必须全部填写完");
    			}
    		});
    	 
    	 
    		$("#where").click(function(){
    			if($("#menu_name").val()==""&&$("#menu_lastid").val()==""){
                    initPagination(pageIndex);
    			}else{
    				selectLike(pageIndex);
    			}
    			alert("点击查询");
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
    	
	        function pageselectCallback(page_index, jq) {
	        	if($("#menu_name").val()==""&&$("#menu_lastid").val()==""){
                    initPagination(page_index);
	        	}else{
                    selectLike(page_index);
	        	}
    		}
	        
	        function selectLike(page_index){
	        	var name= $("#menu_name").val();
	        	var lastid =$("#menu_lastid").val();
	        	alert(name+"和"+lastid);
	        	$.ajax({
	                url : "/test/menuSelectBy.do",
	                data : {
	                    currentPage : page_index+1,
	                    pageSize : page_size,
	                    totalSize : total_size,
	                    menu_name : name,
	                    menu_lastid : lastid
	                },
	                type : "post",
	                dataType : "json",
	                async : false,  
	                error:function(e){
	                	alert(e);
	                },
	                success : function(data){
	                	$("#tb").empty();
	                    if (!$.isEmptyObject(data)) {
	                      var Data=data;
	                      for(var key in Data) { 
	                           if(key=="totalRecord"){
	                           		total_size = Data[key];
	                           		alert(Data[key]);
	                           }  else if(key=="listData"){
	    	                       $(Data[key]).each(function(){
	    		                       var html = "<tr>";
	    				    			html+= "<td>"+this.menu_id+"</td>";
	    				    			html+= "<td>"+this.menu_name+"</td>";
	    				    			html+= "<td>"+this.menu_lastid+"</td>";
	    				    			html+= "<td>"+this.menu_lastname+"</td>";
	    				    			html+= "<td>"+this.menu_state+"</td>";
                                       html+= "<td>"
                                           +"<input type=\"button\" id=\"delect\" value=\"删除\">"+
                                           "</td>";
	    				    			html+= "</tr>";
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
	        
    	function initPagination(page_index) {
        $.ajax({
            url : "/test/menuSelectAll.do",
            data : {
                currentPage : page_index+1,
                pageSize : page_size,
                totalSize : total_size
            },
            type : "post",
            dataType : "json",
            async : false,
            success : function(data){
            	$("#tb").empty();
                if (!$.isEmptyObject(data)) {
                  var Data=data;
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
				    			html+= "<td>"+this.menu_state+"</td>";
				    			html+= "<td>"
				    			+"<input type=\"button\" id=\"delect\" value=\"删除\">"+
				    			"</td>";
				    			html+= "</tr>";
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
    		alert("name :"+name+"--"+"lastname :"+lastname+"--"+"location :"+location+"--"+"state :"+state);
    		
    		$.ajax({
    			 url : "/test/Insert.do",
    	            data : {
    	                menu_name:name,
    	                menu_lastname:lastname,
    	                menu_location:location,
    	                menu_state:state
    	            },
    	            type : "post",
    	            dataType : "json",
                	error : function (jqXHR,errorThrown) {
						alert(jqXHR.responseText)
						alert(errorThrown);
						alert("错误");
                    },
    	            success : function(data){
    			     alert("添加返回的函数"+data)
    	            	if(data==1){
    	            		alert(data);
    	            		$("#insert").modal("hide");
    	            	}else{
    	            		alert("失败，功能名重复了");
    	            	}
    	            }
    		})
    	}
    	</script>
  </body>
</html>
