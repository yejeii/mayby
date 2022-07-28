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

<div class="col-md-6 form-input form">
    <input type="text" placeholder="Last Name">
</div>
</div>
</div>
</div>
<div class="col-md-6">
    <div class="single-form form-default">
        <label>Email Address</label>
        <div class="form-input form">
            <input type="text" placeholder="Email Address">
        </div>
    </div>
</div>
<div class="col-md-6">
    <div class="single-form form-default">
        <label>Phone Number</label>
        <div class="form-input form">
            <input type="text" placeholder="Phone Number">
        </div>
    </div>
</div>
<div class="col-md-12">
    <div class="single-form form-default">
        <label>Mailing Address</label>
        <div class="form-input form">
            <input type="text" placeholder="Mailing Address">
        </div>
    </div>
</div>
<div class="col-md-6">
    <div class="single-form form-default">
        <label>City</label>
        <div class="form-input form">
            <input type="text" placeholder="City">
        </div>
    </div>
</div>
<div class="col-md-6">
    <div class="single-form form-default">
        <label>Post Code</label>
        <div class="form-input form">
            <input type="text" placeholder="Post Code">
        </div>
    </div>
</div>
<div class="col-md-6">
    <div class="single-form form-default">
        <label>Country</label>
        <div class="form-input form">
            <input type="text" placeholder="Country">
        </div>
    </div>
</div>
<div class="col-md-6">
    <div class="single-form form-default">
        <label>Region/State</label>
        <div class="select-items">
            <select class="form-control">
                <option value="0">select</option>
                <option value="1">select option 01</option>
                <option value="2">select option 02</option>
                <option value="3">select option 03</option>
                <option value="4">select option 04</option>
                <option value="5">select option 05</option>
            </select>
        </div>
    </div>
</div>
<div class="col-md-12">
    <div class="single-checkbox checkbox-style-3">
        <input type="checkbox" id="checkbox-3">
        <label for="checkbox-3"><span></span></label>
        <p>My delivery and mailing addresses are the same.</p>
    </div>
</div>
<div class="col-md-12">
    <div class="single-form button">
        <button class="btn" data-bs-toggle="collapse"
                data-bs-target="#collapseFour" aria-expanded="false"
                aria-controls="collapseFour">next
            step</button>
    </div>
</div>
</div>
</section>
</li>
<li>
    <h6 class="title collapsed" data-bs-toggle="collapse" data-bs-target="#collapseFour"
        aria-expanded="false" aria-controls="collapseFour">Shipping Address</h6>
    <section class="checkout-steps-form-content collapse" id="collapseFour"
             aria-labelledby="headingFour" data-bs-parent="#accordionExample">
        <div class="row">
            <div class="col-md-12">
                <div class="single-form form-default">
                    <label>User Name</label>
                    <div class="row">
                        <div class="col-md-6 form-input form">
                            <input type="text" placeholder="First Name">
                        </div>
                        <div class="col-md-6 form-input form">
                            <input type="text" placeholder="Last Name">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="single-form form-default">
                    <label>Email Address</label>
                    <div class="form-input form">
                        <input type="text" placeholder="Email Address">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="single-form form-default">
                    <label>Phone Number</label>
                    <div class="form-input form">
                        <input type="text" placeholder="Phone Number">
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="single-form form-default">
                    <label>Mailing Address</label>
                    <div class="form-input form">
                        <input type="text" placeholder="Mailing Address">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="single-form form-default">
                    <label>City</label>
                    <div class="form-input form">
                        <input type="text" placeholder="City">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="single-form form-default">
                    <label>Post Code</label>
                    <div class="form-input form">
                        <input type="text" placeholder="Post Code">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="single-form form-default">
                    <label>Country</label>
                    <div class="form-input form">
                        <input type="text" placeholder="Country">
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="single-form form-default">
                    <label>Region/State</label>
                    <div class="select-items">
                        <select class="form-control">
                            <option value="0">select</option>
                            <option value="1">select option 01</option>
                            <option value="2">select option 02</option>
                            <option value="3">select option 03</option>
                            <option value="4">select option 04</option>
                            <option value="5">select option 05</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="checkout-payment-option">
                    <h6 class="heading-6 font-weight-400 payment-title">Select Delivery
                        Option</h6>
                    <div class="payment-option-wrapper">
                        <div class="single-payment-option">
                            <input type="radio" name="shipping" checked id="shipping-1">
                            <label for="shipping-1">
                                <img src="https://via.placeholder.com/60x32"
                                     alt="Sipping">
                                <p>Standerd Shipping</p>
                                <span class="price">$10.50</span>
                            </label>
                        </div>
                        <div class="single-payment-option">
                            <input type="radio" name="shipping" id="shipping-2">
                            <label for="shipping-2">
                                <img src="https://via.placeholder.com/60x32"
                                     alt="Sipping">
                                <p>Standerd Shipping</p>
                                <span class="price">$10.50</span>
                            </label>
                        </div>
                        <div class="single-payment-option">
                            <input type="radio" name="shipping" id="shipping-3">
                            <label for="shipping-3">
                                <img src="https://via.placeholder.com/60x32"
                                     alt="Sipping">
                                <p>Standerd Shipping</p>
                                <span class="price">$10.50</span>
                            </label>
                        </div>
                        <div class="single-payment-option">
                            <input type="radio" name="shipping" id="shipping-4">
                            <label for="shipping-4">
                                <img src="https://via.placeholder.com/60x32"
                                     alt="Sipping">
                                <p>Standerd Shipping</p>
                                <span class="price">$10.50</span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="steps-form-btn button">
                    <button class="btn" data-bs-toggle="collapse"
                            data-bs-target="#collapseThree" aria-expanded="false"
                            aria-controls="collapseThree">previous</button>
                    <a href="javascript:void(0)" class="btn btn-alt">Save & Continue</a>
                </div>
            </div>
        </div>
    </section>
