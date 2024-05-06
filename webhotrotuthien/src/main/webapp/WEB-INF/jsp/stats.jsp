<!-- Page Heading -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/stats" var="action" />
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <form method="get" action="${action}">
        <label for="fromDate">From date</label>
        <input type="date" 
               name="fromDate" id="fromDate" />
        <label for="toDate">To date</label>
        <input type="date" 
               name="toDate" id="fromDate" />
        <input type="submit" value="Filter"/>
    </form>
</div>

<!-- Content Row -->
<div class="row">
    <canvas id="myChart"></canvas>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    const ctx = document.getElementById('myChart');
    const statsData = ${statsData};

    const labels = statsData.map(data => data[1]);
    const likeCounts = statsData.map(data => data[2]);
    const commentCounts = statsData.map(data => data[3]);

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: 'like',
                    data: likeCounts,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }, {
                    label: 'comment',
                    data: commentCounts,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
