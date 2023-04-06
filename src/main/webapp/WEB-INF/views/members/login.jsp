<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>

    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"></meta>
<meta id="_csrf_header" name="_csrf_header"
	content="${_csrf.headerName}"></meta>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet" type="text/css">

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Serif+JP:wght@200&display=swap')
	;

@font-face {
    font-family: 'EF_jejudoldam';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-EF@1.0/EF_jejudoldam.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'CWDangamAsac-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/CWDangamAsac-Bold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'RixYeoljeongdo_Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2102-01@1.0/RixYeoljeongdo_Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

/*  body {
	background-color: #888;
	background-image: linear-gradient(45deg, #444 25%, transparent 25%, transparent 75%, #444
		75%, #444),
		linear-gradient(45deg, #444 25%, transparent 25%, transparent 75%, #444
		75%, #444);
	background-position: 0 0, 25px 25px;
	background-size: 50px 50px;
}  */

  body {
	background-color: white;
	
	background-position: 0 0, 25px 25px;
	background-size: 50px 50px;
}  

.wrapper {
	text-align: center;
	margin: auto;
	margin-top: 15px;
	width: 420px;
	height: 530px;
	border: solid 3px;
	border-radius: 10%;
	background: rgba(222, 118, 119, 30);
}

h1 {
font-family: 'CWDangamAsac-Bold';
	margin-top: 120px;
	font-size: 60px;
}

input {
	text-align: left;
	width: 225px;
	height: 30px;
	border: none;
	border-bottom: solid 3px black;
	background: rgba(222, 118, 119, 30);
}

input::placeholder {
	color: black;
	font-size: 10px;
}

.idStyle {
	padding-top: 19px;
}

img {
	width: 250px;
	height: 250px;
}

.psStyle {
	padding-bottom: 32px;
	padding-top: 22px;
}

button {
	background-color:white;
 	position: relative;
    border: none;
    display: inline-block;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}

.gooButton {
	padding-left: 105px;
	padding-top: 20px;
}

.title{
	text-align: center;
}

</style>

</head>
<body>





	<img
		src="https://img.poipiku.com/user_img02/002382369/008490504_nWNItirig.png_640.jpg"
		>
		
	<input type="text" id="userId" placeholder="아이디" required autofocus>
	<input type="password" id="password" placeholder="비밀번호" required>
 	<div class="title">
	<h1>떠나자</h1>
	</div> 
	

	
	<div class="wrapper">

		

		<img src="${context}/resources/assets/img/campingImg.png">
		<div class="idStyle">
			<input type="text" id="userId" placeholder="아이디" required autofocus>
		</div>

		<div class="psStyle">
			<input type="password" id="password" placeholder="비밀번호" required>
		</div>


		<button class="btn btn-lg btn-primary btn-block btn-signin"
			type="button" id="btnClick">로그인</button>


	<script>
	 var token = $("meta[name='_csrf']").attr("content");
	 var header = $("meta[name='_csrf_header']").attr("content");
		function btnlogin() {

			$.ajax({
				url : "afterlogin",
				type : "post",
				data : {
					userId : userId.value,
					password : password.value

				},beforeSend : function(xhr){
					xhr.setRequestHeader(header, token);
				}

			});

		}
		
		 btnClick.addEventListener('click',e=>{
			 	btnlogin();
		        
		        });
		
		
	</script>

                <input type="text" id="inputId"  placeholder="아이디" required autofocus>
                <input type="password" id="inputPassword" placeholder="비밀번호" required>

pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <title>Login</title>
    <style>
    @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Noto+Serif+JP:wght@200&display=swap');
    	body, html {
    height: 80%;
    background-repeat: no-repeat;
    background-image: url(https://images.unsplash.com/photo-1504280390367-361c6d9f38f4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80);
	background-size : cover;
	opacity : 0.9;
}

.card-container.card {
    max-width: 350px;
    padding: 40px 40px;
}

.btn {
    font-weight: 700;
    height: 36px;
    -moz-user-select: none;
    -webkit-user-select: none;
    user-select: none;
    cursor: default;
}

/*
 * Card component
 */
.card {
    background-color: #F7F7F7;
    /* just in case there no content*/
    padding: 20px 25px 30px;
    position : absolute;
    left : 50%;
    top : 50%;
    margin-left : -150px;
    margin-top: -150px;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

}

.profile-img-card {
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    display: block;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
}

/*
 * Form styles
 */
.profile-name-card {
    font-size: 16px;
    font-weight: bold;
    text-align: center;
    margin: 10px 0 0;
    min-height: 1em;
}

.reauth-email {
    display: block;
    color: #404040;
    line-height: 2;
    margin-bottom: 10px;
    font-size: 14px;
    text-align: center;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

.form-signin #inputEmail,
.form-signin #inputPassword {
    direction: ltr;
    height: 44px;
    font-size: 16px;
}

.form-signin input[type=email],
.form-signin input[type=password],
.form-signin input[type=text],
.form-signin button {
    width: 100%;
    display: block;
    margin-bottom: 10px;
    z-index: 1;
    position: relative;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    font-family: 'Nanum Gothic Coding', monospace;
	
    
}

.form-signin .form-control:focus {
    border-color: rgb(104, 145, 162);
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
}

.btn.btn-signin {
    /*background-color: #4d90fe; */
    background-color: rgb(104, 145, 162);
    /* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
    padding: 0px;
    font-weight: 700;
    font-size: 14px;
    height: 36px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    border: none;
    -o-transition: all 0.218s;
    -moz-transition: all 0.218s;
    -webkit-transition: all 0.218s;
    transition: all 0.218s;
}

.btn.btn-signin:hover,
.btn.btn-signin:active,
.btn.btn-signin:focus {
    background-color: rgb(12, 97, 33);
}

.forgot-password {
    color: rgb(104, 145, 162);
}

.forgot-password:hover,
.forgot-password:active,
.forgot-password:focus{
    color: rgb(12, 97, 33);
}
	
    </style>
</head>
<body>
        <div class="container">
        <div class="card card-container">
            <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>

            
            <form class="form-signin" action="/members/login"  method="POST" >

            <form class="form-signin">

                <span id="reauth-email" class="reauth-email"></span>
                <input type="email" id="inputEmail" class="form-control" placeholder="아이디" required autofocus>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" required>

                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> 아이디 기억하기
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">로그인</button>

            </form>
           



            </form><!-- /form -->
            <a href="#" class="forgot-password">
                비밀번호를 잊으셨나요?
            </a>
        </div><!-- /card-container -->
    </div><!-- /container -->
       
    <script>
    $( document ).ready(function() {
        // DOM ready

        // Test data
        /*
         * To test the script you should discomment the function
         * testLocalStorageData and refresh the page. The function
         * will load some test data and the loadProfile
         * will do the changes in the UI
         */
        // testLocalStorageData();
        // Load profile if it exits
        loadProfile();
    });

    /**
     * Function that gets the data of the profile in case
     * thar it has already saved in localstorage. Only the
     * UI will be update in case that all data is available
     *
     * A not existing key in localstorage return null
     *
     */
    function getLocalProfile(callback){
        var profileImgSrc      = localStorage.getItem("PROFILE_IMG_SRC");
        var profileName        = localStorage.getItem("PROFILE_NAME");
        var profileReAuthEmail = localStorage.getItem("PROFILE_REAUTH_EMAIL");

        if(profileName !== null
                && profileReAuthEmail !== null
                && profileImgSrc !== null) {
            callback(profileImgSrc, profileName, profileReAuthEmail);
        }
    }

    /**
     * Main function that load the profile if exists
     * in localstorage
     */
    function loadProfile() {
        if(!supportsHTML5Storage()) { return false; }
        // we have to provide to the callback the basic
        // information to set the profile
        getLocalProfile(function(profileImgSrc, profileName, profileReAuthEmail) {
            //changes in the UI
            $("#profile-img").attr("src",profileImgSrc);
            $("#profile-name").html(profileName);
            $("#reauth-email").html(profileReAuthEmail);
            $("#inputEmail").hide();
            $("#remember").hide();
        });
    }

    /**
     * function that checks if the browser supports HTML5
     * local storage
     *
     * @returns {boolean}
     */
    function supportsHTML5Storage() {
        try {
            return 'localStorage' in window && window['localStorage'] !== null;
        } catch (e) {
            return false;
        }
    }

    /**
     * Test data. This data will be safe by the web app
     * in the first successful login of a auth user.
     * To Test the scripts, delete the localstorage data
     * and comment this call.
     *
     * @returns {boolean}
     */
    function testLocalStorageData() {
        if(!supportsHTML5Storage()) { return false; }
        localStorage.setItem("PROFILE_IMG_SRC", "//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" );
        localStorage.setItem("PROFILE_NAME", "César Izquierdo Tello");
        localStorage.setItem("PROFILE_REAUTH_EMAIL", "oneaccount@gmail.com");
    }
    </script>    

            </form>
           

	


		<button class="btn btn-lg btn-primary btn-block btn-signin"
			type="button" id="btnSign">회원가입</button>

		<div class="gooButton">
			<div id="g_id_onload"
				data-client_id="689885454668-12d8lthjkbrfrjae7plunhqc12ctegfv.apps.googleusercontent.com"
				data-callback="handleCredentialResponse"></div>
			<div class="g_id_signin"  data-shape="rectangular" data-width="215"
				data-height="180"></div>
		</div>

	</div>

</body>


<script src="https://accounts.google.com/gsi/client" async defer></script>
<script defer src="${context}/resources/assets/js/member/login.js"></script>
</html>