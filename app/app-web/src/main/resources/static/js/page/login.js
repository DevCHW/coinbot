$(document).ready(function(){
    // 아이디에서 엔터입력이벤트
    $("input#userId").keydown(function(e){	//아이디에서 값 입력시 이벤트
        if(e.keyCode == 13){	//엔터를 했을 경우
            $("button#btn_login").trigger("click");
        }
    });

    // 패스워드에서 엔터입력이벤트
    $("input#password").keydown(function(e){
        if(e.keyCode == 13){	//엔터를 했을 경우
            $("button#btn_login").trigger("click");
        }
    });

    // 회원가입버튼클릭시 회원가입 페이지로 이동
    $("span#btn_signup").click(()=>{
        alert("This page is under development. We apologize for any inconvenience.");
    });

    // 로그인버튼 클릭시 이벤트
    $("button#btn_login").click(function(){
        const userId = $("input#userId").val().trim();
        const password = $("input#password").val().trim();
        if(userId == ""){	//아이디 값이 비어있다면
            $("span#password_error").css("display","none");
            $("span#userId_error").text("Enter your email");
            $("span#userId_error").css("display","block");

            if (password != "") {
                $("span#passwd_error").text("");	//비밀번호 에러 지우기
                $("span#passwd_error").css("display","none");
            }
            $("input#userId").focus();
            return;
        } else if(password == ""){	//비밀번호 값이 비어있다면
            $("span#userId_error").css("display","none");
            $("span#passwd_error").text("Enter your password");
            $("span#passwd_error").css("display","block");

            if (userId != "") {
                $("span#userId_error").text("");	//아이디 에러 지우기
                $("span#userId_error").css("display","none");
            }
            $("input#password").focus();
            return;
        } else {	//아이디도 입력하고 비밀번호도 입력했다면
            $("span#userId_error").text("");	//아이디 에러 지우기
            $("span#passwd_error").text("");	//비밀번호 에러 지우기
            $("span#userId_error").css("display","none");
            $("span#passwd_error").css("display","none");
            const form = document.loginForm;
            form.method="POST";
            form.action= "/login"
            form.submit();
        }
    });
});//end of $(document).ready(function(){})