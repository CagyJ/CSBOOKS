
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${book.bookName}</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="/lib/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="/lib/raty/lib/jquery.raty.css">
    <script src="/lib/jquery.3.3.1.min.js"></script>
    <script src="/lib/bootstrap/bootstrap.min.js"></script>
    <script src="/lib/art-template.js"></script>
    <script src="/lib/raty/lib/jquery.raty.js"></script>
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
    <script>
        $.fn.raty.defaults.path = '/lib/raty/lib/images';
        $(function () {
            $(".stars").raty({readOnly: true});
        })

        $(function () {
            // 查询是否存在阅读状态
            <#if memberReadState ??>
            //重选阅读状态
            $("*[data-read-state='${memberReadState.readState}']").addClass("disabled");
            </#if>

            // 如果没有登录点击想看/看过或写评论/点赞，触发登录操作
            <#if !loginMember ??>
            $("*[data-read-state],#btnEvaluation,*[data-evaluation-id]").click(function(){
                //未登录情况下提示"需要登录"，modal是对话框
                $("#exampleModalCenter").modal("show");
            })
            </#if>

            <#if loginMember ??>
            /**
             * 更新会员阅读状态
             */
            $("*[data-read-state]").click(function () {
                //会员阅读状态
                var readState = $(this).data("read-state");
                //发送post请求
                $.post("/update_read_state", {
                    memberId:${loginMember.memberId},
                    bookId:${book.bookId},
                    readState: readState
                }, function (json) {
                    if (json.code === "0") {
                        $("*[data-read-state]").removeClass("disabled");
                        $("*[data-read-state='" + readState + "']").addClass("disabled");
                    }
                }, "json")
            });

            // 评论
            $("#btnEvaluation").click(function(){
                $("#score").raty({});//转换为星型组件
                $("#dlgEvaluation").modal("show");//显示短评对话框
            })

            //评论对话框提交数据
            $("#btnSubmit").click(function(){
                var score = $("#score").raty("score");//获取评分
                var content = $("#content").val();
                if(score == 0 || $.trim(content) == "") {
                    return;
                }
                $.post("/evaluate" , {
                    score : score,
                    bookId : ${book.bookId},
                    memberId : ${loginMember.memberId},
                    content : content
                },function(json){
                    if(json.code == "0"){
                        window.location.reload();//刷新当前页面
                    }
                },"json")
            })

            //评论点赞
            $("*[data-evaluation-id]").click(function(){
                var evaluationId = $(this).data("evaluation-id");
                $.post("/enjoy",{
                    evaluationId : evaluationId
                },function(json){
                    if(json.code == "0"){
                        $("*[data-evaluation-id='" + evaluationId + "'] span").text(json.evaluation.enjoy);
                    }
                },"json")
            })
            </#if>
        })
    </script>
</head>
<body>
<!--<div style="width: 375px;margin-left: auto;margin-right: auto;">-->
<div class="container ">
    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <a class="text-muted" href="https://github.com/CagyJ">Follow me</a>
            </div>
            <div class="col-4 text-center">
                <b>
                    <a class="blog-header-logo text-dark" href="/">CS必读书籍📕📗📘📒</a>
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


    <div class="container mt-2 p-2 m-0" style="background-color:rgb(156, 136, 95)">
        <div class="row">
            <div class="col-4 mb-2 pl-0 pr-0">
                <img style="width: 240px;height: 320px"
                     src="${book.cover}">
            </div>
            <div class="col-8 pt-2 mb-2 pl-0">
                <h6 class="text-white" style="font-size: xx-large;">${book.bookName}</h6>
                <div class="p-1 alert alert-warning small" role="alert">
                    ${book.subTitle}
                </div>
                <p class="mb-1">
                    <span class="text-white-50 medium">${book.author}</span>
                </p>
                <br>
                <div class="row pl-0 pr-2" style="width:200px;">
                    <div class="col-6 p-1">
                        <button type="button" data-read-state="1" class="btn btn-light btn-sm w-100">
                            <img style="width: 1rem;" class="mr-1"
                                 src="https://img3.doubanio.com/f/talion/cf2ab22e9cbc28a2c43de53e39fce7fbc93131d1/pics/card/ic_mark_todo_s.png"/>想看
                        </button>
                    </div>
                    <div class="col-6 p-1">
                        <button type="button" data-read-state="2" class="btn btn-light btn-sm  w-100">
                            <img style="width: 1rem;" class="mr-1"
                                 src="https://img3.doubanio.com/f/talion/78fc5f5f93ba22451fd7ab36836006cb9cc476ea/pics/card/ic_mark_done_s.png"/>看过
                        </button>
                    </div>
                </div>

            </div>
        </div>
        <div class="row" style="background-color: rgba(0,0,0,0.1);">
            <div class="col-2"><h2 class="text-white">${book.evaluationScore}</h2></div>
            <div class="col-3 pt-2">
                <span class="stars" data-score="${book.evaluationScore}"></span>
            </div>
            <div class="col-5  pt-2"><h6 class="text-white">${book.evaluationQuantity}人已评</h6></div>
        </div>
    </div>
    <div class="row p-2 description">
        ${book.description}
    </div>



    <div class="alert alert-primary w-100 mt-2" role="alert">评论
        <button type="button" id="btnEvaluation" class="btn btn-success btn-sm text-white float-right"
                style="margin-top: -3px;">
            写评论
        </button>
    </div>
    <div class="reply pl-2 pr-2">
        <!-- 评价列表 -->
        <#list evaluationList as evaluation>
        <div>
            <div>

                <span class="mr-2 small pt-1"><b>${evaluation.member.nickname}</b></span>
                <span class="stars mr-2" data-score="${evaluation.score}"></span>
                <span class="pt-1 small text-black-50 mr-2">${evaluation.createTime?string('MM-dd')}</span>

                <button type="button" data-evaluation-id="${evaluation.evaluationId}"
                        class="btn btn-success btn-sm text-white float-right" style="margin-top: -3px;">
                    <img style="width: 24px;margin-top: -5px;" class="mr-1"
                         src="https://img3.doubanio.com/f/talion/7a0756b3b6e67b59ea88653bc0cfa14f61ff219d/pics/card/ic_like_gray.svg"/>
                    <span>${evaluation.enjoy}</span>
                </button>
            </div>

            <div class="row mt-2 small mb-3">
                ${evaluation.content}
            </div>
            <hr/>
        </div>
        </#list>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                您需要登录才可以操作哦~
            </div>
            <div class="modal-footer">
                <a href="/login.html" type="button" class="btn btn-primary">去登录</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="dlgEvaluation" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <h6>为${book.bookName}写评论</h6>
                <form id="frmEvaluation">
                    <div class="input-group  mt-2 ">
                        <span id="score"></span>
                    </div>
                    <div class="input-group mt-2">
                        <input type="text" id="content" name="content" class="form-control p-4" placeholder="Write down here">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="btnSubmit" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>