<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in-CMS</title>
    <link rel="stylesheet" href="http://cdn.itlaoqi.com./resources/bootstrap4/css/bootstrap.css">
    <link rel="stylesheet" href="/lib/raty/lib/jquery.raty.css">

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

              </div>
              <div class="col-4 text-center">
                <b>
                    <a class="blog-header-logo text-dark" href="/management" style="width:400px">CSBOOKS Management System</a>
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
          <div class="container mt-auto p-2 m-0">
            <form id="frmLogin">
                
                <div class="passport bg-white">
                    <h4 class="float-left">Please sign in</h4>
<#--                    <h6 class="float-right pt-2"><a href="/register.html">Sign up</a></h6>-->
                    <div class="clearfix"></div>
                    <br>

                    <!-- the error msg -->
                    <div class="alert d-none mt-2" id="tips" role="alert">
    
                    </div>
                    
                    
                    <div class="input-group  mt-2 ">
                        <input type="text" id="username" name="username" class="form-control p-4" placeholder="Username"
                               aria-label="Username" aria-describedby="basic-addon1">
                    </div>
    
                    <div class="input-group  mt-4 ">
                        <input id="password" name="password" class="form-control p-4" placeholder="Password" type="password"
                               aria-describedby="basic-addon1">
                    </div>
    
                    <div class="input-group mt-4 ">
                        <div class="col-5 p-0">
                            <input type="text" id="verifyCode" name="vc" class="form-control p-4" placeholder="Code">
                        </div>
                        <div class="col-4 p-0 pl-2 pt-0">
                            <img id="imgVerifyCode" src="/verify_code"
                                 style="width: 120px;height:50px;cursor: pointer">
                        </div>
    
                    </div>
    
                    <a id="btnSubmit" class="btn btn-success  btn-block mt-4 text-white pt-3 pb-3">Log in</a>
                </div>
            </form>
    
            </div> 
    </div>
</body>
<script>
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
    function reloadVerifyCode(){
        // 设置属性，添加现在时间以更新验证码
        $("#imgVerifyCode").attr("src", "/verify_code?ts=" + new Date().getTime());
    }
    $("#imgVerifyCode").click(function () {
        reloadVerifyCode();
    });

    $("#btnSubmit").click(function () {
        var username = $.trim($("#username").val());
        var regex = /^.{1,10}$/;
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
        $.ajax({
            url: "/management/check_login",
            type: "post",
            dataType: "json",
            data: $("#frmLogin").serialize(),
            success: function (data) {
                console.info(data);
                if (data.code == "0") {
                    window.location = "/management/index.html?ts=" + new Date().getTime();
                } else {
                    showTips(true, "alert-danger", data.msg);
                    reloadVerifyCode();
                    $btnReg.html("Login");
                    $btnReg.removeAttr("disabled");
                }
            }
        });
        return false;
    });


</script>
</html>