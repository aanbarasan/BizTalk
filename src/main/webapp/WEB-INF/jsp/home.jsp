<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BizTalk</title>
<link rel="stylesheet" type="text/css" href="plugins/boostrap-4.0.0/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/plugins/bootstrap-table/bootstrap-table.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/plugins/daterangepicker/daterangepicker.css"/>" />
<link rel="stylesheet" href="<c:url value="/plugins/fontawesome_5.8.2/all/all.css"/> ">
<link rel="stylesheet" type="text/css" href="<c:url value="/stylesheet/home.css"/>" />
<script src="<c:url value="/plugins/jquery/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/plugins/boostrap-4.0.0/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/plugins/bootstrap-table/bootstrap-table.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/plugins/moment/moment.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/plugins/daterangepicker/daterangepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/plugins/highcharts/highcharts.js"/>" type="text/javascript"></script>
<script src="<c:url value="/plugins/highcharts/highcharts-3d.js"/>" type="text/javascript"></script>
<script src="<c:url value="/plugins/highcharts/exporting.js"/>" type="text/javascript"></script>
</head>
<body>
	<div class="pageSlider hideSlider">
		<div style="padding: 10px 20px; border-bottom: 5px solid #dbe9fd;">
			<div onclick="togglePageSlider('hide', event)"
				style="font-size: 21px; display: inline-block; padding: 0px 10px; cursor: pointer;">
				<i class="fas fa-bars"></i>
			</div>
		</div>
		<div style="padding: 10px 20px;">
			<div class="sliderMenuList">
				<a href="<c:url value="/"/>">Home</a>
			</div>
			<div class="sliderMenuList">
				<a href="<c:url value="/contact"/>">Contact US</a>
			</div>
			<div class="sliderMenuList">
				<a href="<c:url value="/about"/>">About</a>
			</div>
		</div>
	</div>
	<div class="pageContent" onclick="togglePageSlider('hide', event)">
		<div class="pageHeader">
			<div onclick="togglePageSlider('show', event)"
				style="font-size: 21px; display: inline-block; padding: 0px 10px; cursor: pointer;">
				<i class="fas fa-bars"></i>
			</div>
			<div style="font-size: 21px; display: inline-block; padding: 0px 5px;">BizTalk vehicle
				stats</div>
		</div>
		<div class="pageBody">
			<div class="col-md-12">
				<h5 style="margin: 0px;">
					Please enter the start date and end date: <input type="text" id="dateRange"
						class="form-control" style="display: inline; width: 220px;">
				</h5>
			</div>
			<div class="row col-md-12 nopadding" id="searchDetailsContainer" style="display: none;">
				<div class="row col-md-6" style="padding: 33px 15px;">
					<div class="row col-md-12 nopadding">
						<div class="col-md-4 vehicleDetailsContainer">
							<div>
								<div class="col-md-12 vehicleDetailsHead">No of vehicle passed from BizTalk to
									Microlise</div>
								<div class="col-md-12 vehicleDetailsBody">
									<span id="vehicleTotal"></span>
								</div>
							</div>
						</div>
						<div class="col-md-4 vehicleDetailsContainer">
							<div>
								<div class="col-md-12 vehicleDetailsHead">No of unique vehicles passed from BizTalk to
									Microlise</div>
								<div class="col-md-12 vehicleDetailsBody">
									<span id="vehicleUnique"></span>
								</div>
							</div>
						</div>
						<div class="col-md-4 vehicleDetailsContainer">
							<div>
								<div class="col-md-12 vehicleDetailsHead">No of vehicle details suspended at BizTalk</div>
								<div class="col-md-12 vehicleDetailsBody">
									<span id="vehicleSuspended"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div style="width: 100%; padding: 10px; margin-top: 40px;">
							<h5>Driver trip details:</h5>
						</div>
						<div style="width: 100%; background-color: white;">
							<table id="driverDetails"></table>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<h5 style="text-align: center;">This section will produce graph based on the vehicle
						counts</h5>
					<div id="chart" style="border: 1px solid #ccd6eb;" ondblclick="toggleChartGraphics()"></div>
				</div>
			</div>
			<div id="searchDetailsContainerLoader" style="text-align: center;">
				<div style="width: 250px; display: inline-block;">
					<img src="<c:url value="/images/loader.svg"/>" />
				</div>
			</div>
		</div>
		<div class="pageFooter">
			<div style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden;">
				Copyright © 2019 Sopra Steria Inc. All rights reserved. <span
					style="text-decoration: underline; cursor: pointer;">Terms of use</span>
			</div>
		</div>
	</div>
	<script>
		window.biztalkBaseURL = "<c:url value="/"/>";
	</script>
	<script src="<c:url value="/script/home.js"/>" type="text/javascript"></script>
</body>
</html>