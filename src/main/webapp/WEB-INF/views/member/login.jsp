<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Welcome to MayBy!!</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- ========================= CSS here ========================= -->
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/LineIcons.3.0.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/tiny-slider.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/glightbox.min.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/main.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/member/login.css">

</head>

<body>
<!--[if lte IE 9]>
<p class="browserupgrade">
    You are using an <strong>outdated</strong> browser. Please
    <a href="https://browsehappy.com/">upgrade your browser</a> to improve
    your experience and security.
</p>
<![endif]-->

<!-- Preloader -->
<div class="preloader">
    <div class="preloader-inner">
        <div class="preloader-icon">
            <span></span>
            <span></span>
        </div>
    </div>
</div>
<!-- /End Preloader -->

<!-- Start Header Area -->
<%@include file="../layout/header.jsp"%>
<!-- End Header Area -->

<!-- Start Breadcrumbs -->
<div class="breadcrumbs">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 col-md-6 col-12">
                <div class="breadcrumbs-content">
                    <h1 class="page-title">Login</h1>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-12">
                <ul class="breadcrumb-nav">
                    <li><a href="${contextPath}/index"><i class="lni lni-home"></i> Home</a></li>
                    <li>Login</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumbs -->

<!-- Start Account Login Area -->
<div class="account-login section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3 col-md-10 offset-md-1 col-12">
                <div class="card login-form">
                    <div class="card-body">
                        <div class="title">
                            <h3>Login Now</h3>
                            <p>You can login using your social media account or email address.</p>
                        </div>
                        <div class="social-login">
                            <div class="row">
                                <div class="col-lg-4 col-md-4 col-12"><a class="btn facebook-btn"
                                                                         href="javascript:void(0)"><i class="lni lni-facebook-filled"></i> Facebook
                                    login</a></div>
                                <div class="col-lg-4 col-md-4 col-12"><a class="btn twitter-btn"
                                                                         href="javascript:void(0)"><i class="lni lni-twitter-original"></i> Twitter
                                    login</a></div>
                                <div class="col-lg-4 col-md-4 col-12"><a class="btn google-btn"
                                                                         href="javascript:void(0)"><i class="lni lni-google"></i> Google login</a>
                                </div>
                            </div>
                        </div>
                        <div class="alt-option">
                            <span>Or</span>
                        </div>

                        <form id="loginForm" method="post">
                            <div class="form-group input-group">
                                <label for="m_email">Email</label>
                                <input class="form-control email_input" name="m_email" type="email" id="m_email">
                                <span class="final_mail_ck">이메일 형식을 지켜 필수적으로 입력하세요.</span>
                            </div>
                            <div class="form-group input-group">
                                <label for="m_pw">Password</label>
                                <input class="form-control pw_input" type="password" name="m_pw" id="m_pw">
                            </div>
                        </form>
                        <c:if test="${login_result == 'failed'}">
                            <div class="login_warn">
                                이메일 또는 비밀번호를 잘못 입력하셨습니다!
                            </div>
                        </c:if>
                        <div class="d-flex flex-wrap justify-content-between bottom-content">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input width-auto" id="exampleCheck1">
                                <label class="form-check-label">Remember me</label>
                            </div>
                            <a class="lost-pass" href="account-password-recovery.html">Forgot password?</a>
                        </div>
                        <div class="button">
                            <button class="btn login_button" type="button">Login</button>
                        </div>
                        <p class="outer-link">Don't have an account?
                            <a href="${contextPath }/member/register">Register here </a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Account Login Area -->

<!-- Start Footer Area -->
<%@include file="../layout/footer.jsp"%>
<!--/ End Footer Area -->

<!-- ========================= scroll-top ========================= -->
<a href="#" class="scroll-top">
    <i class="lni lni-chevron-up"></i>
</a>

<!-- ========================= JS here ========================= -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="${contextPath}/static/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/tiny-slider.js"></script>
<script src="${contextPath}/static/js/glightbox.min.js"></script>
<script src="${contextPath}/static/js/main.js"></script>
<script src="${contextPath}/static/js/member/login.js"></script>
</body>

</html>