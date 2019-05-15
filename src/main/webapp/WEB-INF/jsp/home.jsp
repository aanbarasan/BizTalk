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
<link rel="stylesheet"
	href="/resources/plugins/fontawesome_5.8.2/all/all.css">
<link rel="stylesheet" type="text/css"
	href="/resources/stylesheet/home.css" />
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
<script src="/resources/plugins/highcharts/highcharts-3d.js"
	type="text/javascript"></script>
<script src="/resources/plugins/highcharts/exporting.js"
	type="text/javascript"></script>
<script src="/resources/script/home.js" type="text/javascript"></script>
</head>
<body>
	<div class="pageSlider hideSlider">
		<div style="padding: 10px 20px;">
			<div onclick="togglePageSlider('hide')"
				style="font-size: 21px; display: inline-block; padding: 0px 10px; cursor: pointer;">
				<i class="fas fa-bars"></i>
			</div>
		</div>
		<div style="padding: 10px 20px;">
			<div class="sliderMenuList">
				<a href="/">Home</a>
			</div>
			<div class="sliderMenuList">
				<a href="/">Contact US</a>
			</div>
			<div class="sliderMenuList">
				<a href="/">About</a>
			</div>
		</div>
	</div>
	<div class="pageContent">
		<div class="pageHeader">
			<div onclick="togglePageSlider('show')"
				style="font-size: 21px; display: inline-block; padding: 0px 10px; cursor: pointer;">
				<i class="fas fa-bars"></i>
			</div>
			<div
				style="font-size: 21px; display: inline-block; padding: 0px 5px;">BizTalk
				vehicle stats</div>
		</div>
		<div class="pageBody">
			<div style="padding: 30px;">
				<div class="col-md-12 nopadding">
					<h5 style="margin: 0px;">
						Please enter the start date and end date: <input type="text"
							id="dateRange" class="form-control"
							style="display: inline; width: 220px;">
					</h5>
				</div>
				<div class="row col-md-12" id="searchDetailsContainer"
					style="display: none; padding: 0px 15px;">
					<div class="row col-md-6" style="padding: 33px 15px;">
						<div class="col-md-4 nopadding">
							<div class="col-md-12 vehicleTableHeader">No of vehicle
								information passed from BizTalk to Microlise</div>
							<div class="col-md-12 vehicleTableBody">
								<span id="vehicleTotal"></span>
							</div>
						</div>
						<div class="col-md-4 nopadding">
							<div class="col-md-12 vehicleTableHeader">No of unique
								vehicles passed from BizTalk to Microlise</div>
							<div class="col-md-12 vehicleTableBody">
								<span id="vehicleUnique"></span>
							</div>
						</div>
						<div class="col-md-4 nopadding">
							<div class="col-md-12 vehicleTableHeader">No of vehicle
								details suspended at BizTalk</div>
							<div class="col-md-12 vehicleTableBody">
								<span id="vehicleSuspended"></span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<h5 style="text-align: center;">This section will produce
							graph based on the vehicle counts</h5>
						<div id="chart" style="border: 1px solid #ccd6eb;"
							ondblclick="toggleChartGraphics()"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="pageFooter">
			<div>
				Copyright © 2019 Sopra Steria Inc. All rights reserved. <span
					style="text-decoration: underline; cursor: pointer;">Terms
					of use</span>
			</div>
		</div>
	</div>
</body>
</html>