<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="includes/header.jspf" %>
<%@ include file="includes/sidebar.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-4">

    <div>

        <h2 class="mb-1">

            Product Management

        </h2>

        <p class="text-muted mb-0">

            Manage Products & Inventory

        </p>

    </div>

</div>

<div class="card shadow-sm mb-4">

    <div class="card-header bg-primary text-white">

        <h5 class="mb-0">

            Add Product

        </h5>

    </div>

    <div class="card-body">

        <form action="${pageContext.request.contextPath}/admin/products/save"
              method="post">

            <div class="row">

                <div class="col-md-6 mb-3">

                    <label class="form-label">

                        Product Name

                    </label>

                    <input
                            type="text"
                            name="name"
                            class="form-control"
                            required>

                </div>

                <div class="col-md-6 mb-3">

                    <label class="form-label">

                        Category

                    </label>

                    <select
                            name="categoryId"
                            class="form-select"
                            required>

                        <option value="">

                            -- Select Category --

                        </option>

                        <c:forEach items="${categories}"
                                   var="category">

                            <option value="${category.id}">

                                    ${category.name}

                            </option>

                        </c:forEach>

                    </select>

                </div>

            </div>

            <div class="row">

                <div class="col-md-6 mb-3">

                    <label class="form-label">

                        Price

                    </label>

                    <input
                            type="number"
                            step="0.01"
                            min="0"
                            name="price"
                            class="form-control"
                            required>

                </div>

                <div class="col-md-6 mb-3">

                    <label class="form-label">

                        Quantity

                    </label>

                    <input
                            type="number"
                            min="0"
                            name="quantity"
                            class="form-control"
                            required>

                </div>

            </div>

            <div class="row">

                <div class="col-md-6 mb-3">

                    <label class="form-label">

                        Reorder Level

                    </label>

                    <input
                            type="number"
                            min="0"
                            value="5"
                            name="reorderLevel"
                            class="form-control"
                            required>

                </div>

                <div class="col-md-6 mb-3">

                    <label class="form-label">

                        Status

                    </label>

                    <input
                            type="text"
                            class="form-control"
                            value="Active"
                            readonly>

                </div>

            </div>

            <div class="mb-3">

                <label class="form-label">

                    Description

                </label>

                <textarea
                        name="description"
                        rows="4"
                        class="form-control"></textarea>

            </div>

            <button
                    type="submit"
                    class="btn btn-success">

                <i class="bi bi-plus-circle"></i>

                Save Product

            </button>

        </form>

    </div>

</div>

<div class="card shadow-sm">

    <div class="card-header bg-dark text-white">

        <h5 class="mb-0">

            Product List

        </h5>

    </div>

    <div class="card-body">

        <table class="table table-bordered table-hover align-middle">

            <thead class="table-light">

            <tr>

                <th>ID</th>

                <th>Name</th>

                <th>Category</th>

                <th>Price</th>

                <th>Qty</th>

                <th>Status</th>

                <th width="220">

                    Actions

                </th>

            </tr>

            </thead>

            <tbody><c:choose>

                <c:when test="${not empty products}">

                    <c:forEach items="${products}" var="product">

                        <tr>

                            <td>${product.id}</td>

                            <td>${product.name}</td>

                            <td>${product.category.name}</td>

                            <td>Rs. ${product.price}</td>

                            <td>${product.inventory.quantity}</td>

                            <td>

                                <c:choose>

                                    <c:when test="${product.active}">

                            <span class="badge bg-success">

                                Active

                            </span>

                                    </c:when>

                                    <c:otherwise>

                            <span class="badge bg-danger">

                                Inactive

                            </span>

                                    </c:otherwise>

                                </c:choose>

                            </td>

                            <td>

                                <button
                                        type="button"
                                        class="btn btn-warning btn-sm edit-btn"

                                        data-bs-toggle="modal"
                                        data-bs-target="#editProductModal"

                                        data-id="${product.id}"
                                        data-name="${product.name}"
                                        data-description="${product.description}"
                                        data-price="${product.price}"
                                        data-categoryid="${product.category.id}"
                                        data-quantity="${product.inventory.quantity}"
                                        data-reorderlevel="${product.inventory.reorderLevel}"
                                        data-active="${product.active}">

                                    <i class="bi bi-pencil-square"></i>

                                    Edit

                                </button>

                                <a href="${pageContext.request.contextPath}/admin/products/delete?id=${product.id}"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('Delete this product?');">

                                    <i class="bi bi-trash"></i>

                                    Delete

                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </c:when>

                <c:otherwise>

                    <tr>

                        <td colspan="7"
                            class="text-center text-muted">

                            No Products Found

                        </td>

                    </tr>

                </c:otherwise>

            </c:choose>

            </tbody>

        </table>

    </div>

</div><!-- Edit Product Modal -->

<div class="modal fade"
     id="editProductModal"
     tabindex="-1"
     aria-hidden="true">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <form action="${pageContext.request.contextPath}/admin/products/update"
                  method="post">

                <div class="modal-header">

                    <h5 class="modal-title">

                        Edit Product

                    </h5>

                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal">
                    </button>

                </div>

                <div class="modal-body">

                    <input type="hidden"
                           id="editId"
                           name="id">

                    <div class="row">

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Product Name

                            </label>

                            <input
                                    type="text"
                                    id="editName"
                                    name="name"
                                    class="form-control"
                                    required>

                        </div>

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Category

                            </label>

                            <select
                                    id="editCategory"
                                    name="categoryId"
                                    class="form-select"
                                    required>

                                <c:forEach items="${categories}" var="category">

                                    <option value="${category.id}">

                                            ${category.name}

                                    </option>

                                </c:forEach>

                            </select>

                        </div>

                    </div>

                    <div class="row">

                        <div class="col-md-4 mb-3">

                            <label class="form-label">

                                Price

                            </label>

                            <input
                                    type="number"
                                    step="0.01"
                                    id="editPrice"
                                    name="price"
                                    class="form-control"
                                    required>

                        </div>

                        <div class="col-md-4 mb-3">

                            <label class="form-label">

                                Quantity

                            </label>

                            <input
                                    type="number"
                                    id="editQuantity"
                                    name="quantity"
                                    class="form-control"
                                    required>

                        </div>

                        <div class="col-md-4 mb-3">

                            <label class="form-label">

                                Reorder Level

                            </label>

                            <input
                                    type="number"
                                    id="editReorderLevel"
                                    name="reorderLevel"
                                    class="form-control"
                                    required>

                        </div>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">

                            Description

                        </label>

                        <textarea
                                id="editDescription"
                                name="description"
                                rows="4"
                                class="form-control"></textarea>

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

                        Update Product

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>
<script>

    document.addEventListener("DOMContentLoaded", function () {

        const editModal =
            document.getElementById("editProductModal");

        editModal.addEventListener("show.bs.modal", function (event) {

            const button = event.relatedTarget;

            document.getElementById("editId").value =
                button.getAttribute("data-id");

            document.getElementById("editName").value =
                button.getAttribute("data-name");

            document.getElementById("editDescription").value =
                button.getAttribute("data-description");

            document.getElementById("editPrice").value =
                button.getAttribute("data-price");

            document.getElementById("editCategory").value =
                button.getAttribute("data-categoryid");

            document.getElementById("editQuantity").value =
                button.getAttribute("data-quantity");

            document.getElementById("editReorderLevel").value =
                button.getAttribute("data-reorderlevel");

        });

    });

</script>

<%@ include file="includes/footer.jspf" %>