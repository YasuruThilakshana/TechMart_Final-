<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <title>TechMart Store</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>

            <h2>

                TechMart Store

            </h2>

            <p class="text-muted">

                Browse available products

            </p>

        </div>

        <a href="${pageContext.request.contextPath}/customer/cart"
           class="btn btn-primary">

            🛒 My Cart

        </a>

    </div>

    <div class="row">

        <c:choose>

            <c:when test="${not empty products}">

                <c:forEach items="${products}" var="product">

                    <div class="col-md-4 mb-4">

                        <div class="card h-100 shadow-sm">

                            <div class="card-body d-flex flex-column">

                                <h5 class="card-title">

                                        ${product.name}

                                </h5>

                                <p class="text-muted">

                                        ${product.category.name}

                                </p>

                                <p class="card-text">

                                        ${product.description}

                                </p>

                                <h4 class="text-primary">

                                    Rs. ${product.price}

                                </h4>

                                <c:choose>

                                    <c:when test="${not empty product.inventory}">

                                        <p>

                                            <strong>Stock :</strong>

                                                ${product.inventory.quantity}

                                        </p>

                                    </c:when>

                                    <c:otherwise>

                                        <p class="text-danger">

                                            Stock Not Available

                                        </p>

                                    </c:otherwise>

                                </c:choose>

                                <form action="${pageContext.request.contextPath}/customer/cart/add"
                                      method="post"
                                      class="mt-auto">

                                    <input
                                            type="hidden"
                                            name="productId"
                                            value="${product.id}">

                                    <div class="input-group mb-3">

                                        <input
                                                type="number"
                                                name="quantity"
                                                value="1"
                                                min="1"
                                                class="form-control">

                                    </div>

                                    <button
                                            type="submit"
                                            class="btn btn-success w-100">

                                        🛒 Add To Cart

                                    </button>

                                </form>

                            </div>

                        </div>

                    </div>

                </c:forEach>

            </c:when>

            <c:otherwise>

                <div class="col-12">

                    <div class="alert alert-warning text-center">

                        No Products Available

                    </div>

                </div>

            </c:otherwise>

        </c:choose>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>