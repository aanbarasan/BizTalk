$(document).ready(function() {
	loadDatePiker();
});

function loadDatePiker() {
	var options = {};
	var dt = new Date();
	options.startDate = new Date(dt.getTime() - (86400000 * 7));
	options.endDate = new Date(dt.getTime() + (86400000 * 7));
//	options.timePicker = true;
//	options.timePicker24Hour = true;
	$("#dateRange").daterangepicker(options);
	$('#dateRange').on('apply.daterangepicker', function(ev, picker) {
		loadData(picker.startDate.format('YYYY-MM-DD'), picker.endDate.format('YYYY-MM-DD')); // Thh:mm:ss
	}).click();
}

function loadData(start, end) {
	var postData = {
		"start" : start + "T00:00:000",
		"end" : end + "T23:59:999"
	};
	$.ajax({
		method : "POST",
		url : "/api/processview",
		dataType: "json",
		contentType: "application/json",
		data: JSON.stringify(postData),
	}).done(function(data) {
		var chartData = {"x":[ "No of vehicle information", "No of unique vehicles", "No of suspended vehicles"], y:[data.total, data.unique, data.suspended]};
		$("#searchDetailsContainer").show();
		loadChart(chartData);
		loadTable(data);
	});
}

function loadTable(data){
	$("#vehicleTotal").html(data.total);
	$("#vehicleUnique").html(data.unique);
	$("#vehicleSuspended").html(data.suspended);
}

function loadChart(chartData) {
	Highcharts.chart('chart', {
		chart : {
			type : 'column'
		},
		title : {
			text : ''
		},
		xAxis : {
			categories: chartData.x,
			tickInterval : 1
		},
		yAxis : {
			allowDecimals : false,
			title : {
				text : 'Units'
			}
		},
		series : [ {
			name : 'Biz data stats',
			data : chartData.y
		} ],
		tooltip : {
			formatter : function() {
				return '<b>' + this.series.name + '</b><br/>' + this.point.y
						+ ' ' + this.x;
			}
		}
	});
}