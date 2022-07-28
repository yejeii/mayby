<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" /><!DOCTYPE html>
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
                    <h1 class="page-title">FAQ</h1>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-12">
                <ul class="breadcrumb-nav">
                    <li><a href="index.html"><i class="lni lni-home"></i> Home</a></li>
                    <li>FAQ</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumbs -->

<!-- Start Faq Area -->
<section class="faq section">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title">
                    <h2>Haven't found the answer?<br> Ask us your question.</h2>
                    <p>We normally respond within 2 business days. Most popular questions will appear on this page.
                    </p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10 offset-lg-1 col-md-12 col-12">
                <div class="accordion" id="accordionExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <span class="title">What payment methods do you accept?</span><i
                                    class="lni lni-plus"></i>
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam consectetur sit
                                    amet ante nec vulputate. Nulla aliquam, justo auctor consequat tincidunt, arcu
                                    erat mattis lorem, lacinia lacinia dui enim at eros. Pellentesque ut gravida
                                    augue. Duis ac dictum tellus</p>
                                <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                    richardson ad squid. 3 wolf moon officia aute. non cupidatat skateboard dolor
                                    brunch. Foosd truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                                    sunt alqua put a bird on it squid single-origin coffee nulla assumenda
                                    shoreditch et. Nihil anim ke ffiyeh helvetica, craft beer labore wes anderson
                                    cred nesciunt sapiente ea proident.</p>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingTwo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <span class="title">How long will delivery take?</span><i class="lni lni-plus"></i>
                            </button>
                        </h2>
                        <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                    richardson ad squid. 3 wolf moon officia aute. non cupidatat skateboard dolor
                                    brunch. Foosd truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                                    sunt alqua put a bird on it squid single-origin coffee nulla assumenda
                                    shoreditch et. Nihil anim ke ffiyeh helvetica, craft beer labore wes anderson
                                    cred nesciunt sapiente ea proident.</p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam consectetur sit
                                    amet ante nec vulputate. Nulla aliquam, justo auctor consequat tincidunt, arcu
                                    erat mattis lorem, lacinia lacinia dui enim at eros. Pellentesque ut gravida
                                    augue. Duis ac dictum tellus</p>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingThree">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <span class="title">Do I need an account to place an order?</span><i
                                    class="lni lni-plus"></i>
                            </button>
                        </h2>
                        <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas expedita,
                                    repellendus est nemo cum quibusdam optio, voluptate hic a tempora facere, nihil
                                    non itaque alias similique quas quam odit consequatur.</p>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingFour">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                <span class="title">Do you have discounts for returning customers?</span><i
                                    class="lni lni-plus"></i>
                            </button>
                        </h2>
                        <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                    richardson ad squid. 3 wolf moon officia aute. non cupidatat skateboard dolor
                                    brunch. Foosd truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                                    sunt alqua put a bird on it squid single-origin coffee nulla assumenda
                                    shoreditch et.</p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Provident iure ab nisi,
                                    magnam vitae. Laboriosam laborum suscipit recusandae officia laudantium,
                                    consectetur adipisci voluptates doloremque quisquam. Id rerum iusto
                                    reprehenderit assumenda!</p>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingFive">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                <span class="title">What are the product refund conditions?</span><i
                                    class="lni lni-plus"></i>
                            </button>
                        </h2>
                        <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                    richardson ad squid. 3 wolf moon officia aute. non cupidatat skateboard dolor
                                    brunch. Foosd truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                                    sunt alqua put a bird on it squid single-origin coffee nulla assumenda
                                    shoreditch et. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--/ End Faq Area -->

<!-- Start Footer Area -->
<%@include file="../layout/footer.jsp"%>
<!--/ End Footer Area -->

!-- ========================= scroll-top ========================= -->
<a href="#" class="scroll-top">
    <i class="lni lni-chevron-up"></i>
</a>

<!-- ========================= JS here ========================= -->
<script src="${contextPath}/static/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/tiny-slider.js"></script>
<script src="${contextPath}/static/js/glightbox.min.js"></script>
<script src="${contextPath}/static/js/main.js"></script>
</body>

</html>