<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

<head>

    <title>Checkout</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-5">

    <div class="card shadow">

        <div class="card-header">

            <h3>

                Checkout

            </h3>

        </div>

        <div class="card-body">

            <p>

                You are about to place your order.

            </p>

            <p>

                After clicking <strong>Place Order</strong>:

            </p>

            <ul>

                <li>Order will be created.</li>

                <li>Inventory will be updated.</li>

                <li>Payment record will be created.</li>

                <li>Your cart will be cleared.</li>

            </ul>

            <form method="post"
                  action="${pageContext.request.contextPath}/customer/checkout">

                <button
                        type="submit"
                        class="btn btn-success">

                    Place Order

                </button>

            </form>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>