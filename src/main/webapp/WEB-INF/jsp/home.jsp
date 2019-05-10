<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BizTalk</title>
<link rel="stylesheet" type="text/css"
	href="/resources/plugins/boostrap-4.0.0/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/plugins/daterangepicker/daterangepicker.css" />
<script src="/resources/plugins/jquery/jquery.min.js"
	type="text/javascript"></script>
<script src="/resources/plugins/boostrap-4.0.0/bootstrap.js"
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
	<div style="padding: 15px">
		<div class="col-md-12">
			<h4>
				Please enter the start date and end date: <input type="text"
					id="dateRange" class="form-control"
					style="display: inline; width: 220px;">
			</h4>
		</div>
		<div class="row col-md-12" id="searchDetailsContainer" style="display:none;padding:15px;">
			<div class="row col-md-6">
				<div class="col-md-4">
					<div class="col-md-12">
						No of vehicle information passed from BizTalk to Microlise
					</div>
					<div class="col-md-12">
						<span id="vehicleTotal"></span>
					</div>
				</div>
				<div class="col-md-4">
					<div class="col-md-12">
						No of unique vehicles passed from BizTalk to Microlise
					</div>
					<div class="col-md-12">
						<span id="vehicleUnique"></span>
					</div>
				</div>
				<div class="col-md-4">
					<div class="col-md-12">
						No of vehicle details suspended at BizTalk
					</div>
					<div class="col-md-12">
						<span id="vehicleSuspended"></span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div id="chart"></div>
			</div>
		</div>
	</div>
</body>
</html>