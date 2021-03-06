<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>东家小站_新增talk</title>
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


                    <div class="col-md-12">
                        <div class="col-md-6 animate-box">
                            <h3>Add a talk</h3>
                            <form id="form1" enctype="multipart/form-data">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label class="sr-only" for="title">标题</label>
                                        <input type="text" id="title" name="title" class="form-control" placeholder="标题">
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label class="sr-only" for="content">内容</label>
                                        <textarea name="content" id="content" cols="30" rows="10" class="form-control" placeholder="内容"></textarea>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label class="sr-only" >类型</label>
                                        <input type="radio" name="type" value="99" checked> 随便说说
                                    </div>
                                </div>

                                <div class="form-group">
                                    <input type="submit" value="新 增" class="btn btn-primary" id="submitBtn">
                                </div>
                            </form>
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
<script type="application/javascript">
    $("#submitBtn").click(function () {

        $.ajax({
            url:'<%=basePath%>homepage/addTalk',
            type:'POST',
            cache:false,
            async:false,
            data: new FormData($('#form1')[0]),
            //dataType:'json',
            contentType: false,
            processData: false,

            success:function(data){
                var ajaxobj =eval("("+data+")");
                alert(ajaxobj.result);
            },
            error:function(xhr,textStatus){
                console.log('错误');
                console.log(xhr);
                console.log(textStatus);
            }
        });
    });
</script>
</body>
</html>
