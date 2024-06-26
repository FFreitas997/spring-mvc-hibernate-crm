<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>List Customers</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <input class="add-button" type="button" value="Add Customer" onclick="window.location.href='showForm'; return false;">

        <table>

            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempCustomer" items="${customers}">

                <c:url var="updateLink" value="/customer/showFormUpdate">
                    <c:param name="customerID" value="${tempCustomer.id}" />
                </c:url>

                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerID" value="${tempCustomer.id}" />
                </c:url>

                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td>
                        <a href="${updateLink}">Update</a> |
                        <a onclick="if (!(confirm('Are you sure you want to delete this customer ?'))) return false" href="${deleteLink}">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>


</body>

</html>