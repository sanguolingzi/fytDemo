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
  		<button type="button"  data-toggle="modal" data-target="#insertTitle">
		  	添加
		</button>
    	<table id="titletable" border="1" style="width: 100%">
    	<thead>
	  		<tr>
	  			<th>权限编号</th>
	  			<th>权限名称</th>
	  			<th>状态</th>
	  			<th>操作</th>
	  		</tr>
  		</thead>
	  		<tbody id="titletable1">
			</tbody>
	   </table>

	   	<div id="Pagination"></div>
 	
	
	<div class="modal fade" id="insertTitle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增</h4>
      </div>
      <div class="modal-body">
      <div align="center">
        <from>
        	权限名称<input type="text" id="insertTitle_name"></br></br>
        	</br></br>
        	<input type="radio" id="menu_state" name="title_state" checked="checked" value="可用">可用</input>
        	<input type="radio" id="menu_state" name="title_state" value="失效">失效</input>
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

		<div class="modal fade" id="updataTitle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="UpdataModalLabel">修改</h4>
					</div>
					<div class="modal-body">
						<div align="center">
							<from>
								权限名称<input type="text" id="updataTitle_name"></br></br>
								</br>
								<input type="radio" id="updata_state" name="Ttile_state" checked="checked" value="可用">可用</input>
								<input type="radio" id="updata_state" name="Title_state" value="失效">失效</input>
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

		<div class="modal fade" id="showTitle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="UpdatatitleModalLabel">权限修改</h4>
					</div>
					<div class="modal-body">
						<div>
							<from>权限名称: <span id="updatatitleName"></span></br>
								<hr>
								<table id="UpdatatitleTable" border="0" style="width: 100%">

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

    	var titleId = 0;
    	var menuname=0;
		var updataMenuTitleId=0;
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
    			if($("#insertTitle_name").val()!=""&&$("#menu_state").val()!=""){
    				Insert();
    			}else{
    				alert("必须全部填写完");
    			}
    		});

			$("#updataButton").click(function(){
			    if($("#updataTitle_name").val()!=""&&$("#updata_state").val()!=""){
                    Updata();
				}else{
                    alert("必须全部填写完");
				}
            })

			$("#updatatitleButton").click(function(){
                var titleid = updataMenuTitleId
                var Arr = new Array();
                $.each($('input:checkbox:checked'),function(i){
                    Arr[i] = $(this).val();
                });

                $.ajax({
                        url:"/title/insertTitleMenu.do",
                        data:{Arr:Arr,
                            titleId :titleid
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
                    initPagination(page_index);
    		}


	        
    	function initPagination(page_index) {
        $.ajax({
            url : "/title/titleSelect.do",
            data : {
                currentPage : page_index+1,
                pageSize : page_size,
                totalSize : total_size
            },
            type : "post",
            dataType : "json",
            async : false,
            success : function(data){
            	$("#titletable1").empty();
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
                                      html+= "<td>"+this.title_id+"</td>";
                                      html+= "<td>"+this.title_name+"</td>";
                                      html+= "<td>"+this.title_state+"</td>";
                                      html+= "<td><a href=\"#\" onclick=\"Delete("+this.title_id+")\">删除</a> / <button type=\"button\"  data-toggle=\"modal\" data-target=\"#updataTitle\" onclick=\"saveid("+this.title_id+")\">修改</button>" +
										  "/ <button type=\"button\"  data-toggle=\"modal\" data-target=\"#showTitle\" onclick=\"updataTitle("+this.title_id+")\">修改权限</button></td>"
                                      $("#titletable1").append(html);
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
    		var name = $("#insertTitle_name").val();
    		var state = $("input[name='title_state']:checked").val();

    		$.ajax({
    			 url : "/title/insertTitle.do",
    	            data : {
    	                title_name:name,
    	                title_state:state
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
                                 $("#insert").modal("hide");
                                 location.reload(true);
                             }else{
                                 alert("失败，功能名重复了");
                             }
						 }
    		})
    	}

    	function Delete(id){
    	    var titleid = id;

    	    $.ajax({
                url : "/title/deleteTitle.do",
				data :{
                    "title_id" : titleid
				},
				type:"post",
				dataType:"json",
                success :function(data){
                    alert("返回值:"+data);
                    location.reload(true);
                }
			})
		}
		
		function saveid(id) {
            titleId = id;
            $.ajax({
                url : "/title/titleSelectName.do",
                data : {
                    title_id:titleId
                },
                type:"post",
                dataType:"json",
                success :function(data){
                    for(var key in data){
                        if(key == "data"){
                                $(data["data"]).each(function(){
									$("#updataTitle_name").val(this.title_name);
                                })
                        }
                    }
                }
            })
        }
		
        
        function Updata() {
            var name = $("#updataTitle_name").val();
            var state = $("input[name='Title_state']:checked").val();

            $.ajax({
                url : "/title/updateTitle.do",
                data :{
                    title_name:name,
                    title_state:state,
					title_id:titleId
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

		function updataTitle (id) {
            updataMenuTitleId = id;
            $.ajax({
                url : "/title/selectMenu.do",
                data:{
                    "titleId": id
                },
                type:"post",
                dataType:"json",
                success :function(data){
                    $("#UpdatatitleTable").empty();
                    if (!$.isEmptyObject(data)){
                        for(var key in data){
                            if(key =="data"){
                                var Data = data[key];
                                for(var i=0;i<Data.length;i++){
                                    var Data2 = Data[i];
                                    var html="";
                                    var html2="";
                                    for(var key in Data2){
                                        if(key=="menu_name"){
                                            html+="<tr>";
                                            html+="<td>"+Data2[key]+"</td>";
                                            html+="</tr>"
                                            $("#UpdatatitleTable").append(html);
                                        }else if(key=="SubmenuList"){
                                            html2+="<tr><td>"
                                            $(Data2[key]).each(function(){
                                                if(this.checked==1){
                                                    html2+="<input id=\"menuchoose\" type=\"checkbox\" checked=\"true\" value=\""+this.menu_id+"\">"+this.menu_name+" "
												}else {
                                                    html2+="<input id=\"menuchoose\" type=\"checkbox\" value=\""+this.menu_id+"\">"+this.menu_name+" "
												}
                                            })
                                            html2+="</td></tr>"
                                        }else if(key=="title_name"){
                                            $("#updatatitleName").empty();
                                            $("#updatatitleName").append(Data2[key]);
										}
                                    }
                                    $("#UpdatatitleTable").append(html2);
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
