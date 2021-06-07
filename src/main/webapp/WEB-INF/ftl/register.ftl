<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up-CSBOOK</title>
    <link rel="stylesheet" href="http://cdn.itlaoqi.com./resources/bootstrap4/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/raty/lib/jquery.raty.css">

    <script src="http://cdn.itlaoqi.com./resources/jquery.3.3.1.min.js"></script>
    <script src="http://cdn.itlaoqi.com./resources/bootstrap4/js/bootstrap.min.js"></script>

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
        .blog-header-logo {
            font-size: x-large;
        }

        #frmLogin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .description p {
            text-indent: 2em;
        }

        .description img {
            width: 100%;
        }

        .alert {
            font-size: small;
        }
    </style>
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
                    <a class="blog-header-logo text-dark" href="./index.html">CS必读书籍📕📗📘📒</a>
                </b>
              </div>
              <div class="col-4 d-flex justify-content-end align-items-center">
                <!-- <a class="btn btn-sm btn-outline-secondary" href="./register.html">Sign up</a>
                &nbsp;&nbsp;
                <a class="btn btn-sm btn-outline-secondary" href="./login.html">Sign in</a> -->
              </div>
            </div>
          </header>

          <br><br>

          <div class="container mt-2 p-2 m-0">
            <form id="frmLogin">
                <div class="passport bg-white">
                    <h4 class="float-left">Register</h4>
                    <h6 class="float-right pt-2"><a href="./login.html">Login</a></h6>
                    <div class="clearfix"></div>
                    <br>

                    <!-- the error msg -->
                    <div class="alert d-none mt-2" id="tips" role="alert">
    
                    </div>
    
                    <div class="input-group  mt-2 ">
                        <input type="text" id="username" name="username" class="form-control p-4" placeholder="Username">
                    </div>
    
                    <div class="input-group  mt-4 ">
                        <input id="password" name="password" class="form-control p-4" placeholder="Password" type="password">
                    </div>
    
                    <div class="input-group  mt-4 ">
                        <input type="text" id="nickname" name="nickname" class="form-control p-4" placeholder="Name to display"
                        >
                    </div>
    
                    <div class="input-group mt-4 ">
                        <div class="col-5 p-0">
                            <input type="text" id="verifyCode" name="vc" class="form-control p-4" placeholder="Code">
                        </div>
                        <div class="col-4 p-0 pl-2 pt-0">
                            <!-- 验证码图片 -->
                            <img id="imgVerifyCode" src="/verify_code"
                                 style="width: 120px;height:50px;cursor: pointer">
                        </div>
    
                    </div>
    
                    <a id="btnSubmit" class="btn btn-success  btn-block mt-4 text-white pt-3 pb-3">Sign up</a>
                </div>
            </form>
    
        </div>
    </div>
    
    
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    Successfully! You've registered.
                </div>
                <div class="modal-footer">
                    <a href="./login.html" type="button" class="btn btn-primary">Go to login</a>
                </div>
            </div>
        </div>
    </div>
    
          
    </div>  
</body>
<script>
	//控制错误信息的显示与隐藏
    function showTips(isShow, css, text) {
        if (isShow) {
            $("#tips").removeClass("d-none")
            $("#tips").hide();
            $("#tips").addClass(css);
            $("#tips").text(text);
            $("#tips").fadeIn(200);
        } else {
            $("#tips").text("");
            $("#tips").fadeOut(200);
            $("#tips").removeClass();
            $("#tips").addClass("alert")
        }
    }
	//重新发送请求,刷新验证码
    function reloadVerifyCode(){
        //请在这里实现刷新验证码
        $("#imgVerifyCode").attr("src","/verify_code?ts=" + new Date().getTime());
    }
	
	//点击验证码图片刷新验证码
    $("#imgVerifyCode").click(function () {
        reloadVerifyCode();
    });
	
	//点击提交按钮,向/registe发起ajax请求
	//提交请求包含四个参数
	//vc:前台输入验证码  username:用户名 password:密码 nickname:昵称
    $("#btnSubmit").click(function () {
		//表单校验
        var username = $.trim($("#username").val());
        var regex = /^.{6,10}$/;
        if (!regex.test(username)) {
            showTips(true, "alert-danger", "The username should be 6-10 characters, pls check!");
            return;
        } else {
            showTips(false);
        }

        var password = $.trim($("#password").val());

        if (!regex.test(password)) {
            showTips(true, "alert-danger", "The password should be 6-10 characters, pls check!");
            return;
        } else {
            showTips(false);
        }

        $btnReg = $(this);

        $btnReg.text("Processing...");
        $btnReg.attr("disabled", "disabled");
		
		//发送ajax请求
        $.ajax({
            url: "/register",
            type: "post",
            dataType: "json",
            data: $("#frmLogin").serialize(),
            success: function (data) {
				//结果处理,根据服务器返回code判断服务器处理状态
				//服务器要求返回JSON格式:
				//{"code":"0","msg":"处理消息"}
                console.info("Server response:" , data);
                if (data.code == "0") {
					//显示注册成功对话框
                    $("#exampleModalCenter").modal({});
                    $("#exampleModalCenter").modal("show");
                } else {
					//服务器校验异常,提示错误信息
                    showTips(true, "alert-danger", data.msg);
                    reloadVerifyCode();
                    $btnReg.text("Sign up");
                    $btnReg.removeAttr("disabled");
                }
            }
        });
        return false;
    });


</script>
</html>