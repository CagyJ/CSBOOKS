<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CS BOOKS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./lib/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="./lib/raty/lib/jquery.raty.css">
    <script src="./lib/jquery.3.3.1.min.js"></script>
    <script src="./lib/bootstrap/bootstrap.min.js"></script>
    <script src="./lib/art-template.js"></script>
	<script src="./lib/raty/lib/jquery.raty.js"></script>

    <style>
        .highlight {
            color: red !important;
        }
        a:active{
            text-decoration: none!important;
        }
    </style>
    <style>
        .container {
            padding: 20px;
            margin: auto;
        }

        .row {
            padding: 0px;
            margin: 0px;
        }

        .col- * {
            padding: 0px;
        }

        .blog-header {
            background: lightgray;
        }
        .blog-header-logo {
            font-size: x-large;
        }

        .card-body .text-dark {
            font-size : large;
        }
    </style>
    <script type="text/html" id="tpl">
        <a href="/book/{{bookId}}" style="color: inherit">
            <div class="row mt-2 book">
                <div class="col-md-6">
                    <div class="card flex-md-row mb-4 box-shadow h-md-250">
                    <div class="card-body d-flex flex-column align-items-start">
                        <strong class="d-inline-block mb-2 text-primary">Book No.{{bookId}}</strong>
                        <h3 class="mb-0">
                        <a class="text-dark" href="/book/{{bookId}}">{{bookName}}</a>
                        </h3>
                        <h5 class="mb-2" style="background: gray;font-size:medium;color: white;">
                            {{subTitle}}
                        </h5>
                        <p class="card-text mb-auto">
                            <p>
                                <span class="stars" data-score="{{evaluationScore}}" title="gorgeous">
                                <#--星级图片-->
                                </span>
                                <span class="mt-2 ml-2">{{evaluationScore}}</span>
                                <span class="mt-2 ml-2">{{evaluationQuantity}}人已评</span>
                            </p>
                        </p>
                    </div>
                    <img class="card-img-right flex-auto d-md-block" style="width: 200px; height: 250px;" src={{cover}} readonly="true">
                    </div>
                </div>
            </div>
        </a>

        <hr>
    </script>
    <script>
        // 指明raty路径
        $.fn.raty.defaults.path ="./lib/raty/lib/images";



        // ajax动态加载图书内容(已封装至loadMore函数中)
        $(function(){
            // $.ajax({
            //     url : "/books" ,
            //     data : {p:1},
            //     type : "get" ,
            //     dataType : "json" , // 服务器返回的数据类型
            //     success : function(json){
            //         console.info(json);
            //         var list = json.records;  // 对应分页数据
            //         for(var i = 0 ; i < list.length ; i++){
            //             var book = json.records[i];
            //             // var html = "<li>" + book.bookName + "</li>";
            //             //将数据结合tpl模板,生成html
            //             var html = template("tpl" , book);
            //             // console.info(html);
            //             $("#bookList").append(html); // 将html添加至对应div
            //         }
            //         //显示星型评价组件
            //         $(".stars").raty({readOnly:true});
            //     }
            // })
            loadMore(true);
        })

        //loadMore()加载更多数据
        //isReset参数设置为true,代表从第一页开始查询,否则按nextPage查询后续页
        function loadMore(isReset){
            if(isReset == true){
                $("#bookList").html("");
                $("#nextPage").val(1);
            }
            var nextPage = $("#nextPage").val();
            var categoryId= $("#categoryId").val();
            var order = $("#order").val();

            $.ajax({
                url : "/books" ,
                data : {p:nextPage,"categoryId":categoryId , "order":order},
                type : "get" ,
                dataType : "json" ,
                success : function(json){
                    console.info(json);
                    var list = json.records;
                    for(var i = 0 ; i < list.length ; i++){
                        var book = json.records[i];
                        // var html = "<li>" + book.bookName + "</li>";
                        //将数据结合tpl模板,生成html
                        var html = template("tpl" , book);  // 绑定HTML代码块，JS模板引擎
                        console.info(html);
                        $("#bookList").append(html);
                    }
                    //显示星型评价组件
                    $(".stars").raty({readOnly:true});

                    //判断是否到最后一页
                    if(json.current < json.pages){
                        $("#nextPage").val(parseInt(json.current) + 1);
                        $("#btnMore").show();
                        $("#divNoMore").hide();
                    }else{
                        $("#btnMore").hide();
                        $("#divNoMore").show();
                    }
                }
            })
        }

        //绑定加载更多按钮单击事件
        $(function(){
            $("#btnMore").click(function(){
                loadMore();
            })

            $(".category").click(function () {
                $(".category").removeClass("highlight");
                $(".category").addClass("text-black-50");
                $(this).addClass("highlight");
                var categoryId = $(this).data("category");
                $("#categoryId").val(categoryId);
                loadMore(true);
            })

            $(".order").click(function(){
                $(".order").removeClass("highlight");
                $(".order").addClass("text-black-50");
                $(this).addClass("highlight");
                var order = $(this).data("order");
                $("#order").val(order);
                loadMore(true);
            })
        })
    </script>