</li>
<li>
    <h6 class="title collapsed" data-bs-toggle="collapse" data-bs-target="#collapsefive"
        aria-expanded="false" aria-controls="collapsefive">Payment Info</h6>
    <section class="checkout-steps-form-content collapse" id="collapsefive"
             aria-labelledby="headingFive" data-bs-parent="#accordionExample">
        <div class="row">
            <div class="col-12">
                <div class="checkout-payment-form">
                    <div class="single-form form-default">
                        <label>Cardholder Name</label>
                        <div class="form-input form">
                            <input type="text" placeholder="Cardholder Name">
                        </div>
                    </div>
                    <div class="single-form form-default">
                        <label>Card Number</label>
                        <div class="form-input form">
                            <input id="credit-input" type="text"
                                   placeholder="0000 0000 0000 0000">
                            <img src="assets/images/payment/card.png" alt="card">
                        </div>
                    </div>
                    <div class="payment-card-info">
                        <div class="single-form form-default mm-yy">
                            <label>Expiration</label>
                            <div class="expiration d-flex">
                                <div class="form-input form">
                                    <input type="text" placeholder="MM">
                                </div>
                                <div class="form-input form">
                                    <input type="text" placeholder="YYYY">
                                </div>
                            </div>
                        </div>
                        <div class="single-form form-default">
                            <label>CVC/CVV <span><i
                                    class="mdi mdi-alert-circle"></i></span></label>
                            <div class="form-input form">
                                <input type="text" placeholder="***">
                            </div>
                        </div>
                    </div>
                    <div class="single-form form-default button">
                        <button class="btn">pay now</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</li>
</ul>
</div>
</div>
<div class="col-lg-4">
    <div class="checkout-sidebar">
        <div class="checkout-sidebar-coupon">
            <p>Appy Coupon to get discount!</p>
            <form action="#">
                <div class="single-form form-default">
                    <div class="form-input form">
                        <input type="text" placeholder="Coupon Code">
                    </div>
                    <div class="button">
                        <button class="btn">apply</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="checkout-sidebar-price-table mt-30">
            <h5 class="title">Pricing Table</h5>

            <div class="sub-total-price">
                <div class="total-price">
                    <p class="value">Subotal Price:</p>
                    <p class="price">$144.00</p>
                </div>
                <div class="total-price shipping">
                    <p class="value">Subotal Price:</p>
                    <p class="price">$10.50</p>
                </div>
                <div class="total-price discount">
                    <p class="value">Subotal Price:</p>
                    <p class="price">$10.00</p>
                </div>
            </div>

            <div class="total-payable">
                <div class="payable-price">
                    <p class="value">Subotal Price:</p>
                    <p class="price">$164.50</p>
                </div>
            </div>
            <div class="price-table-btn button">
                <a href="javascript:void(0)" class="btn btn-alt">Checkout</a>
            </div>
        </div>
        <div class="checkout-sidebar-banner mt-30">
            <a href="product-grids.html">
                <img src="https://via.placeholder.com/400x330" alt="#">
            </a>
        </div>
    </div>
</div>
</div>
</div>
</section>
<!--====== Checkout Form Steps Part Ends ======-->

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