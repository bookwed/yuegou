<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>电影、电视剧、纪录片下载_BT种子下载_东家小站</title>
    <meta name="keywords" content="奥斯卡电影,豆瓣9.0,纪录片,东家小站" />
    <meta name="description" content="东家小站为您提供最优质的奥斯卡电影、豆瓣评分9.0以及优质纪录片BT种子下载，欢迎您来体验。" />
    <%@include file="resources.jsp" %>
    <!--分页 -->
    <script type="text/javascript" src="<%=basePath%>wed/src/kkpager.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/wed/src/kkpager_orange.css" />
    <style type="text/css">
        body {
            font-size: 14px;
            line-height: 1.5;
            color: #666;
            font-family: Lantinghei SC,Open Sans,Arial,Hiragino Sans GB,Microsoft YaHei,\\5FAE\8F6F\96C5\9ED1,STHeiti,WenQuanYi Micro Hei,SimSun,sans-serif;
            overflow-x: hidden;
        }
        /* 搜索框 */
        /* reset webkit search input browser style */
        input {
            outline: none;
        }
        input[type=search] {
            -webkit-appearance: textfield;
            -webkit-box-sizing: content-box;
            font-family: inherit;
            font-size: 100%;
        }
        input::-webkit-search-decoration,
        input::-webkit-search-cancel-button {
            display: none; /* remove the search and cancel icon */
        }

        /* search input field */
        input[type=search] {
            background: #ededed url("http://www.weierdong.com/wed/images/search-icon.png") no-repeat 9px center;
            border: solid 1px #ccc;
            padding: 5px 10px 5px 32px;
            width: 55px;

            -webkit-border-radius: 10em;
            -moz-border-radius: 10em;
            border-radius: 10em;

            -webkit-transition: all .5s;
            -moz-transition: all .5s;
            transition: all .5s;
        }
        input[type=search]:focus {
            width: 130px;
            background-color: #fff;
            border-color: #6dcff6;

            -webkit-box-shadow: 0 0 5px rgba(109,207,246,.5);
            -moz-box-shadow: 0 0 5px rgba(109,207,246,.5);
            box-shadow: 0 0 5px rgba(109,207,246,.5);
        }
        /*content*/
        #breadcrumb {
            width: 1400px;
            margin: 15px 0 15px 15px;
            font-size: 12px;
            display: block;
            background: #f9f9f9;
            padding: 0 5px;
            color: #666;
            border-top: 1px solid #eee;
            height: 30px;
            line-height: 30px;
            clear: both;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .post-grid {
            display: block;
            margin-left: 15px;
        }
        .post-grid .post {
            /*margin-bottom: 15px;*/
            padding-bottom: 10px;
            /*border-bottom: 1px solid #ddd;*/
        }
        .clearfix {
            zoom: 1;
        }
        .entry-thumb {
            display: block;
            float: left;
            position: relative;
            margin: 0 20px 0 0;
            display: block;
        }
        .entry-title {
            line-height: 18px;
            font-size: 14px;
            overflow: hidden;
            white-space: nowrap;
            -o-text-overflow: ellipsis;
            text-overflow: ellipsis;
        }
        .entry-title> a:hover{
            text-decoration: underline;
        }
        .entry-meta {
            color: #999;
            margin: 4px 0;
            font-size: 12px;
        }

        .menu {
            width: 100%;
            background: #3A3D28;
            height: 45px;
            padding: 10px 0;
        }
        .wrapper {
            position: relative;
            width: 1200px;
            margin: 0 auto;
            height: inherit;
        }
        .menu dl.menu-movie {
            width: 1200px;
            border-right: 1px solid #111;
        }
        .menu dl.menu-movie dt {
            background-position: 0 0;
        }
        .menu dl dt {
            /*background: url("http://www.chapaofan.com/res/images/bg-menu.png") no-repeat;*/
            width: 31px;
            height: 41px;
            float: left;
        }
        .menu dl dt a {
            width: 31px;
            height: 41px;
            display: inline-block;
        }
        dl>dd, ul>li {
            list-style: none;
        }
        dl>dd>a {
            color: #D0CCC8;
            padding-left: 30px;
        }
        dl>dd>a:hover{
            color: gray;
            text-decoration: underline;
        }
        /*底部*/
        .ali_group {
            overflow: hidden;
            border-bottom: 1px dashed #CCC;
        }
        .ali_group a {
            float: left;
            margin: 10px 0 10px -1px;
            padding: 0 10px;
            border-left: 1px solid #EEE;
            color: #999;
            text-decoration: none;
        }
        .ali_group a:hover{
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="gtco-loader"></div>

    <div id="page">

    <div class="page-inner">
        <jsp:include page="header.jsp"></jsp:include>

        <div style="width: 990px;padding-bottom: 20px;">
            <div style="float: left;width: 1200px;overflow: hidden;display: block;">
                <div class="menu">
                    <div class="wrapper clearfix">
                        <dl class="menu-movie">
                            <dd>
                                <a href="<%=basePath%>homepage/movies?type=99" title="奥斯卡迅雷下载--每周更新两部">奥斯卡</a>
                                <a href="<%=basePath%>homepage/movies?type=98" title="豆瓣9.0迅雷下载--不定期更新">豆瓣9.0</a>
                                <a href="<%=basePath%>homepage/movies?type=97" title="纪录片迅雷下载--不定期更新">纪录片</a>
                                <a href="<%=basePath%>homepage/movies?type=96" title="系列迅雷下载--不定期更新">系列</a>
                                <a href="<%=basePath%>homepage/movies?type=95" title="短片迅雷下载--不定期更新">短片</a>
                                <a href="<%=basePath%>homepage/movies?type=94" title="最新迅雷下载--不定期更新">最新</a>
                            </dd>
                        </dl>
                    </div>
                </div>

                <div id="breadcrumb" style="zoom:1;">
                    当前位置：WED > 电影
                    <c:if test="${not empty typeStr}">
                        > ${typeStr}
                    </c:if>
                </div>

                <div class="post-grid clearfix">
                    <!-- 数据展示-->
                    <c:if test="${empty results.list}">
                        <div id="post-97" class="post clearfix">
                            没有查到符合条件的记录，请换换别的关键词试试。
                        </div>
                    </c:if>

                    <c:forEach items="${results.list}" var="item">
                        <div id="post-97" class="post clearfix">
                            <a class="entry-thumb" href="<%=basePath%>homepage/movieId/${item.id}" title="${item.fullName}">
                                <img src="${item.pic}" height="130" width="100" >
                            </a>
                            <h3 class="entry-title">
                                <a href="<%=basePath%>homepage/movieId/${item.id}" style="color: #999;" title="${item.fullName}" target="_blank">${item.fullName}</a>
                            </h3>
                            <div class="entry-meta">
                                <span class="">最后更新：<fmt:formatDate value="${item.updatetime}" pattern="yyyy年MM月dd日"/></span>
                                <!-- <span>|</span>
                                <span><a href="#">2 条影评</a></span> -->
                            </div>
                            <div>
                                <p>${item.plot}</p>
                            </div>
                        </div>
                    </c:forEach>
                    <!--分页-->
                    <div id="kkpager"></div>
                </div>

            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </div>

</div>

<div class="gototop js-top">
    <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
</div>
<script type="text/javascript">
    var pageNo=${results.pageNum};
    var totalPage=${results.pages};
    var totalRecords=${results.total};
    //生成分页控件
    kkpager.generPageHtml({
        pno : pageNo,	//当前页数
        total : totalPage,  //总页数
        totalRecords : totalRecords,  //总数据条数
        mode : 'link', //可选，默认就是link
        hrefFormer : 'movies',//链接前部
        hrefLatter : '',//链接尾部
        //链接算法
        getLink : function(n){
            return this.hrefFormer + this.hrefLatter + "?pageNum="+n;
        }

    });
</script>
</body>
</html>