</head>
<body>
    <div class="container">

        <header class="blog-header py-3">
            <div class="row flex-nowrap justify-content-between align-items-center">
              <div class="col-4 pt-1">
                <a class="text-muted" href="https://github.com/CagyJ">Follow me</a>
              </div>
              <div class="col-4 text-center">
                <b>
                    <a class="blog-header-logo text-dark" href="#">CS必读书籍📕📗📘📒</a>
                </b>
              </div>
              <div class="col-4 d-flex justify-content-end align-items-center">

                <#if loginMember??>
                    <h6 class="mt-1">
                        <img style="width: 2rem;margin-top: -5px" class="mr-1" src="/imgs/profile.png">${loginMember.nickname}
                    </h6>
                <#else>
                    <a class="btn btn-sm btn-outline-secondary" href="/register.html">Sign up</a>
                    &nbsp;&nbsp;
                    <a class="btn btn-sm btn-outline-secondary" href="/login.html">Sign in</a>
                </#if>
              </div>
            </div>
          </header>


          <div class="row mt-2">
    
            <div class="col-8 mt-2">
                    <span data-category="-1" style="cursor: pointer" class="highlight  font-weight-bold category">ALL</span>
                    |
<#--                    遍历数据-->
                    <#list categoryList as category>
                    <a style="cursor: pointer" data-category="${category.categoryId}" class="text-black-50 font-weight-bold category">${category.categoryName}</a>
                    <#if category_has_next>|</#if>
                    </#list>
                        
    
            </div>
    
            <div class="col-8 mt-2">
                <span data-order="quantity" style="cursor: pointer" class="order highlight  font-weight-bold mr-3">HOT🧨</span>
    
                <span data-order="score" style="cursor: pointer" class="order text-black-50 mr-3 font-weight-bold">Rating✨</span>
            </div>
        </div>


        <div class="d-none">
            <input type="hidden" id="nextPage" value="2">
            <input type="hidden" id="categoryId" value="-1">
            <input type="hidden" id="order" value="quantity">
        </div>
        <br>
        

        <div id="bookList">
            <!-- book list test data -->
            
        <!-- <div class="row mb-2">

        <div class="col-md-6">
            <div class="card flex-md-row mb-4 box-shadow h-md-250">
            <div class="card-body d-flex flex-column align-items-start">
                <strong class="d-inline-block mb-2 text-primary">World</strong>
                <h3 class="mb-0">
                <a class="text-dark" href="https://getbootstrap.com/docs/4.0/examples/blog/#">Featured post</a>
                </h3>
                <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
            </div>
            <img class="card-img-right flex-auto d-none d-md-block" data-src="holder.js/200x250?theme=thumb" alt="Thumbnail [200x250]" style="width: 200px; height: 250px;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22200%22%20height%3D%22250%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20200%20250%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_179bdefe6eb%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A13pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_179bdefe6eb%22%3E%3Crect%20width%3D%22200%22%20height%3D%22250%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2256.1953125%22%20y%3D%22131%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">
            </div>
        </div>
        
        </div> -->


    
        </div>


        <button type="button" id="btnMore" data-next-page="1" class="mb-5 btn btn-outline-primary btn-lg btn-block">
            Load more...
        </button>
        <div id="divNoMore" class="text-center text-black-50 mb-5" style="display: none;">已经到底了~</div>
    </div>
    
    </body>
</html>