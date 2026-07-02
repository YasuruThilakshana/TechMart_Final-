<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="includes/header.jspf" %>
<%@ include file="includes/sidebar.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-4">

    <div>

        <h2 class="mb-1">

            Inventory Dashboard

        </h2>

        <p class="text-muted mb-0">

            Monitor and manage product inventory

        </p>

    </div>

</div>

<div class="row mb-4">

    <div class="col-md-4">

        <div class="card border-primary shadow-sm">

            <div class="card-body text-center">

                <h6 class="text-muted">

                    Total Inventory Items

                </h6>

                <h2 class="text-primary">

                    ${inventories.size()}

                </h2>

            </div>

        </div>

    </div>

    <div class="col-md-4">

        <div class="card border-warning shadow-sm">

            <div class="card-body text-center">

                <h6 class="text-muted">

                    Low Stock Items

                </h6>

                <h2 class="text-warning">

                    ${lowStockItems.size()}

                </h2>

            </div>

        </div>

    </div>

    <div class="col-md-4">

        <div class="card border-danger shadow-sm">

            <div class="card-body text-center">

                <h6 class="text-muted">

                    Out of Stock

                </h6>

                <h2 class="text-danger">

                    0

                </h2>

            </div>

        </div>

    </div>

</div>

<div class="card shadow-sm">

    <div class="card-header bg-dark text-white">

        <h5 class="mb-0">

            Inventory List

        </h5>

    </div>

    <div class="card-body">

        <table class="table table-bordered table-hover align-middle">

            <thead class="table-light">

            <tr>

                <th>ID</th>

                <th>Product</th>

                <th>Category</th>

                <th>Quantity</th>

                <th>Reorder Level</th>

                <th>Status</th>

                <th width="170">

                    Action

                </th>

            </tr>

            </thead>

            <tbody><c:choose>

                <c:when test="${not empty inventories}">

                    <c:forEach items="${inventories}" var="inventory">

                        <tr>

                            <td>${inventory.id}</td>

                            <td>${inventory.product.name}</td>

                            <td>${inventory.product.category.name}</td>

                            <td>${inventory.quantity}</td>

                            <td>${inventory.reorderLevel}</td>

                            <td>

                                <c:choose>

                                    <c:when test="${inventory.quantity == 0}">

                            <span class="badge bg-danger">

                                Out of Stock

                            </span>

                                    </c:when>

                                    <c:when test="${inventory.quantity <= inventory.reorderLevel}">

                            <span class="badge bg-warning text-dark">

                                Low Stock

                            </span>

                                    </c:when>

                                    <c:otherwise>

                            <span class="badge bg-success">

                                In Stock

                            </span>

                                    </c:otherwise>

                                </c:choose>

                            </td>

                            <td>

                                <button
                                        type="button"
                                        class="btn btn-primary btn-sm"

                                        data-bs-toggle="modal"
                                        data-bs-target="#updateInventoryModal"

                                        data-id="${inventory.id}"
                                        data-product="${inventory.product.name}"
                                        data-quantity="${inventory.quantity}"
                                        data-reorder="${inventory.reorderLevel}">

                                    <i class="bi bi-pencil-square"></i>

                                    Update

                                </button>

                            </td>

                        </tr>

                    </c:forEach>

                </c:when>

                <c:otherwise>

                    <tr>

                        <td colspan="7"
                            class="text-center text-muted">

                            No Inventory Records Found

                        </td>

                    </tr>

                </c:otherwise>

            </c:choose>

            </tbody>

        </table>

    </div>

</div>

<!-- Update Inventory Modal -->

<div class="modal fade"
     id="updateInventoryModal"
     tabindex="-1"
     aria-hidden="true">

    <div class="modal-dialog">

        <div class="modal-content">

            <form action="${pageContext.request.contextPath}/admin/inventory/update"
                  method="post">

                <div class="modal-header">

                    <h5 class="modal-title">

                        Update Inventory

                    </h5>

                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal">
                    </button>

                </div>

                <div class="modal-body">

                    <input type="hidden"
                           id="inventoryId"
                           name="id">

                    <div class="mb-3">

                        <label class="form-label">

                            Product

                        </label>

                        <input
                                type="text"
                                id="productName"
                                class="form-control"
                                readonly>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">

                            Quantity

                        </label>

                        <input
                                type="number"
                                id="inventoryQuantity"
                                name="quantity"
                                class="form-control"
                                required>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">

                            Reorder Level

                        </label>

                        <input
                                type="number"
                                id="inventoryReorder"
                                name="reorderLevel"
                                class="form-control"
                                required>

                    </div>

                </div>

                <div class="modal-footer">

                    <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">

                        Cancel

                    </button>

                    <button
                            type="submit"
                            class="btn btn-primary">

                        Update Stock

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>

<script>

    document.addEventListener("DOMContentLoaded", function () {

        const modal =
            document.getElementById("updateInventoryModal");

        modal.addEventListener("show.bs.modal", function (event) {

            const button = event.relatedTarget;

            document.getElementById("inventoryId").value =
                button.getAttribute("data-id");

            document.getElementById("productName").value =
                button.getAttribute("data-product");

            document.getElementById("inventoryQuantity").value =
                button.getAttribute("data-quantity");

            document.getElementById("inventoryReorder").value =
                button.getAttribute("data-reorder");

        });

    });

</script>

<%@ include file="includes/footer.jspf" %>