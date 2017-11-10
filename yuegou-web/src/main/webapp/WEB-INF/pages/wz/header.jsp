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
                <div id="gtco-logo"><a href="index.html" style="color: #3A3D28;">WED</a></div>
            </div>
            <div class="col-xs-8 text-right menu-1">
                <ul>
                    <li><a href="<%=basePath%>homepage/getMoviesTop3" style="color: #3A3D28;"><b>首 页</b></a></li>
                    <li class="has-dropdown"><a href="<%=basePath%>homepage/getMovies" style="color: #3A3D28;"><b>电 影</b></a></li>
                    <li><a href="javascript:alert('客官稍等，马上就来。。。');" style="color: #3A3D28;"><b>电视剧</b></a></li>
                    <li><a href="javascript:alert('客官稍等，马上就来。。。');" style="color: #3A3D28;"><b>读 书</b></a></li>
                    <li><a href="javascript:alert('客官稍等，马上就来。。。');" style="color: #3A3D28;"><b>联系我</b></a></li>
                    <li><input type="search" placeholder="影片/演员名称"></li>
                </ul>
            </div>
        </div>
    </div>
</nav>

<header id="gtco-header" class="gtco-cover" role="banner" >
</header>


