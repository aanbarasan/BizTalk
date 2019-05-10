<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BizTalk</title>
<link rel="stylesheet" type="text/css"
	href="/resources/plugins/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/plugins/bootstrap-4.3.1-dist/css/bootstrap.css" />
<script src="/resources/plugins/jquery/jquery.min.js"
	type="text/javascript"></script>
<script src="/resources/plugins/bootstrap-4.3.1-dist/js/bootstrap.js"
	type="text/javascript"></script>
<script src="/resources/plugins/moment/moment.min.js"
	type="text/javascript"></script>
<script src="/resources/plugins/daterangepicker/daterangepicker.js"
	type="text/javascript"></script>
<script src="/resources/plugins/highcharts/highcharts.js"
	type="text/javascript"></script>
<script src="/resources/script/home.js" type="text/javascript"></script>
</head>
<body>
	<div style="padding:15px">
		<div class="col-md-12 col-md-offset-2 demo">
			<h4>
				Please enter the start date and end date: <input type="text" id="dateRange"
					class="form-control" style="display: inline;width: 220px;">
			</h4>
		</div>
		<div class="col-md-12 col-md-offset-2 demo">
			<div id="chart"></div>
		</div>
	</div>
</body>
</html>