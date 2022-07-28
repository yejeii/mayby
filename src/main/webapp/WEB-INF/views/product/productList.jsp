<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="${contextPath }/static/css/product/productList.css">

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
                    <h1 class="page-title">Shop Grid</h1>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-12">
                <ul class="breadcrumb-nav">
                    <li><a href="${contextPath}/index"><i class="lni lni-home"></i> Home</a></li>
                    <li><a href="javascript:void(0)">Shop</a></li>
                    <li>Shop Grid</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumbs -->

<!-- Start Product Grids -->
<section class="product-grids section">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-12">
                <!-- Start Product Sidebar -->
                <div class="product-sidebar">
                    <!-- Start Single Widget -->
                    <div class="single-widget search">
                        <h3>Search Product</h3>
                        <form action="#">
                            <input type="text" placeholder="Search Here...">
                            <button type="submit"><i class="lni lni-search-alt"></i></button>
                        </form>
                    </div>
                    <!-- End Single Widget -->
                    <!-- Start Single Widget -->
                    <div class="single-widget">
                        <h3>All Categories</h3>
                        <ul class="list">
                            <li>
                                <a href="product-grids.html">Computers & Accessories </a><span>(1138)</span>
                            </li>
                            <li>
                                <a href="product-grids.html">Smartphones & Tablets</a><span>(2356)</span>
                            </li>
                            <li>
                                <a href="product-grids.html">TV, Video & Audio</a><span>(420)</span>
                            </li>
                            <li>
                                <a href="product-grids.html">Cameras, Photo & Video</a><span>(874)</span>
                            </li>
                            <li>
                                <a href="product-grids.html">Headphones</a><span>(1239)</span>
                            </li>
                            <li>
                                <a href="product-grids.html">Wearable Electronics</a><span>(340)</span>
                            </li>
                            <li>
                                <a href="product-grids.html">Printers & Ink</a><span>(512)</span>
                            </li>
                        </ul>
                    </div>
                    <!-- End Single Widget -->
                    <!-- Start Single Widget -->
                    <div class="single-widget range">
                        <h3>Price Range</h3>
                        <input type="range" class="form-range" name="range" step="1" min="100" max="10000"
                               value="10" onchange="rangePrimary.value=value">
                        <div class="range-inner">
                            <label>$</label>
                            <input type="text" id="rangePrimary" placeholder="100" />
                        </div>
                    </div>
                    <!-- End Single Widget -->
                    <!-- Start Single Widget -->
                    <div class="single-widget condition">
                        <h3>Filter by Price</h3>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault1">
                            <label class="form-check-label" for="flexCheckDefault1">
                                $50 - $100L (208)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault2">
                            <label class="form-check-label" for="flexCheckDefault2">
                                $100L - $500 (311)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault3">
                            <label class="form-check-label" for="flexCheckDefault3">
                                $500 - $1,000 (485)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault4">
                            <label class="form-check-label" for="flexCheckDefault4">
                                $1,000 - $5,000 (213)
                            </label>
                        </div>
                    </div>
                    <!-- End Single Widget -->
                    <!-- Start Single Widget -->
                    <div class="single-widget condition">
                        <h3>Filter by Brand</h3>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault11">
                            <label class="form-check-label" for="flexCheckDefault11">
                                Apple (254)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault22">
                            <label class="form-check-label" for="flexCheckDefault22">
                                Bosh (39)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault33">
                            <label class="form-check-label" for="flexCheckDefault33">
                                Canon Inc. (128)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault44">
                            <label class="form-check-label" for="flexCheckDefault44">
                                Dell (310)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault55">
                            <label class="form-check-label" for="flexCheckDefault55">
                                Hewlett-Packard (42)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault66">
                            <label class="form-check-label" for="flexCheckDefault66">
                                Hitachi (217)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault77">
                            <label class="form-check-label" for="flexCheckDefault77">
                                LG Electronics (310)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault88">
                            <label class="form-check-label" for="flexCheckDefault88">
                                Panasonic (74)
                            </label>
                        </div>
                    </div>
                    <!-- End Single Widget -->
                </div>
                <!-- End Product Sidebar -->
            </div>
            <div class="col-lg-9 col-12">
                <div class="product-grids-head">
                    <div class="product-grid-topbar">
                        <div class="row align-items-center">
                            <div class="col-lg-7 col-md-8 col-12">
                                <div class="product-sorting">
                                    <label for="sorting">Sort by:</label>
                                    <select class="form-control" id="sorting">
                                        <option>Popularity</option>
                                        <option>Low - High Price</option>
                                        <option>High - Low Price</option>
                                        <option>Average Rating</option>
                                        <option>A - Z Order</option>
                                        <option>Z - A Order</option>
                                    </select>
                                    <h3 class="total-show-product">Showing: <span>1 - 12 items</span></h3>
                                </div>
                            </div>
                            <div class="col-lg-5 col-md-4 col-12">
                                <nav>
                                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                        <button class="nav-link active" id="nav-grid-tab" data-bs-toggle="tab"
                                                data-bs-target="#nav-grid" type="button" role="tab"
                                                aria-controls="nav-grid" aria-selected="true"><i
                                                class="lni lni-grid-alt"></i></button>
                                        <button class="nav-link" id="nav-list-tab" data-bs-toggle="tab"
                                                data-bs-target="#nav-list" type="button" role="tab"
                                                aria-controls="nav-list" aria-selected="false"><i
                                                class="lni lni-list"></i></button>
                                    </div>
                                </nav>
                            </div>
                        </div>
                    </div>

                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="nav-grid" role="tabpanel"
                             aria-labelledby="nav-grid-tab">
                            <div class="row">
                                <!-- 게시물 O -->
                                <c:if test="${listcheck != 'empty' }">
                                    <div class="col-lg-4 col-md-6 col-12" id="searchList">
                                        <c:forEach items="${list }" var="list">
                                            <div class="single-product">
                                                <div class="product-image product_image">
                                                    <div class="image_wrap" data-productid ="${list.imageList[0].pi_p_id}" data-path="${list.imageList[0].pi_uploadPath}" data-uuid="${list.imageList[0].pi_uuid}" data-filename="${list.imageList[0].pi_fileName}">
                                                        <img>
                                                    </div>
                                                    <div class="button">
                                                        <a href="${contextPath}/product/productDetail/${list.p_idx}" class="btn"><i
                                                                class="lni lni-cart"></i> Add to Cart</a>
                                                    </div>
                                                </div>
                                                <div class="product-info product_info">
                                                    <span class="category">[${list.pc_name}]</span>
                                                    <h4 class="title product_name">
                                                        <a href="${contextPath}/product/productDetail/${list.p_idx}">${list.p_name}</a>
                                                    </h4>
                                                    <ul class="review">
                                                        <li><i class="lni lni-star-filled"></i></li>
                                                        <li><i class="lni lni-star-filled"></i></li>
                                                        <li><i class="lni lni-star-filled"></i></li>
                                                        <li><i class="lni lni-star-filled"></i></li>
                                                        <li><i class="lni lni-star"></i></li>
                                                        <li><span>4.0 Review(s)</span></li>
                                                    </ul>
                                                    <div class="price product_price">
                                                        <div class="org_price">
                                                            <del> ${list.p_price } </del>
                                                        </div>
                                                        <div class="sell_price">
                                                            <strong>
                                                                <fmt:formatNumber value="${list.p_price - (list.p_price * list.p_discount)}" pattern="#,### 원"/>
                                                            </strong>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <!-- End Single Product -->
                                    </div>
                                </c:if>

                                <!-- 게시물 X -->
                                <c:if test="${listcheck == 'empty' }">
                                    <div>
                                        검색 결과가 없습니다!
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <!-- 페이지 이동 인터페이스 -->
                                <div class="pagination left">
                                    <ul class="pagination-list page_nums">
                                        <!-- 이전 버튼 -->
                                        <c:if test="${pageMaker.prev }">
                                            <li class="pageMaker_btn prev">
                                                <a href="${pageMaker.pageStart -1}">Previous</a>
                                            </li>
                                        </c:if>

                                        <!-- 페이지 번호 -->
                                        <c:forEach begin="${pageMaker.pageStart }" end="${pageMaker.pageEnd }" var="num">
                                            <li class="pageMaker_btn ${pageMaker.cri.pageNm == num ? 'active':''}">
                                                <a href="${num}">${num}</a>
                                            </li>
                                        </c:forEach>

                                        <!-- 다음 버튼 -->
                                        <c:if test="${pageMaker.next}">
                                            <li class="pageMaker_btn next">
                                                <a href="${pageMaker.pageEnd + 1 }">Next</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                    <!-- <form> 태그를 통한 페이지 이동처리 -->
                                    <form id="moveForm" action="/product/productList" method="get" >
                                        <input type="hidden" name="pageNm" value="${pageMaker.cri.pageNm}">
                                        <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                                        <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
                                        <input type="hidden" name="cateCode" value="<c:out value="${pageMaker.cri.cateCode}"/>">
                                        <input type="hidden" name="type" value="${pageMaker.cri.type}">
                                    </form>
                                </div>
                                <!-- 페이지 이동 인터페이스 -->
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End Product Grids -->

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
<script src="${contextPath}/static/js/product/paging.js"></script>
<script>
    $(document).ready(function() {

        // 검색 타입 selected
        const selectedType = '<c:out value="${pageMaker.cri.type}"/>';
        if(selectedType != ""){
            $("select[name='type']").val(selectedType).attr("selected", "selected");
        }

        /* 이미지 삽입 */
        $(".image_wrap").each(function(i, obj){
            const pobj = $(obj);

            // 이미지가 있는 경우
            if(pobj.data("productid")) {
                const uploadPath = pobj.data("path");
                const uuid = pobj.data("uuid");
                const fileName = pobj.data("filename");
                const fileCallPath = encodeURIComponent(uploadPath + "/thumbnail/TN_" + uuid + "_" + fileName);

                $(this).find("img").attr('src', '${contextPath}/product/display?fileName=' + fileCallPath);
            } else {
                $(this).find("img").attr('src', '${contextPath}/static/images/noImage.png');
            }
        });
    });
</script>
</body>

</html>