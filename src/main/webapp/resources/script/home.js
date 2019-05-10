$(document).ready(function() {
	loadDatePiker();
});

function loadDatePiker() {
	var options = {};
	$("#dateRange").daterangepicker(options, function(start, end, label) {
		loadData(start, end);
	});
}

function loadData(start, end) {
	var data = {};
	$.ajax({
		method : "POST",
		url : "/api/search",
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
			text : 'Biz data stats'
		},
		xAxis : {
			title : {
				text : ''
			},
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
			data : [ {
				"name" : "No of vehicle information",
				"y" : 34
			}, {
				"name" : "No of unique vehicles",
				"y" : 20
			}, {
				"name" : "No of suspened vehicles",
				"y" : 5
			} ]
		} ],
		tooltip : {
			formatter : function() {
				return '<b>' + this.series.name + '</b><br/>' + this.point.y
						+ ' ' + this.point.name;
			}
		}
	});
}