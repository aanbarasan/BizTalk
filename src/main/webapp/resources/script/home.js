$(document).ready(function() {
	loadDatePiker();
});

function loadDatePiker() {
	var options = {};
	$("#dateRange").daterangepicker(options);
	$('#dateRange').on('apply.daterangepicker', function(ev, picker) {
		loadData(picker.startDate.format('YYYY-MM-DD'), picker.endDate.format('YYYY-MM-DD'));
	});
}

function loadData(start, end) {
	var data = {
		"start" : start,
		"end" : end
	};
	$.ajax({
		method : "POST",
		url : "/api/search",
		contentType: "application/json",
		data : {
			"data" : data
		}
	}).done(function(data) {
		loadChart(data);
	});
}

function loadChart() {
	Highcharts.chart('chart', {
		chart : {
			type : 'column'
		},
		title : {
			text : ''
		},
		xAxis : {
			categories: [ "No of vehicle information", "No of unique vehicles", "No of suspened vehicles"],
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
			data : [34, 20, 5]
		} ],
		tooltip : {
			formatter : function() {
				return '<b>' + this.series.name + '</b><br/>' + this.point.y
						+ ' ' + this.x;
			}
		}
	});
}