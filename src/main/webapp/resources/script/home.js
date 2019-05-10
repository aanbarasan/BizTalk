$(document).ready(function() {
	loadDatePiker();
});

function loadDatePiker() {
	var options = {};
	$("#dateRange").daterangepicker(
			options,
			function(start, end, label) {
				loadChart();
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
		xAxis: {
			categories: ['No of vehicle information', 'No of unique vehicles', 'No of suspened vehicles'],
            title: {
                text: ''
            },
            tickInterval: 1
        },
		yAxis : {
			allowDecimals : false,
			title : {
				text : 'Units'
			}
		},
		series: [{
            name: 'Biz data stats',
            data: [34, 20, 5]
        }],
		tooltip : {
			formatter : function() {
				return '<b>' + this.series.name + '</b><br/>' + this.point.y
						+ ' ' + this.x;
			}
		}
	});
}