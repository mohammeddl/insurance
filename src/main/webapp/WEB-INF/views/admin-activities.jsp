<!DOCTYPE html>
<html>
<head>
    <title>User Activities</title>
</head>
<body>
    <h1>User Activities</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Activity</th>
                <th>Timestamp</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="activity" items="${activities}">
                <tr>
                    <td>${activity.id}</td>
                    <td>${activity.username}</td>
                    <td>${activity.activity}</td>
                    <td>${activity.timestamp}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
