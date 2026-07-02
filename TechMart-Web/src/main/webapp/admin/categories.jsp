<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="includes/header.jspf" %>
<%@ include file="includes/sidebar.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-4">

    <div>

        <h2 class="mb-1">

            Category Management

        </h2>

        <p class="text-muted mb-0">

            Manage all product categories

        </p>

    </div>

</div>

<div class="card shadow-sm mb-4">

    <div class="card-header bg-primary text-white">

        <h5 class="mb-0">

            Add New Category

        </h5>

    </div>

    <div class="card-body">

        <form action="${pageContext.request.contextPath}/admin/categories/save"
              method="post">

            <div class="row">

                <div class="col-md-6 mb-3">

                    <label class="form-label">

                        Category Name

                    </label>

                    <input
                            type="text"
                            name="name"
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
                        class="form-control"
                        rows="3"
                        name="description"></textarea>

            </div>

            <button
                    type="submit"
                    class="btn btn-success">

                <i class="bi bi-plus-circle"></i>

                Save Category

            </button>

        </form>

    </div>

</div>

<div class="card shadow-sm">

    <div class="card-header bg-dark text-white">

        <h5 class="mb-0">

            Category List

        </h5>

    </div>

    <div class="card-body">

        <table class="table table-bordered table-hover align-middle">

            <thead class="table-light">

            <tr>

                <th>ID</th>

                <th>Name</th>

                <th>Description</th>

                <th>Status</th>

                <th width="220">

                    Actions

                </th>

            </tr>

            </thead>

            <tbody><c:choose>

    <c:when test="${not empty categories}">

        <c:forEach var="category" items="${categories}">

            <tr>

                <td>${category.id}</td>

                <td>${category.name}</td>

                <td>${category.description}</td>

                <td>

                    <c:choose>

                        <c:when test="${category.active}">

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
                            data-bs-target="#editCategoryModal"

                            data-id="${category.id}"
                            data-name="${category.name}"
                            data-description="${category.description}"
                            data-active="${category.active}">

                        <i class="bi bi-pencil-square"></i>

                        Edit

                    </button>

                    <a href="${pageContext.request.contextPath}/admin/categories/delete?id=${category.id}"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure you want to delete this category?');">

                        <i class="bi bi-trash"></i>

                        Delete

                    </a>

                </td>

            </tr>

        </c:forEach>

    </c:when>

    <c:otherwise>

        <tr>

            <td colspan="5"
                class="text-center text-muted">

                No Categories Found

            </td>

        </tr>

    </c:otherwise>

</c:choose>


            </tbody>

        </table>

    </div>

</div>

<!-- Edit Category Modal -->

<div class="modal fade"
     id="editCategoryModal"
     tabindex="-1"
     aria-hidden="true">

    <div class="modal-dialog">

        <div class="modal-content">

            <form action="${pageContext.request.contextPath}/admin/categories/update"
                  method="post">

                <div class="modal-header">

                    <h5 class="modal-title">

                        Edit Category

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

                    <div class="mb-3">

                        <label class="form-label">

                            Category Name

                        </label>

                        <input
                                type="text"
                                id="editName"
                                name="name"
                                class="form-control"
                                required>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">

                            Description

                        </label>

                        <textarea
                                id="editDescription"
                                name="description"
                                class="form-control"
                                rows="3"></textarea>

                    </div>

                    <div class="form-check">

                        <input
                                class="form-check-input"
                                type="checkbox"
                                id="editActive"
                                name="active">

                        <label
                                class="form-check-label"
                                for="editActive">

                            Active

                        </label>

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

                        Update Category

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>

<script>

    const editModal = document.getElementById("editCategoryModal");

    editModal.addEventListener("show.bs.modal", function (event) {

        const button = event.relatedTarget;

        document.getElementById("editId").value =
            button.getAttribute("data-id");

        document.getElementById("editName").value =
            button.getAttribute("data-name");

        document.getElementById("editDescription").value =
            button.getAttribute("data-description");

        document.getElementById("editActive").checked =
            button.getAttribute("data-active") === "true";

    });

</script>


<%@ include file="includes/footer.jspf" %>