<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<nav class="gtco-nav" role="navigation">
    <div class="gtco-container">

        <div class="row">
            <div class="col-sm-4 col-xs-12">
                <div id="gtco-logo"><a href="<%=basePath%>homepage/index" style="color: #3A3D28;">
                    <img src="<%=basePath %>/wed/images/dong.jpg" height="60px" title="WED"></a></div>
            </div>
            <div class="col-xs-8 text-right menu-1">
                <ul>
                    <li><a href="<%=basePath%>homepage/index" style="color: #3A3D28;"><b>首 页</b></a></li>
                    <li class="has-dropdown"><a href="<%=basePath%>homepage/movies" style="color: #3A3D28;"><b>电 影</b></a></li>
                    <%--<li><a href="javascript:alert('稍等，马上来。。。');" style="color: #3A3D28;"><b>电视剧</b></a></li>--%>
                    <%--<li><a href="javascript:alert('稍等，马上来。。。');" style="color: #3A3D28;"><b>读 书</b></a></li>--%>
                    <li><a href="<%=basePath%>homepage/talks" style="color: #3A3D28;"><b>自话</b></a></li>
                    <li><a href="<%=basePath%>homepage/toContactMe" style="color: #3A3D28;"><b>联系我</b></a></li>
                    <li><input  id="search" type="search" placeholder="影片/演员名称" onkeydown="javascript:ss(this);"></li>
                    <script language="javascript" type="text/javascript">
                        function ss(obj){
                            if(event.keyCode==13) {
                                location.href='<%=basePath%>homepage/movies?title='+obj.value;
                            }
                        }
                    </script>
                </ul>
            </div>
        </div>
    </div>
</nav>

<header id="gtco-header" class="gtco-cover" role="banner" >
</header>


