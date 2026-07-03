<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Shopping Cart</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2>

            My Shopping Cart

        </h2>

        <a href="${pageContext.request.contextPath}/customer/products"
           class="btn btn-secondary">

            ← Continue Shopping

        </a>

        <a href="${pageContext.request.contextPath}/customer/dashboard.jsp"
           class="btn btn-dark">

            Dashboard

        </a>

    </div>

    <table class="table table-bordered table-hover">

        <thead class="table-dark">

        <tr>

            <th>Product</th>

            <th>Unit Price</th>

            <th>Quantity</th>

            <th>Subtotal</th>

            <th width="120">

                Action

            </th>

        </tr>

        </thead>

        <tbody>

        <c:choose>

            <c:when test="${not empty cartItems}">

                <c:set var="grandTotal" value="0"/>

                <c:forEach items="${cartItems}" var="item">

                    <tr>

                        <td>

                                ${item.product.name}

                        </td>

                        <td>

                            Rs. ${item.unitPrice}

                        </td>

                        <td>

                            <form action="${pageContext.request.contextPath}/customer/cart/update"
                                  method="post"
                                  class="d-flex">

                                <input
                                        type="hidden"
                                        name="cartItemId"
                                        value="${item.id}">

                                <input
                                        type="number"
                                        name="quantity"
                                        value="${item.quantity}"
                                        min="1"
                                        class="form-control form-control-sm me-2"
                                        style="width:90px;">

                                <button
                                        type="submit"
                                        class="btn btn-primary btn-sm">

                                    Update

                                </button>

                            </form>

                        </td>
                        <td>

                            Rs. ${item.subtotal}

                        </td>

                        <td>

                            <a href="${pageContext.request.contextPath}/customer/cart/remove?id=${item.id}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('Remove this item from cart?');">

                                Remove

                            </a>

                        </td>

                    </tr>

                    <c:set var="grandTotal"
                           value="${grandTotal + item.subtotal}"/>

                </c:forEach>

                <tr class="table-light">

                    <td colspan="3"
                        class="text-end">

                        <strong>Total</strong>

                    </td>

                    <td>

                        <strong>

                            Rs. ${grandTotal}

                        </strong>

                    </td>

                    <td></td>

                </tr>

            </c:when>

            <c:otherwise>

                <tr>

                    <td colspan="5"
                        class="text-center text-muted">

                        Your shopping cart is empty.

                    </td>

                </tr>

            </c:otherwise>

        </c:choose>

        </tbody>

    </table>

    <div class="d-flex justify-content-end">

        <a href="${pageContext.request.contextPath}/customer/checkout"
           class="btn btn-success">

            Proceed To Checkout

        </a>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>