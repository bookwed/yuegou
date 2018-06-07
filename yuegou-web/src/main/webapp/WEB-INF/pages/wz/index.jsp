<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>东家小站_电影下载_电视剧下载_kindle电子书下载</title>
    <meta name="keywords" content="奥斯卡电影,经典电影,纪录片,kindle电子书,电影,东家小站,电影天堂,BT下载" />
    <meta name="description" content="东家小站为您提供最优质的高清电影BT种子下载、电视剧BT种子下载以及适合Kindle阅读的电子书下载，欢迎您来体验。" />
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

        <div class="gtco-section">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                        <h2 style="color: gray;font-family: 'Hiragino Sans GB','Microsoft Yahei',SimHei,SimSun ">看电影</h2>
                        <p>Life is short, take the time to see the best, it will be fun.</p>
                    </div>
                </div>
                <div class="row">
                    <!-- 此处可以显示3个，或6个-->
                    <c:forEach items="${results}" var="item">
                        <div class="col-lg-4 col-md-4 col-sm-6">
                            <a href="${item.pic}" class="fh5co-project-item image-popup">
                                <figure>
                                    <div class="overlay"><i class="ti-plus"></i></div>
                                    <img src="${item.pic}" alt="Image" class="img-responsive">
                                </figure>
                            </a>
                            <div class="fh5co-text">
                                <h2 style="color: #f54c53;font-family: 'Hiragino Sans GB','Microsoft Yahei',SimHei,SimSun; font-size: 18px;font-weight: 300;margin: 0 0 10px 0;">
                                    <center><a href="<%=basePath%>/homepage/movieId/${item.id}" target="_blank"><b>${item.shortName}</b></a></center>
                                </h2>
                                <p style="font-size: 14px; font-family: 'Hiragino Sans GB','Microsoft Yahei',SimHei,SimSun "><a href="<%=basePath%>/homepage/movieId/${item.id}" target="_blank" style="color: #777;">${item.plot}</a></p>
                            </div>
                        </div>
                    </c:forEach>
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
