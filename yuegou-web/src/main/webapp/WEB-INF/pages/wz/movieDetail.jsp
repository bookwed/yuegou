<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>电影详情页</title>
    <%@include file="resources.jsp" %>
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
            background: #ededed url(images/search-icon.png) no-repeat 9px center;
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
        .entry {
            width: 900px;
            margin-left: 15px;
            overflow: hidden;
        }
        .entry h1, .entry-attachment h1 {
            display: block;
            font-size: 16px;
            color: #ff7b00;
            padding: 2px 0 5px;
            border-bottom: 2px solid #ddd;
            clear: both;
        }
        .entry-meta {
            color: #999;
            margin: 12px 0;
            font-size: 12px;
        }
        .clearfix {
            zoom: 1;
        }
        .meta-date, .meta-sep, .meta-view, .meta-comments, .edit-link {
            margin-top: 5px;
        }
        .entry-content {
            font-size: 14px;
        }
        .entry-content .poster {
            float: left;
        }
        .entry-content #poster_src {
            float: left;
            margin-right: 12px;
            max-width: 150px;
            overflow: hidden;
            text-align: center;
        }
        .entry-content #movie_info {
            color: #666;
            float: left;
            width: 780px;
            height: 200px;
            word-wrap: break-word;
            overflow:auto;
            margin-top: -6px;
        }
        .entry-content h3 {
            margin: 10px 0;
            border-left: 2px solid #ff7d00;
            padding: 2px 0 2px 10px;
            color: #ff7d00;
            clear: both;
            font-size: 16px;
            font-family: Lantinghei SC,Open Sans,Arial,Hiragino Sans GB,Microsoft YaHei,\\5FAE\8F6F\96C5\9ED1,STHeiti,WenQuanYi Micro Hei,SimSun,sans-serif;
        }
        .entry-content #movie_description {
            text-indent: 2em;
            margin-bottom: 5px;
            height: 82px;
            overflow: hidden;
        }
        #zdownload {
            position: relative;
            background: #efefef;
            border-radius: 8px;
            margin-bottom: 10px;
            display: block;
            height: 40px;
            line-height: 40px;
            font-size: 14px;
        }
        #zdownload a {
            color: #37a;
            display: inline-block;
            padding: 0 10px 0 0;
            text-decoration: none;
            word-break: keep-all;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            width: 98.5%;
        }
        a.download-link {
            text-decoration: underline;
            color: #ff7d00;
        }
        #comments {
            width: 100%;
            line-height: 160%;
            clear: both;
        }
        .download-list {
            background: #000;
            padding: 15px;
        }
        /* abbr, address, article, aside, audio, b, blockquote, body, canvas, caption, cite, code, dd, del, details, dfn, div, dl, dt, em, fieldset, figcaption, figure, footer, form, h1, h2, h3, h4, h5, h6, header, hgroup, html, i, iframe, img, ins, kbd, label, legend, li, mark, menu, nav, object, ol, p, pre, q, samp, section, small, span, strong, sub, summary, sup, table, tbody, td, tfoot, th, thead, time, tr, ul, var, video {
            margin: 0;
            padding: 0;
            border: 0;
            outline: 0;
            font-size: 100%;
            vertical-align: baseline;
            background: 0 0;
            -webkit-font-smoothing: antialiased;
        } */
        .download-list li {
            height: 35px;
            line-height: 35px;
            border-bottom: 1px solid #000;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            background: #111;
            padding: 0 15px;
        }
        dl>dd, ul>li {
            list-style: none;
        }
        .download-list li em {
            background: url("http://www.chapaofan.com/res/images/icon-arrow.png") no-repeat;
            width: 5px;
            height: 5px;
            float: left;
            display: none;
            margin: 15px 0 0 0;
        }
        .download-list li a {
            font-size: 12px;
            color: #666;
            transition: .2s;
            -moz-transition: .2s;
            -webkit-transition: .2s;
            -o-transition: .2s;
        }
        .linketext {
            color: #fff;
        }
        .download-list a:hover{
            text-decoration: underline;
            color: #FDD035;
        }
        ul,ol{
            margin-bottom: 0px;
            margin-left: -35px;
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
                    <div id="breadcrumb" style="zoom:1;">
                        当前位置：WED > 电影 > 纪录片 > 具体影片名
                    </div>

                    <div id="detail-001" class="entry" style="padding-bottom: 10px;">
                        <h1>${result.fullName}</h1>
                        <div class="entry-meta clearfix">
                            <span class="meta-date">发布时间：<fmt:formatDate value="${result.publishtime}" pattern="yyyy-MM-dd"/></span>
                            <span class="meta-sep">&nbsp;|&nbsp;</span>
                            <span class="meta-date">更新时间：<fmt:formatDate value="${result.updatetime}" pattern="yyyy-MM-dd"/></span>
                            <span class="meta-sep">&nbsp;|&nbsp;</span>
                            <span class="meta-comments">
                        <a href="${result.douban}" target="_blank">豆瓣影评</a></span>
                        </div>
                        <div class="entry-content clearfix">
                            <div class="poster">
                                <div id="poster_src">
                                    <img src="${result.pic}" height="150" width="100">
                                </div>
                                <div id="movie_info">
                                    导演：${result.director}<br>
                                    编剧：${result.screenwriter}<br>
                                    主演：${result.mainactor}<br>
                                    剧情介绍：${result.plot}
                                </div>
                            </div>

                            <h3>下载地址</h3>
                            <div class="download-list">
                                <ul>
                                    <c:forEach items="${downloadList}" var="item" >
                                        <li>
                                            <em></em>
                                            <a href="${item.downloadAddress}">
                                                    ${item.downloadName}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>

    </div>

    <div class="gototop js-top">
        <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
    </div>
</body>
</html>
